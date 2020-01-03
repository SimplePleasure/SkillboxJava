
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.stream.Stream;
import java.util.List;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;


public class PathsTest {

    public static void main(String[] args) throws IOException {
////======================== File ================================================
//        File file = new File ("./res/1/2");
//        System.out.println(file.mkdirs());
//        File filePath = new File(file.getAbsolutePath() + "/1.txt");
//        System.out.println(filePath.createNewFile());


//======================== Folder creating =======================================
        Path pp = Paths.get("./res/1");
        if (!Files.exists(pp)) {
            Files.createDirectories(pp);
        }

//======================== File creating =========================================
        Path p = Paths.get(pp.toAbsolutePath().normalize() + "/text.txt");
        if (!Files.exists(p)) {
            Files.createFile(p);
            System.out.println(p);
        }


//======================== Checking ==============================================
        System.out.println("Is exists " + Files.exists(p));
        if (Files.isRegularFile(p)) {
            System.out.println("It's a file: " + Files.isRegularFile(p));
        } else if (Files.isDirectory(p)) {
            System.out.println("It's a directory: " + Files.isDirectory(p));
        }

        Path np = Files.copy(p, Paths.get("./res/" + p.getFileName()), REPLACE_EXISTING);
        System.out.println(Files.exists(np));



//======================== Read / Write ==========================================
        String sss = "bgdabadfbasdbndznD";
        byte[] b = sss.getBytes();
        Files.write(p, b);

        Stream<String> str = Files.lines(np, Charset.defaultCharset());
        str.forEach(System.out::print);





//        Path path = Paths.get("./res/111/" + UUID.randomUUID().toString());
//        Files.createDirectories(path);
    }
}
