import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) {
        System.out.println("----Hangman----");
        System.out.println("-- Start = 1, Exit = 0 -- ");
        lineCounter("russian-nouns.txt");
        getWord(lineCounter("russian-nouns.txt"));
    }

    public static int lineCounter(String path) {
        long lineCount = 0;
        try {
            lineCount = Files.lines(Path.of(path)).parallel().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int) lineCount;
    }

    public static char[] getWord(int size) {
        char[] charArray = new char[10];
        int randStr = (int) (1 + Math.random()*size);

        return charArray;
    }

    public static String readLine(){
        return "";
    }

}