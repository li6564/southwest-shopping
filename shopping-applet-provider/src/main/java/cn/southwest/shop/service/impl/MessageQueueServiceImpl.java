package cn.southwest.shop.service.impl;

import cn.southwest.shop.properties.ShopKafkaTopicProperties;
import cn.southwest.shop.queue.message.DataItemChangeMessage;
import cn.southwest.shop.queue.provider.service.IMessageQueueService;
import cn.southwest.shop.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * @Author：linan
 * @Date：2023/8/15 9:44
 */
@Service
@Slf4j
public class MessageQueueServiceImpl implements IMessageQueueService {

    private KafkaTemplate<String,String> kafkaTemplate;

    private ShopKafkaTopicProperties topicProperties;

    @Autowired
    public MessageQueueServiceImpl(KafkaTemplate<String, String> kafkaTemplate, ShopKafkaTopicProperties topicProperties) {
        this.kafkaTemplate = kafkaTemplate;
        this.topicProperties = topicProperties;
    }

    @Override
    public void sendDataItemChangeMessage(DataItemChangeMessage itemUpdateMessage) {
        try {
            kafkaTemplate.send(topicProperties.getDataItemChange(), JsonUtils.toJson(itemUpdateMessage));
            log.debug("发送kafka消息：{}", itemUpdateMessage.toString());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Kafka消息发送失败：{}", e.getMessage());
        }
    }
}
