package inheritanceExample;

import org.w3c.dom.ls.LSOutput;

public class PersonTester {
    public static void main(String[] args){
        Person sally=new Person("Sally", "Phillips");
        System.out.println(sally);
        Student mike=new Student("Mike", "Labo", "12345");
        System.out.println(mike);
        StudentEmployee jeff=new StudentEmployee("Jeff", "Sam", "001", 20.5, "#6666");
        System.out.println(jeff);
    }
}
