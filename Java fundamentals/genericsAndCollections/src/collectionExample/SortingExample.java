
package collectionExample;

import java.util.ArrayList;
import java.util.Collections;

public class SortingExample {
    public static void main(String[] args){
        ArrayList <String> names=new ArrayList<String>();
        names.add("Poppy");
        names.add("Rui");

        Collections.sort(names);
        for (String name:names){
            System.out.println(name);
        }

    }
}
