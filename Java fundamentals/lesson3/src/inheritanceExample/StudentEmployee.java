package inheritanceExample;

public class StudentEmployee extends Student{
    private double rateOfPayPerHour;
    private String employeeID;

    public StudentEmployee (String firstName, String lastName,String studentID, double rateOfPayPerHour, String employeeID){
        super (firstName, lastName, studentID);
        this.rateOfPayPerHour=rateOfPayPerHour;
        this.employeeID=employeeID;
    }
    public String getEmployeeID(){
        return employeeID;
    }
    public double getRateOfPayPerHour(){
        return rateOfPayPerHour;
    }
    public void setRateOfPayPerHour(double rateOfPayPerHour){
        this.rateOfPayPerHour=rateOfPayPerHour;
    }
    public void setEmployeeID(String employeeID){
        this.employeeID=employeeID;
    }

    @Override
    public String toString(){
       return super.toString() +" employee ID: "+ employeeID + " pay: "+ rateOfPayPerHour;
    }
}
