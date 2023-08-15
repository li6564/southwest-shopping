package cn.southwest.shop.queue.message;

import cn.southwest.shop.pojo.WxUser;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 条目更新消息
 * @author meteor
 */
@Data
@ToString
@NoArgsConstructor
public class DataItemChangeMessage implements Serializable{

    public DataItemChangeMessage(DataItemChangeType changeType, DataItemType itemType, Serializable itemId) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.changeType = changeType;
    }

    public DataItemChangeMessage(DataItemChangeType changeType, DataItemType itemType, WxUser wxUser,Serializable itemId) {
        this.itemId = itemId;
        this.itemType = itemType;
        this.subject = wxUser;
        this.changeType = changeType;
    }

    public static DataItemChangeMessage addMessage(DataItemType itemType, Serializable itemId) {
        return new DataItemChangeMessage(DataItemChangeType.ADD, itemType, itemId);
    }

    public static DataItemChangeMessage deleteMessage(DataItemType itemType, Serializable itemId) {
        return new DataItemChangeMessage(DataItemChangeType.DELETE, itemType, itemId);
    }

    public static DataItemChangeMessage updateMessage(DataItemType itemType, Serializable itemId) {
        return new DataItemChangeMessage(DataItemChangeType.UPDATE, itemType, itemId);
    }

    /**
     * 条目ID
     */
    private Serializable itemId;

    /**
     * 操作者ID
     */
    private String operatorId;

    /**
     * 操作者
     */
    private WxUser subject;

    /**
     * 条目类型
     */
    private DataItemType itemType;

    /**
     * 条目改变类型
     */
    private DataItemChangeType changeType;
}
