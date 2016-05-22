
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

            IRegistry myR = (IRegistry) Naming.lookup("rmi://localhost:1098/my_registry");
            IHello stub = (IHello) myR.lookup("Hello");

            //IHello stub = (IHello) Naming.lookup("rmi://localhost:1098/my_registry");
            System.out.println(stub.sayHello());


        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
