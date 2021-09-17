package mapsExample;
import java.util.HashMap;
import java.util.Map;

public class MapExercise {
    public static void main(String[] args){

        Person mike=new Person("Mike", "mike1@email.com");
        Person mo=new Person("Mo", "mo2@email.com");
        Person joe=new Person("Joe", "joe3@email.com");
        Person poop=new Person("Poop", "poop4@email.com");

        Map<String, Person> mapOfPeople=new HashMap<String, Person>();
        mapOfPeople.put(mike.getEmail(), mike);
        mapOfPeople.put(mo.getEmail(), mo);
        mapOfPeople.put(joe.getEmail(), joe);
        mapOfPeople.put(poop.getEmail(), poop);

        for (String email: mapOfPeople.keySet()){
            System.out.println(email);
        }
        for (Person person: mapOfPeople.values()){
            System.out.println(person);
        }

        System.out.println(mapOfPeople.get("mike1@email.com"));
        System.out.println("Contains Jeff: "+ mapOfPeople.containsKey("jeff@email.com"));

    }
}
