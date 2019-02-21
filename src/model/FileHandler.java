package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Get available files in directory and a files content as a list of strings
 */
public class FileHandler {

    public File[] getFiles() {
        File folder = new File("src/files");
        return folder.listFiles();
    }

    public List<String> readFile(String filename) {
        try {
            Stream<String> stream = Files.lines(Paths.get("src/files", filename));
            return stream.collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Could not find a file with that name" +
                    "\nPlease try again");
            return null;
        }
    }
}
