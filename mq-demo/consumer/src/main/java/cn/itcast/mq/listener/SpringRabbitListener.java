package cn.itcast.mq.listener;


import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


//将当前类放到SpringIOC容器中
@Component
public class SpringRabbitListener {
    //监听队列
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String msg){
        System.out.println("spring 消费者接收到消息：【\" + msg + \"】");
    }
}
