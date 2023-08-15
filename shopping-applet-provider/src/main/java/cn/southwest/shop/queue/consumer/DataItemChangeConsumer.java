package cn.southwest.shop.queue.consumer;

import cn.southwest.shop.listener.DataItemChangeListener;
import cn.southwest.shop.queue.message.DataItemChangeMessage;
import cn.southwest.shop.queue.message.DataItemChangeType;
import cn.southwest.shop.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.awt.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author：linan
 * @Date：2023/8/15 9:19
 */
@Component
@Slf4j
public class DataItemChangeConsumer implements TopicConsumer , ApplicationContextAware {
    private Collection<DataItemChangeListener> listeners;

    @Override
    @KafkaListener(topics = "${shop.kafka.topics.data-item-change}")
    public void onMessage(ConsumerRecord<?, String> record) {
        Optional<String> kafkaMessage = Optional.ofNullable(record.value());
        System.out.println(kafkaMessage.toString());
        if (kafkaMessage.isPresent()){
            DataItemChangeMessage dataItemChangeMessage;
            try {
                dataItemChangeMessage = JsonUtils.toBean(kafkaMessage.get(), DataItemChangeMessage.class);
                log.debug("接收到kafka消息：{}", dataItemChangeMessage.toString());
                System.out.println("接收到kafka消息：{}"+ dataItemChangeMessage.toString());
                // 调用每个监听器对应的消息处理函数
                DataItemChangeType changeType = dataItemChangeMessage.getChangeType();
                System.out.println(listeners.size());
                listeners.forEach(listener->{
                    // try-catch，保证每个listener互不影响
                    try {
                        switch (changeType) {
                            case ADD:
                                listener.onDataItemAdd(dataItemChangeMessage);
                                break;
                            case DELETE:
                                listener.onDataItemDelete(dataItemChangeMessage);
                                break;
                            case UPDATE:
                                listener.onDataItemUpdate(dataItemChangeMessage);
                                break;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        log.error(e.getMessage());
                    }
                });
            }catch (Exception e){
                e.printStackTrace();
                log.error("DataItemUpdateMessage consume failed:{}", e.getMessage());
            }
        }

    }



    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, DataItemChangeListener> listenerMap = applicationContext.getBeansOfType(DataItemChangeListener.class);
        if (!CollectionUtils.isEmpty(listenerMap)){
            this.listeners = listenerMap.values();
        }else {
            this.listeners = List.of();
        }

    }
}
