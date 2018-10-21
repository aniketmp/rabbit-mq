package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

public class Receiver {

	 @Value("${queueName1}")
	 private String queueName1;
	 
	 @Value("${queueName2}")
	 private String queueName2;
	 
	 @RabbitListener(queues="${queueName1}")
	 public int receive1(@Payload String payload,int in) throws InterruptedException {
		 System.out.println("Payload:"+payload);
	       return receiveMessage(in, queueName1);
	  }
	 
	 @RabbitListener(queues="${queueName2}")
	 public int receive2(@Payload String payload,int in) throws InterruptedException {
		 System.out.println("Payload:"+payload);
	       return receiveMessage(in, queueName2);
	  }
	 
    public int receiveMessage(int no,String queueName) {
        System.out.println("=====================   Received on "+queueName+": " + no + "   ======================");
        return no*10;
    }
}
