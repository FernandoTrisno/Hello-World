package com.example.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: dipanggil");
        Log.i(TAG, "onCreate: percobaan");
        setContentView(R.layout.activity_main);
        txtUsername=findViewById(R.id.txtUsername);
        txtPassword=findViewById(R.id.txtPassword);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    onClickBtnLogin(v);

            }
        });
    }
    private void onClickBtnLogin(View v){
        Toast.makeText(getApplicationContext(), "Button di Tekan", Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), txtUsername.getText(), Toast.LENGTH_LONG).show();
        Toast.makeText(getApplicationContext(), txtPassword.getText(), Toast.LENGTH_LONG).show();

        if(txtUsername.getText().toString().equals("admin")&&txtPassword.getText().toString().equals("admin")){
            // Masuk ke activity baru
            Intent intent = new Intent(v.getContext(), HomeActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(),"Username atau Password Anda tidak benar!",Toast.LENGTH_LONG).show();
        }

     //   startActivity(new Intent(this, HomeActivity.class));
    }
}