package menus;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import Service.CustomerService;
import Service.ReservationService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import Service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class MainMenu {
    public static String menuSelection(){
        System.out.println("Select from below and give a number");
        System.out.println("1. Create an account");
        System.out.println("2. Find a room");
        System.out.println("3. See my reservation");
        System.out.println("4. Admin");
        System.out.println("5. Exit the application");
        //Scanner写法要记一下
        Scanner customerInput=new Scanner (System.in);
        String toDo=customerInput.next();
        return toDo;
    }
    public static String reserveARoom(){
        SimpleDateFormat dateFormat=new SimpleDateFormat("MM.dd.yyyy");
        Scanner scanner=new Scanner(System.in);
        System.out.println("Please enter date of arrival (MM.DD.YYYY format)");
        String checkInDate=scanner.next();

        //parse date if usable and check if parsable
        Date formattedCheckInDate;
        try {
            formattedCheckInDate = dateFormat.parse(checkInDate);

        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Please enter a valid date. Format must be DD.MM.YYYY. The reservation process will be started again.");
            //if this error is shown, the reservation process is started again.
            return reserveARoom();
        }

        System.out.println("Please enter your date of departure (MM.DD.YYYY).");
        String checkOutDate = scanner.next();

        //sanity-check input date before parsing ///I got this code from mentor answer prt but don't understand it, also it doesn't work....
        //boolean validDateDeparture;
        //validDateDeparture = ReservationService.isReservationDateValid(checkOutDate, dateFormatString);

        //parse date if usable and check if parsable
        Date formattedCheckOutDate;
        try {
            formattedCheckOutDate = dateFormat.parse(checkOutDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Please enter a valid date. Format must be DD.MM.YYYY. The reservation process will be started again.");
            //if this error is shown, the reservation process is started again.
            return reserveARoom();
        }

        System.out.println("Checking available rooms....");
        Collection<IRoom> availableRooms=new LinkedHashSet<>();
        availableRooms=HotelResource.hotelResource.findARoom(formattedCheckInDate, formattedCheckOutDate);

        if (availableRooms.isEmpty()){
            System.out.println("Unfortunately there is no available rooms.");
            return reserveARoom();
        }
        System.out.println("Pls type in the room number you want to get: ");
        String roomNumberSelected=scanner.next();
        IRoom roomSelected=HotelResource.getRoom(roomNumberSelected);
        if(!availableRooms.contains(roomSelected)){
            System.out.println("The room number you entered is not valid");
            return reserveARoom();
        }
        System.out.println("The room you've chosen is "+roomSelected);
        System.out.println("Pls enter your email for your account: ");
        String customerInputEmail=scanner.next();

        boolean customerAlreadyExist;
        customerAlreadyExist=CustomerService.customerExisting(customerInputEmail);
        if(customerAlreadyExist){
            Customer validCustomer=CustomerService.getCustomer(customerInputEmail);
            HotelResource.bookARoom(validCustomer, roomSelected, formattedCheckInDate, formattedCheckOutDate);
            System.out.println("Your room is reserved! Customer name: "+ validCustomer+ " The room number is "+roomSelected);
            System.out.println("Checking date is from "+formattedCheckInDate +" to "+ formattedCheckOutDate);
            return null;
        }else{
            System.out.println("The email you entered is not linked to any account in our system.");
            System.out.println("Press 1 to create an account, or press 2 to go back to room-selecting page again.");
            String numberCustomerPress=scanner.next();
            if (numberCustomerPress.equals("1")){
                createAnAccount();
            }else{
                if (numberCustomerPress.equals("2")) {
                 return reserveARoom();
                }else{
                    System.out.println("Invalid input, returning to the real menu now.");
                }
            }
            return null;
        }
    }
    public static void seeMyReservation(){
        System.out.println("Pls enter your email to see your reservation: ");
        Scanner scanner=new Scanner(System.in);
        String customerEnteredEmail=scanner.next();
        boolean customerExisting;
        customerExisting=CustomerService.customerExisting(customerEnteredEmail);
        if(customerExisting){
            System.out.println("Your reservation is here: "+ HotelResource.getCustomerReservations(customerEnteredEmail));
        }else{
            System.out.println("There is no reservation related to this email");
            System.out.println("If you don't have an account yet pls create one first.");
            System.out.println("We will return to the main menu now");
        }

    }
    protected static void createAnAccount(){
        Scanner scanner=new Scanner (System.in);
        System.out.println("Pls enter your firstname: ");
        String customerFirstName=scanner.next();
        System.out.println("Pls enter your lastname: ");
        String customerLastName=scanner.next();
        System.out.println("Pls enter your email: ");
        String customerEmail=scanner.next().toLowerCase(Locale.ROOT);
        HotelResource.addCustomer(customerEmail, customerFirstName, customerLastName);
    }
    public static void main(String[] args){
        while (true){
            System.out.println("Pls select from the following options and select number: ");
            String toDo=menuSelection();
            int toDoNumber=0;
            try{
                toDoNumber=Integer.parseInt(toDo);
            }catch(NumberFormatException e){
                System.out.println("Invalid input, pls select from giving numbers.");
            }
            switch (toDoNumber){
                case 1:
                    System.out.println("Creating a new account now...");
                    createAnAccount();
                    break;
                case 2:
                    System.out.println("OK, let's find you a room!");
                    reserveARoom();
                    break;
                case 3:
                    System.out.println("Showing your reservation: ");
                    seeMyReservation();
                    break;
                case 4:
                    System.out.println("Return to the admin menu now.");
                    AdminMenu.adminMenu();
                    break;
                case 5:
                    System.out.println("Exiting the application now.");
                    System.exit(0); //Why there is a 0?
                    break;
                default:
                    System.out.println("Pls choose from 1-5");
                    break;
            }

        }

    }

}
