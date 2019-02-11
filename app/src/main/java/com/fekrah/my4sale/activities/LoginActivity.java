package com.fekrah.my4sale.activities;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.User;
import com.fekrah.my4sale.server.BaseClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    public static final String USER_NAME = "user_name";
    public static final String IS_LOGIN = "LOGIN";
    public static final String USER_ID = "user_id";
    public static final String FIRST_NAME = "FIRST_NAME";
    public static final String SECOND_NAME = "SECOND_NAME";
    public static final String EMAIL = "EMAIL";
    public static final String PHONE = "PHONE";
    public static final String CITY = "CITY";
    public static final String DESTRICT = "DESTRICT";
    public static final String IMAGE = "IMAGE";
    public static final String TOAKEN = "TOAKEN";
    public static final String ACTIVE = "ACTIVR";

    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.email)
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        email.setText("eslam.faisal.ef@gmail.com");
        pass.setText("123456");
    }

    @OnClick(R.id.log_in)
    void logInUserClient() {
        final Dialog dialog = new Dialog(this);
        dialog.show();
        if (
                email.getText().toString().equals("") ||
                        pass.getText().toString().equals("")) {
            dialog.dismiss();
            Toast.makeText(this, getString(R.string.fill_all), Toast.LENGTH_SHORT).show();
            return;
        }
        BaseClient.getApi().logInClient(
                email.getText().toString(),
                pass.getText().toString()
        ).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null) {
                    User user = response.body();
                    if (user.getUser() != null) {

                        SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "yes");
                        SharedHelper.putKey(getApplicationContext(), CITY, user.getUser().getCity());
                        SharedHelper.putKey(getApplicationContext(), USER_NAME, user.getUser().getUsername());
                        SharedHelper.putKey(getApplicationContext(), FIRST_NAME, user.getUser().getFirst_name());
                        SharedHelper.putKey(getApplicationContext(), SECOND_NAME, user.getUser().getLast_name());
                        SharedHelper.putKey(getApplicationContext(), DESTRICT, user.getUser().getDistrict());
                        SharedHelper.putKey(getApplicationContext(), EMAIL, user.getUser().getEmail());
                        SharedHelper.putKey(getApplicationContext(), IMAGE, user.getUser().getImage());
                        SharedHelper.putKey(getApplicationContext(), TOAKEN, user.getUser().getToken());
                        SharedHelper.putKey(getApplicationContext(), PHONE, user.getUser().getMobile());
                        SharedHelper.putKey(getApplicationContext(), USER_ID, user.getUser().getUser_id());
                        Log.d("llllll", "onResponse: " + user.getUser().getUser_id());

                        //   MainActivity.hideClient();
                        setResult(Activity.RESULT_OK);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, getString(R.string.exist_email), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(LoginActivity.this, getString(R.string.wrong_data), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(LoginActivity.this, getString(R.string.error_try_again), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    public void goMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    public void goRegister(View view) {
        startActivity(new Intent(this, RegisterActivity.class));
    }

    public void reset(View view) {
        startActivity(new Intent(this, ResetPassword.class));
    }

    public void skip(View view) {
        SharedHelper.putKey(getApplicationContext(), IS_LOGIN, "no");
        startActivity(new Intent(this, MainActivity.class));
    }
}
