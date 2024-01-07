package com.nowon.cho.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
@EnableRabbit
public class MyrabbitConfig {
	
	private final ConnectionFactory connectionFactory;

	@Value("${spring.rabbitmq.template.default-receive-queue}")
	private String queueName;
	@Value("${spring.rabbitmq.template.exchange}")
	private String topicExchangeName;
	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;
	
	//RabbitMQ메세지를 수신하기 위한 리스너 컨테이너를 생성
	//RabbitMQ와 통신에 필요한 리스너 컨테이너를 구성하고 관리할 수 있습니다.
	//1. 리스너 컨테이너의 스레드 관리를 담당. 리스너의 동작을 제어하고, 스레드 풀을 관리하여 메세지 처리의 성능과 확장성을 조절할 수 있음
	//2. 커스텀한 설정을 적용할 수 있음. 예를들어, 메세지변환기(messageConverter), Acknowledge 모드, 스레드 풀 사이즈 등
	//3. 여러 개의 리스너를 동시에 처리할 수 있는 컨테이너를 생성할 수 있다. 각 리스너는 별도의 설정을 가질 수 있고,
	//SimpleRabbitListenerContainerFactory를 사용하여 각 리스너의 독립적인 환경을 구성할 수 있다.
	@Bean
    SimpleRabbitListenerContainerFactory myFactory(SimpleRabbitListenerContainerFactoryConfigurer configurer) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        connectionFactory = getCustomConnectionFactory();
//        System.out.println(">>>>>>>>connectionFactory: "+connectionFactory);
//        factory.setMessageConverter(messageConverter());
        configurer.configure(factory, connectionFactory);
        return factory;
    }

	//Jackson2JsonMessageConverter
	//Spring의 Jackson라이브러리를 사용하여 객체를 JSON형식으로 직렬화하고 역직렬화하는 Messageconverter구현체
	//MessageConverter는 RabbitMQ와의 통신에서 자동으로 적용되어 메세지의 직렬화 및 역직렬화 처리해준다.
	@Bean
	MessageConverter messageConverter() {
		return new Jackson2JsonMessageConverter();
	}

	
	  @Bean
	  Queue queue() {
	    return new Queue(queueName, false);
	  }
	
	  @Bean
	  TopicExchange exchange() {
	    return new TopicExchange(topicExchangeName);
	  }
	
	  @Bean
	  Binding binding(Queue queue, TopicExchange exchange) {
	    return BindingBuilder.bind(queue).to(exchange).with(routingKey);
	  }
	  
	  @Bean
	  MessageListenerAdapter listenerAdapter(RabbitListener receiver,MessageConverter messageConverter) {
		  MessageListenerAdapter listenerAdapter=new MessageListenerAdapter(receiver, "receiveMessage");
		  listenerAdapter.setMessageConverter(messageConverter);
		  return listenerAdapter;
	  }
	  
	  @Bean
	  SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
	      MessageListenerAdapter listenerAdapter) {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
	    container.setConnectionFactory(connectionFactory);
	    container.setQueueNames(queueName);
	    container.setMessageListener(listenerAdapter);
	    return container;
	  }

}
