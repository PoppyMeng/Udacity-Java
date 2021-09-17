package menus;

import api.AdminResource;
import model.RoomType;

import java.util.Locale;
import java.util.Scanner;


public class AdminMenu {
    public static String adminMenuSelection() {
        System.out.println("This is the administrator menu. Please select what you'd like to do and enter the respective number!");
        System.out.println("1. See all customers");
        System.out.println("2. See all rooms");
        System.out.println("3. See all reservations");
        System.out.println("4. Add a room");
        System.out.println("5. Back to main menu");

        Scanner customerInput = new Scanner(System.in);
        String todo = customerInput.next();
        return todo;
    }
    public static void seeAllCustomers(){
        AdminResource.getAllCustomers();
    }
    public static void seeAllRooms(){
        AdminResource.getAllRooms();
    }
    public static void seeAllReservations(){
        AdminResource.displayAllReservations();
    }
    public static void addRoom(){
        Scanner scanner=new Scanner(System.in);
        System.out.println("Pls enter a room ID: ");
        String roomID=scanner.next();
        System.out.println("Pls enter a room price: ");
        Double roomPrice=scanner.nextDouble();
        System.out.println("Pls enter a room type: single or double");
        RoomType roomType = RoomType.valueOf(scanner.next().toUpperCase(Locale.ROOT));
        AdminResource.addRoom(roomType, roomID, roomPrice);
    }
    public static void adminMenu(){
        boolean set=true;
        while (set){
            Scanner scanner=new Scanner(System.in);
            System.out.println("Pls select from following numbers for next action");
            String toDo = adminMenuSelection();
            int toDoNumber=0;
            try{
                toDoNumber=Integer.parseInt(toDo);
            }catch (NumberFormatException e){
                System.out.println("invalid input, pls select from the following numbers.");
            }
            switch(toDoNumber){
                default:
                    System.out.println("Pls select from following numbers.");
                    break;
                case 1:
                    System.out.println("1. See all customers");
                    seeAllCustomers();
                    break;
                case 2:
                    System.out.println("2. See all rooms");
                    seeAllRooms();
                    break;
                case 3:
                    System.out.println(" 3. See all reservations");
                    seeAllReservations();
                    break;
                case 4:
                    System.out.println("4.Adding a room now");
                    addRoom();
                    break;
                case 5:
                    set=false;
                    System.out.println("5. Back to main menu");
                    MainMenu.main(null);
                    break;
            }

            }



        }



}
