package service;

import api.HotelResource;
import model.Customer;
import model.IRoom;
import model.Room;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static model.RoomType.SINGLE;
import static model.RoomType.DOUBLE;

public class AddSampleData {


    public static void addRoomSampleData() {

        double priceRoom1 = 200;
        double priceRoom2 = 270;
        double priceRoom3 = 390;
        double priceRoom4 = 520;


        ReservationService.addRoom("100", priceRoom1, SINGLE);
        ReservationService.addRoom("101", priceRoom2, DOUBLE);
        ReservationService.addRoom("102", priceRoom3, SINGLE);
        ReservationService.addRoom("103", priceRoom4, DOUBLE);

    }

    public static void addCustomerSampleData (){


        CustomerService.addCustomer("John", "Doe","john@gmail.com");
        CustomerService.addCustomer("Max", "Musterman","Muster@web.de");
        CustomerService.addCustomer("Frieda", "Meyer","F.Meyer@gmx.com");
        CustomerService.addCustomer("Carla", "Schmidt","CarlaSchmidt@webmail.at");

    }

    public static void addReservationSampleData(){

        Customer john = new Customer("John", "Doe","john@gmail.com");
        Customer max = new Customer("Max", "Musterman","Muster@web.de");
        Customer frieda = new Customer("Frieda", "Meyer","F.Meyer@gmx.com");
        Customer carla = new Customer("Carla", "Schmidt","CarlaSchmidt@webmail.at");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        IRoom room1 = HotelResource.getRoom("100");
        IRoom room2 = HotelResource.getRoom("101");
        IRoom room3 = HotelResource.getRoom("102");
        IRoom room4 = HotelResource.getRoom("103");

        Date checkInDate1 = null;
        Date checkInDate2 = null;
        Date checkInDate3 = null;
        Date checkInDate4 = null;
        Date checkOutDate1 = null;
        Date checkOutDate2 = null;
        Date checkOutDate3 = null;
        Date checkOutDate4 = null;


        try {
        checkInDate1 = dateFormat.parse("2020-12-23");
        checkInDate2 = dateFormat.parse("2021-01-04");
        checkInDate3 = dateFormat.parse("2020-12-26");
        checkInDate4 = dateFormat.parse("2020-12-12");
        checkOutDate1 = dateFormat.parse("2020-12-31");
        checkOutDate2 = dateFormat.parse("2021-01-12");
        checkOutDate3 = dateFormat.parse("2020-12-27");
        checkOutDate4 = dateFormat.parse("2021-01-19");
    }  catch (ParseException e) {
        e.printStackTrace();
    }

        ReservationService.reserveARoom(john,room1,checkInDate1,checkOutDate1);
        ReservationService.reserveARoom(max,room2,checkInDate2,checkOutDate2);
        ReservationService.reserveARoom(frieda,room3,checkInDate3,checkOutDate3);
        ReservationService.reserveARoom(carla,room4,checkInDate4,checkOutDate4);

    }
}


