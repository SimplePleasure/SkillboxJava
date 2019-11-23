import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader
{
    public static void main(String[] args) throws Exception
    {

        //Reading file to the String
        String text = new String(Files.readAllBytes(Paths.get("res/text_01.txt")));

        TextAnalyzer analyzer = new TextAnalyzer(text);
        System.out.println("Most frequent word: " + analyzer.getMostFrequentWord());
    }


    @Test
    public void Test() throws IOException { //(String expected, String actual){
        String text = new String(Files.readAllBytes(Paths.get("res/text_01.txt")));
        TextAnalyzer test = new TextAnalyzer(text);
        String expectedWord = "the";
        Assert.assertEquals(expectedWord ,test.getMostFrequentWord());
    }

}