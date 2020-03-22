package com.didispace.chapter1.web;

import com.didispace.chapter1.entity.Dept;
import com.didispace.chapter1.mapper.DeptMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "部门管理（Mybatis操作数据库）")
@RestController
@RequestMapping(value = "/DeptMybatis") // 通过这里配置使下面的映射都在/users下
public class DeptMybatisController {

    private DeptMapper deptMapper;
    private StringRedisTemplate stringRedisTemplate;

    public DeptMybatisController(DeptMapper deptMapper, StringRedisTemplate stringRedisTemplate) {
        this.deptMapper = deptMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 处理"/DeptMybatis/"的GET请求，用来获取部门列表
     * @return
     */
    @ApiOperation(value = "获取部门列表")
    @GetMapping("/")
    public List<Dept> getDeptList() {
        List<Dept> r = deptMapper.findAll();
        return r;
    }

    /**
     * 处理"/DeptMybatis/"的POST请求，用来创建Dept
     * @param dept
     * @return
     */
    @ApiOperation(value = "创建一个部门", notes = "根据Dept对象创建部门")
    @PostMapping("/")
    public String postDept(@Valid @RequestBody com.didispace.chapter1.domain.Dept dept) {
        // @RequestBody注解用来绑定通过http请求中application/json类型上传的数据
        // int ret = userMapper.insert(user.getName(), user.getAge());
        // Dept d = new Dept(dept.getDeptName(),dept.getDeptDesc());
        int ret = deptMapper.insert(dept.getDeptNo(), dept.getDeptName(), dept.getDeptDesc());
        return Integer.toString(ret);
    }

    /**
     * 获取创建部门次数
     * @return
     */
    @ApiOperation(value = "测试redis变量更新", notes = "测试redis")
    @GetMapping("/getCreateCount/")
    public String getCreateCount() {
        // 从Redis获取创建次数
        String keyName = "creatCount";
        if(!stringRedisTemplate.hasKey(keyName)) {
            stringRedisTemplate.opsForValue().set(keyName, "0");
        }
        Integer count = Integer.parseInt(stringRedisTemplate.opsForValue().get(keyName));
        count++;
        stringRedisTemplate.opsForValue().set(keyName, count.toString());
        return stringRedisTemplate.opsForValue().get(keyName);
    }

    /**
     * 处理"/DeptMybatis/deptName/"的POST请求，用来获取部门
     * @return
     */
    @ApiOperation(value = "根据部门名称获取一个部门信息")
    @PostMapping("/deptName/")
    public Dept getDeptByName(@RequestParam String deptName) {
        Dept r = deptMapper.findByName(deptName);
        return r;
    }

    /**
     * 处理"/DeptMybatis/deptNo/"的POST请求，用来获取部门
     * @return
     */
    @ApiOperation(value = "根据部门编号获取一个部门信息")
    @PostMapping("/deptNo/")
    public List<Dept> getDeptByNo(@RequestParam Integer deptNo) {
        List<Dept> r = deptMapper.findByNo(deptNo);
        return r;
    }

    /**
     * 处理"/DeptMybatis/del/"的POST请求，用来删除一个部门
     * @return
     */
    @ApiOperation(value = "删除一个部门")
    @PostMapping("/del/")
    public String deleteOneDept(@RequestParam Integer deptNo) throws Exception {
        deptMapper.delete(deptNo);
        return "yes";
    }
}
