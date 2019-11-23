import java.io.*;

import java.nio.file.*;

public class Main {
    public static void main (String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter source path:");
        String src = reader.readLine();
        System.out.println("Enter destination path:");
        String destination = reader.readLine();
        copy(src, destination);
    }

    public static void copy ( String source, String dest) throws IOException {
        File[] listFiles = new File(source).listFiles();
        Path path = Paths.get(dest);
        for (File f: listFiles) {
            if (f.isDirectory()) {
                Files.copy(f.toPath(), path.resolve(f.getName()), StandardCopyOption.COPY_ATTRIBUTES);
                copy(f.toString(), dest+"\\"+f.getName());
            } else {
                Files.copy(f.toPath(), path.resolve(f.getName()), StandardCopyOption.COPY_ATTRIBUTES);
            }
        }
    }
}


