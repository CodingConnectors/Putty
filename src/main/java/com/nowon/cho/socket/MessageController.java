package com.nowon.cho.socket;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import com.nowon.cho.rabbitmq.MyMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MessageController {

	
	private final RabbitTemplate rabbitTemplate;
	
	@Value("${spring.rabbitmq.template.exchange}")
	private String exchange;
	@Value("${spring.rabbitmq.template.routing-key}")
	private String routingKey;
	
	// /message/hello
	@MessageMapping("/hello")
	@SendTo("/topic/greetings") //js에서 구독하는 구독자 메세지 전송
	public GreetingDTO hello(Authentication auth,HelloDTO dto) {
		String content = auth==null?"어서오세요":auth.getName()+"님 환영합니다!";
		return new GreetingDTO(content);
	}
	// /message/question
	@MessageMapping("/question")
	public void question(MyMessage message) {
//		System.out.println(message);
		rabbitTemplate.convertAndSend(exchange, routingKey, message);
		// {key:11111, content:"메세지"}
	}
}
