package MiniProjectJavaSE;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
    private Socket socket;
    private int id;
    private PackageData pd;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public ClientHandler(Socket socket, int id, PackageData pd) {
        this.socket = socket;
        this.id = id;
        this.pd = pd;
    }

    public void run(){
        Server.connectToDb();
        System.out.println("ClientHandler connected to DB");
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

            while((pd=(PackageData)inputStream.readObject())!=null){
                System.out.println("in while loop");
                if(pd.getOperationType().equals("ADD")){
                        Server.addStudentToDb(pd.getStudent());
                        System.out.println("Student successfully added");
                }
                else if(pd.getOperationType().equals("LIST")) {
                        outputStream.writeObject(Server.getAllStudents().clone());
                        System.out.println("List was sended");
                }
                else if(pd.getOperationType().equals("EXIT")){
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
