import java.io.IOException;
import java.net.URISyntaxException;

import Dictionary.DictionaryMaker;
import GUI.*;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException {
        DictionaryMaker.makeDictionary("../data");
        MainGUI gui = new MainGUI("Word Ladder Solver");
        gui.setVisible(true);
    }
}
