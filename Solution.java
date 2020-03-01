import java.io.FileNotFoundException;
import java.io.FileWriter;   // Import the FileWriter class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import java.io.*;

public class Solution {

    public Solution() {
    }

    public void writeSolutionToFile(List<Vertex> route, int puzzleNumber) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(puzzleNumber + ".txt", "UTF-8");
            for (Vertex r : route) {
                writer.print("(" + r.getX() + ", " + r.getY() + ") ");
            }
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Solution.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
             writer.close();
        }
       
    }

}
