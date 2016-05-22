import java.io.Externalizable;
import java.io.NotSerializableException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


public class Registry extends UnicastRemoteObject implements IRegistry {

    private Fonctionnalite registry;

    public Registry(int numport) throws RemoteException{
        super(numport);
        registry = new Fonctionnalite();
    }

    public Registry() throws RemoteException{
        super();
        registry = new Fonctionnalite();
    }

    @Override
    public void bind(String name, Serializable obj) throws NotSerializableException, AlreadyBoundException {
        if ((obj instanceof Serializable) || (obj instanceof Externalizable)){
            registry.add(name, obj);
        } else {
            throw new NotSerializableException("Not serializable");
        }
    }

    @Override
    public void rebind(String name, Serializable obj) throws NotSerializableException {
        if ((obj instanceof Serializable) || (obj instanceof Externalizable)){
            registry.addForce(name, obj);
        } else {
            throw new NotSerializableException("Not serializable");
        }
    }

    @Override
    public Serializable lookup(String name) {
        return registry.get(name);
    }

    public static void main(String[] args) {
        try {
            if (System.getSecurityManager() == null) {
                System.setSecurityManager(new RMISecurityManager());
            }
            Registry serv = new Registry(5000);
            Naming.rebind("rmi://localhost:1098/my_registry", serv);

            System.out.println("Registry ready");
        } catch (RemoteException | MalformedURLException e){
            e.printStackTrace();
        }
    }

}