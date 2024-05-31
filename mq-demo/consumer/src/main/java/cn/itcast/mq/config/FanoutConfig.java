package cn.itcast.mq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {
    /**
     * 声明交换机
     * @return Fanout类型交换机
     */
    @Bean//@Bean注解特点将修饰方法返回值放到IOC容器中，方法名fanoutExchange作为bean对象的key
    public FanoutExchange fanoutExchange(){
        //交换机名是：itcast.fanout
        return new FanoutExchange("itcast.fanout");
    }

    /**
     * 第1个队列
     */
    @Bean
    public Queue fanoutQueue1(){
        //fanout.queue1表示队列名
        return new Queue("fanout.queue1");
    }

    /**
     * 绑定队列和交换机
     * TODO:
     *  1.Queue fanoutQueue1 : fanoutQueue1表示IOC容器中Queue的bean的对应的key
     *  2.FanoutExchange fanoutExchange:fanoutExchange表示IOC容器中FanoutExchange的bean的对应的key是上述方法
     *          public FanoutExchange fanoutExchange(){}的方法名
     */
    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        //绑定第一个队列到fanoutExchange交换机上
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    /**
     * 第2个队列
     */
    @Bean
    public Queue fanoutQueue2(){
        //fanout.queue2 表示队列名
        return new Queue("fanout.queue2");
    }

    /**
     * 绑定队列和交换机
     */
    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        //绑定第2个队列到fanoutExchange交换机上
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}