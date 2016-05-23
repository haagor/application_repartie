import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;


public class ClientJMS implements javax.jms.MessageListener{

    private javax.jms.Connection connect = null;
    private javax.jms.Session receiveSession = null;
    InitialContext context = null;

    public void configurer() {

        try {

            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY,
                    "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
            properties.put(Context.PROVIDER_URL, "tcp://localhost:61616");

            context = new InitialContext(properties);

            javax.jms.ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
            connect = factory.createConnection();
            receiveSession = connect.createSession(false,javax.jms.Session.AUTO_ACKNOWLEDGE);
            connect.start();
        } catch (javax.jms.JMSException jmse){
            jmse.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void configurerConsommateur(String id) throws JMSException, NamingException{
        Queue queue = (Queue) context.lookup(id);
        javax.jms.MessageConsumer qReceiver = receiveSession.createConsumer(queue);
        qReceiver.setMessageListener(this);
    }

    @Override
    public void onMessage(Message message) {
        try {
            System.out.println("Recu : " + ((TextMessage) message).getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
