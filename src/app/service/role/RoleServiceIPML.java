package app.service.role;

import app.model.Role;
import app.model.RoleName;

import java.util.ArrayList;
import java.util.List;

public class RoleServiceIPML implements IRoleService{
    public static List<Role> roleList = new ArrayList<>();
    static {
        roleList.add(new Role(1,RoleName.USER));
        roleList.add(new Role(2,RoleName.PM));
        roleList.add(new Role(3,RoleName.ADMIN));
    }

    @Override
    public List<Role> findAll() {
        return roleList;
    }

    @Override
    public Role findByName(RoleName name) {
        for (int i = 0; i < roleList.size(); i++) {
            if (roleList.get(i).getName()==name){
                return roleList.get(i);
            }
        }
        return null;
    }
}
