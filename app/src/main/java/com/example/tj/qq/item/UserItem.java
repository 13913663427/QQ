package com.example.tj.qq.item;

public class UserItem {

    public UserItem() {
    }

    public UserItem(String userName, String password) {
            this.userName = userName;
            this.password = password;
    }

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserItem{" + "userName='" + userName + '\'' + ", password='" + password + '\'' + '}';
    }
}
