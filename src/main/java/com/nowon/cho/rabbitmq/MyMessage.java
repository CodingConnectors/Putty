package com.nowon.cho.rabbitmq;

import lombok.Data;

@Data
public class MyMessage {

	private String key;
	private String content;
}
