package Service; //this part is really hard!!
import model.Customer;
import java.util.*;

public class CustomerService {
  static Collection <Customer> listOfCustomers = new ArrayList<>();
  private static CustomerService customerService=null;//设置初始值
  private CustomerService() {
  }
  public static CustomerService getInstance() {

    if (null == customerService) {
      customerService = new CustomerService();
    }
    return customerService;
  }
  //up static reference:  implement the Singleton pattern so that there is only one
  // instance of the CustomerService class
  // and that way it can be used for both normal and admin mode

  public Collection<Customer> getAllCustomers(){
    return listOfCustomers;
  }

  //below is the method to add Customers, we will add customer to the list of customers
  public static void addCustomer(String email, String firstName, String lastName){
    Customer customer = new Customer(email, firstName, lastName);
    listOfCustomers.add(customer);
  }

  //Below is we return a specific user using their email, so for this case
  // we will use streams more specifically the filter to see which customer has that email
  // and use findAny to return it, we use Optional just because it is recommended to handle nulls
  // so that if the customer is found we return the customer otherwise we return null.
  public static Customer getCustomer(String customerEmail){
    Optional<Customer> customer=listOfCustomers.stream().filter(c -> customerEmail.equals(c.getEmail())).findAny();
    return customer.orElse(null);
  }
  public static boolean customerExisting(String customerMail) {
    if (!(listOfCustomers.contains(getCustomer(customerMail.toLowerCase(Locale.ROOT))))) {
      return false;
    }
    return true;
  }

}