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
    private int age;
    private String sex;
    private String headUrl;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

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
        return "UserItem{" + "userName='" + userName + '\'' + ", age=" + age + ", sex='" + sex + '\'' + '}';
    }
}
