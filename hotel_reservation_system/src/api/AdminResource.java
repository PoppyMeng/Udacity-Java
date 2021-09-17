package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.RoomType;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {

    public Customer getCustomer(String customerEMail){
        return CustomerService.getCustomer(customerEMail);
    }


    public static void addRoom(String roomNumber, Double price, RoomType roomType){
        ReservationService.addRoom(roomNumber, price, roomType);
    }

    public static Collection<IRoom> getAllRooms(){
        return ReservationService.allRooms();
    }

    public static void displayAllReservations(){
        System.out.println(ReservationService.getAllReservations());
    }
}

