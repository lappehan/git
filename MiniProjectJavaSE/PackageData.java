package MiniProjectJavaSE;


import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    private String operationType;
    private ArrayList<Student> students;
    private Student student;

    public PackageData() {
    }

    public PackageData(Student student) {
        this.student = student;
    }

    public PackageData(String operationType) {
        this.operationType = operationType;
    }

    public PackageData(ArrayList<Student> students) {
        this.students = students;
    }

    public PackageData(String operationType, ArrayList<Student> students, Student student) {
        this.operationType = operationType;
        this.students = students;
        this.student = student;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
