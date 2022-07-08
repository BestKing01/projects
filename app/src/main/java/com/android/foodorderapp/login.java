package com.android.foodorderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.foodorderapp.model.User;

import java.util.ArrayList;

public class login extends AppCompatActivity {
    ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteDatabase db = openOrCreateDatabase("app.db",MODE_PRIVATE,null);

        Cursor query = db.rawQuery("Select * from users", null);
        while (query.moveToNext()){
            User user = new User();
            String Username = query.getString(0);
            String Email = query.getString(1);
            String password = query.getString(2);
            user.setUsername(Username);
            user.setEmail(Email);
            user.setPassword(password);
            users.add(user);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        EditText edUsername = findViewById(R.id.UsernameLogin);
        EditText edPassword = findViewById(R.id.UserPassword);


        Button btnLogin = findViewById(R.id.btnSignIn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Usname = edUsername.getText().toString();
                String USpassword = edPassword.getText().toString();
                for(User user : users){
                    if(Usname.equals(user.getUsername())){
                        if(USpassword.equals(user.getPassword())){
                            Toast.makeText(login.this, "User Found", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(login.this, com.android.foodorderapp.login.class));

                        }
                        else {
                            Toast.makeText(login.this, "User not found", Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });

        ImageButton btnBack = findViewById(R.id.imageButtonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this, MainActivity.class));

            }
        });
    }
}