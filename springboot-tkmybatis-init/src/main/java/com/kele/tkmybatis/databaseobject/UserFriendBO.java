package com.kele.tkmybatis.databaseobject;

import lombok.Data;

/**
 * Description: tk-mybatis
 * <p>
 * Created by ys on 2020/6/10 15:14
 */
@Data
public class UserFriendBO {
    private Long id;
    /**
     * 好友1id
     */
    private Long friend1Id;
    /**
     * 好友2id
     */
    private Long friend2Id;
}
