package exp.Forms;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

@Component
public class FileStorage {

    public boolean saveFile(MultipartFile file) {
        try {
            String path = DefaultController.path+file.getOriginalFilename();
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(file.getBytes());
            fos.flush();
            fos.close();
            File f = new File(path);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Set<File> getFiles() {


        HashSet<File> files = new HashSet<>();
        File[] pics = new File(DefaultController.path).listFiles();
        if (pics.length > 0) {
           Arrays.stream(pics).filter(e -> !e.getName().equals(".DS_Store")).forEach(files::add);
        }
        return files;
    }




}
