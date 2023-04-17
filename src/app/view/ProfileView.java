package app.view;

import app.controller.UserController;
import app.model.Role;
import app.model.RoleName;
import app.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProfileView {
    UserController userController = new UserController();
    public ProfileView() {
        User user = userController.getUserLogin();
        if(user!=null){
            Set<Role> roleSet = user.getRoles();
            List<Role> roles = new ArrayList<>(roleSet);
            if(roles.get(0).getName()== RoleName.ADMIN){
                System.out.println("PHẦN DÀNH CHO ADMIN");
            } else if(roles.get(0).getName()==RoleName.USER){
                System.out.println("PHẦN DÀNH CHO USER");
            }
        }
    }
}
