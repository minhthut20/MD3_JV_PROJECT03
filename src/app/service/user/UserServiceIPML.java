package app.service.user;

import app.config.Config;
import app.model.User;
import database.DataBase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceIPML implements IUserService{
    List<User> userList = new Config<User>().readFromFile(DataBase.PATH_USER);

    @Override
    public List<User> findAll() {
        return userList;
    }

    public void synchonizeLoginUser(User user){
        List<User> userLogin = new ArrayList<>();
        userLogin.add(user);
        new Config<User>().writeFile(DataBase.PATH_USER_LOGIN,userLogin);
    }

    @Override
    public void save(User user) {
        User curentUser = findById(user.getId());
        if (curentUser==null){
            userList.add(user);
        }else {
            int index = userList.indexOf(curentUser);
            userList.set(index,user);
        }
        new Config<User>().writeFile(DataBase.PATH_USER,userList);

    }
    @Override
    public User findById(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId()==id){
                return userList.get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getId()==id){
                userList.remove(i);
            }
        }
    }

    @Override
    public boolean existedByUserName(String userName) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existedByEmail(String email) {
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    @Override
    public boolean checkLogin(String username, String password) {
        List<User> userLogin = new ArrayList<>();
        for (int i = 0; i < userList.size(); i++) {
            if(userList.get(i).getUserName().equals(username)&&userList.get(i).getPassword().equals(password)){
                if (userList.get(i).isStatus()){
                    System.out.println("Tài khoản đã bị khoá. Vui lòng liên hệ ADMIN");
                    return false;
                }else {
                    userLogin.add(userList.get(i));
                    new Config<User>().writeFile(DataBase.PATH_USER_LOGIN, userLogin);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public User getCurentUser() {
        if (new Config<User>().readFromFile(DataBase.PATH_USER_LOGIN).size() != 0) {
            User user = new Config<User>().readFromFile(DataBase.PATH_USER_LOGIN).get(0);
            return user;
        }
        return null;
    }

    @Override
    public void signOut() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(DataBase.PATH_USER_LOGIN);
            fileOutputStream.write(("").getBytes());
            fileOutputStream.close();
        }catch (FileNotFoundException f ){
            System.out.println("File không tồn tại!");
        }catch (IOException i){
            System.out.println("Lỗi IOE");
        }
    }
}
