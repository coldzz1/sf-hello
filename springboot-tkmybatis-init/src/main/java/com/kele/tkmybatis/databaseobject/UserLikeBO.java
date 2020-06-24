package com.kele.tkmybatis.databaseobject;

import lombok.Data;

/**
 * Description: tk-mybatis
 * <p>
 * Created by ys on 2020/6/10 15:13
 */
@Data
public class UserLikeBO {
    /**
     * 主键id
     */
    private Long id ;

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 关注的用户id
     */
    private Long likeId;
}
