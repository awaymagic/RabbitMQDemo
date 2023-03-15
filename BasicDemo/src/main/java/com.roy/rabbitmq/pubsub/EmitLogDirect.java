package com.roy.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.roy.rabbitmq.RabbitMQUtil;

public class EmitLogDirect {

	private static final String EXCHANGE_NAME = "directExchange";

	/**
	 * exchange有四种类型， fanout topic headers direct
	 * direct 类型的exchange 会根据 routingKey，将消息转发到该 exchange 上绑定了该 routingKey 的所有queue
	 * 定向，把消息交给符合指定routing key 的队列
	 */
	public static void main(String[] args) throws Exception{
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();
		//发送者只管往exchange里发消息，而不用关心具体发到哪些queue里。
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		String message = "LOG INFO 44444";
		// channel.basicPublish(EXCHANGE_NAME, "info", null, message.getBytes());
		channel.basicPublish(EXCHANGE_NAME, "debug", null, message.getBytes());
		// channel.basicPublish(EXCHANGE_NAME, "warn", null, message.getBytes());

		channel.close();
		connection.close();
	}
}
