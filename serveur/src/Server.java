
import java.io.NotSerializableException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
//import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String args[]) {

        try {
            Hello obj = new Hello();

            IRegistry myR = (IRegistry) Naming.lookup("rmi://localhost:1098/my_registry");
            myR.rebind("Hello", obj);

            System.out.println("Server ready");
        } catch (RemoteException | NotBoundException | NotSerializableException | MalformedURLException e){
            e.printStackTrace();
        }
    }
}