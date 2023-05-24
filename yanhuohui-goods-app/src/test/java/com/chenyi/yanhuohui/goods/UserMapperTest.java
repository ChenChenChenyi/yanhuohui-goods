package com.chenyi.yanhuohui.goods;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenyi.yanhuohui.goods.entity.User;
import com.chenyi.yanhuohui.goods.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        List<User> userList = userMapper.selectList(null);
        for (User user : userList) {
            System.out.println(user);
        }
    }

    @Test
    public void testFindById() {
        User user = userMapper.findById(2L);
        System.out.println(user);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(1L); //主键
        user.setAge(100); //更新的字段
//根据id更新，更新不为null的字段
        this.userMapper.updateById(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setAge(22); //更新的字段
        //更新的条件
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("name", "曹操");
//可以设置多个条件...
//执行更新操作
        int result = this.userMapper.update(user, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testUpdate2() {
//更新的条件以及字段
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", 6).set("age", 23).set("birthday", null);
//执行更新操作
        int result = this.userMapper.update(null, wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteById() {
//执行删除操作
        int result = this.userMapper.deleteById(6L);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteByMap() {
        User user = new User();
        user.setAge(20);
        user.setName("张三");
//将实体对象进行包装，包装为操作条件
        QueryWrapper<User> wrapper = new QueryWrapper<>(user);

//        定义QueryWrapper可以不包装模型对象，手动设置条件，如下：
//        QueryWrapper<User> wrapper = new QueryWrapper<>();
//        wrapper.eq("age",20);
//        wrapper.eq("name","张三");

        int result = this.userMapper.delete(wrapper);
        System.out.println("result = " + result);
    }

    @Test
    public void testDeleteByMap2() {
//根据id集合批量删除
        int result = this.userMapper.deleteBatchIds(Arrays.asList(1L, 10L, 20L));
        System.out.println("result = " + result);
    }

    @Test
    public void testSelectById() {
//根据id查询数据
        User user = this.userMapper.selectById(2L);
        System.out.println("result = " + user);
    }

    @Test
    public void testSelectBatchIds() {
//根据id集合批量查询
        List<User> users = this.userMapper.selectBatchIds(Arrays.asList(2L, 3L, 10L));
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectOne() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.eq("name", "李四");
//根据条件查询一条数据，如果结果超过一条会报错
        User user = this.userMapper.selectOne(wrapper);
        System.out.println(user);
    }

    @Test
    public void testSelectCount() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 23); //年龄大于23岁
//根据条件查询数据条数
        Integer count = this.userMapper.selectCount(wrapper);
        System.out.println("count = " + count);
    }

    @Test
    public void testSelectList() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 23); //年龄大于23岁
//根据条件查询数据
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testSelectPage() {
        QueryWrapper<User> wrapper = new QueryWrapper<User>();
        wrapper.gt("age", 20); //年龄大于20岁
//参数1：当前页码，小于1的按1算
//参数2：每页记录数
        Page<User> page = new Page<>(1, 1);
//根据条件查询数据
        IPage<User> iPage = this.userMapper.selectPage(page, wrapper);
        System.out.println("数据总条数：" + iPage.getTotal());
        System.out.println("总页数：" + iPage.getPages());
//取出分页记录
        List<User> users = iPage.getRecords();
        for (User user : users) {
            System.out.println("user = " + user);
        }
    }

    @Test
    public void testEq() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq("password", "123456")
                .ge("age", 20)
                .in("name", "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testEq2() {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user WHERE password = ? AND age >= ? AND name IN (?,?,?)
        wrapper.eq(User::getPassword, "123456")
                .ge(User::getAge, 20)
                .in(User::getName, "李四", "王五", "赵六");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testWrapper() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user WHERE name LIKE ? Parameters: %五%(String)
        wrapper.like("name", "五");
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(User::getName, "五");
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void testWrapper2() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user WHERE name = ? OR age = ?
//      wrapper.eq("name","李四").or().eq("age", 24);
//变为and方式
        wrapper.eq("name","李四").eq("age", 24);
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getName,"李四").or().eq(User::getAge, 24);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //查询部分字段
    @Test
    public void testWrapper3() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,name,age FROM tb_user WHERE name = ? OR age = ?
        wrapper.eq("name", "李四")
                .or()
                .eq("age", 24)
                .select("id", "name", "age");
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.eq(User::getName, "李四")
//                .or()
//                .eq(User::getAge, 24)
//                .select(User::getId, User::getName, User::getAge);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

    //排序
    @Test
    public void testWrapper4() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
//SELECT id,user_name,password,name,age,email FROM tb_user ORDER BY age DESC
        wrapper.orderByDesc("age");
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.orderByDesc(User::getAge);
        List<User> users = this.userMapper.selectList(wrapper);
        for (User user : users) {
            System.out.println(user);
        }
    }

}