package cn.southwest.shop.listener;
import cn.southwest.shop.queue.message.DataItemChangeMessage;

/**
 * 数据更新监听器
 * 监听本系统所有的数据更新 操作：更新操作
 * @author linan
 */
public interface DataItemChangeListener {

    /**
     * 有新数据添加到数据库
     * @param dataItemChangeMessage
     */
    default void onDataItemAdd(DataItemChangeMessage dataItemChangeMessage) throws Exception {

    }

    /**
     * 有数据从数据库删除
     * @param dataItemChangeMessage
     */
    default void onDataItemDelete(DataItemChangeMessage dataItemChangeMessage) throws Exception {

    }

    /**
     * 数据库里面的数据更新
     * @param dataItemChangeMessage
     */
    default void onDataItemUpdate(DataItemChangeMessage dataItemChangeMessage) throws Exception {


    }


}
