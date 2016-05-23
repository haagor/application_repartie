import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Translate extends UnicastRemoteObject implements ITranslate, Serializable {

    Map<String, List<String>> dict;

    public Translate() throws RemoteException {
        dict = new HashMap<>();
        dict.put("fog", new ArrayList<>(Arrays.asList("brouillard")));
        dict.put("monk", new ArrayList<>(Arrays.asList("moine")));
        dict.put("legacy", new ArrayList<>(Arrays.asList("heritage", "patrimoine")));
    }

    @Override
    public List<String> getTranslation(String word) {
        return dict.get(word);
    }

    @Override
    public void addTranslation(String word, String translation) {
        List<String> list = dict.get(word);
        if (list == null) {
            List l = new ArrayList<String>();
            l.add(translation);
            dict.put(word,l);
        } else {
            list.add(translation);
        }
    }
}