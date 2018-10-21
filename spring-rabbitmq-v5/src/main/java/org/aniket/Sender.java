package org.aniket;

import org.springframework.amqp.core.DirectExchange;
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
    private DirectExchange exchange;
     
    @Value("${noOfMsg}")
    private int noOfMsg;
    
	@Override
	public void run(String... args) throws Exception {
		 System.out.println("=================================   Sending message   ==================================");	
		  for(int i=0;i<noOfMsg;i++)
		  {
			  int res=0;
			  if(i%2==0)
			  {
				  res=(Integer) rabbitTemplate.convertSendAndReceive(exchange.getName(),"even",i);  
			  }
			  else
			  {				  
				  res=(Integer)rabbitTemplate.convertSendAndReceive(exchange.getName(),"odd",i); 
			  }
			  System.out.println("Result:"+res);
		  }		
	}
}
