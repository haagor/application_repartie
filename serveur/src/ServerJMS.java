import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.HashMap;
import java.util.Hashtable;


public class ServerJMS {

    private javax.jms.Connection connect = null;
    private javax.jms.Session sendSession = null;
    private javax.jms.Session receiveSession = null;
    private HashMap<String, javax.jms.MessageProducer> producers = null;
    private javax.jms.Queue queue = null;
    InitialContext context = null;

    public ServerJMS() {
        producers = new HashMap<String, javax.jms.MessageProducer>();
    }

    public void configurer() {
        try {
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();
            sendSession = connect.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);
            connect.start(); // on peut activer la connection.
        } catch (javax.jms.JMSException jmse){
            jmse.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public String configurerProducteur(String id) throws JMSException, NamingException{
        String res = "dynamicQueues/" + id;
        if (producers.get(id) == null) {
            Queue queue = (Queue) context.lookup(res);
            producers.put(id, sendSession.createProducer(queue));
        }
        return res;
    }

    public void sendMessage(String message) throws JMSException{
        TextMessage msg = sendSession.createTextMessage();
        msg.setText(message);
        for (MessageProducer p : producers.values()){
            p.send(msg);
        }
    }

}