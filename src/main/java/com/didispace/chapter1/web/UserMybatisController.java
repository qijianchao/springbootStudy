package com.didispace.chapter1.web;

import com.didispace.chapter1.entity.User;
import com.didispace.chapter1.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "用户管理（Mybatis操作数据库）")
@RestController
@RequestMapping(value = "/usersMybatis") // 通过这里配置使下面的映射都在/users下
public class UserMybatisController {

    private UserMapper userMapper;

    public UserMybatisController(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 处理"/usersMybatis/"的GET请求，用来获取用户列表
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/")
    public List<User> getUserList() {
        List<User> r = userMapper.findAll();
        return r;
    }

    /**
     * 处理"/usersMybatis/"的POST请求，用来创建User
     * @param user
     * @return
     */
    @ApiOperation(value = "创建一个用户", notes = "根据User对象创建用户")
    @PostMapping("/")
    public String postUser(@Valid @RequestBody com.didispace.chapter1.domain.User user) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        // int ret = userMapper.insert(user.getName(), user.getAge());
        User u = new User(user.getName(),user.getAge());
        int ret = userMapper.insertByUser(u);
        return Integer.toString(ret) + " userId:" + Long.toString(u.getId());
    }

    /**
     * 处理"/usersMybatis/name/"的POST请求，用来获取用户
     * @return
     */
    @ApiOperation(value = "根据用户名称获取一个用户")
    @PostMapping("/name/")
    public User getUserByName(@RequestParam String name) {
        User r = userMapper.findByName(name);
        return r;
    }

    /**
     * 处理"/usersMybatis/dellAll/"的POST请求，用来获取用户
     * @return
     */
    @ApiOperation(value = "删除所有用户")
    @PostMapping("/delAll/")
    public String deleteAllUser(@RequestParam String yes) throws Exception {
        if(yes.equals("1")) {
            userMapper.deleteAll();
            return "yes";
        } else {
            throw new Exception("暗号不对，无法删除");
        }
    }

}
