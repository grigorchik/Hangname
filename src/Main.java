import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int userInput = -1;
        int numberOfAttempts = 6;
        Scanner scanner = new Scanner(System.in);
        System.out.println("----Hangman----");
        System.out.println("-- Start = 1, Exit = 0 -- ");

        do {
            while (true) {
                if(scanner.hasNextInt()) {
                    userInput = scanner.nextInt();
                    break;
                } else {
                    System.out.print("Это не целое число. Пожалуйста, введите целое число: ");
                    scanner.next();
                }
            }

            if (userInput == 1) {
                numberOfAttempts = 6;
                System.out.println("Игра начинается...");
                String word = getRandomWord();
                char[] hiddenWord = getHiddenWord(word);
                char[] checkWord = new char[hiddenWord.length];

                System.out.println(hiddenWord);

                do {
                    System.out.println(Hangman_stages(numberOfAttempts));
                    System.out.println("Количество попыток: "+numberOfAttempts+" Введите букву:");
                    char letter = scanner.next().charAt(0);
                    char[] updatedHiddenWord = openWord(word.toCharArray(), hiddenWord, letter);

                    if (String.valueOf(updatedHiddenWord).equals(String.valueOf(checkWord))) {
                        numberOfAttempts--;
                    } else {
                        checkWord = updatedHiddenWord.clone();
                    }

                    hiddenWord = updatedHiddenWord;
                    System.out.println(hiddenWord);

                    if (String.valueOf(hiddenWord).equals(word)) {
                        System.out.println("Поздравляем! Вы угадали слово.");
                        break;
                    }

                } while (numberOfAttempts > 0);

                if (numberOfAttempts == 0) {
                    System.out.println(Hangman_stages(0));
                    System.out.println("Вы исчерпали все попытки. Загаданное слово было: " + word);
                    userInput = 0;
                }

            } else if (userInput == 0) {
                System.out.println("Выход из игры.");
            } else {
                System.out.println("Неверный ввод. Пожалуйста, введите 1 или 0.");
            }
        } while (userInput != 0);

        scanner.close();
    }

    public static char[] openWord(char[] word, char[] hiddenWord, char letter) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] == letter) {
                hiddenWord[i] = letter;
            }
        }
        return hiddenWord;
    }

    public static String getRandomWord() {
        int randStr = (int) (1 + Math.random() * lineCounter("russian-nouns.txt"));
        String word = readLine("russian-nouns.txt", randStr);
        return word;
    }

    public static char[] getHiddenWord(String word) {
        char[] hiddenWord = new char[word.length()];
        for (int i = 0; i < word.length(); i++) {
            hiddenWord[i] = '*';
        }
        return hiddenWord;
    }

    public static int lineCounter(String filePath) {
        long lineCount = 0;
        try {
            lineCount = Files.lines(Path.of(filePath)).parallel().count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return (int) lineCount;
    }

    public static String readLine(String filePath, int lineNumber) {
        String line = "";
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            line = allLines.get(lineNumber - 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    public static String Hangman_stages(int numb){
        String[] HANGMAN_STAGES = {
                """
           +---+
           |   |
           O   |
          /|\\  |
          / \\  |
               |
        =========
        """,
                """
           +---+
           |   |
           O   |
          /|\\  |
          /    |
               |
        =========
        """,
                """
           +---+
           |   |
           O   |
          /|\\  |
               |
               |
        =========
        """,
                """
           +---+
           |   |
           O   |
          /|   |
               |
               |
        =========
        """,
                """
           +---+
           |   |
           O   |
           |   |
               |
               |
        =========
        """,
                """
           +---+
           |   |
           O   |
               |
               |
               |
        =========
        """,
                """
           +---+
           |   |
               |
               |
               |
               |
        =========
        """};
        return HANGMAN_STAGES[numb];
    }
}
