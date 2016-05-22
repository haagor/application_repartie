import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Hello extends UnicastRemoteObject implements IHello{

    public Hello(int numport) throws RemoteException {
        super(numport);
    }

    public Hello() throws RemoteException{
        super();
    }

    public String sayHello() {
        System.out.println("un client est passé");
        return "Hello, world!";
    }
}
