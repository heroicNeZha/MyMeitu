package ustc.sse.meitu.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import ustc.sse.meitu.R;
import ustc.sse.meitu.Service.UserService;
import ustc.sse.meitu.pojo.MyApplicationContext;
import ustc.sse.meitu.pojo.User;
import ustc.sse.meitu.utils.ToastUtils;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";

    @BindView(R.id.et_name)
    EditText _nameText;
    @BindView(R.id.et_username)
    EditText _usernameText;
    @BindView(R.id.et_password)
    EditText _passwordText;
    @BindView(R.id.btn_signup)
    Button _signupButton;
    @BindView(R.id.link_login)
    TextView _loginLink;

    UserService userService;

    private MyApplicationContext myAppCtx;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        myAppCtx = ((MyApplicationContext) this.getApplicationContext());
        userService = new UserService();

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "Signup");

        if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        //可以使用进度条

        String name = _nameText.getText().toString();
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        // TODO: Implement your own signup logic here.

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        new Thread(() -> {
            String result = userService.Register(user);
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
                onSignupSuccess();
            } else {
                onSignupFailed();
                _passwordText.post(() -> _passwordText.setError(result.split(":", 2)[1]));
            }
        }
    };


    public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        ToastUtils.showShort(getBaseContext(),"注册成功");
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String username = _usernameText.getText().toString();
        String password = _passwordText.getText().toString();

        if (name.isEmpty() || name.length() < 3) {
            _nameText.setError("至少3个字符");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (username.isEmpty() || username.length() < 4) {
            _usernameText.setError("输入有效用户名");
            valid = false;
        } else {
            _usernameText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 20) {
            _passwordText.setError("密码大于6位且不能为空");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
