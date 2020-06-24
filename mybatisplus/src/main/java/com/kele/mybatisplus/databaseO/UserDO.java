package com.kele.mybatisplus.databaseO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息表
 */
//MyBatisPlus提供了一些注解供我们在实体类和表信息出现不对应的时候使用。通过使用注解完成逻辑上匹配。
//@TableName	实体类的类名和数据库表名不一致
//@TableField 实体类中的成员名称和表中不一致
//但是发现对于manager_id 在映射为实体类时会自动转驼峰
@TableName("user")
@Data
public class UserDO {
    @TableId(value = "id",type = IdType.NONE)
    private Long id;/*主键id*/
    private String name ;/*姓名*/
    private Integer age;/*年龄*/
    private String email;/*邮箱*/
    private Long managerId;/*直属上级id*/
    @TableField("create_time")
    private LocalDateTime createdTime;/*创建时间*/

    /**
     * 排除实体类中非表字段
     * 使用transient关键字修饰非表字段，但是被transient修饰后，无法进行序列化。
     * 使用static关键字，因为我们使用的是lombok框架生成的get/set方法，所以对于静态变量，我们需要手动生成get/set方法。
     * 使用@TableField(exist = false)注解
     */
    @TableField(exist = false)
    private boolean extraFlag;
}
