package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class Receiver {

	 @Autowired
	 private CountDownLatch CountDownLatch;

	 @Value("${queueName1}")
	 private String queueName1;
	 
	 @Value("${queueName2}")
	 private String queueName2;
	 
	 @RabbitListener(queues="${queueName1}")
	 public void receive1(String in) throws InterruptedException {
	        receiveMessage(in, queueName1);
	  }
	 
	 @RabbitListener(queues="${queueName2}")
	 public void receive2(String in) throws InterruptedException {
	        receiveMessage(in, queueName2);
	  }
	 
    public void receiveMessage(String message,String queueName) {
        System.out.println("=====================   Received on "+queueName+": " + message + "   ======================");
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CountDownLatch.countDown();
    }
}
