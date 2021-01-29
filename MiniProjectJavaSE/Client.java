package MiniProjectJavaSE;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            Socket socket = new Socket("127.0.0.1",1998);
            ObjectOutputStream outStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


            while(true){
                System.out.println("Enter 1 to add Student" + "\n" +
                                    "Enter 2 to list Students" + "\n" +
                                    "Enter 3 to Exit");

                String choice = sc.next();
                if(choice.equals("1")){
                    System.out.println("Enter name:");
                    String name = sc.next();
                    System.out.println("Enter surname");
                    String surname = sc.next();
                    System.out.println("Enter age");
                    int age = sc.nextInt();
                    Student students = new Student(name,surname,age);
                    PackageData pd = new PackageData(students);
                    pd.setOperationType("ADD");
                    outStream.writeObject(pd);

                }
                else if(choice.equals("2")){
                    PackageData pd = new PackageData();
                    pd.setOperationType("LIST");
                    outStream.writeObject(pd);
                    ArrayList<Student> students = (ArrayList<Student>) in.readObject();
                    for (Student s: students) {
                        System.out.println(s.toString());
                    }
                }
                else if(choice.equals("3")){
                    PackageData pd = new PackageData();
                    pd.setOperationType("EXIT");
                    outStream.writeObject(pd);
                    break;
                }
                else System.out.println("Enter Valid number");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
