import javax.jms.JMSException;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;


public class Translate extends UnicastRemoteObject implements ITranslate, Serializable {

    Map<String, Set<String>> dict;
    ServerJMS jms;

    public Translate() throws RemoteException {
        dict = new HashMap<>();
        dict.put("fog", new HashSet<>(Arrays.asList("brouillard")));
        dict.put("monk", new HashSet<>(Arrays.asList("moine")));
        dict.put("legacy", new HashSet<>(Arrays.asList("heritage", "patrimoine")));
        jms = new ServerJMS();
        jms.configurer();
    }

    @Override
    public Set<String> getTranslation(String word) {
        return dict.get(word);
    }

    @Override
    synchronized public void addTranslation(String word, String translation) {
        Set<String> translations = dict.get(word);
        if (translations == null) {
            Set<String> s = new HashSet<String>();
            s.add(translation);
            dict.put(word,s);
            try {
                jms.sendMessage("maintenant je sais traduire " + word);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        } else {
            try {
                jms.sendMessage("traduction mise a jour " + word);
            } catch (JMSException e) {
                e.printStackTrace();
            }
            translations.add(translation);
        }
    }

    public String subscribe(String id) throws RemoteException {
        String res = null;
        try {
            res = jms.configurerProducteur(id);
        } catch (NamingException | JMSException e) {
            e.printStackTrace();
        }
        return res;
    }
}