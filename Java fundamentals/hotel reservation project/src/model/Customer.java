package model;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private final String emailRegex = "^(.+)@(.+).com$";
    private final Pattern pattern = Pattern.compile(emailRegex);

    public Customer(String firstName, String lastName, String email){
        if (!pattern.matcher(email).matches()){
            throw new IllegalArgumentException("Invalid Email ");
        }
        this.firstName=firstName;
        this.email=email;
        this.lastName=lastName;

    }
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getEmail(){
        return email;
    }
    public void setFirstName(String firstName){
        this.firstName=firstName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }
    public void setEmail(String email){
        this.email=email;
    }
    @Override
    public String toString(){
        return ("Customer: " +firstName +" "+lastName+" has the email: "+email);
    }
}
