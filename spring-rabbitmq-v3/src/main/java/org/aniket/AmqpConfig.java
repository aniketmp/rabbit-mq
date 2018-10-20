package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {
	@Value("${queueName1}")
	private String queueName1;
	
	@Value("${queueName2}")
	private String queueName2;
	
	@Value("${exchangeName}")
	private String exchangeName;
	
	@Value("${noOfMsg}")
	private int noOfMsg;
	
	@Bean
    public Queue getQueue1() {
        return new Queue(queueName1);
    }
	
	@Bean
    public Queue getQueue2() {
        return new Queue(queueName2);
    }
	
	@Bean
    public CountDownLatch getLatch() {
	    return new CountDownLatch(noOfMsg*2); //Since each queue is receiving copy of same message.
    }
	
	@Bean
    public Receiver getReceiver() {
        return new Receiver();
    }
	
	@Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(exchangeName);
    }
	
	 @Bean
     public Binding binding1(FanoutExchange fanout,
         Queue getQueue1) {
         return BindingBuilder.bind(getQueue1).to(fanout);
     }

     @Bean
     public Binding binding2(FanoutExchange fanout,
         Queue getQueue2) {
         return BindingBuilder.bind(getQueue2).to(fanout);
     }
}
