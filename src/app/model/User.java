package app.model;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private int id;
    private String Name;
    private String userName;
    private String email;
    private String password;
    private boolean status;
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(int id, String name, String userName, String email, String password, boolean status, Set<Role> roles) {
        this.id = id;
        Name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.status = status;
        this.roles = roles;
    }

    public User(int id, String name, String userName, String email, String password , Set<Role> roleSet){
        this.id = id;
        Name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.roles = roleSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", roles=" + roles +
                '}';
    }
}
