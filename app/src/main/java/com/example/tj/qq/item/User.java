package com.example.tj.qq.item;

public class User {
//    {
//    "code": 200,
//    "message": "登录成功",
//    "data": {
//        "id": 2,
//        "userName": "张三",
//        "password": "123456",
//        "headUrl": null
//    }
//    }

    private int code;
    private String message;
    private User1 data;

    class User1 {
        private int id;
        private String userName;
        private String password;
        private String headUrl;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getHeadUrl() {
            return headUrl;
        }

        public void setHeadUrl(String headUrl) {
            this.headUrl = headUrl;
        }
    }


}
