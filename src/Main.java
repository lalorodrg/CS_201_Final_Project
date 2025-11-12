import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {

    String filePath = "C:\\Users\\lalor\\Documents\\OneDrive\\Documents\\" +
                      "School Documents\\Fall 2025 Courses\\Accelerated CS " +
                      "FA25\\sample TXT Project file.txt";



    try( BufferedReader reader = new BufferedReader(new FileReader(filePath))){
        String line;
        line = reader.readLine();
        while((line = reader.readLine()) !=  null){
            System.out.println(line);
        }
    }
    catch(FileNotFoundException e){
        System.out.println("File was not located");
    }
    catch(IOException e){
        System.out.println("Something went wrong");
    }



    }
}