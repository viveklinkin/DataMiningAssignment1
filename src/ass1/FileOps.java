/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vivek
 */
public class FileOps {

    public static List<String> openCSV(String path) {
        File f = new File(path);
        List<String> res = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty())
                res.add(line);
            }
        } catch (Exception e) {
            System.err.println("Error reading file");
        }
        return res;
    }
}
