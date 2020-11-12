package com.example.helloworld;

import android.content.ContentValues;
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

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = SignUpActivity.class.getSimpleName();

    private EditText txtUsername;
    private EditText txtPassword;
    private EditText txtCPassword;
    private Button btnSignUp;
    Database db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new Database(this);
        Log.i(TAG, "onCreate: dipanggil");
        Log.i(TAG, "onCreate: percobaan");
        setContentView(R.layout.activity_sign_up);

        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        txtCPassword= findViewById(R.id.txtCPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString().trim();
                String password = txtPassword.getText().toString().trim();
                String cPassword = txtCPassword.getText().toString().trim();

                ContentValues values = new ContentValues();


                if (!password.equals(cPassword)){
                    Toast.makeText(SignUpActivity.this, "Password tidak cocok!", Toast.LENGTH_SHORT).show();
                }else if (password.equals("") || username.equals("")){
                    Toast.makeText(SignUpActivity.this, "Username atau Password tidak boleh kosong!", Toast.LENGTH_SHORT).show();
                }else {
                    values.put(Database.row_username, username);
                    values.put(Database.row_password, password);
                    db.insertData(values);

                    Toast.makeText(SignUpActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void onClickBtnSignUp(View v) {
        String tempP = txtPassword.getText().toString();
        String tempCP = txtCPassword.getText().toString();
        if (tempP==tempCP) {
            // Masuk ke activity baru
            Intent intent = new Intent(v.getContext(), LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Password Tidak Sesuai, Ulangi!!!", Toast.LENGTH_LONG).show();
        }

        //   startActivity(new Intent(this, HomeActivity.class));
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

}