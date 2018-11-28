//package com.example.tj.qq.activity;
//
//import android.content.Intent;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.alibaba.fastjson.JSON;
//import com.example.tj.qq.R;
//import com.example.tj.qq.item.UserItem;
//import com.lzy.okgo.OkGo;
//import com.lzy.okgo.callback.StringCallback;
//import com.lzy.okgo.model.Response;
//
//import org.json.JSONObject;
//
//public class RegisterAcountActivity extends BaseActivity implements View.OnClickListener {
//    private EditText nameET;
//    private EditText pwdET;
//    private Button registerBTN;
//
//    @Override
//    protected int getLayoutId() {
//        return R.layout.register_account;
//    }
//
//    @Override
//    protected void initView() {
//        nameET = findViewById(R.id.edit_name);
//        pwdET = findViewById(R.id.edit_password);
//        registerBTN = findViewById(R.id.btn_register);
//
//
//    }
//
//    @Override
//    protected void initData() {
//
//    }
//
//    @Override
//    protected void initListener() {
//        findViewById(R.id.text_back).setOnClickListener(this);
//        registerBTN.setOnClickListener(this);
//
//    }
//
//
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.text_back:
//                finish();
//                break;
//            case R.id.btn_register:
//                doRegister();
//        }
//    }
//
//    private void doRegister(){
//        String userName = nameET.getText().toString().trim();
//        String password = pwdET.getText().toString().trim();
//        UserItem userItem = new UserItem();
//        userItem.setUserName(userName);
//        userItem.setPassword(password);
//
//        org.json.JSONObject jsonObject = null;
//        try{
//            jsonObject = new JSONObject(JSON.toJSONString(userItem));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        OkGo.<String>post("http://139.199.14.212:10086/user/register")//
//                .upJson(jsonObject)//
//                .execute(new StringCallback() {
//                    @Override
//                    public void onSuccess(Response<String> response) {
//                        String backstr = response.body();
//                        com.alibaba.fastjson.JSONObject jo = JSON.parseObject(backstr);
//                        if (jo.getIntValue("code") == 200){
//                            //注册成功
//                            String jsonStr = jo.getString("data");
//                            UserItem userItem1 = JSON.parseObject(jsonStr,UserItem.class);
//                            Intent intent = new Intent(RegisterAcountActivity.this,LoginActivity.class);
//                            startActivity(intent);
//
//                        }
//
//                        Toast toast = Toast.makeText(RegisterAcountActivity.this,jo.getString("message"),Toast.LENGTH_LONG);
//                        LinearLayout linearLayout = (LinearLayout) toast.getView();
//                        TextView messageTextView = (TextView) linearLayout.getChildAt(0);
//                        messageTextView.setTextSize(60);
//                        toast.show();
//                    }
//                });
//    }
//}
