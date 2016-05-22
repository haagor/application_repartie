import java.io.Serializable;
import java.rmi.AlreadyBoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Fonctionnalite {

    //(nom, objet)
    private static HashMap<String, Serializable> map = new HashMap<>();

    //liste de nom d'objet range par ordre d'ajout
    private static List<String> keys = new ArrayList<>();

    public void add(String key, Serializable obj) throws AlreadyBoundException {
        if(map.containsKey(key)){
            throw new AlreadyBoundException(key + " existe deja");
        }
        map.put(key, obj);
        if (keys.contains(key)){
            keys.remove(key);
        }
        keys.add(key);
    }

    public void addForce(String key, Serializable obj){
        map.put(key, obj);
        if (keys.contains(key)){
            keys.remove(key);
        }
        keys.add(key);
    }

    public Serializable get(String key){
        if(map.containsKey(key)){
            return map.get(key);
        }

        return null;
    }

    public void remove(String key){
        if (map.containsKey(key)){
            map.remove(key);
            keys.remove(key);
        }
    }

    public String[] list(){
        String[] entries = new String[map.size()];
        for (int i = 0; i < keys.size(); ++i){
            entries[i] = keys.get(i);
        }

        return entries;
    }

    public boolean isService(String key){
        String serviceKey = "/" + key;
        if (keys.contains(serviceKey)) {
            return true;
        } else if (keys.contains(key)){
            return false;
        }
        return false;
    }

    public static HashMap<String, Serializable> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, Serializable> map) {
        Fonctionnalite.map = map;
    }

    public static List<String> getKeys() {
        return keys;
    }

    public static void setKeys(List<String> keys) {
        Fonctionnalite.keys = keys;
    }
}