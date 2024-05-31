package cn.itcast.mq.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


//TODO:是一个测试启动器，可以加载SpringBoot测试注解,让测试方法在Spring容器环境下执行
@RunWith(SpringRunner.class)
//TODO:目的是加载ApplicationContext，启动spring容器
@SpringBootTest
public class SpringAmqpTest {
    //自动装配RabbitTemplate模板对象
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * Basic Queue 简单队列模型
     */
    @Test
    public void testSimpleQueue() {
        String queueName = "simple.queue";
        String message = "hello,spring amqp";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    /**
     * workQueue
     * 向队列中不停发送消息，模拟消息堆积。
     */
    @Test
    public void testWorkQueue() throws InterruptedException {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "hello, message_";
        for (int i = 0; i < 50; i++) {
            // 发送消息
            rabbitTemplate.convertAndSend(queueName, message + i);
            Thread.sleep(20);
        }
    }
    /**
     * Fanout Exchange：广播
     */
    @Test
    public void testFanoutExchange() {

    }
    /**
     * Direct Exchange：路由
     */
    @Test
    public void testSendDirectExchange() {

    }

    /**
     * Topic Exchange：主题
     */
    @Test
    public void testSendTopicExchange() {

    }

    /**
     *  消息转换器
     *
     */
    @Test
    public void testSendMap() throws InterruptedException {

    }
}
