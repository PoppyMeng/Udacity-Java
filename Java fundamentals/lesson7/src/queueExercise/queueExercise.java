package queueExercise;

import java.util.LinkedList;
import java.util.Queue;

public class queueExercise {
    public static void main(String[] args){
        Queue<String> queuedCustomers=new LinkedList<String>();
        queuedCustomers.add("1234");
        queuedCustomers.add("1111");
        queuedCustomers.add("0123");
        queuedCustomers.add("4321");

        while(!queuedCustomers.isEmpty()){
            System.out.println(("Customer "+queuedCustomers.poll()+" is getting helped"));
        }
    }


}
