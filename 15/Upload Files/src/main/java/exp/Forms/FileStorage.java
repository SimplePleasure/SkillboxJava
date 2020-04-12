package exp.Forms;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

@Component
public class FileStorage {

    @Value("${fileSave.path}")
    private String path;

    public boolean saveFile(MultipartFile file) {
        try (FileOutputStream fos = new FileOutputStream(path+file.getOriginalFilename())){
            fos.write(file.getBytes());
            fos.flush();
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public Set<File> getFiles() {
        HashSet<File> files = new HashSet<>();
        try {
            Files.walk(new File(path).toPath()).map(Path::toFile).filter(File::isFile).
                    filter(e -> !e.getName().equals(".DS_Store")).forEach(files::add);
            return files;
        } catch (IOException e) {
            e.printStackTrace();
            return files;
        }
    }

    public boolean delFile(String name) {
        try {
            Files.walk(new File(path).toPath()).map(Path::toFile).filter(File::isFile).
                    filter(x -> x.getName().equals(name)).forEach(File::delete);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
