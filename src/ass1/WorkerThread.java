/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass1;

/**
 *
 * @author vivek
 */
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
public class WorkerThread implements Runnable {  
    
    Data a, b;
    
    public WorkerThread(Data a, Data b){  
          this.a = a; this.b = b;
    }  
    
    public void run() {
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
    
    private void processmessage() {
        
    }
}  