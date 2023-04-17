package app.view;

import app.config.Config;
import app.config.ValidateConfig;
import app.controller.UserController;
import app.model.Role;
import app.model.RoleName;
import app.model.User;
import database.DataBase;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AdminView {
    UserController userController = new UserController();
    List<User> userList = userController.getListUser();

    List<User> currentUserList = new Config<User>().readFromFile(DataBase.PATH_USER_LOGIN);

    public AdminView() {
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                             WELCOME ADMIN                                     |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.println("| 1. Đăng xuất.                                                                 |");
        System.out.println("| 2. Thông tin Admin.                                                           |");
        System.out.println("| 3. Thay đổi quyền truy cập.                                                   |");
        System.out.println("| 4. Chặn, mở chặn tài khoản.                                                   |");
        System.out.println("| 5. Quản lý danh mục.                                                          |");
        System.out.println("| 6. Quản lý video.                                                             |");
        System.out.println("| 7. Quàn lý Film.                                                              |");
        System.out.println("| 0. Thoát                                                                      |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice) {
            case 1:
                new UserView().signOut();
                break;
            case 2:
                showInfoAdmin();
                break;
            case 3:
                changeRole();
                break;
            case 4:
                blockUser();
                break;
            case 5:
                new ManageCategory().manageCategory();
                break;
            case 6:
                new ManageVideo().manageVideo();
                break;
            case 7:
                new ManageFilm().manageFilm();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Không hợp lệ!!!");
                new AdminView();
        }
    }


    private void blockUser() {
        Set<Role> roleSet = currentUserList.get(0).getRoles();
        List<Role> roleList = new ArrayList<>(roleSet);
        if (roleList.get(0).getName() == RoleName.ADMIN) {
            for (User user : userList) {
                Set<Role> roles = user.getRoles();
                List<Role> listRole = new ArrayList<>(roles);
                if (listRole.get(0).getName() == RoleName.PM || listRole.get(0).getName() == RoleName.USER)
                    System.out.printf("ID: %d - Tên: %s - %s - Trạng thái: %b \n", user.getId(), user.getName(), user.getRoles(), user.isStatus());
            }
            System.out.println("Nhập ID người dùng bạn muốn khoá hoặc mở khoá: ");
            int id = Config.getInteger();
            User user = userController.findUserById(id);
            if (user != null) {
                if (user.isStatus()) {
                    System.out.println("Bạn có muốn mở khoá tài khoản này không? Nhập Y/N");
                } else {
                    System.out.println("Bạn có muốn khoá tài khoản này không? Nhập Y/N");
                }
                String choice = Config.getString();
                if (choice.equalsIgnoreCase("y")) {
                    userController.blockUser(user);
                    System.out.println("Thực hiện thành công!");
                    new AdminView();
                }
                if (choice.equalsIgnoreCase("n")) {
                    new AdminView();
                }
            } else {
                System.out.println("Id không tồn tại!!!");
                new AdminView();
            }
        } else {
            for (User user : userList) {
                Set<Role> roles = user.getRoles();
                List<Role> listRole = new ArrayList<>(roles);
                if (listRole.get(0).getName() == RoleName.PM || listRole.get(0).getName() == RoleName.USER)
                    System.out.printf("ID: %d - Tên: %s - %s - Trạng thái: %b \n", user.getId(), user.getName(), user.getRoles(), user.isStatus());
            }
            System.out.println("Nhập ID người dùng bạn muốn khoá hoặc mở khoá ");
            int id = Config.getInteger();
            User user = userController.findUserById(id);
            if (user != null) {
                Set<Role> roleSetUser = user.getRoles();
                List<Role> roles = new ArrayList<>(roleSetUser);
                if (roles.get(0).getName() != RoleName.ADMIN && roles.get(0).getName() != RoleName.PM) {
                    userController.blockUser(user);
                    System.out.println("Thực hiện thành công!");
                    new AdminView();
                } else {
                    System.out.println("Không thể thực hiện!!!");
                    new AdminView();
                }
            } else {
                System.out.println("Id không tồn tại!!!");
                new AdminView();
            }
        }
    }

    public void changeRole(){
        for (User user : userList) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getName() == RoleName.PM || roles.get(0).getName() == RoleName.USER)
                System.out.printf("ID: %d - Name: %s - %s - Status: %b \n", user.getId(), user.getName(), user.getRoles(), user.isStatus());
        }
        System.out.println("Nhập người dùng bạn muốn sửa: ");
        int id = Config.getInteger();
        User user = userController.findUserById(id);
        if (user != null) {
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if (roles.get(0).getName() != RoleName.ADMIN) {
                System.out.printf("Tên người dùng: %s - Chức năng: %s \n", user.getName(), roles.get(0).getName());
                while (true) {
                    if (roles.get(0).getName() == RoleName.USER) {
                        System.out.println("Bạn có muốn chuyển người dùng này thành PM không? Nhập Y/N");
                    } else {
                        System.out.println("Bạn có muốn chuyển người dùng này thành USER không? Nhập Y/N");
                    }
                    String choice = Config.getString();
                    if (choice.equalsIgnoreCase("y")) {
                        userController.changeRole(user);
                        System.out.println("Thay đổi thành công!");
                        new AdminView();
                        break;
                    }
                    if (choice.equalsIgnoreCase("n")) {
                        new AdminView();
                        break;
                    }
                    System.out.println("Không thể thực hiện!!!");
                }
            } else {
                System.out.println("Không thể thực hiện!!!");
                new AdminView();
            }
        }
        System.out.println("Id không tồn tại!!!");
        new AdminView();
    }

    public void showInfoAdmin(){
        User user = userController.getUserLogin();
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("|                             TÀI KHOẢN ADMIN                                   |");
        System.out.println("|-------------------------------------------------------------------------------|");
        System.out.printf("|  -  Tên đăng nhập:%-60s|\n",user.getUserName());
        System.out.printf("|  -  Tên          :%-60s|\n",user.getName());
        System.out.printf("|  -  Email        :%-60s|\n",user.getEmail());
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.println("| 1. Đổi mật khẩu.                                                              |");
        System.out.println("| 9. Quay lại.                                                                  |");
        System.out.println("| 0. Thoát                                                                      |");
        System.out.println("*-------------------------------------------------------------------------------*");
        System.out.print("Nhập vào lựa chọn của bạn: ");
        int choice = Config.getInteger();
        switch (choice){
            case 1:
                changeAdminPassword(user);
                break;
            case 9:
                 new AdminView();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Không hợp lệ!!!");
                showInfoAdmin();
        }
    }

    private void changeAdminPassword(User user) {
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
                System.out.println("Đổi mật khẩu thành công!");
                break;
            }
            System.out.println("Mật khẩu không hợp lệ!");
        }
        showInfoAdmin();
    }
}
