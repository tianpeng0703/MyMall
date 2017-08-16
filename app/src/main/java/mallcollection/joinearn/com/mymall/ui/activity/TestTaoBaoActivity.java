package mallcollection.joinearn.com.mymall.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ali.auth.third.ui.context.CallbackContext;
import com.alibaba.baichuan.trade.biz.login.AlibcLogin;
import com.alibaba.baichuan.trade.biz.login.AlibcLoginCallback;

import mallcollection.joinearn.com.mymall.R;

public class TestTaoBaoActivity extends AppCompatActivity {

    private Button mLogin;
    private Button mLogout;;
    private Button mTride;;
    private Button mOrder;;
    private Button mProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        mLogin = (Button)findViewById(R.id.login);
        mLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);
            }
        });

        mLogout = (Button)findViewById(R.id.logout);
        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout(view);
            }
        });
        mTride = (Button)findViewById(R.id.tride);
        mTride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                trade(view);
            }
        });
        mOrder = (Button)findViewById(R.id.order);
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                orderAndCart(view);
            }
        });
        mProxy = (Button)findViewById(R.id.proxy);
        mProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webview(view);
            }
        });
    }

    /**
     * 登录
     */
    public void login(View view) {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.showLogin(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(TestTaoBaoActivity.this, "登录成功 ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int code, String msg) {
                Toast.makeText(TestTaoBaoActivity.this, "登录失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * 退出登录
     */
    public void logout(View view) {

        AlibcLogin alibcLogin = AlibcLogin.getInstance();

        alibcLogin.logout(new AlibcLoginCallback() {
            @Override
            public void onSuccess(int i) {
                Toast.makeText(TestTaoBaoActivity.this, "退出成功 ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(int i, String s) {
                Toast.makeText(TestTaoBaoActivity.this, "退出失败 ",
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    /**
     * 电商交易
     */
    public void trade(View view) {
        Intent transactionIntent = new Intent(TestTaoBaoActivity.this, AliSdkTransactionActivity.class);
        startActivity(transactionIntent);
    }

    /**
     * 订单和购物车
     */
    public void orderAndCart(View view) {
        Intent mineIntent = new Intent(TestTaoBaoActivity.this, AliSdkOrderActivity.class);
        startActivity(mineIntent);
    }

    /**
     * webview代理
     */
    public void webview(View view) {
        Intent webviewIntent = new Intent(TestTaoBaoActivity.this, AliSdkWebViewProxyActivity.class);
        startActivity(webviewIntent);
    }

    //登录须重写onActivityResult方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        CallbackContext.onActivityResult(requestCode, resultCode, data);
    }
}
