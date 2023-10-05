package Main;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class JoHttp {

    private String url = "localhost";
    private int responseCode = 0;
    private String responseContent = "";

    public JoHttp(String url) {
        this.url = url;
    }

    public boolean Open() {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);

            // Get the response code
            int statusCode = response.getStatusLine().getStatusCode();
            responseCode = statusCode;
            // Read the response content
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            responseContent = content;
            // Print the content
            return entity != null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isCheck() {
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);

            // Get the response code
            int statusCode = response.getStatusLine().getStatusCode();
            responseCode = statusCode;
            // Read the response content
            HttpEntity entity = response.getEntity();
            String content = EntityUtils.toString(entity);
            responseContent = content;
            // Print the content
            return entity != null;
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean DownloadFile(String fileUrl, String FileName, String savePath) {
        try {
            URL url = new URL(fileUrl);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            try (BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream); FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = bufferedInputStream.read(buffer, 0, 1024)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
            File downloadedFile = new File(savePath);
            return downloadedFile.exists();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseContent() {
        return responseContent;
    }

    public void setResponseContent(String responseContent) {
        this.responseContent = responseContent;
    }

}
