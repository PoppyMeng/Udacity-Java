package menus;
import api.HotelResource;
import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class MainMenu {
    public static String mainMenuSelection() {
        //print empty line first for better readability in console
        System.out.println("");
        System.out.println("Welcome to our hotel reservation system. What would you like to do? Please select the respective number e.g. enter 1 and press Enter if you would like to reserve a room. ");
        System.out.println("Please be aware, that you have to create an account if you want to use our system. So if you're a new guest please start with entering 3 and pressing Enter. Thanks for your cooperation!");
        System.out.println("1. Find and reserve a room");
        System.out.println("2. See my reservations");
        System.out.println("3. Create an account");
        System.out.println("4. Admin");
        System.out.println("5. Exit");

        Scanner customerInput = new Scanner(System.in);
        String whatToDo = customerInput.next();
        return whatToDo;
    }

    public static String reserveARoom() {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        String dateFormatString = "dd.MM.yyyy";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please enter your date of arrival (DD.MM.YYYY).");
        String checkInDate = scanner.next();

        //sanity-check input date before parsing
        boolean validDateArrival;

        validDateArrival = ReservationService.isReservationDateValid(checkInDate, dateFormatString);

        //parse date if usable and check if parsable
        Date formattedCheckInDate;

        if (validDateArrival) {
            try {
                formattedCheckInDate = format.parse(checkInDate);
            } catch (ParseException e) {
                e.printStackTrace();
                System.out.println("Please enter a valid date. Format must be DD.MM.YYYY. The reservation process will be started again.");
                //if this error is shown, the reservation process is started again.
                return reserveARoom();
            }
        } else {
            System.out.println("Please enter a valid date. Format must be DD.MM.YYYY. The reservation process will be started again.");
            //if this error is shown, the reservation process is started again.
            return reserveARoom();
        }


        System.out.println("Please enter your date of departure (DD.MM.YYYY).");
        String checkOutDate = scanner.next();

        //sanity-check input date before parsing
        boolean validDateDeparture;
        validDateDeparture = ReservationService.isReservationDateValid(checkOutDate, dateFormatString);

        //parse date if usable and check if parsable
        Date formattedCheckOutDate;

        if (validDateDeparture) {
        try {
            formattedCheckOutDate = format.parse(checkOutDate);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Please enter a valid date. Format must be DD.MM.YYYY. The reservation process will be started again.");
            //if this error is shown, the reservation process is started again.
            return reserveARoom();
            }
        } else {
            System.out.println("Please enter a valid date. Format must be DD.MM.YYYY. The reservation process will be started again.");
            //if this error is shown, the reservation process is started again.
            return reserveARoom();
        }


        //Printing available rooms
        System.out.println("Thanks for providing this information. We will show you a list of available rooms at this dates:");
        Collection<IRoom> availableRoomsForCheck = new LinkedHashSet<>();
        availableRoomsForCheck = HotelResource.findARoom(formattedCheckInDate, formattedCheckOutDate);


        if (availableRoomsForCheck.isEmpty()){
            System.out.println("Unfortunately there are no rooms available at your selected dates. Please try again with other dates.");
            return reserveARoom();
        }


        //Input of wanted room number
        System.out.println("Please enter the room number of the room you would like to reserve.");
        String selectedRoomNumber = scanner.next();
        IRoom selectedRoom = HotelResource.getRoom(selectedRoomNumber);


        if (!availableRoomsForCheck.contains(selectedRoom)){
                System.out.println("The room number you entered is not available. Please start the reservation again.");
                return reserveARoom();
        }

        //Input of customer mail address including existence check
        System.out.println("Thank you! This is a great choice! Now please enter your customer mail (the same you used in your account).");
        String customerMail = scanner.next();

        boolean customerIsExisting;
        customerIsExisting = CustomerService.customerExisting(customerMail);

        //Todo reservations needs to block period of time in available room list. Maybe the list is not updated?
        if (customerIsExisting) {
            Customer reservingCustomer = CustomerService.getCustomer(customerMail.toLowerCase(Locale.ROOT));
            System.out.println("Thanks for your patience! We will now finish the reservation.");
            HotelResource.bookARoom (reservingCustomer,selectedRoom,formattedCheckInDate,formattedCheckOutDate);
            System.out.println("We received your reservation. Thank you! Overview: Room Number: " + selectedRoom.getRoomNumber() + " Check-in Date: " + formattedCheckInDate + " Check-Out Date: " + formattedCheckOutDate + " Mail address: " + customerMail);
            return null;
        } else {
            //If this error occurs, the customer has the chance to select whether he/she would like to start the reservation process again or return to the main menu
            System.out.println("The mail address you entered is not connected to an account yet. Maybe you spelled it wrong or you need to create an account first.");
            System.out.println("Would you like to change to the account creation menu (press 1 and Enter)? Or would you like to start the reservation process again (press 2 and Enter)?");
            String nextAction = scanner.next();

            if (nextAction.equals("1")){
                createANewAccount();
                return null;
            } else if (nextAction.equals("2")){
                return reserveARoom();
            } else {
                System.out.println("Invalid input. Returning to main menu");
            }
        return null;
        }

    }

    public static void seeMyReservations(){
        System.out.println("Please enter your customer mail (the same you used in your account)");
        Scanner scanner = new Scanner(System.in);
        String customerMail = scanner.next().toLowerCase(Locale.ROOT);

        //Check if customer mail is existing
        boolean customerIsExisting;
        customerIsExisting = CustomerService.customerExisting(customerMail);

        if (customerIsExisting) {
            System.out.println("The following rooms are reserved for you: " + HotelResource.getCustomersReservations(customerMail));
        } else {
            System.out.println("The mail address you entered is not connected to an account yet. Please check if you entered the right address or create an account first. Thank you!");
            System.out.println("You will now be redirected to the main menu");
        }
    }

    protected static void createANewAccount(){
        System.out.println("This is the account creation menu. Please provide the following information:");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your first name.");
        String firstName = scanner.next();
        System.out.println("Please enter your last name.");
        String lastName = scanner.next();
        System.out.println("Please enter your mail address.");
        String mailAddress = scanner.next().toLowerCase(Locale.ROOT);

        HotelResource.addCustomer(firstName,lastName,mailAddress);
    }



    public static void main(String[] args) {

        //'retry' is used to show the menu again after selecting one of the options. Could not be 'false'
        boolean retry = true;
    do {
        String whatToDo = mainMenuSelection();
        int whatToDoNumber = 0;

        try {
            whatToDoNumber = Integer.parseInt(whatToDo);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter one of the displayed numbers!");
        }

        switch (whatToDoNumber) {
            case 1:
                System.out.println("Starting a new reservation. Thanks for being our guest");
                reserveARoom();
                break;

            case 2:
                System.out.println("Here is an overview of all your reservations:");
                seeMyReservations();
                break;

            case 3:
                System.out.println("Stating to create a new customer account. Thank you for choosing us!");
                createANewAccount();
                break;

            case 4:
                System.out.println("Stating the Admin Menu.");
                AdminMenu.adminMenu();
                break;

            case 5:
                System.out.println("Shutting down. Have a nice day and thanks for using this system!");
                System.exit(0);

            default:
                System.out.println("Valid inputs are numbers 1 - 5");
                break;

        }
    } while (retry);

}



}
