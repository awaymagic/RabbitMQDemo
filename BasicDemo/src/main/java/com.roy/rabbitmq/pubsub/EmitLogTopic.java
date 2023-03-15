package com.roy.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.roy.rabbitmq.RabbitMQUtil;

public class EmitLogTopic {

	private static final String EXCHANGE_NAME = "topicExchange";

	/**
	 * exchange有四种类型， fanout topic headers direct
	 * topic 类型的 exchange 在根据 routingKey 转发消息时，可以对 routingKey 做一定的规则，比如 anonymous.info 可以被 *.info 匹配到
	 * 通配符，把消息交给符合routing pattern（路由模式） 的队列
	 */
	public static void main(String[] args) throws Exception{
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();
		// 发送者只管往exchange里发消息，而不用关心具体发到哪些queue里
		channel.exchangeDeclare(EXCHANGE_NAME, "topic");
		String message = "LOG INFO";
		channel.basicPublish(EXCHANGE_NAME, "anonymous.info", null, message.getBytes());
		channel.basicPublish(EXCHANGE_NAME, "tuling.loulan.debug", null, message.getBytes());

		channel.close();
		connection.close();
	}
}
