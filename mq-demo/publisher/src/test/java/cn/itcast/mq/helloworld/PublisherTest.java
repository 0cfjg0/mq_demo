package cn.itcast.mq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;
import org.springframework.validation.support.BindingAwareConcurrentModel;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.concurrent.TimeoutException;

public class PublisherTest {
    @Test
    public void testSendMessage() throws IOException, TimeoutException, InterruptedException {
        // 1.建立连接
        ConnectionFactory factory = new ConnectionFactory();
        // 1.1.设置连接参数，分别是：主机名、端口号、vhost、用户名、密码
        factory.setHost("192.168.200.128");
        factory.setPort(5672);
        factory.setVirtualHost("/");
        factory.setUsername("cfjg");
        factory.setPassword("ljymqpwd");
        // 1.2.建立连接
        Connection connection = factory.newConnection();

        // 2.创建通道Channel
        Channel channel = connection.createChannel();

        // 3.创建队列
		/*
            声明队列
                参数1：队列的名称
                参数2：队列是否支持持久化 fallse：不持久化处理
                参数3：队列是否排它：是否允许其它的connection下的channel连接
                参数4：是否空闲时自动删除
         */
        String queueName = "simple.queue";
        channel.queueDeclare(queueName, false, false, false, null);

        // 4.发送消息
        String message = "hello, rabbitmq!";
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println("发送消息成功：【" + message + "】");

        // 5.关闭通道和连接
        channel.close();
        connection.close();

    }
}
