package api;

import Service.CustomerService;
import Service.ReservationService;
import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;

public class HotelResource {
    public static HotelResource hotelResource=new HotelResource();
    public HotelResource(){
    }
    public static Customer getCustomer(String email){
        return CustomerService.getInstance().getCustomer(email);
    }
    public static IRoom getRoom(String roomNumber){
        return ReservationService.getInstance().getARoom(roomNumber);
    }
    public static void addCustomer(String email, String firstName, String lastName){
        CustomerService.addCustomer(email, firstName, lastName);
    } //void不return，没有getinstance（）
    public static Reservation bookARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().reserveARoom(customer, room, checkInDate, checkOutDate);
    }
    public static Collection<Reservation> getCustomerReservations(String customerEmail){
        return ReservationService.getInstance().getCustomerReservation(customerEmail);
    }
    public Collection<IRoom> findARoom(Date checkInDate, Date checkOutDate){
        return ReservationService.getInstance().findRooms(checkInDate, checkOutDate);
    }









}
