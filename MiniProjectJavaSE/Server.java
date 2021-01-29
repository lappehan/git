package MiniProjectJavaSE;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.ArrayList;

public class Server {
    public static Connection conn;
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1998);
            int id = 0;

            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Client# " + id + " connected");
                id++;
                PackageData packageData = new PackageData();
                ClientHandler ch = new ClientHandler(socket,id,packageData);
                ch.start();
                System.out.println("New Thread is started");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connectToDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/miniprojectstudents?useUnicode=true&serverTimezone=UTC","root","");
            System.out.println("Connected");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void addStudentToDb(Student student){
        try{
            PreparedStatement st = conn.prepareStatement("INSERT INTO students(id,name,surname,age) values (NULL,?,?,?)");
            st.setString(1,student.getName());
            st.setString(2, student.getSurname());
            st.setInt(3,student.getAge());

            st.executeUpdate();
            st.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<Student> getAllStudents(){
        ArrayList<Student> students = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM  students");
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");

                students.add(new Student(id,name,surname,age));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return   students;
    }
}
