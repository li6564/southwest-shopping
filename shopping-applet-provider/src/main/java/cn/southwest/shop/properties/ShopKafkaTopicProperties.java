package cn.southwest.shop.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author meteor
 */
@Data
@Component
@ConfigurationProperties(prefix = "shop.kafka.topics")
public class ShopKafkaTopicProperties {
    /**
     * 数据条目更新
     */
    private String dataItemChange = "topic.shop.dataitem.change";

}
