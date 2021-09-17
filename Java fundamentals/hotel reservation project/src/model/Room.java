package model;

public class Room implements IRoom{

    private RoomType enumeration;
    private String roomNumber;
    private Double price;
    private boolean isFree; //boolean出现在variable里但没有getter, setter， 没有constructor, 而是在override里面

    public Room(RoomType enumeration, String roomNumber, Double price){

        this.enumeration=enumeration;
        this.roomNumber=roomNumber;
        this.price=price;
    }

    public RoomType getRoomType(){
        return enumeration;
    }
    public String getRoomNumber(){
        return roomNumber;
    }
    public Double getRoomPrice(){
        return price;
    }

    public void setEnumeration(RoomType enumeration) {
        this.enumeration = enumeration;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public String toString(){
       return("room number: "+ roomNumber+" is "+ enumeration +" type and the price is: "+ price);
    }
    public boolean isFree() {
        return isFree;
    }
}
