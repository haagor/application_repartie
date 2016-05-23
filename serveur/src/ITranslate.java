import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;


public interface ITranslate extends Remote {

    List<String> getTranslation(String word) throws RemoteException;
    void addTranslation(String word, String translation) throws RemoteException;

}