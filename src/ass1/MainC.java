/*************************************************
 * This is a multi-threaded version of the same. *
 ************************************************* 
 */

package ass1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vivek
 */
public class MainC {
    public static final String FILENAME = "/home/vivek/mnistu5.csv";
    public static long asuid = 0;
    public static void main(String args[]) {
        System.out.println("Main");
        List<Data> dataSet = new ArrayList<>();
        List<String> csvLines = FileOps.openCSV(FILENAME);
        
        for(int i = 0; i<csvLines.size();i++){
            dataSet.add(new Data(csvLines.get(i), i));
        }
        
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(50);
        System.out.println("Size:" + dataSet.size() + " * " + dataSet.get(0).length());
        for(int i = 0; i < dataSet.size() - 1; i++){
            for(int j = i + 1; j < dataSet.size(); j++){
                WorkerThread task = new WorkerThread(dataSet.get(i), dataSet.get(j));
                executor.execute(task);
                if(asuid % 10000 == 0) System.out.println(asuid/10000);
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) {   }  
        int counter = 0;
        /*
        for(Data x : dataSet){
            System.out.println((counter++) +":: " +
                    " J:" + x.vJ +" v:" + x.closestJ + 
                    " C:" + x.vC +" v:" + x.closestC + 
                    " E:" + x.vE +" v:" + x.closestE);
        }
        */
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
}