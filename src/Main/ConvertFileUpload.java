package Main;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConvertFileUpload {

    private File file;
    private String extension;

    public ConvertFileUpload(File file) {
        this.file = file;
        String filename = file.getName();
        int dotIndex = filename.lastIndexOf(".");
        extension = filename.substring(dotIndex + 1);
    }

    public File convertFile() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss");
        String fileName = dateFormat.format(new Date());
        String createName = file.getParent() + "\\" + "jofile" + fileName + "." + extension;
        Path sourceFile = Path.of(file.getAbsolutePath());
        Path targetFile = Path.of(createName);
        try {
            Files.copy(sourceFile, targetFile);
        } catch (IOException ex) {
            Logger.getLogger(ConvertFileUpload.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new File(createName);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

}
