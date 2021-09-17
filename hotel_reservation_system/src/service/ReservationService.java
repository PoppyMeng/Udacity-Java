package service;

import model.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class ReservationService {
    static List<IRoom> roomList = new ArrayList<>();
    static List<Reservation> reservationList = new ArrayList<>();
    //static Collection<IRoom> availableRooms = new ArrayList<>();


    public static void addRoom(String roomNumber, Double price, RoomType roomType) {

        Room room = new Room(roomNumber, price, roomType);

        if (roomList.contains(getARoom(roomNumber))) {
            System.out.println("This room number already exists. The room can not be created.");
        } else {
            //room = new Room(room.getRoomNumber(), room.getRoomPrice(), room.getRoomType());
            roomList.add(room);
            System.out.println("The room was successfully added to our room list.");
        }
    }

    //date verification taken from: https://stackoverflow.com/questions/226910/how-to-sanity-check-a-date-in-java
    public static boolean isReservationDateValid(String date, String date_format) {
        try {
            DateFormat df = new SimpleDateFormat(date_format);
            df.setLenient(false);
            df.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static IRoom getARoom(String roomId) {
        for (IRoom room : roomList) {
            if ((roomId).equals(room.getRoomNumber())) {
                return room;
            }
        }
        return null;
    }


    public static Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservationList.add(reservation);
        return reservation;
    }


    public static Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {

        Collection<IRoom> availableRooms = new LinkedHashSet<>();
        System.out.println("Available Rooms at start of method " + availableRooms);

        if (reservationList.size() == 0) {
            availableRooms = roomList;
            System.out.println(availableRooms);
            return availableRooms;
        } else {
            for (IRoom room : roomList) {
                for (Reservation reservation : reservationList) {

                    if ((room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) &&
                            //if the room is booked in another reservation, the checkInDate and checkOutDate must be either before or after the dates entered in the reservation process
                            (((checkInDate.before(reservation.getCheckInDate())) && (checkOutDate.before(reservation.getCheckInDate()))) ||
                                    ((checkInDate.after(reservation.getCheckOutDate())) && ((checkOutDate.after(reservation.getCheckOutDate())))))) {
                        availableRooms.add(room);

                    } else {
                        if (room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) {
                            availableRooms.remove(room);
                        }
                    }
                }

            }
        }

        System.out.println(availableRooms);
        return availableRooms;
    }

    public static List<Reservation> getCustomersReservation(String customerMail) {

        List<Reservation> customerReservations = new ArrayList<>();

        for (Reservation reservation : reservationList) {
            if (reservation.getCustomer() != null && (CustomerService.getCustomer(customerMail)) != null) {
                if ((reservation.getCustomer().toString()).equals(CustomerService.getCustomer(customerMail).toString())) {
                    customerReservations.add(reservation);
                }
            }
        }
        return customerReservations;
    }

    public static Collection<Reservation> getAllReservations() {
        return reservationList;
    }


    public static Collection<IRoom> allRooms() {
        return roomList;
    }


}