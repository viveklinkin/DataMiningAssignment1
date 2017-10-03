/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vivek
 */
public class TestC {
    public static void main(String args[]){
        System.out.println("Alternate");
        List<Data> dataSet = new ArrayList<>();
        List<String> csvLines = FileOps.openCSV("/home/vivek/Downloads/Data mining Assignments/test.csv");
        
        for(int i = 0; i<csvLines.size();i++){
            dataSet.add(new Data(csvLines.get(i), i));
        }
        
        Data da = dataSet.get(0);
        
        da.reduce();
    }
}
