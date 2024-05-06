package Dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Word {
    public static Set<String> dictionary = new HashSet<>();

    public static void getDictionary(String path) {
        try (FileReader file = new FileReader(path)){
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line.toLowerCase());
            }
        } catch (IOException e) {
            throw new RuntimeException("File tidak valid!");
        }
    }

    public static boolean isWordExist(String word) {
        return dictionary.contains(word);
    }

    public static boolean isLengthSame(String first, String second) {
        return first.length() == second.length();
    }

    public static List<String> getNeighbors(String word) {
        List<String> neighbors = new ArrayList<>();
        char[] wordChars = word.toCharArray();

        for (int i = 0; i < wordChars.length; i++) {
            char original = wordChars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) {
                    continue;
                }
                wordChars[i] = c;
                if (Word.isWordExist(new String(wordChars))) {
//                    System.out.println("Checking: " + new String(wordChars));
                    neighbors.add(new String(wordChars));
                }
            }
            wordChars[i] = original;
        }
        return neighbors;
    }
}
