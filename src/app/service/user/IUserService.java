package app.service.user;

import app.model.User;
import app.service.IGenericService;

public interface IUserService extends IGenericService<User> {
    boolean existedByUserName(String userName);
    boolean existedByEmail(String email);
    boolean checkLogin(String username, String password);
    User getCurentUser();
    void signOut();
}
