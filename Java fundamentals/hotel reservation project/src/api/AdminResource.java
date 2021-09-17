package api;
import Service.CustomerService;
import Service.ReservationService;
import model.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;

public class AdminResource {
    public static AdminResource adminResource=new AdminResource();
    public AdminResource(){
    }
      //这两行是static的固定格式， 要记住
    public Customer getCustomer(String email){
        return CustomerService.getInstance().getCustomer(email);
    }
    public static Room addRoom(RoomType roomType, String roomID, Double roomPrice){
        return ReservationService.addRoom(roomType, roomID, roomPrice);
    }
    public static Collection<IRoom> getAllRooms(){
        return ReservationService.getInstance().getAllRooms();
    }
    public static Collection<Customer> getAllCustomers(){
        return CustomerService.getInstance().getAllCustomers();
    }
    public static void displayAllReservations(){
        ReservationService.printAllReservation();
    }

}
