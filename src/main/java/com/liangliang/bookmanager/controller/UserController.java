package com.liangliang.bookmanager.controller;

import com.liangliang.bookmanager.bean.Message;
import com.liangliang.bookmanager.bean.User;
import com.liangliang.bookmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @return
     */
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    @ResponseBody
    public Message getUserList(){

        List<User> userList = new ArrayList<>();

        try {
            userList = userService.getUserList();
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"获取用户列表失败！",null);
        }
        return new Message(Message.SUCCESS,"获取用户列表成功！",userList);
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
    @ResponseBody
    public Message getUserById(@RequestParam("userId") Integer id){
        User user = new User();
        try {
            user = userService.getUserById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"获取用户失败！", null);
        }
        return new Message(Message.SUCCESS,"获取用户成功！",user);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public Message addUser(@RequestBody User user){

        try {
            if(userService.addUser(user)){
                return new Message(Message.SUCCESS,"新增用户成功！",null);
            }else{
                return new Message(Message.ERROR,"新增用户失败！",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"新增用户失败！",null);
        }
    }

    /**
     * 修改用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public Message updateUser(@RequestBody User user){
        try {
            if(userService.updateUser(user)){
                return new Message(Message.SUCCESS,"修改用户成功！",null);
            }else{
                return new Message(Message.ERROR,"修改用户失败！",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"修改用户失败！",null);
        }
    }
    /**
     * 删除用户
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    @ResponseBody
    public Message deleteUser(@RequestParam(value = "userId") Integer id){
                try {
            if(userService.deleteUser(id)){
                return new Message(Message.SUCCESS,"删除用户成功！",null);
            }else{
                return new Message(Message.ERROR,"删除用户失败！",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Message(Message.ERROR,"删除用户失败！",null);
        }
    }
}