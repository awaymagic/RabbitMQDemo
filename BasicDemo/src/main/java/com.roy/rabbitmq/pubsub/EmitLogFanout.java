package com.roy.rabbitmq.pubsub;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.roy.rabbitmq.RabbitMQUtil;

/**
 * @author guowei
 *
 * Fanout：广播，将消息交给所有绑定到交换机的队列
 */
public class EmitLogFanout {

	private static final String EXCHANGE_NAME = "fanoutExchange";

	/**
	 * exchange有四种类型， fanout topic headers direct
	 * fanout 类型的 exchange: 会往其上绑定的所有 queue 转发消息
	 */
	public static void main(String[] args) throws Exception{
		Connection connection = RabbitMQUtil.getConnection();
		Channel channel = connection.createChannel();
		// 发送者只管往exchange里发消息，而不用关心具体发到哪些queue里
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String message = "LOG INFO 222";
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		
		channel.close();
		connection.close();
	}
}
