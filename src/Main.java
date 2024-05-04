import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Dictionary.*;
import UCS.*;
import GBFS.*;
import A_Star.*;
import GUI.*;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        GUI gui = new GUI("Word Ladder Solver");
//        gui.fillMainGUI();

        // Get input
        DictionaryMaker.makeDictionary("../data");
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan kata pertama: ");
        String first = input.nextLine().toLowerCase();
        System.out.print("Masukkan kata kedua: ");
        String second = input.nextLine().toLowerCase();
//        System.out.println(Word.dictionary.size());

        // Check if the length is the same
        if (!Word.isLengthSame(first, second)) {
            System.out.println("Kedua kata tidak memiliki panjang yang sama.");
            Main.main(args);
        }

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


        // Give choice: 1. UCS, 2. GBFS, 3. A*
        System.out.print("Masukkan algoritma yang akan digunakan: ");
        int choice = input.nextInt();
        List<String> path = new ArrayList<>();
        if (choice == 1) {
            path = UCS.findLadder(first, second);
            System.out.println("Jumlah node yang dikunjungi: " + UCS.checkedNode);
        } else if (choice == 2) {
            path = GBFS.findLadder(first, second);
        } else if (choice == 3) {
            path = A_Star.findLadder(first, second);
        } else {
            System.out.println("Pilihan tidak valid!");
        }

        // Solve UCS
        if (path == null) {
            System.out.println("Tidak ada jalur yang ditemukan.");
        } else {
            System.out.println("Jalur yang ditemukan: ");
            for (String word : path) {
                System.out.println(word);
            }
        }
    }
}
