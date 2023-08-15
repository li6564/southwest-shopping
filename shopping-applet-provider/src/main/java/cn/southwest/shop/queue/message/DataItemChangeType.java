package cn.southwest.shop.queue.message;

import java.io.Serializable;

/**
 * 数据更新操作
 * @author meteor
 */
public enum DataItemChangeType implements Serializable {

    /**
     * 新增条目
     */
    ADD,

    /**
     * 删除条目
     */
    DELETE,

    /**
     * 更新条目
     */
    UPDATE;
}
