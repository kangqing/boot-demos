package com.yunqing.direct;

import org.springframework.amqp.rabbit.AsyncRabbitTemplate;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author kangqing
 * @since 2023/6/30 14:28
 */
@Component
public class Demo01Producer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate() {
        SimpleMessageListenerContainer smc = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
        smc.setQueueNames(Demo01Message.QUEUE);
        AsyncRabbitTemplate async = new AsyncRabbitTemplate(rabbitTemplate, smc);
        return async;
    }

    @Resource
    private AsyncRabbitTemplate asyncRabbitTemplate;

    /**
     * 同步发送
     * 指定 Exchange + RoutingKey ，从而路由到一个 Queue 中。
     */
    public void syncSend(Integer id, String str) {
        // 创建 Demo01Message 消息
        final Demo01Message<String> message = new Demo01Message<>();
        message.setId(id);
        message.setData(str);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.EXCHANGE, Demo01Message.ROUTING_KEY, message);
    }

    /**
     * 这里我们传入的 RoutingKey 为队列名？！因为 RabbitMQ 有一条默认的 Exchange: (AMQP default) 规则：
     * 默认交换器，隐式地绑定到每个队列，路由键等于队列名称
     * 所以，此处即使我们传入的 RoutingKey 为队列名，一样可以发到对应队列
     * @param id
     */
    public void syncSendDefault(Integer id, String str) {
        // 创建 Demo01Message 消息
        Demo01Message<String> message = new Demo01Message<>();
        message.setId(id);
        message.setData(str);
        // 同步发送消息
        rabbitTemplate.convertAndSend(Demo01Message.QUEUE, message);
    }

    //确认机制
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        /**
         * correlationData: 回调的相关数据，包含了消息ID
         * ack: ack结果，true代表ack，false代表nack
         * cause: 如果为nack，返回原因，否则为null
         */
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            System.err.println("correlationData: " + correlationData);
            System.err.println("ack: " + ack);
            if(!ack){
                //做一些补偿机制等
                System.err.println("异常处理....");
            }
        }
    };
    //返回机制
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                    String exchange, String routingKey) {
            System.err.println("return exchange: " + exchange + ", routingKey: "
                    + routingKey + ", replyCode: " + replyCode + ", replyText: " + replyText);
        }
    };

    //发送消息方法调用: 构建Message消息
    public void asyncSendMessage(Integer id, String msg) throws Exception {
        Demo01Message<String> message = new Demo01Message<>();
        message.setId(id);
        message.setData(msg);
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);
        //id + 时间戳 ，保证全局唯一 ，这个是实际消息的ID
        //在做补偿性机制的时候通过ID来获取到这条消息进行重发
        //CorrelationData correlationData = new CorrelationData(String.valueOf(id));
        //exchange, routingKey, object, correlationData
        //rabbitTemplate.convertSendAndReceive(DefaultExange, topic,  msg, correlationData);

        asyncRabbitTemplate.convertSendAndReceive(Demo01Message.EXCHANGE, Demo01Message.ROUTING_KEY,  message);
    }

    @Bean
    public AsyncRabbitTemplate asyncRabbitTemplate(RabbitTemplate rabbitTemplate) {
        AsyncRabbitTemplate asyncRabbitTemplate = new AsyncRabbitTemplate(rabbitTemplate);
        asyncRabbitTemplate.setReceiveTimeout(10000);

        return asyncRabbitTemplate;
    }

}
