package menus;

import java.util.Locale;
import java.util.Scanner;

import api.AdminResource;
import model.RoomType;
import service.AddSampleData;
import service.CustomerService;



public class AdminMenu {

    public static String adminMenuSelection() {
        //print empty line first for better readability in console
        System.out.println("");
        System.out.println("This is the administrator menu. Please select what you'd like to do and enter the respective number!");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Use sample data");
        System.out.println("6. Back to main menu");

        Scanner customerInput = new Scanner(System.in);
        String whatToDoAdmin = customerInput.next();
        return whatToDoAdmin;
    }

    private static void showAllCustomers() {
        System.out.println("Below you can find a list of all customer accounts:");
        System.out.println(CustomerService.getAllCustomers());
    }

    public static void showAllRooms() {
        System.out.println("Below you can find a list of all rooms:");
        System.out.println(AdminResource.getAllRooms());
    }

    public static void showAllReservations() {
        System.out.println("Below you can find a list of all reservations:");
        AdminResource.displayAllReservations();
    }


//found valueOf in https://stackoverflow.com/questions/9578986/java-and-enum-with-scanner
    public static void addNewRoom (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the room number.");
        String newRoomId = scanner.next();
        System.out.println("Please enter the room price per night.");
        Double newRoomPricePerNight = scanner.nextDouble();
        System.out.println("Please enter the room type. Valid values are Single or Double");
        RoomType newRoomType = RoomType.valueOf(scanner.next().toUpperCase(Locale.ROOT));
        AdminResource.addRoom(newRoomId,newRoomPricePerNight, newRoomType );

    }


    public static void adminMenu() {

        //'retry' is used to stay in the administrators menu after selecting one of the options
        boolean retry = true;

        do {
            String whatToDo = adminMenuSelection();
            int whatToDoNumberAdmin = 0;

            try {
                whatToDoNumberAdmin = Integer.parseInt(whatToDo);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter one of the displayed numbers!");
            }


            switch (whatToDoNumberAdmin) {
                case 1:
                    showAllCustomers();
                    break;

                case 2:
                    showAllRooms();
                    break;

                case 3:
                    showAllReservations();
                    break;

                case 4:
                    addNewRoom();
                    break;

                case 5:
                    AddSampleData.addRoomSampleData();
                    AddSampleData.addCustomerSampleData();
                    AddSampleData.addReservationSampleData();
                    break;

                case 6:
                    retry = false;
                    MainMenu.main(null);
                    break;

                default:
                    System.out.println("Please enter a number between 1 and 5");
                    break;

            }
        } while (retry);


    }


}
