package com.example.applogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username, password, passwordConfirm;
    Button btnLogar, btnCadastrar;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.txtUser);
        password = findViewById(R.id.txtPassword);
        passwordConfirm = findViewById(R.id.txtPassword2);
        btnLogar = findViewById(R.id.btnLogar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        DB = new DBHelper(this);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String passConfirm = passwordConfirm.getText().toString();

                if(user.equals("") || pass.equals("") || passConfirm.equals("")){
                    Toast.makeText(MainActivity.this, "Por favor preencha todos os campos.", Toast.LENGTH_SHORT).show();
                }else{
                    if(pass.equals(passConfirm)){
                        Boolean verificaUser = DB.verificaUsername(user);
                        if(verificaUser == false){
                            Boolean insert = DB.insertData(user, pass);
                            if(insert == true){
                                Toast.makeText(MainActivity.this, "Cadastrado com sucesso.", Toast.LENGTH_SHORT).show();
                                Intent intent  =new Intent(getApplicationContext(), Home.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(MainActivity.this, "Cadastro não realizado.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Usuário já existente. Por favor, faça login.", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(MainActivity.this, "As senhas precisam ser iguais.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),LoginActivity1.class);
                startActivity(intent);

            }
        });



    }
}