package com.example.applogin;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity1 extends AppCompatActivity {

    EditText username, password;
    Button btnLogin;
    DBHelper DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);

        username = findViewById(R.id.textLoginUser);
        password = findViewById(R.id.textLoginPssw);
        btnLogin = findViewById(R.id.btnLoginEntrar);
        DB = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") || pass.equals("")){
                    Toast.makeText(LoginActivity1.this, "Por favor, preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean verrificaUserPass = DB.verificaUsernameSenha(user, pass);
                    if(verrificaUserPass == true){
                        Toast.makeText(LoginActivity1.this, "Login realizado com sucesso.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity1.this, "Usuário ou senhas incorretos.", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });

    }
}