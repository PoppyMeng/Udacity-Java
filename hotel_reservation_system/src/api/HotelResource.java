package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;

public class HotelResource {

    public static Customer getCustomer(String eMail) {
       return CustomerService.getCustomer(eMail);
    }

    public static void addCustomer(String firstName, String lastName, String email){
            CustomerService.addCustomer(firstName, lastName, email);
        }

    public static IRoom getRoom(String roomNumber){
         return ReservationService.getARoom(roomNumber);
    }

    public static Reservation bookARoom (Customer customerMail, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.reserveARoom(customerMail,room,checkInDate,checkOutDate);
    }

    public static Collection <Reservation> getCustomersReservations (String customerMail){
        return ReservationService.getCustomersReservation(customerMail);
    }

    public static Collection <IRoom> findARoom (Date checkIn, Date checkOut){
        return ReservationService.findRooms(checkIn, checkOut);
    }



}

