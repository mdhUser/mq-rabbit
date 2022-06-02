package org.maxwell.publisher;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: Maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/6/1 22:55
 */
@SpringBootTest
public class SpringAmqpTest {


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void testBasicQueue() {

        /**
         * 1.队列名称
         * 2.消息
         */
        rabbitTemplate.convertAndSend("simple.queue", "hello spring amqp test!");

    }

    @Test
    void testWorkQueue() throws InterruptedException {
        String message = "工作消息队列发送__";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend("work.queue", message + i);
            TimeUnit.MILLISECONDS.sleep(20);
        }
    }


    @Test
    void FanoutQueue() {

        rabbitTemplate.convertAndSend("itcast.fanout", "", "fanout 模式 消息发送");

    }

    @Test
    void DirectQueue() {
        String message = "浦东某小区再次复阳 呜呜呜~~~~";
        rabbitTemplate.convertAndSend("itcast.direct", "red", message);
    }

    @Test
    void TopicQueue() {
        String message = "去日本啪啪啪 ~~~~";
        rabbitTemplate.convertAndSend("itcast.topic", "fu.cm.aqdlt.love.fuck", message);
    }


    @Test
    void testObj() {

        Map<String, Object> map = new HashMap<String, Object>() {
            {
                put("age", 23);
                put("name", "Maxwell");
            }
        };
        rabbitTemplate.convertAndSend("simple.queue", map);

    }

}
