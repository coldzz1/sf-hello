package com.kele.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kele.mybatisplus.MybatisPlusApplication;
import com.kele.mybatisplus.dao.UserMapper;
import com.kele.mybatisplus.databaseO.UserDO;
import javafx.scene.control.Pagination;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * java.lang.IllegalStateException: Unable to find a @SpringBootConfiguration, you need to use @ContextConfiguration or @SpringBootTest(classes=...) with your test
 * 出现上述异常的原因
 * 找不到SpringBoot的启动类
 * 解决方法:
 * 这个测试类的路径需要和启动类的路径保持一致
 * 当前项目下启动类MybatisPlusApplication的路径是
 * com\kele\mybatisplus\MybatisPlusApplication.java
 * 那只需要保证当前测试类也在com\kel\mybatisplus\下就可以
 *
 * 或者在@SpringBootTest上增加属性(classes={MybatisPlusApplication.class})
 * 指明当前启动类的类名
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {MybatisPlusApplication.class})
public class MybatisPlusApplicationTests {

    @Resource
    private UserMapper userMapper;

   /* @Test
    public void select(){
        List<UserDO> userDOList = userMapper.selectList(null);
        userDOList.forEach(System.out::println);
    }

    @Test
    public void insert(){
        UserDO user = new UserDO();
        user.setAge(31);
        user.setManagerId(1088250446457389058L);
        user.setCreatedTime(LocalDateTime.now());
        user.setExtraFlag(false);
        int insert = userMapper.insert(user);
        System.out.println("影像记录数："+insert);
    }

    *//**
     * 查询名字中包含'雨'并且年龄小于40
     * where name like '%雨%' and age < 40
     * select id,`name`,manager_id,age,email,create_time ,mark
     * from user where `name` like '%雨%' and age <40;
     *//*
    @Test
    public void selectByWrapper(){
        QueryWrapper<UserDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name","雨").lt("age",40);
        List<UserDO> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }

    *//**
     * 创建日期为2019年2月14日并且直属上级姓名为王姓
     * select id ,name ,email,manager_id, create_time,mark
     * from user
     * where DATE_FORMAT(create_time,'%Y-%m-%d')='2019-02-14'
     * and
     * manager_id =(select id from user where name like '王%')
     *//*
    @Test
    public void selectByNestedWrapper(){
        //String time ="2019-02-14";
        String time ="2019-02-14 or 1=1";
        QueryWrapper<UserDO> queryWrapper=new QueryWrapper<>();
        //会有sql注入的风险
        queryWrapper.apply("date_format(create_time,'%Y-%m-%d')="+time)
               .inSql("manager_id","select id from user where name like '王%'");
        //queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}",time)
        //        .inSql("manager_id","select id from user where name like '王%'");
        List<UserDO> userDOList = userMapper.selectList(queryWrapper);
        userDOList.forEach(System.out::println);
    }


    *//**
     * 名字为王姓，（年龄小于40或者邮箱不为空）
     *   select id,name,age,email,create_time,mark
     *     from user
     *     where name like '王%' and (age <40 or not email=null);
     *//*
    @Test
    public void selectByAndOr(){
        QueryWrapper<UserDO> wrapper=new QueryWrapper<>();
        wrapper.like("name","王%").and(wq->wq.lt("age",40).or().isNotNull("email"));
        List<UserDO> userDOList = userMapper.selectList(wrapper);
        userDOList.forEach(System.out::println);
    }
    *//**
     * 名字为王姓，（年龄小于40，并且年龄大于20，并且邮箱不为空）
     * select id ,name,age,email,create_time,mark
     *     from user
     *     where name like '王%' and age <40 and age >20 and not email is null;
     *
     *      select id ,name,age,email,create_time,mark
     *  from user
     *  where name like '王%' and age BETWEEN 20 and 40 and not email is null;
     *//*
    @Test
    public void selectByBetween(){
        QueryWrapper <UserDO>queryWrapper=new QueryWrapper<>();
        //queryWrapper.like("name","王%").between("age",20,40).isNotNull("email");
        //queryWrapper.likeLeft("name","王").and(wq->wq.between("age",20,40).isNotNull("email"));

        // SELECT id,name,age,email,manager_id,create_time AS createdTime FROM user                WHERE name LIKE ? AND ( age BETWEEN ? AND ? AND email IS NOT NULL )
        //==>  Preparing: SELECT id,name,age,email,manager_id,create_time AS createdTime FROM user WHERE name LIKE ? AND ( age BETWEEN ? AND ? AND ( email IS NOT NULL ) )
        queryWrapper.likeRight("name","王").and(wq->wq.between("age",20,40).and(wq1->wq1.isNotNull("email").isNotNull("mark")));
        List<UserDO> userDOList = userMapper.selectList(queryWrapper);
        userDOList.forEach(System.out::println);
    }
*/

    @Test
    public void testSelectByAge(){
        userMapper.selectUserByAge(20,1,2);
    }


    @Test
    public void testSelectByPage(){
        //Pagination pagination=new Pagination(1,2);
        Page page=new Page(0,10L);
        Integer age =20;
        IPage<UserDO> userDOIPage = userMapper.selectUserByPage(page,age);
        List<UserDO> records = userDOIPage.getRecords();
        records.stream().forEach(System.out::println);
    }


    @Test
    public void insertTestAutoIncrement(){
        UserDO userDO=new UserDO();
        userDO.setName("ceshi");
        userDO.setAge(11);
        userDO.setCreatedTime(LocalDateTime.now());
        userDO.setEmail("ceshiemail");
        userDO.setManagerId(1232L);
        userDO.setExtraFlag(true);
        int insert = userMapper.insert(userDO);

    }

    @Test
    @Rollback(value = false)
    @Transactional(rollbackFor = RuntimeException.class)
    public  void testUpdate() throws Exception{
        UserDO userDO=new UserDO();
        try{
            userDO.setId(1087982257332887557L);
            userDO.setEmail("c11i@email.com");
            userDO.setAge(18);
            userMapper.insert(userDO);
            throw new RuntimeException();
        }catch (Exception e){
            if(e.getCause().toString().contains("Duplicate")){
                userDO.setAge(15);
                userMapper.updateById(userDO);
            }else throw e;
        }

    }

    @Test
    public void testInsert(){
        UserDO userDO=new UserDO();
        userDO.setAge(11);
        userMapper.insert(userDO);
    }

    @Test
    public void selectByPrimaryKey(){
        UserDO userDO = userMapper.selectById(1087982257332887553L);
        System.out.println(userDO.toString());
    }

    @Test
    public void updateByPrimaryKey(){
        List<UserDO> userDOList = userMapper.selectList(new QueryWrapper<>());

    }




}
