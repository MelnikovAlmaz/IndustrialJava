import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;

public class UniqueWordParser implements Parser<File>, Runnable {
    // Exception flags
    static volatile boolean isDuplicateFound = false;
    static volatile boolean isIncorrectSymbolFound = false;
    // Unique word set, it is common for all parsers
    static HashSet<String> uniqueWordSet = new HashSet<>();
    static HashSet<Character> punctuation = new HashSet<>(Arrays.asList(',', '.', ';', ':', '!', '?', '«', '»', ' ', '-', '"', '(', ')'));
    static final int BUFFER_SIZE = 100;

    private File fileToParse;

    UniqueWordParser(File file){
        fileToParse = file;
    }

    private boolean pushWords(LinkedList<String> words){
        // Synchronize uniqueWordSet to prevent simultaneous edition
        synchronized (uniqueWordSet){
            // Check exceptions
            if(isIncorrectSymbolFound || isDuplicateFound){
                return false;
            }
            else {
                for (String word : words) {
                    if(uniqueWordSet.contains(word)){
                        isDuplicateFound = true;
                        System.out.println("Найден дубликат слова: " + word);
                        return false;
                    }
                    else {
                        uniqueWordSet.add(word);
                        System.out.println("Уникальных слов: " + uniqueWordSet.size() + " - " + word);
                    }
                }
            }
            return true;
        }
    }

    @Override
    public void parse(File file) {
        try {
            LinkedList<String> words = new LinkedList<>(); // Word buffer
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = reader.readLine();
            while (line != null) {
                char currentChar;
                StringBuilder currentWord = new StringBuilder();

                for (int i = 0; i < line.length(); i++) {
                    currentChar = line.charAt(i);

                    // Add char if it is correct
                    if (currentChar >= 'А' && currentChar <= 'Я' || currentChar >= 'а' && currentChar <= 'я' || currentChar >= '0' && currentChar <= '9') {
                        currentWord.append(currentChar);
                    } else if (punctuation.contains(currentChar)) {
                        if (currentWord.length() > 0) {
                            words.add(currentWord.toString());
                            currentWord = new StringBuilder();

                            // Push words if word buffer has critical size
                            if (words.size() == BUFFER_SIZE) {
                                boolean isPushCorrect = pushWords(words);
                                if (!isPushCorrect) {
                                    break;
                                }
                                words = new LinkedList<>();
                            }
                        }
                    } else {
                        // Incorrect character is detected - exception
                        // Synchronize uniqueWordSet to prevent adding new words and stop other workers
                        synchronized (uniqueWordSet) {
                            // Check that another exception has not happen before
                            if (!isDuplicateFound && !isIncorrectSymbolFound) {
                                isIncorrectSymbolFound = true;
                                System.out.println("Найден инородный символ - " + currentChar);
                            }
                            break;
                        }
                    }
                }
                line = reader.readLine();
            }
            pushWords(words);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        parse(fileToParse);
    }
}
