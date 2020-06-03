package pl.raszkowski.examples;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@MessageDriven(mappedName="ExampleQueue", activationConfig =  {
        @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge"),
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue="java:jboss/exported/jms/queue/ExampleQueue"),
})
public class ExampleMDB implements MessageListener {

    private static final Logger LOG = LoggerFactory.getLogger(ExampleMDB.class);

    @Resource
    private MessageDrivenContext context;

    @Override
    public void onMessage(Message message) {
        LOG.info("Processing new message...");

        try {
            String messageContent = message.getBody(String.class);

            LOG.info("Message = {}.", messageContent);
        } catch (JMSException e) {
            context.setRollbackOnly();
        }
    }
}
