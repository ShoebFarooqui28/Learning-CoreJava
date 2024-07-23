package BasicJava.FileClass;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class readerFile {
    public static void main(String[] args) {
        try{
            FileWriter writer = new FileWriter("D:\\Learning CoreJava\\CoreJava\\FileClass\\testFile.txt");
            writer.write("                                           /\r\n" + //
                                "                        _,.------....___,.' ',.-.\r\n" + //
                                "                     ,-'          _,.--\"        |\r\n" + //
                                "                   ,'         _.-'              .\r\n" + //
                                "                  /   ,     ,'                   `\r\n" + //
                                "                 .   /     /                     ``.\r\n" + //
                                "                 |  |     .                       \\.\\\r\n" + //
                                "       ____      |___._.  |       __               \\ `.\r\n" + //
                                "     .'    `---\"\"       ``\"-.--\"'`  \\               .  \\\r\n" + //
                                "    .  ,            __               `              |   .\r\n" + //
                                "    `,'         ,-\"'  .               \\             |    L\r\n" + //
                                "   ,'          '    _.'                -._          /    |\r\n" + //
                                "  ,`-.    ,\".   `--'                      >.      ,'     |\r\n" + //
                                " . .'\\'   `-'       __    ,  ,-.         /  `.__.-      ,'\r\n" + //
                                " ||:, .           ,'  ;  /  / \\ `        `.    .      .'/\r\n" + //
                                " j|:D  \\          `--'  ' ,'_  . .         `.__, \\   , /\r\n" + //
                                "/ L:_  |                 .  \"' :_;                `.'.'\r\n" + //
                                ".    \"\"'                  \"\"\"\"\"'                    V\r\n" + //
                                " `.                                 .    `.   _,..  `\r\n" + //
                                "   `,_   .    .                _,-'/    .. `,'   __  `\r\n" + //
                                "    ) \\`._        ___....----\"'  ,'   .'  \\ |   '  \\  .\r\n" + //
                                "   /   `. \"`-.--\"'         _,' ,'     `---' |    `./  |\r\n" + //
                                "  .   _  `\"\"'--.._____..--\"   ,             '         |\r\n" + //
                                "  | .\" `. `-.                /-.           /          ,\r\n" + //
                                "  | `._.'    `,_            ;  /         ,'          .\r\n" + //
                                " .'          /| `-.        . ,'         ,           ,\r\n" + //
                                " '-.__ __ _,','    '`-..___;-...__   ,.'\\ ____.___.'\r\n" + //
                                " `\"^--'..'   '-`-^-'\"--    `-^-'`.''\"\"\"\"\"`.,^.`.--' ");
            writer.close();

            FileReader reader = new FileReader("D:\\Learning CoreJava\\CoreJava\\FileClass\\testFile.txt");
            int data = reader.read();

            while(data != -1){
                System.out.print((char)data);
                data = reader.read();
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
    }   
}