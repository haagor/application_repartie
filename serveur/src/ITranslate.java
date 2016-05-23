import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Set;


public interface ITranslate extends Remote {

    Set<String> getTranslation(String word) throws RemoteException;
    void addTranslation(String word, String translation) throws RemoteException;
    String subscribe(String id) throws RemoteException;

    }