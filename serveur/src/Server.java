
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
//import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {

        try {
            Hello obj = new Hello();
            Naming.rebind("rmi://localhost:1098/Hello", obj);
            System.out.println("Server ready");
        } catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
    }
}