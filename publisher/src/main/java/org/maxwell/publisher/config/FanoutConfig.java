package org.maxwell.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Maxwell
 * @email: maodihui@foxmail.com
 * @date: 2022/6/1 16:11
 */
@Configuration
public class FanoutConfig {

    /**
     * 1队列
     *
     * @return
     */
    @Bean
    public Queue fanoutQueue() {
        return new Queue("fanout.queue1");
    }

    /**
     * fanout 交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("itcast.fanout");
    }

    /**
     * 1队列与交换机绑定
     *
     * @param fanoutQueue
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding fanoutBinding(Queue fanoutQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue).to(fanoutExchange);
    }

    /**
     * 2队列
     *
     * @return
     */
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanout.queue2");
    }

    /**
     * 2队列与交换机绑定
     *
     * @param fanoutQueue1
     * @param fanoutExchange
     * @return
     */
    @Bean
    public Binding fanoutBinding1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }



}
