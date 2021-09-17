package Service;
import model.*;

import java.util.*;

public class ReservationService {

    static Collection<IRoom> listOfRooms=new ArrayList<>();
    static Collection<Reservation> listOfReservation=new ArrayList<>();//建一个list保存数据
    public static Room addRoom(RoomType roomType, String roomID, Double roomPrice){
        Room room=new Room(roomType,roomID, roomPrice);
        listOfRooms.add(room);
        return room;
    }
    public Collection<IRoom> getAllRooms(){
        return listOfRooms;
    }


    public IRoom getARoom (String roomID){
        Optional<IRoom> room=listOfRooms.stream().filter(c -> roomID.equals(c.getRoomNumber())).findFirst();
        return room.orElse(null);//从listofroom里面把room提取出来， 利用的是输入和roomnumber相同
        //filter的写法需要记住， 比较难写
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate){
       Reservation reservation=new Reservation(customer, room, checkInDate, checkOutDate);
       listOfReservation.add(reservation);
       return reservation;
    }

    private static ReservationService reservationService=null; //给instance一个初始值
    private ReservationService(){ //并不用定义什么内部
    }
    public static ReservationService getInstance(){
        if (reservationService==null){
            reservationService=new ReservationService();//如果是还没定义就见一个新的，已经定义好了就不用了
        }
        return reservationService;
    }
    //find room method is to check for all available rooms and return a collection of those rooms
    public Collection <IRoom> findRooms(Date checkInDate,Date checkOutDate){
        Collection<IRoom> availableRooms = new LinkedHashSet<>();
        if (listOfReservation.size() == 0) {
            availableRooms = listOfRooms;
            System.out.println(availableRooms);
            return availableRooms;
        } else {
            for (IRoom room : listOfRooms) {
                for (Reservation reservation : listOfReservation) {
                    if (((room.getRoomNumber().equals(reservation.getRoom().getRoomNumber())) &&
                            //if the room is booked in another reservation, the checkInDate and checkOutDate must be either before or after the dates entered in the reservation process
                            ((checkOutDate.before(reservation.getCheckInDate())) ||
                                    (checkInDate.after(reservation.getCheckOutDate())) )) ||
                                        (!room.getRoomNumber().equals(reservation.getRoom().getRoomNumber()))){
                        availableRooms.add(room);
                    }
                }
            }
            System.out.println(availableRooms);
            return availableRooms;
        }
    }
    public Collection <Reservation> getCustomerReservation(String customerEmail){
        List<Reservation> customerReservation=new ArrayList<>();
        for (Reservation reservation: listOfReservation){
            if ((reservation.getCustomer()).equals(CustomerService.getCustomer(customerEmail))) {
                customerReservation.add(reservation);
            }
        }
        return customerReservation;
    }

    public Collection<Reservation> getAllReservation(){
        return listOfReservation;
    }
    public static void printAllReservation(){
        for (Reservation reservation: listOfReservation){
            System.out.println(reservation);
        }

    }

}
