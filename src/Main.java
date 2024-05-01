import java.io.IOException;
import java.net.*;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        Scanner input = new Scanner(System.in);
        String first = input.nextLine();
        String second = input.nextLine();

        URL start = new URI("https://api.dictionaryapi.dev/api/v2/entries/en/" + first).toURL();
        HttpURLConnection conn = (HttpURLConnection) start.openConnection();
        conn.setRequestMethod("GET");
        conn.connect();
        int responseCode = conn.getResponseCode();

        if (responseCode != 200) {
            System.out.println("Error: " + responseCode);
            return;
        } else {
            Scanner sc = new Scanner(start.openStream());
            StringBuilder inline = new StringBuilder();
            while (sc.hasNext()) {
                inline.append(sc.nextLine());
            }
            sc.close();
            System.out.println(inline);
        }
    }
}
