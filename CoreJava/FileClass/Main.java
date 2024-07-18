package CoreJava.FileClass;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        File file = new File("D:/Learning CoreJava/CoreJava/FileClass/hey.txt");

        if(file.exists()){
            System.out.println("The file exists!");
            System.out.println(file.getPath());
            System.out.println(file.getAbsolutePath());
            System.out.println(file.isFile());
            file.delete();
        }

        else{
            System.out.println("The file doesn't exist!");
        }
    }
    
}