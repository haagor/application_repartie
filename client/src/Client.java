
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client extends UnicastRemoteObject {

    Client() throws RemoteException {
    }

    Client(int port) throws RemoteException {
        super(port);
    }

    public static void main(String[] args) {

        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new SecurityManager());
            }
            IHello stub = (IHello) Naming.lookup("rmi://localhost:1098/Hello");
            System.out.println(stub.sayHello());


            /*TP 2 exo 3
            IClientRMI clientRMI = new ClientRMI();
            System.out.println(stub.getService());
            System.out.println(stub.getService().getValue());
            System.out.println(stub.getService().multiplyValueBy(2, clientRMI));
            */

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
