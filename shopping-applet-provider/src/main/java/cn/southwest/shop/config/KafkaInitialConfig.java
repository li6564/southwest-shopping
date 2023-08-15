package cn.southwest.shop.config;

import cn.southwest.shop.properties.ShopKafkaTopicProperties;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author meteor
 */
@Configuration
public class KafkaInitialConfig {

    private ShopKafkaTopicProperties topicProperties;

    @Autowired
    public KafkaInitialConfig(ShopKafkaTopicProperties topicProperties) {
        this.topicProperties = topicProperties;
    }

    /**
     * 创建DataItemChangeTopic topic
     * @return
     */
    @Bean
    public NewTopic initialDataItemChangeTopic() {
        return new NewTopic(topicProperties.getDataItemChange(),16, (short) 1 );
    }

}
