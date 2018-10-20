package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RabbitListener(queues="${queueName}")
public class Receiver {

	 @Autowired
	 private CountDownLatch CountDownLatch;
	 private String receiverName;
	
	@RabbitHandler
    public void receiveMessage(String message) {
        System.out.println("=====================   Received by "+receiverName+": " + message + "   ======================");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CountDownLatch.countDown();
    }

	public void setReceiverName(String receiverName) {
		this.receiverName=receiverName;
	}
}
