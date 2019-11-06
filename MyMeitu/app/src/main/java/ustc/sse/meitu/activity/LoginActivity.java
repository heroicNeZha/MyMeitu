package ustc.sse.meitu.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import ustc.sse.meitu.R;
import ustc.sse.meitu.Service.UserService;
import ustc.sse.meitu.pojo.MyApplicationContext;
import ustc.sse.meitu.pojo.PicData;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.utils.ToastUtils;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;

    private MyApplicationContext myAppCtx;


    @BindView(R.id.et_username)
    EditText _usernameText;
    @BindView(R.id.et_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;

    UserService userService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        myAppCtx = ((MyApplicationContext) this.getApplicationContext());
        myAppCtx.setPicData(new PicData());
        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        userService = new UserService();

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own authentication logic here.

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        new Thread(() -> {
            String result = userService.Login(user);
            Message msg = new Message();
            Bundle data = new Bundle();
            data.putString("value", result);
            msg.setData(data);
            handler.sendMessage(msg);
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();
            String result = data.getString("value");
            if (result.split(":", 2)[0].

                    equals("success")) {
                myAppCtx.setToken(result.split(":", 2)[1]);
                onLoginSuccess();
            } else {
                onLoginFailed();
                _passwordText.post(() -> _passwordText.setError(result.split(":", 2)[1]));
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        ToastUtils.showLong(LoginActivity.this, "Login failed");
        _loginButton.post(() -> _loginButton.setEnabled(true));
    }

    public boolean validate() {
        boolean valid = true;

        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();
        User user = new User();
        if (username.isEmpty() || username.length() < 4) {
            _usernameText.setError("输入有效用户名");
            valid = false;
        } else {
            user.setUsername(username);
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4) {
            _passwordText.setError("长度大于4");
            valid = false;
        } else {
            user.setPassword(password);
            _passwordText.setError(null);
        }


        return valid;
    }
}
