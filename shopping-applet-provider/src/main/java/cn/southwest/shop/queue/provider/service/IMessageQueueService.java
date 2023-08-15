package cn.southwest.shop.queue.provider.service;

import cn.southwest.shop.queue.message.DataItemChangeMessage;

/**
 * @author linan
 */
public interface IMessageQueueService {

    /**
     * 发送条目更新消息
     * @param itemUpdateMessage
     */
    void sendDataItemChangeMessage(DataItemChangeMessage itemUpdateMessage);

}
