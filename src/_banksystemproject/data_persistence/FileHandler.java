package _banksystemproject.data_persistence;

import java.io.*;
import java.util.Scanner;

public class FileHandler {

    private FileHandler(){}

    public static void writeOver(String path, String information) throws IllegalArgumentException, FileNotFoundException, IOException {
        if (path == null || information == null) throw new IllegalArgumentException("Invalid Arguments!");
        FileOutputStream output = new FileOutputStream(path); //Can cause a FileNotFoundException to be thrown!
        OutputStreamWriter writer = new OutputStreamWriter(output);
        BufferedWriter buffer = new BufferedWriter(writer);
        buffer.write(information); //Can cause an IOException to be thrown!
        buffer.close(); //Can cause an IOException to be thrown!
    }

    public static boolean appendTo(String path, String information) {
        if (path == null || information == null) return false;
        try (FileOutputStream output = new FileOutputStream(path,true)) {
            OutputStreamWriter writer = new OutputStreamWriter(output);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.newLine();
            buffer.write(information);
            buffer.close();
        }catch(IOException e) {
            return false;
        }
        return false;
    }

    public static String readFile(String path){
        try (Scanner fileScan = new Scanner(new File(path))){
            StringBuilder builder = new StringBuilder();
            while (fileScan.hasNextLine()) {
                builder.append(fileScan.nextLine()).append("\n");
            }
            return builder.toString();
        }catch(FileNotFoundException e){
            return null;
        }
    }

    public String getAllContent(String path) throws IOException {
        //Another way to read a file completely.
        if (path == null || path.length() == 0 || !FileHandler.fileExists(path)) return null;
        InputStream fileInput = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(fileInput);
        BufferedReader buffer = new BufferedReader(reader);
        String temp = buffer.readLine();
        StringBuilder result = new StringBuilder();
        while (temp != null) {
            result.append(temp).append("\n");
            temp = buffer.readLine();
        }
        buffer.close();
        return result.toString();
    }

    public static boolean fileExists(String path) {
        try(Scanner fileScan = new Scanner(new File(path))) {
            return true;
        }catch(FileNotFoundException e) {
            return false;
        }
    }

    public static String getLine(String path, int number) throws IllegalArgumentException, FileNotFoundException {
        if (path == null || number < 0 || number >= FileHandler.fileSize(path)) throw new IllegalArgumentException("Invalid Argument!");
        Scanner fileScan = new Scanner(new File(path));
        while (number > 0) {
            fileScan.nextLine();
            number --;
        }
        return fileScan.nextLine();
    }

    public static int fileSize(String path) throws IllegalArgumentException, FileNotFoundException {
        if (path == null) throw new IllegalArgumentException("Invalid Argument!");
        Scanner fileScan = new Scanner(new File(path));
        int counter = 0;
        while (fileScan.hasNextLine()) {
            counter++;
            fileScan.nextLine();
        }
        fileScan.close();
        return counter;
    }

    public static boolean fileIsEmpty(String path) throws IllegalArgumentException, FileNotFoundException {
        return FileHandler.fileSize(path) == 0;
    }
}
