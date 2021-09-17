package setExample;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SetExercise {
    public static void main(String[] args){
        List <String> numbers=new ArrayList<String>(); //arraylist allows duplicates
        numbers.add("111");
        numbers.add("222");
        numbers.add("111");
        numbers.add("543");

        Set<String> uniqueNumbers=new HashSet<String>(numbers); //hashset no duplicates
        uniqueNumbers.add("768");
        uniqueNumbers.add("543");
        for(String element: uniqueNumbers){
            System.out.println(element);
        }

    }

}
