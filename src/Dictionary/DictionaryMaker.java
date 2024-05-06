package Dictionary;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

    public class DictionaryMaker {
        public static List<String> getFilesFolder(String path) {
            File folder = new File(path);
            File[] listOfFiles = folder.listFiles();
            List<String> files = new ArrayList<>();
            if (listOfFiles == null) {
                return null;
            }
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    files.add(file.getName());
                }
            }
            return files;
        }

        public static void makeDictionary(String path) {
            List<String> files = getFilesFolder(path);
            if (files == null) {
                throw new RuntimeException("Folder tidak valid!");
            }
            for (String file : files) {
                Word.getDictionary(path + "/" + file);
            }
        }
    }
