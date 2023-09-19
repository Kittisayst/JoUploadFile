package Main;

import java.io.File;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class JoUploadFile {

    private String url = "http://sayfoneapi/upload.php";
    private String postKey = "fileToUpload";
    private File file;
    private String FileName = "jo";
    private ConvertFileUpload convert;

    public JoUploadFile(String url, String postKey, File file, String FileName) {
        this.url = url;
        this.postKey = postKey;
        this.file = file;
        this.FileName = FileName;
        convert = new ConvertFileUpload(file);
    }

    public boolean upload() {
        //Convert File

        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            // Create the multipart entity builder
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.addBinaryBody(postKey, convert.getFile(), ContentType.APPLICATION_OCTET_STREAM, FileName + "." + convert.getExtension());
            System.out.println(FileName + "." + convert.getExtension());
            // Set the entity to the request
            HttpEntity multipart = builder.build();
            httpPost.setEntity(multipart);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                String responseString = EntityUtils.toString(responseEntity);
                System.out.println("Response: " + responseString);
            }
            EntityUtils.consume(responseEntity);
            return responseEntity != null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    

    public String getExtension() {
        return convert.getExtension();
    }

    public void close() {
        convert.convertFile().delete();
    }

}
