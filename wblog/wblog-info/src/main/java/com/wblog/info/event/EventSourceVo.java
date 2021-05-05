package com.wblog.info.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author luyanan
 * @since 2020/12/21
 * <p>事件监听的vo</p>
 **/
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EventSourceVo {


    /**
     * <p>id</p>
     *
     * @author luyanan
     * @since 2020/12/21
     */
    private String id;

    private Type type;

    public static enum Type {
        // 更新
        UPDATE,
        // 删除
        DELETE,
        // 添加
        SAVE,
        // 上架
        ENABLE_STATUS,
        // 下架
        STOP_STATUS
    }
}
