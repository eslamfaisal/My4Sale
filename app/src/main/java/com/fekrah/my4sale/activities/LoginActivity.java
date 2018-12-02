package com.fekrah.my4sale.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.fekrah.my4sale.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goMain(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void goRegister(View view) {
        startActivity(new Intent(this,RegisterActivity.class));
    }
}
