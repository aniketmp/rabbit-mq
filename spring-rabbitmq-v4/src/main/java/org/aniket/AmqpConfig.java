package org.aniket;

import java.util.concurrent.CountDownLatch;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
	    return new CountDownLatch(noOfMsg); //Since each queue is receiving only his message, not copy.
    }
	
	@Bean
    public Receiver getReceiver() {
        return new Receiver();
    }
	
	@Bean
    public DirectExchange direct() {
        return new DirectExchange(exchangeName);
    }
	
	 @Bean
     public Binding binding1(DirectExchange direct,
         Queue getQueue1) {
         return BindingBuilder.bind(getQueue1).to(direct).with("odd");
     }

     @Bean
     public Binding binding2(DirectExchange direct,
         Queue getQueue2) {
         return BindingBuilder.bind(getQueue2).to(direct).with("even");
     }
}
