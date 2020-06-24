package com.kele.tkmybatis.service;

import com.kele.tkmybatis.databaseobject.UserBO;
import org.springframework.stereotype.Service;

/**
 * Description: tk-mybatis
 * <p>
 * Created by ys on 2020/6/10 15:15
 */
@Service
public class FollowService {


    //A userId =1 ,B userId =2

    //A关注B B关注A都用这段代码
    //保证user_like 表的 user_id 小于 liker_id  值为1的时候表示 user_id 关注   liker_id
    public Object follow(UserBO user,UserBO liker){
        if(user.getId()<liker.getId()){
            // insert into `like`(user_id, liker_id, relation_ship) values(user, liker, 1) on duplicate key update relation_ship=relation_ship | 1;
            // select relation_ship from `like` where user_id=A and liker_id=B;
            // 代码中判断返回的 relation_ship， 如果是1，事务结束，执行 commit 如果是3，则执行下面这两个语句： */
            // insert ignore into friend(friend_1_id, friend_2_id) values(A,B);
            // commit;
        }
        if(user.getId()>liker.getId()){
            //insert into `like`(user_id, liker_id, relation_ship) values(liker, user, 2) on duplicate key update relation_ship=relation_ship | 2;
        }


    }
}
