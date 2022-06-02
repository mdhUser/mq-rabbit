package org.maxwell.consumer.listener;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONUtil;
import org.json.JSONString;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/6/1 23:02
 */
@Slf4j
@Component
public class SpringAmqpListener {

    @RabbitListener(queues = "work.queue")
    public void testBasicQueue(String msg) throws InterruptedException {
        log.info("amqp消费者-1接收消息：{}", msg);
        TimeUnit.MILLISECONDS.sleep(20);
    }


    @RabbitListener(queues = "work.queue")
    public void testBasicQueue1(String msg) throws InterruptedException {
        log.info("amqp消费者-2接收消息：{}", msg);
        TimeUnit.MILLISECONDS.sleep(100);
    }

    @RabbitListener(queues = "fanout.queue1")
    public void testFanoutQueue1(String msg) throws InterruptedException {
        log.info("fanout消费者-1接收消息：{}", msg);
    }


    @RabbitListener(queues = "fanout.queue2")
    public void testFanoutQueue2(String msg) throws InterruptedException {
        log.info("fanout消费者-2接收消息：{}", msg);
    }

    /**
     *  direct 模式
     * @param msg
     * @throws InterruptedException
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue1"),
            exchange = @Exchange(value = "itcast.direct"),
            key = {"red","blue"}
    ))
    public void testDirectQueue(String msg) throws InterruptedException {
        log.info("direct消费者-1接收消息：{}", msg);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct"),
            key = {"red","yellow"}
    ))
    public void testDirectQueue1(String msg) throws InterruptedException {
        log.info("direct消费者-2接收消息：{}", msg);
    }


    /**
     * topic 模式
     * @param msg
     */
    @RabbitListener(queues = "topic.queue1")
    public void testTopicQueue(String msg){
        log.info("topic消费者-1接收消息：{}",msg);
    }

    @RabbitListener(queues = "topic.queue2")
    public void testTopicQueue1(String msg){
        log.info("topic消费者-2接收消息：{}",msg);
    }


    @RabbitListener(queues = "simple.queue")
    public void testJson2Object(Map<String,Object> map){
        log.info("消费者接收对象:{}",map);
    }
}
