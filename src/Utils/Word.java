package Utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.*;
import java.util.HashSet;
import java.util.Set;

public class Word {
    public static Set<String> dictionary = new HashSet<>();

    public static void getDictionary(String path) {
        Set <String> temp = new HashSet<>();
        try (FileReader file = new FileReader(path)){
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                temp.add(line);
            }
            dictionary = temp;
        } catch (IOException e) {
            throw new RuntimeException("File tidak valid!");
        }
    }

    public static boolean isWordExist(String word) {
        return dictionary.contains(word);
    }

    public static boolean isWordExistAPI(String word) {
        // Get data from API
        try {
            URL start = new URI("https://api.dictionaryapi.dev/api/v2/entries/en/" + word).toURL();
            HttpURLConnection conn = (HttpURLConnection) start.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            int responseCode = conn.getResponseCode();
            return responseCode == 200;
        } catch (URISyntaxException | IOException e) {
            return false;
        }
    }

    public static boolean isLengthSame(String first, String second) {
        return first.length() == second.length();
    }
}
