package service;

import model.Customer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public class CustomerService {

    private static final List<Customer> customerList = new ArrayList<model.Customer>();


    public static void addCustomer(String firstName, String lastName, String email) {
        if (customerList.contains(getCustomer(email.toLowerCase(Locale.ROOT)))) {
            System.out.println("There's already an account with this customer mail address in our system.");
            return;
        } else {
            try {
                customerList.add(new Customer(firstName, lastName, email.toLowerCase(Locale.ROOT)));
                System.out.println("Your account was successfully created. Thank you for being our guest!");
            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid mail address. Please try again");
            }
        }
    }

    public static Customer getCustomer(String customerMail) {
        for (Customer customer : customerList) {
            if ((customer.getEmail()).equals(customerMail)) {
                return customer;
            }
        }
        return null;
    }


    public static Collection<Customer> getAllCustomers() {
        return customerList;
    }


    public static boolean customerExisting(String customerMail) {
        if (!(customerList.contains(getCustomer(customerMail.toLowerCase(Locale.ROOT))))) {
            return false;
        }
        return true;
    }

}