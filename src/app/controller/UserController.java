package app.controller;

import app.config.MenuAndAlertConfig;
import app.dto.request.SignInDTO;
import app.dto.request.SignUpDTO;
import app.dto.response.ResponseMessage;
import app.model.Role;
import app.model.RoleName;
import app.model.User;
import app.service.role.IRoleService;
import app.service.role.RoleServiceIPML;
import app.service.user.IUserService;
import app.service.user.UserServiceIPML;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    private UserServiceIPML userService = new UserServiceIPML();
    private IRoleService roleService = new RoleServiceIPML();

    public ResponseMessage register(SignUpDTO sign){
        if (userService.existedByUserName(sign.getUserName())){
            return new ResponseMessage(MenuAndAlertConfig.USER_EXIST);
        }
        if (userService.existedByUserName(sign.getEmail())){
            return new ResponseMessage(MenuAndAlertConfig.EMAIL_EXIST);
        }
        Set<Role> roleSet = new HashSet<>();
        Set<String> strRole = sign.getStrRole();
        strRole.forEach(role->{
            switch (role){
                case "admin":
                    roleSet.add(roleService.findByName(RoleName.ADMIN));
                    break;
                case "pm":
                    roleSet.add(roleService.findByName(RoleName.PM));
                    break;
                default:
                    roleSet.add(roleService.findByName(RoleName.USER));
            }
        });
        User user = new User(sign.getId(), sign.getName(), sign.getUserName(), sign.getEmail(), sign.getPassword(), roleSet);
        userService.save(user);
        return new ResponseMessage(MenuAndAlertConfig.REGISTER_SUCCESS);
    }
    public List<User> getListUser(){
        return userService.findAll();
    }
    public ResponseMessage login( SignInDTO signInDTO){
        if(userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())){
            return new ResponseMessage(MenuAndAlertConfig.LOGIN_SUCCESS);
        } else {
            return new ResponseMessage(MenuAndAlertConfig.LOGIN_SUCCESS_F);
        }
    }
    public User getUserLogin(){
        return userService.getCurentUser();
    }

    public void updatePassword(User user) {
        userService.save(user);
    }

    public void updateUserName(User user) {
        userService.save(user);
    }
    public void updateEmail(User user) {
        userService.save(user);
    }
    public  void signOut() {
        userService.signOut();
    }
    public void synchonizeLoginUser(User user){
        userService.synchonizeLoginUser(user);
    }

    public void changeRole(User user){
        Set<Role>  roleSet = user.getRoles();
        List<Role> roleList = new ArrayList<>(roleSet);
        if (roleList.get(0).getName() == RoleName.USER){
            Set<Role> newRoleSet = new HashSet<>();
            newRoleSet.add(roleService.findByName(RoleName.PM));
            user.setRoles(newRoleSet);
            userService.save(user);
        } else if (roleList.get(0).getName() == RoleName.PM){
            Set<Role> newRoleSet = new HashSet<>();
            newRoleSet.add(roleService.findByName(RoleName.USER));
            user.setRoles(newRoleSet);
            userService.save(user);
        }
    }
    public  User findUserById(int id){
        return userService.findById(id);
    }
    public void blockUser(User user){
        user.setStatus(!user.isStatus());
        userService.save(user);
    }
}
