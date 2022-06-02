package org.maxwell.publisher.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/6/2 0:56
 */
@Configuration
public class TopicConfig {

    /**
     * topic 交换机
     * @return
     */
    @Bean
    public TopicExchange top() {
        return new TopicExchange("itcast.topic");
    }

    /**
     *  队列1
     * @return
     */
    @Bean
    public Queue topicQueue() {
        return new Queue("topic.queue1");
    }


    @Bean
    public Binding topicBinding(Queue topicQueue, TopicExchange top) {
        return BindingBuilder.bind(topicQueue).to(top).with("fu.#");
    }


    /**
     * 队列2
     *
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        return new Queue("topic.queue2");
    }


    @Bean
    public Binding topicBinding1(Queue topicQueue1, TopicExchange top) {
        return BindingBuilder.bind(topicQueue1).to(top).with("#.fuck");
    }


}
