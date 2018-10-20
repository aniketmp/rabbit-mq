package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
	@Value("${queueName}")
	private String queueName;
	
	@Value("${noOfMsg}")
	private int noOfMsg;
	
	@Bean
    public Queue getQueue() {
        return new Queue(queueName);
    }
	
	@Bean
    public CountDownLatch getLatch() {
        return new CountDownLatch(noOfMsg);
    }
	
	@Bean
    public Receiver getReceiver1() {
		Receiver receiver=new Receiver();
		receiver.setReceiverName("Receiver_1");
        return receiver;
    }
	
	@Bean
    public Receiver getReceiver2() {
		Receiver receiver=new Receiver();
		receiver.setReceiverName("Receiver_2");
        return receiver;
    }
}
