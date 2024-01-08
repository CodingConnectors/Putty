package com.nowon.cho.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitListener {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;
	
	public void receiveMessage(MyMessage message) {
//		System.out.println(">>>수신된 메세지: "+message);
		String content=message.getContent();
		if(content.contains("안녕")) {
			message.setContent("반갑습니다!"+message.getKey()+"님!");
		}
		//구독하는 클라이언트에게 메세지 보내기
		simpMessagingTemplate.convertAndSend("/topic/question/"+message.getKey(), message);
	}
}