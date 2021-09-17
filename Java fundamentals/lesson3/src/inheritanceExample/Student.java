package inheritanceExample;

public class Student extends Person {
    private String studentID;
    public Student( String firstName, String lastName,String studentID){
        super(firstName,lastName);
        this.studentID=studentID;
    }
    public String getStudentID(){
        return studentID;
    }
    public void setStudentID(String studentID){
        this.studentID=studentID;
    }

    @Override
    public String toString() {
        return super.toString() +" student ID: "+ studentID;
    }
}
