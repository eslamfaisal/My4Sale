package com.fekrah.my4sale.activities;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.Cities;
import com.fekrah.my4sale.models.User;
import com.fekrah.my4sale.server.Apis;
import com.fekrah.my4sale.server.BaseClient;
import com.rafakob.drawme.DrawMeTextView;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.zelory.compressor.Compressor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @BindView(R.id.email)
    EditText email;

    @BindView(R.id.last_name)
    EditText last_name;

    @BindView(R.id.first_name)
    EditText first_name;

    @BindView(R.id.pass)
    EditText pass;

    @BindView(R.id.address)
    EditText address;

    @BindView(R.id.phone)
    EditText phone;

    @BindView(R.id.cities_spinner)
    Spinner citiesSpinner;

    @BindView(R.id.user_image)
    SimpleDraweeView user_image;

    @BindView(R.id.register)
    DrawMeTextView registerButton;

    private String loginType;
    private Apis apis;

    private String REQUEST_FOR_PICTURE;
    private static final String PROFILE_IMAGE_REQUEST = "PROFILE_IMAGE_REQUEST";
    private Bitmap thumbBitmap = null;
    UCrop.Options options;
    byte[] profilebyte;
    private Uri imageUri;

    List<String> cities = new ArrayList<>();
    List<String> citiesId = new ArrayList<>();

    public static String CITY_ID;
    private List<Cities.CityData> citiesBody;

    boolean edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        getcities();
        Intent intent = getIntent();
        if (intent.getStringExtra("edit")!=null){
            edit = true;
            pass.setVisibility(View.GONE);
            setTitle(getString(R.string.update));
            user_image.setImageURI(SharedHelper.getKey(getApplicationContext(), LoginActivity.IMAGE));
            first_name.setText(SharedHelper.getKey(getApplicationContext(), LoginActivity.FIRST_NAME));
            last_name.setText(SharedHelper.getKey(getApplicationContext(), LoginActivity.SECOND_NAME));
            phone.setText(SharedHelper.getKey(getApplicationContext(), LoginActivity.PHONE));
            email.setText(SharedHelper.getKey(getApplicationContext(), LoginActivity.EMAIL));
            address.setText(SharedHelper.getKey(getApplicationContext(), LoginActivity.DESTRICT));
            registerButton.setText(getString(R.string.update));
        }else {
            user_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    REQUEST_FOR_PICTURE = PROFILE_IMAGE_REQUEST;
                    ImagePicker.create(RegisterActivity.this)
                            .limit(1)
                            .theme(R.style.UCrop)
                            .folderMode(true)
                            .start();
                }
            });
        }
        options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));


    }

    private void getcities() {
        if (cities != null && cities.size() > 1) {
            cities.clear();
        }
        Call<Cities> getCiriesCall = BaseClient.getApi().getCities();

        getCiriesCall.enqueue(new Callback<Cities>() {
            @Override
            public void onResponse(Call<Cities> call, Response<Cities> response) {
                if (response.body() != null) {
                    if (response.body().getCities().size() > 0) {

                        citiesBody = response.body().getCities();
                        for (Cities.CityData city : citiesBody) {
                            cities.add(city.getCity_name());
                            citiesId.add(city.getId());
                        }
                        ArrayAdapter adapter = new ArrayAdapter(RegisterActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        citiesSpinner.setAdapter(adapter);
                        citiesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CITY_ID = String.valueOf(citiesBody.get(position).getId());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Cities> call, Throwable t) {

            }
        });
    }

    @OnClick(R.id.register)
    void registerProvider() {
        if (!edit)
        register();

    }

    private void register() {
        final Dialog dialog = new Dialog(this);
        dialog.show();
        if (first_name.getText().toString().equals("") ||
                last_name.getText().toString().equals("") ||
                email.getText().toString().equals("") ||
                phone.getText().toString().equals("") ||
                pass.getText().toString().equals("") ||
                imageUri == null) {
            dialog.dismiss();
            Toast.makeText(this, getString(R.string.fill_all), Toast.LENGTH_SHORT).show();

            return;
        }
        File file2 = new File(imageUri.getPath());

        RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
        MultipartBody.Part image = MultipartBody.Part.createFormData("image", String.valueOf(System.currentTimeMillis() + ".jpg"), surveyBody);
        RequestBody name1 = RequestBody.create(MediaType.parse("text/plain"), first_name.getText().toString());
        RequestBody name2 = RequestBody.create(MediaType.parse("text/plain"), last_name.getText().toString());
        RequestBody fullName = RequestBody.create(MediaType.parse("text/plain"), first_name.getText().toString() + " " + last_name.getText().toString());
        RequestBody email2 = RequestBody.create(MediaType.parse("text/plain"), email.getText().toString());
        RequestBody phone2 = RequestBody.create(MediaType.parse("text/plain"), phone.getText().toString());
        RequestBody address2 = RequestBody.create(MediaType.parse("text/plain"), address.getText().toString());
        RequestBody pass2 = RequestBody.create(MediaType.parse("text/plain"), pass.getText().toString());

        BaseClient.getApi().registerUser(
                fullName,
                name1,
                name2,
                phone2,
                email2,
                pass2,
                Integer.parseInt(CITY_ID),
                address2,
                image

        ).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body() != null) {
                    User user = response.body();
                    if (user.getUser() != null) {

                        SharedHelper.putKey(getApplicationContext(), "log_in", "yes");
                        SharedHelper.putKey(getApplicationContext(), "user_city", user.getUser().getCity());
                        SharedHelper.putKey(getApplicationContext(), "user_name", user.getUser().getUsername());
                        SharedHelper.putKey(getApplicationContext(), "user_address", user.getUser().getDistrict());
                        SharedHelper.putKey(getApplicationContext(), "user_email", user.getUser().getEmail());
                        SharedHelper.putKey(getApplicationContext(), "user_image", user.getUser().getImage());
                        SharedHelper.putKey(getApplicationContext(), "user_token", user.getUser().getToken());
                        SharedHelper.putKey(getApplicationContext(), "user_mobile", user.getUser().getMobile());
                        SharedHelper.putKey(getApplicationContext(), "user_id", user.getUser().getUser_id());
                        Log.d("llllll", "onResponse: " + user.getUser().getUser_id());

                        //   MainActivity.hideClient();
                        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                        finish();

                    } else {
                        Toast.makeText(RegisterActivity.this, getString(R.string.exist_email), Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(RegisterActivity.this, getString(R.string.wrong_data), Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, getString(R.string.error_try_again), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            //Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            return;
        }
        String destinationFileName = "SAMPLE_CROPPED_IMAGE_NAME" + ".jpg";

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            Image image = ImagePicker.getFirstImageOrNull(data);

            Uri res_url = Uri.fromFile(new File((image.getPath())));
            //imageUri = image.getPath();
            CropImage(image, res_url);

        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            //  if (resultUri!=null)
            assert resultUri != null;
            bitmapCompress(resultUri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thumbBitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);

            profilebyte = byteArrayOutputStream.toByteArray();
            user_image.setImageURI(resultUri);
            user_image.setVisibility(View.VISIBLE);

            imageUri = resultUri;

        }

    }

    private void CropImage(Image image, Uri res_url) {
        UCrop.of(res_url, Uri.fromFile(new File(this.getCacheDir(), image.getName())))
                .withOptions(options)
                .start(this);
    }

    private void bitmapCompress(Uri resultUri) {
        final File thumbFilepathUri = new File(resultUri.getPath());

        try {
            thumbBitmap = new Compressor(this)
                    .setQuality(50)
                    .compressToBitmap(thumbFilepathUri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void goMain(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }
}
