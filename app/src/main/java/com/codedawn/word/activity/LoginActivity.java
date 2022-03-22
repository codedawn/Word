package com.codedawn.word.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.widget.Toast;

import com.codedawn.word.R;
import com.codedawn.word.litepal.UserData;
import com.codedawn.word.util.ConstantData;
import com.codedawn.word.util.LogUtil;
import com.google.android.material.button.MaterialButton;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.AuthAgent;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.lang.reflect.Field;
import java.util.List;

public class LoginActivity extends BasicActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    //QQ包名
    private static final String PACKAGE_QQ = "com.tencent.mobileqq";

    private IUiListener listener;
    private Tencent mTencent;

    private MaterialButton buttonLogin;

    private UserInfo mInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
        // 其中APP_ID是分配给第三方应用的appid，类型为String。
        mTencent = Tencent.createInstance(ConstantData.TENCENT_APP_ID, this.getApplicationContext());

        Tencent.setIsPermissionGranted(true);
        // 1.4版本:此处需新增参数，传入应用程序的全局context，可通过activity的getApplicationContext方法获取
        // 初始化视图
        init();
        initListener();

    }

    private void init() {
        buttonLogin = findViewById(R.id.button_login);

        listener = new QQUiListener();
    }

    private void initListener() {
        buttonLogin.setOnClickListener(v -> {
            LogUtil.d(TAG, "点击了登录按钮");
            QQLogin();
        });
    }

    private void QQLogin() {
        //注意：此段非必要，如果手机未安装应用则会跳转网页进行授权
        if (!fixWebAuth(LoginActivity.this, mTencent)) {
            Toast.makeText(LoginActivity.this, "未安装QQ应用",
                    Toast.LENGTH_SHORT).show();
        }
        //如果session无效，就开始做登录操作
        if (!mTencent.isSessionValid()) {
            //context上下文、第二个参数SCOPO 是一个String类型的字符串，表示一些权限
            //应用需要获得权限，由“,”分隔。例如：SCOPE = “get_user_info,add_t”；所有权限用“all”
            //第三个参数事件监听器
            mTencent.login(this, "all", listener);
        }
    }

    /**
     * true 安装了相应包名的app
     */
    private boolean hasApp(LoginActivity context, String packName) {
        boolean is = false;
        List<PackageInfo> packages = context.getPackageManager()
                .getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String packageName = packageInfo.packageName;
            if (packageName.equals(packName)) {
                is = true;
            }
        }
        return is;
    }

    /**
     * 解决网页授权问题
     *
     * @return true: 成功; false: 失败
     */
    private boolean fixWebAuth(Context context, Tencent tencent) {
        if (hasApp(LoginActivity.this, PACKAGE_QQ)) {
            return true;
        }
        if (tencent == null) {
            return false;
        }
        boolean ok = false;
        try {
            Field aField = tencent.getClass().getDeclaredField("a");
            aField.setAccessible(true);
            Object cVal = aField.get(tencent);
            Field authAgentField = cVal.getClass().getDeclaredField("a");
            authAgentField.setAccessible(true);
            authAgentField.set(cVal, new FixWebAuthAgent(tencent.getQQToken()));
            ok = true;
        } catch (Throwable ignore) {
        }
        return ok;
    }

    /**
     * 解决网页授权问题的AuthAgent
     */
    private static class FixWebAuthAgent extends AuthAgent {

        FixWebAuthAgent(QQToken qqToken) {
            super(qqToken);
        }

        @Override
        protected Bundle a() {
            Bundle bundle = super.a();
            if (bundle != null) {
                // 主要原因在这, 腾讯的server估计是判断这个版本号来进行限制登录的, 从1.5版本后不能打开网页授权了
                bundle.putString("sdkv", "1.4.0");
            }
            return bundle;
        }
    }


    private class QQUiListener implements IUiListener {

        @Override
        public void onComplete(Object object) { //登录成功
            LogUtil.e(TAG, "登录成功: " + object.toString());
            //获取openid和token
            initOpenIdAndToken(object);
            //获取用户信息
            getUserInfo();
        }

        @Override
        public void onError(UiError uiError) {
            //登录失败
            LogUtil.e(TAG, "登录失败" + uiError.errorDetail);
            LogUtil.e(TAG, "登录失败" + uiError.errorMessage);
            LogUtil.e(TAG, "登录失败" + uiError.errorCode + "");
        }

        @Override
        public void onCancel() {
            //取消登录
            LogUtil.d(TAG, "登录取消");
        }

        @Override
        public void onWarning(int i) {
            //警告
            LogUtil.w(TAG, "" + i);
        }
    }

    private void getUserInfo() {
        QQToken token = mTencent.getQQToken();
        mInfo = new UserInfo(LoginActivity.this, token);
        mInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object object) {
                JSONObject jb = (JSONObject) object;
                try {
                    UserData userData = UserData.getInstance();
                    userData.setNickname(jb.getString("nickname"));
                    userData.setAvatar(jb.getString("figureurl_qq_2")); //头像图片的url

                    int count = LitePal.where("openid = ?", userData.getOpenId()).count("userdata");
                    if (count < 1) {
                        userData.save();
                    } else {
                        userData.updateAll("openid = ?", userData.getOpenId());
                    }
                    //登录成功
                    Intent intent = new Intent(LoginActivity.this, BookListActivity.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(UiError uiError) {
                LogUtil.d(TAG, "获取userinfo错误");
            }

            @Override
            public void onCancel() {
                LogUtil.d(TAG, "获取userinfo取消");
            }

            @Override
            public void onWarning(int i) {
                LogUtil.d(TAG, "获取userinfo警告" + i);
            }
        });
    }

    private void initOpenIdAndToken(Object object) {
        JSONObject jb = (JSONObject) object;
        try {
            String openID = jb.getString("openid");  //openid用户唯一标识
            String access_token = jb.getString("access_token");
            String expires = jb.getString("expires_in");

            UserData userData = UserData.getInstance();
            userData.setAccessToken(access_token);
            userData.setOpenId(openID);
            userData.setExpire(expires);

            mTencent.setOpenId(openID);
            mTencent.setAccessToken(access_token, expires);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 回调必不可少,官方文档未说明
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //腾讯QQ回调
        Tencent.onActivityResultData(requestCode, resultCode, data, listener);
        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, listener);
            }
        }
    }
}