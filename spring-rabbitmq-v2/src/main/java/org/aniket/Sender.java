package org.aniket;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Sender implements CommandLineRunner{
	@Autowired
    private RabbitTemplate rabbitTemplate;
    
    @Autowired
    private Queue queue;
     
    @Autowired
    private CountDownLatch countDownLatch;
    
    @Value("${noOfMsg}")
    private int noOfMsg;
    
    @Value("${message}")
	private String message;

	@Override
	public void run(String... args) throws Exception {
		  System.out.println("=======================================   Sending message   =======================================");	
		  for(int i=0;i<noOfMsg;i++)
		  {
			  rabbitTemplate.convertAndSend(queue.getName(),message+""+i);  
		  }		
	      countDownLatch.await();
	}
}
