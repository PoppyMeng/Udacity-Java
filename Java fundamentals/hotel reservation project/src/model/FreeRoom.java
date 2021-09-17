package model;
public class FreeRoom extends Room{
    public FreeRoom( RoomType enumeration, String roomNumber, Double price) {
        super(enumeration, roomNumber, price);
    }
    @Override
    public void setPrice(Double price) {
        price=0.0;
    }
    public String toString(){
        return("WOW This room is free!");
    }

}
