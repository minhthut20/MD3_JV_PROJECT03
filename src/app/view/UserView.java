package app.view;

import app.config.Config;
import app.config.MenuAndAlertConfig;
import app.config.ValidateConfig;
import app.controller.UserController;
import app.dto.request.SignInDTO;
import app.dto.request.SignUpDTO;
import app.dto.response.ResponseMessage;
import app.model.Role;
import app.model.RoleName;
import app.model.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserView {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();
    public void formRegister() {
        int id = 0;
        if (userList.size() == 0) {
            id = 1;
        } else {
            id = userList.get(userList.size()-1).getId()+1;
        }
        String name , username, email, password;
        while (true){
            System.out.println("Nhập vào tên của bạn :  ");
            name = Config.getString();
            if (ValidateConfig.validateName(name)){
                break;
            }
            System.out.println("Không hợp lệ! Vui lòng nhập lại !");
        }
        while (true){
            System.out.println("Nhập vào tên đăng nhập :  ");
            username = Config.getString();
            if (ValidateConfig.validateUsername(username)){
                break;
            }
            System.out.println("Tên đăng nhập không hợp lệ ! Vui lòng nhập lại !");

        }
        while (true){
            System.out.println("Nhập vào Email : ");
            email = Config.getString();
            if (ValidateConfig.validateEmail(email)){
                break;
            }
            System.out.println("Email không hợp lệ! Vui lòng nhập lại !");

        }
        while (true){
            System.out.println("Nhập vào Password ");
            password = Config.getString();
            if (ValidateConfig.validatePassword(password)){
                break;
            }
            System.out.println("Mật khẩu không hợp lệ! Vui lòng nhập lại !");

        }
        Set<String> strRole = new HashSet<>();
        strRole.add("User");
        SignUpDTO sign = new SignUpDTO(id,name,username,email,password,strRole);
        while (true){
            ResponseMessage responseMessage = userController.register(sign);
            if(responseMessage.getMessage().equals(MenuAndAlertConfig.USER_EXIST)){
                System.out.println(MenuAndAlertConfig.USER_EXIST);
                System.out.println("Nhập lại tên đăng nhập : ");
                username = Config.getString();
                sign.setUserName(username);
            } else if(responseMessage.getMessage().equals(MenuAndAlertConfig.EMAIL_EXIST)){
                System.out.println(MenuAndAlertConfig.EMAIL_EXIST);
                System.out.println("Nhập lại Email : ");
                email = Config.getString();
                sign = new SignUpDTO(id,name,username,email,password,strRole);
            } else if(responseMessage.getMessage().equals(MenuAndAlertConfig.REGISTER_SUCCESS)){
                System.out.println(MenuAndAlertConfig.REGISTER_SUCCESS);
                formLogin();
                break;
            }
        }


    }
    public  void formLogin(){
        System.out.println("Tên đăng nhập : ");
        String username = Config.getString();
        System.out.println("Password : ");
        String password = Config.getString();
        SignInDTO signInDTO = new SignInDTO(username,password);
        while (true) {
            ResponseMessage responseMessage = userController.login(signInDTO);
            if(responseMessage.getMessage().equals(MenuAndAlertConfig.LOGIN_SUCCESS_F)){
                System.out.println("Đăng nhập thất bại! Vui lòng kiểm tra tài khoản của bạn!");
                System.out.println("Tên đăng nhập : ");
                username = Config.getString();
                System.out.println("Password : ");
                password = Config.getString();
                signInDTO.setUsername(username);
                signInDTO.setPassword(password);
            } else {
                System.out.println(MenuAndAlertConfig.LOGIN_SUCCESS);
                User loginUser = userController.getUserLogin();
                Set<Role> roleLogin = loginUser.getRoles();
                for (Role role: roleLogin) {
                    if (role.getName().equals(RoleName.USER)){
                        new NarBar();
                    }
                    if (role.getName().equals(RoleName.ADMIN)){
                        new AdminView();
                    }
                    if (role.getName().equals(RoleName.PM)){
                        new PmView();
                    }
                }

                break;
            }
        }
    }
    public void showListUser(){
        System.out.println(userController.getListUser());
        System.out.println("Nhập Back để quay lại Menu ! ");
        String back = Config.getString();
        if(back.equalsIgnoreCase("back")){
            new NarBar();
        }
    }

    public void showUserInfo() {
        User user = userController.getUserLogin();
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                             THÔNG TIN CÁ NHÂN                                 |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.printf("|  -  Tên đăng nhập:%-60s|\n",user.getUserName());
        System.out.printf("|  -  Tên          :%-60s|\n",user.getName());
        System.out.printf("|  -  Email        :%-60s|\n",user.getEmail());
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("| 1. Đổi mật khẩu.                                                              |");
        System.out.println("| 2. Thay đổi thông tin cá nhân.                                                |");
        System.out.println("| 9. Quay lại.                                                                  |");
        System.out.println("| 0. Thoát                                                                      |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice){
            case 1:
                changePassword(user);
                break;
            case 2:
                changeInfoUser(user);
                break;
            case 9:
                new NarBar();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Không hợp lệ!!!");
                showUserInfo();
        }
    }

    public void signOut() {
        while (true){
            System.out.println("Bạn có muốn dăng xuất không? - Nhập Y/N");
            String choice = Config.getString();
            if (choice.equalsIgnoreCase("Y")){
                userController.signOut();
                new NarBar();
                break;
            }
            if (choice.equalsIgnoreCase("n")){
                new NarBar();
                break;
            }
        }
    }

    private void changePassword(User user) {
        while (true) {
            System.out.println("Mật khẩu cũ : ");
            String oldPassword = Config.getString();
            if (oldPassword.equals(user.getPassword())){
                break;
            }
            System.out.println("Mật khẩu không chính xác!");
        }
        String newPassword;
        while (true){
            System.out.println("Nhập mật khẩu mới: ");
            newPassword = Config.getString();
            if (ValidateConfig.validatePassword(newPassword)){
                break;
            }
            System.out.println("Mật khẩu không hợp lệ! Vui lòng nhập lại !");
        }
        String reNewPassword;
        while (true){
            System.out.println("Nhập lại mật khẩu mới: ");
            reNewPassword = Config.getString();
            if (reNewPassword.equals(newPassword)){
                user.setPassword(newPassword);
                userController.updatePassword(user);
                userController.synchonizeLoginUser(user);
                System.out.println("Đổi mật khẩu thành công!");
                break;
            }
            System.out.println("Mật khẩu không hợp lệ!");
        }
        showUserInfo();
    }

    private void changeInfoUser(User user) {
        System.out.println("Nhập vào tên đăng nhập muốn thay đổi: ");
        String newUserName = Config.getString();
        while (!newUserName.equalsIgnoreCase(user.getUserName())){
             boolean useNameExist = false;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getUserName().equalsIgnoreCase(newUserName)){
                    useNameExist = true;
                    break;
                }
            }
            if (useNameExist){
                System.out.println("Tên đăng nhập đã tồn tại!");
                System.out.println("Nhập lại tên đăng nhập: ");
                newUserName = Config.getString();

            }else {
                break;
            }
        }
        user.setUserName(newUserName);
        userController.updateUserName(user);
        userController.synchonizeLoginUser(user);
        System.out.println("Cập nhật tên đăng nhập thành công!!!");


        System.out.println("Nhập vào email muốn thay đối : ");
        String newEmail = Config.getString();
        while (!newEmail.equalsIgnoreCase(user.getEmail())){
            boolean useEmailExist = false;
            for (int i = 0; i < userList.size(); i++) {
                if (userList.get(i).getEmail().equalsIgnoreCase(newEmail)){
                    useEmailExist = true;
                    break;
                }
            }
            if (useEmailExist){
                System.out.println("Email đã tồn tại !!!");
                System.out.println("Nhập lại Email: ");
                newEmail = Config.getString();

            }else {
                break;
            }
        }
        user.setEmail(newEmail);
        userController.updateEmail(user);
        userController.synchonizeLoginUser(user);
        System.out.println("Cập nhật Email thành công !!!");
        showUserInfo();
    }

}
