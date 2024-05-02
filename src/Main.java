import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import Utils.*;
import UCS.*;


public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        // Get input
        DictionaryMaker.makeDictionary("../data");
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan kata pertama: ");
        String first = input.nextLine().toLowerCase();
        System.out.print("Masukkan kata kedua: ");
        String second = input.nextLine().toLowerCase();
        System.out.println(Word.dictionary.size());

        // Check if the word exist
        boolean firstValid = true, secondValid = true;
        if (!Word.isWordExist(first)) {
            firstValid = false;
        }
        if (!Word.isWordExist(second)) {
            secondValid = false;
        }

        if (!firstValid && !secondValid) {
            System.out.println("Kedua kata tidak valid.");
            return;
        } else if (!firstValid) {
            System.out.println("Kata pertama tidak valid.");
            return;
        } else if (!secondValid){
            System.out.println("Kata kedua tidak valid.");
            return;
        }

        // Check if the length is the same
        if (!Word.isLengthSame(first, second)) {
            System.out.println("Kedua kata tidak memiliki panjang yang sama.");
            Main.main(args);
        }

        // Solve UCS
        List<String> path = UCS.findLadder(first, second);
        if (path == null) {
            System.out.println("Tidak ada jalur yang ditemukan.");
            return;
        } else {
            System.out.println("Jalur yang ditemukan: ");
            for (String word : path) {
                System.out.println(word);
            }
        }
    }
}
