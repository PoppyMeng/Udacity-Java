package exceptionExample;

public class Phone {
    private String phoneType;
    private String phoneNumber;
    public Phone(String phoneType, String phoneNumber){
        if (phoneNumber==null || phoneNumber==null){
            throw new IllegalArgumentException("The type ans number cannot be null");}

        this.phoneType=phoneType;
        this.phoneNumber=phoneNumber;
    }
    public String getPhoneType() {
        return phoneType;
    }
    public String getPhoneNumber () {
        return phoneNumber;
    }
    public void setPhoneNumber (String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setPhoneType(String phoneType){
            this.phoneType = phoneType;
    }

    @Override
    public String toString(){
        return "Phone number: "+ phoneNumber+ " Phone type: "+ phoneType;
    }
}
