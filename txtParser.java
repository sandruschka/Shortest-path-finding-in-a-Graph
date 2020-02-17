
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
public final class txtParser {

    private static txtParser single_instance = null; 
    private txtParser() {
    }
    
    public static txtParser getInstance() { 
        if (single_instance == null) 
            single_instance = new txtParser(); 
  
        return single_instance; 
    } 
    
    public static List<String> readTxtFile(String file) {
        List<String> res = new ArrayList<>();
        FileReader input = null;
        try {
            input = new FileReader(file);
            BufferedReader bufRead = new BufferedReader(input);
            String line = null;
            while ((line = bufRead.readLine()) != null) {
                res.add(line);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(txtParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(txtParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                input.close();
            } catch (IOException ex) {
                Logger.getLogger(txtParser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
      return res;
    }
}