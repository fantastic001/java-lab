package pl.raszkowski.examples;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.jms.DeliveryMode;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("message")
public class MessageSender {

    private static final Logger LOG = LoggerFactory.getLogger(MessageSender.class);

    @Inject
    @JMSConnectionFactory("java:jboss/DefaultJMSConnectionFactory")
    private JMSContext jmsContext;

    @Resource(mappedName = "java:jboss/exported/jms/queue/ExampleQueue")
    private Queue queue;

    @GET
    @Path("send")
    @Produces(MediaType.TEXT_PLAIN)
    public String sendMessage(@QueryParam("message") String messageContent) {
        LOG.info("Sending new message = {}.", messageContent);

        JMSProducer jmsProducer = jmsContext.createProducer();
        jmsProducer.setDeliveryMode(DeliveryMode.PERSISTENT);
        jmsProducer.send(queue, messageContent);

        return "Message has been sent!";
    }
}
