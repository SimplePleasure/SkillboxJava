import java.io.*;
import java.util.Properties;

public class TestForm {

    static String test;
    static Properties props = new Properties();


    public static void getProp() {

        try (FileInputStream in = new FileInputStream(new File("./res/conf.ini"))) {
            props.load(in);
            test = props.getProperty("REQUIRED_PHONE_LENGTH");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        getProp();
        System.out.println(test);
    }

}
