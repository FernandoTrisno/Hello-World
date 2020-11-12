package com.example.helloworld;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnLogin;
    private Button btnSignUp;
    private Database database;
    private Preferences sharedPrefManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: dipanggil");
        Log.i(TAG, "onCreate: percobaan");
        setContentView(R.layout.activity_main);
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        database = new Database(this);
        sharedPrefManager = new Preferences(this);
        btnLogin.setOnClickListener(v -> onClickBtnLogin());
        btnSignUp.setOnClickListener(s -> onClickBtnSignUp(s));

        Button btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        if (sharedPrefManager.getSPSudahLogin()) {
            startActivity(new Intent(LoginActivity.this, HomeActivity.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }



    private void onClickBtnLogin() {
        String username = txtUsername.getText().toString().trim();
        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
        String password = txtPassword.getText().toString().trim();
        Toast.makeText(getApplicationContext(), "Selamat Datang " + txtUsername.getText(), Toast.LENGTH_LONG).show();
        Boolean res = database.checkUser(username,password);
        startActivity(i);
        if(res == true){
            Toast.makeText(LoginActivity.this, "Login Berhasil", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Maaf tidak ada " + txtUsername.getText() + " dalam sistem", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            sharedPrefManager.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, true);
            finish();
        }
        else {
            Toast.makeText(LoginActivity.this, "Login Gagal", Toast.LENGTH_SHORT).show();
        }
    }
    public static Spanned fromHtml(String html){
        Spanned result;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        }else {
            result = Html.fromHtml(html);
        }
        return result;
    }



    private void onClickBtnSignUp(View s) {
        Toast.makeText(getApplicationContext(), "Sign Up di Tekan", Toast.LENGTH_LONG).show();
        startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
    }
}
