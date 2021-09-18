package model;

import java.util.Locale;
import java.util.regex.Pattern;

public class Customer {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected String emailRegex = "^(.+)@(.+).(.+)$";


    public Customer (String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email.toLowerCase(Locale.ROOT);

        Pattern pattern = Pattern.compile(emailRegex);

        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("The mail address you entered has no valid format. Please try again!");
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
    return "FirstName: " + firstName + " LastName: " + lastName +  " Email: " + email;
    }

}
