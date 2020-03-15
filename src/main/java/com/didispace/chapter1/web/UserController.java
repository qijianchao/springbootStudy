package com.didispace.chapter1.web;

import com.didispace.chapter1.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/users") // 通过这里配置使下面的映射都在/users下
public class UserController {

    // 创建线程安全的Map，模拟users信息的存储
    static Map<Long, User> users = Collections.synchronizedMap(new HashMap<Long, User>());

    /**
     * 处理"/users/"的GET请求，用来获取用户列表
     * @return
     */
    @ApiOperation(value = "获取用户列表")
    @GetMapping("/")
    public List<User> getUserList() {
        List<User> r = new ArrayList<User>(users.values());
        return r;
    }

    /**
     * 处理"/users/"的POST请求，用来创建User
     * @param user
     * @return
     */
    @ApiOperation(value = "创建一个用户", notes = "根据User对象创建用户")
    @PostMapping("/")
    public String postUser(@Valid @RequestBody User user) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        users.put(user.getId(), user);
        return "success";
    }

    /**
     * 处理"/users/{id}"的GET请求，用来获取url中的id值的User信息
     * @param id
     * @return
     */
    @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户详细信息")
    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        // url中的id可通过@PathVariable绑定到函数的参数中
        return users.get(id);
    }

    /**
     * 处理"/users/{id}"的PUT请求，用来更新User信息
     * @param id
     * @param user
     * @return
     */
    @ApiImplicitParam(paramType = "path", dataType = "Long", name ="id", value = "用户编号", required = true, example = "1")
    @ApiOperation(value = "更新用户详细信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
    @PutMapping("/{id}")
    public String putUser(@PathVariable Long id, @RequestBody User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id,u);
        return "success";
    }

    /**
     * 处理"/users/{id}"的DELETE请求，用来删除User
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据url的id来指定删除对象")
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

    /**
     * 处理"/users/add/{id}/{name}/{age}"的GET请求，用来手动添加用户
     * @param id
     * @param name
     * @param age
     * @return
     */
    @GetMapping("/add/{id}/{name}/{age}")
    public String getUser(@PathVariable Long id, @PathVariable String name, @PathVariable Integer age) {
        User u = new User();
        u.setId(id);
        u.setName(name);
        u.setAge(age);
        users.put(id,u);
        return "success";
    }
}
