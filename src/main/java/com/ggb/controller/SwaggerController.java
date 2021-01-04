package com.ggb.controller;

import com.ggb.po.CommonResult;
import com.ggb.po.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * 功能描述:
 * 第三部分：Swagger 让你的API可视化
 * 1、Swagger 是一个规范和完整的框架，用于生成、描述、调用和可视化 RESTful 风格的 Web 服务
 * 2、总体目标是使客户端和文件系统作为服务器以同样的速度来更新
 * 3、pom.xml需要引入springfox-swagger2和springfox-swagger-ui
 * 4、SpringbootApplication上添加@EnableSwagger2注解启用Swagger
 * 5、@Api：用在类上，说明该类的作用
 * 6、@ApiOperation：用在方法上，说明方法的作用，标注在具体请求上，value 和 notes 的作用差不多，都是对请求进行说明；tags 则是对请求进行分类的，比如你有好几个 controller，分别属于不同的功能模块，那这里我们就可以使用 tags 来区分了。
 * 7、@ApiImplicitParams：用在方法上包含一组参数说明，其他注解自行百度
 * 8、访问地址https://localhost:9080/demo/swagger-ui.html
 * 9、如果项目上线并且需要关闭swagger接口，可以通过配置权限，或者再SwaggerConfig里面return new Docket的时候加多一个.enable(false)
 * @Author: genggb
 * @Date: 2020-1-20 17:15
 */
@RestController
@Api(value = "用户测试模块")
public class SwaggerController {

    // 创建线程安全的Map
    static Map<String, User> users = Collections.synchronizedMap(new HashMap<String, User>());

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public ResponseEntity<CommonResult> getUserById (@PathVariable(value = "id") String id){
        CommonResult commonResult = new CommonResult();
        try {
            User user = users.get(id);
            commonResult.setResult(user);
            commonResult.setStatus("ok");
        } catch (Exception e) {
            commonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            commonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(commonResult);
    }

    /**
     * 查询用户列表
     * @return
     */
    @ApiOperation(value="获取用户列表", notes="获取用户列表")
    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity<CommonResult> getUserList (){
        CommonResult commonResult = new CommonResult();
        try {
            List<User> userList = new ArrayList<User>(users.values());
            commonResult.setResult(userList);
            commonResult.setStatus("ok");
        } catch (Exception e) {
            commonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            commonResult.setStatus("error");
            e.printStackTrace();
        }
        return ResponseEntity.ok(commonResult);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @ApiOperation(value="创建用户", notes="根据User对象创建用户")
    @ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "User")
    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<CommonResult> add (@RequestBody User user){
        CommonResult commonResult = new CommonResult();
        try {
            users.put(user.getId(), user);
            commonResult.setResult(user.getId());
            commonResult.setStatus("ok");
        } catch (Exception e) {
            commonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            commonResult.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(commonResult);
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @ApiOperation(value="删除用户", notes="根据url的id来指定删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<CommonResult> delete (@PathVariable(value = "id") String id){
        CommonResult commonResult = new CommonResult();
        try {
            users.remove(id);
            commonResult.setResult(id);
            commonResult.setStatus("ok");
        } catch (Exception e) {
            commonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            commonResult.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(commonResult);
    }

    /**
     * 根据id修改用户信息
     * @param user
     * @return
     */
    @ApiOperation(value="更新信息", notes="根据url的id来指定更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String",paramType = "path"),
            @ApiImplicitParam(name = "user", value = "用户实体user", required = true, dataType = "User")
    })
    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CommonResult> update (@PathVariable("id") String id, @RequestBody User user){
        CommonResult commonResult = new CommonResult();
        try {
            User user1 = users.get(id);
            user1.setUsername(user.getUsername());
            user1.setAge(user.getAge());
            users.put(id, user1);
            commonResult.setResult(user1);
            commonResult.setStatus("ok");
        } catch (Exception e) {
            commonResult.setResult(e.getClass().getName() + ":" + e.getMessage());
            commonResult.setStatus("error");

            e.printStackTrace();
        }
        return ResponseEntity.ok(commonResult);
    }

    @ApiIgnore//使用该注解忽略这个API
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String  jsonTest() {
        return " hi you!";
    }
}
