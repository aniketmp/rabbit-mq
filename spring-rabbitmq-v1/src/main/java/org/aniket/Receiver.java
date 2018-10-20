package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RabbitListener(queues="${queueName}")
@Component
public class Receiver {

	 @Autowired
	 private CountDownLatch CountDownLatch;
	
	@RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("=========================   Received: " + message + "   ===========================");
        CountDownLatch.countDown();
    }
}
