import javax.jms.JMSException;
import javax.naming.NamingException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
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
            ITranslate stub = (ITranslate) myR.lookup("Translate");
            if (stub == null) {
                System.err.println("stub null");
                System.exit(1);
            }

            ClientJMS jms = new ClientJMS();
            jms.configurer();
            try {
                jms.configurerConsommateur(stub.subscribe("pierre"));
            } catch (NamingException | JMSException e) {
                e.printStackTrace();
            }

            //IHello stub = (IHello) myR.lookup("Hello");
            //System.out.println(stub.sayHello());

            System.out.println(stub.getTranslation("monk"));
            System.out.println(stub.getTranslation("legacy"));
            stub.addTranslation("monkey", "singe");
            stub.addTranslation("monk", "religieux");
            System.out.println(stub.getTranslation("monkey"));
            System.out.println(stub.getTranslation("monk"));


        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

    }
}
