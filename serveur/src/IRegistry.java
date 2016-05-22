import java.io.NotSerializableException;
import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IRegistry extends Remote {

    void bind(String name, Serializable obj) throws RemoteException, NotSerializableException, AlreadyBoundException;
    void rebind(String name, Serializable obj) throws RemoteException, NotSerializableException;
    Serializable lookup(String name) throws RemoteException;

}