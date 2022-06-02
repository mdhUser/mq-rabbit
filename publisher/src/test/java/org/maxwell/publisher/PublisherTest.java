package org.maxwell.publisher;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description:
 * @author: Maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/6/1 20:56
 */
public class PublisherTest {

    @Test
    public void testSendMessage() throws IOException, TimeoutException {

        //创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("47.107.226.181");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("itcast");
        factory.setPassword("itcast142");

        //建立连接
        Connection connection = factory.newConnection();

        //建立通道
        Channel channel = connection.createChannel();

        //创建队列
        String queueName = "work.queue";
        channel.queueDeclare(queueName, false, false, false, null);

        //4.发送消息
        String message = "hello,rabbitmq!";
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println("发送消息成功：【" + message + "】");

        //关闭通道和连接
        channel.close();
        connection.close();

    }


}
