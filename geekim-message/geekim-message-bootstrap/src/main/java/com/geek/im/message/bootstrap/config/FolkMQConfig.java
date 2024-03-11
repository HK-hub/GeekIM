package com.geek.im.message.bootstrap.config;

import com.geek.im.message.infrastructure.annotation.MessageConsumer;
import org.noear.folkmq.FolkMQ;
import org.noear.folkmq.client.MqClient;
import org.noear.folkmq.client.MqConsumeHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * @author : HK意境
 * @ClassName : FolkMQConfig
 * @date : 2024/3/10 20:07
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Component
@ConditionalOnProperty(name = "geek.im.message.using.type", havingValue = "folkmq")
public class FolkMQConfig implements ApplicationContextAware, InitializingBean {

    @Value("${folkmq.server}")
    private String serverUrl;

    @Value("${folkmq.consumerGroup}")
    private String consumerGroup;

    private ApplicationContext applicationContext;


    /**
     * 获取所有实现 MqConsumerHandler 接口的消息消费者
     *
     * @return
     */
    public Map<String, MqConsumeHandler> getMessageHandlers() {
        return applicationContext.getBeansOfType(MqConsumeHandler.class);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.registerMqClient();
    }


    private void registerMqClient() throws IOException {

        MqClient mqClient = FolkMQ.createClient(this.serverUrl)
                //.config(c -> c.metaPut("ak", "demo").metaPut("sk", "pLtqej89"))
                .nameAs(consumerGroup)
                .connect();

        Map<String, MqConsumeHandler> messageHandlerMap = this.getMessageHandlers();
        // 获取Bean上的注解
        for (MqConsumeHandler consumer : messageHandlerMap.values()) {
            Class<? extends MqConsumeHandler> consumerClass = consumer.getClass();
            MessageConsumer annotation = consumerClass.getAnnotation(MessageConsumer.class);
            if (Objects.isNull(annotation)) {
                throw new RuntimeException("消息消费者订阅topic不能为空!");
            }

            // 获取订阅主题
            String topic = annotation.topic();
            // 注册订阅关系
            mqClient.subscribe(topic, consumer);
        }

        // 注册mqClient进入容器
        //将applicationContext转换为ConfigurableApplicationContext
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) applicationContext;

        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) configurableApplicationContext.getAutowireCapableBeanFactory();

        //动态注册bean.
        defaultListableBeanFactory.registerSingleton("mqClient", mqClient);
    }

}
