/**
 * FileOperations.java
 * 
 * This program demonstrates file operations in Java including:
 * 1. Writing content to a file
 * 2. Reading content from a file
 * 3. Modifying content within a file
 * 
 * The program creates a text file, writes data, reads it, and replaces
 * specific words within the file.
 */

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileOperations {
    private static final String FILE_PATH = "sample.txt";

    public static void main(String[] args) {
        try {
            // Writing to the file
            writeFile("Hello, this is a sample text file.\nWelcome to Java file handling!");
            
            // Reading the file
            System.out.println("File Contents:");
            readFile();
            
            // Modifying the file
            modifyFile("sample", "example");
            
            // Reading the file after modification
            System.out.println("\nFile Contents After Modification:");
            readFile();
            
        } catch (IOException e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }

    /**
     * Writes the specified content to the file.
     * 
     * @param content The text content to be written to the file.
     * @throws IOException If an I/O error occurs.
     */
    public static void writeFile(String content) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write(content);
        }
    }

    /**
     * Reads and prints the content of the file.
     * 
     * @throws IOException If an I/O error occurs.
     */
    public static void readFile() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    /**
     * Modifies the file by replacing all occurrences of a specified word.
     * 
     * @param oldWord The word to be replaced.
     * @param newWord The replacement word.
     * @throws IOException If an I/O error occurs.
     */
    public static void modifyFile(String oldWord, String newWord) throws IOException {
        Path path = Paths.get(FILE_PATH);
        List<String> lines = Files.readAllLines(path);
        List<String> modifiedLines = new ArrayList<>();
        
        for (String line : lines) {
            modifiedLines.add(line.replaceAll(oldWord, newWord));
        }
        
        Files.write(path, modifiedLines);
    }
}
