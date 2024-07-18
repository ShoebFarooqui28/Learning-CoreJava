package CoreJava.FileClass;
import java.io.FileWriter;
import java.io.IOException;

public class writerFile {
    public static void main(String[] args) {
        try{
            FileWriter writer = new FileWriter("D:\\Learning CoreJava\\CoreJava\\FileClass\\testFile.txt");
            writer.write("Hello World!");
            writer.write("Hello Earth!");
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }   
}