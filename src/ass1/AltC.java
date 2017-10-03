/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/***********************************************
 * Run this class for the output.              *
 * Specify the file name in the static String. *
 ***********************************************
*/
package ass1;


import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author vivek
 */
public class AltC {
    public static final String FILENAME = "/home/vivek/Downloads/Data mining Assignments/mnist_test.csv";
    public static long asuid = 0;
    public static void main(String args[]) {
        List<Data> dataSet = new ArrayList<>();
        List<String> csvLines = FileOps.openCSV(FILENAME);
        
        for(int i = 0; i<csvLines.size();i++){
            dataSet.add(new Data(csvLines.get(i), i));
        }
        for(Data da : dataSet){
            da.reduce();
        }
        System.out.println("Size:" + dataSet.size() + " * " + dataSet.get(0).length());
        for(int i = 0; i < dataSet.size() - 1; i++){
            for(int j = i + 1; j < dataSet.size(); j++){
                doSomething(dataSet.get(i), dataSet.get(j));
        //        if(++asuid % 10000 == 0) System.out.println(asuid/10000);
            }
        }
        int counter = 0;
        
        int outC = 0, outJ = 0, outE = 0;
        for(int i = 0; i<dataSet.size(); i++){
            if(dataSet.get(i).label == dataSet.get(dataSet.get(i).vC).label)
                outC++;
            if(dataSet.get(i).label == dataSet.get(dataSet.get(i).vJ).label)
                outJ++;
            if(dataSet.get(i).label == dataSet.get(dataSet.get(i).vE).label)
                outE++;
        }
        System.out.println("Number of times labels were same \n J:"+outJ+" C:"+outC+" E:"+outE);
    } 
    
    public static void doSomething(Data a, Data b){
        float C = Data.cDistance(a, b), E = Data.eDistance(a, b), J = Data.jDistance(a, b);
        //System.out.println(C + " " + E + " " + J);
        MainC.asuid++;
        if(C > a.closestC){
            a.closestC = C;
            a.vC = b.number;
        }
        if(C > b.closestC){
            b.closestC = C;
            b.vC = a.number;
        }
        
        if(E < a.closestE){
            a.closestE = E;
            a.vE = b.number;
        }
        if(E < b.closestE){
            b.closestE = E;
            b.vE = a.number;
        }
        
        if(J > a.closestJ){
            a.closestJ = J;
            a.vJ = b.number;
        }
        if(J > b.closestJ){
            b.closestJ = J;
            b.vJ = a.number;
        }
    }
}
