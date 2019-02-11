package com.fekrah.my4sale.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsIntent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.LoginActivity;
import com.fekrah.my4sale.activities.RegisterActivity;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.Ad;
import com.fekrah.my4sale.models.AdResponse;
import com.fekrah.my4sale.models.Category;
import com.fekrah.my4sale.models.Cities;
import com.fekrah.my4sale.models.DeleteResponse;
import com.fekrah.my4sale.models.Partnerships;
import com.fekrah.my4sale.models.SubCategory;
import com.fekrah.my4sale.server.BaseClient;
import com.rafakob.drawme.DrawMeButton;
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

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddAdFragment extends Fragment {
    View mainView;

    @BindView(R.id.category_spinner)
    Spinner categorySpinner;

    @BindView(R.id.sub_category_spinner)
    Spinner subCategorySpinner;

    @BindView(R.id.city_spinner)
    Spinner citiesSpinner;

    @BindView(R.id.partnership_spinner)
    Spinner partnershipSpinner;

    @BindView(R.id.add_image)
    SimpleDraweeView image;

    @BindView(R.id.price)
    EditText price;

    @BindView(R.id.description)
    EditText description;

    @BindView(R.id.phone)
    EditText phone;

    @BindView(R.id.title)
    EditText title;

    @BindView(R.id.addTheAd)
    DrawMeButton adTheAd;

    List<String> cities = new ArrayList<>();
    List<String> citiesId = new ArrayList<>();
    public static String CITY_ID = "";
    private List<Cities.CityData> citiesBody;

    List<String> categories = new ArrayList<>();
    List<String> categoriesId = new ArrayList<>();
    public static String CATEGORY_ID = "";
    private List<Category.CategoryData> categoriesBody;

    List<String> subCategories = new ArrayList<>();
    List<String> subCategoriesId = new ArrayList<>();
    public static String SUB_CATEGORY_ID = "";
    private List<SubCategory.SubCategoryData> subCategoriesBody;

    List<String> partnerships = new ArrayList<>();
    List<String> partnershipId = new ArrayList<>();
    public static String PARTNERSHIP_ID ="";
    private List<Partnerships.PartnershipsData> partnershipsBody;

    private String REQUEST_FOR_PICTURE;
    private static final String PROFILE_IMAGE_REQUEST = "PROFILE_IMAGE_REQUEST";
    private Bitmap thumbBitmap = null;
    UCrop.Options options;
    byte[] profilebyte;
    private Uri imageUri;
    private String PARTNERSHIP_PRICE;
    private String adId;


    public AddAdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_add_ad, container, false);
        ButterKnife.bind(this, mainView);
        return mainView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments()!=null){
            if (getArguments().getSerializable("my_ad")!=null){
                Ad.AdData ad = (Ad.AdData) getArguments().getSerializable("my_ad");
               if (ad!=null){
                   adId = ad.getId();
                   image.setImageURI(ad.getImage());
                   price.setText(ad.getPrice());
                   description.setText(ad.getDes());
                   title.setText(ad.getTitle());
                   phone.setText(ad.getMobile());
                   adTheAd.setText(getString(R.string.update));
               }
            }

        }else {

        }

        options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(getActivity(), R.color.colorPrimaryDark));

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                REQUEST_FOR_PICTURE = PROFILE_IMAGE_REQUEST;
                ImagePicker.create(getActivity())
                        .limit(1)
                        .theme(R.style.UCrop)
                        .folderMode(true)
                        .start();
            }
        });
        getcities();
        getCategories();
        getPartnership();

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
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, cities);

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

    private void getPartnership() {
        if (partnerships != null && partnerships.size() > 1) {
            partnerships.clear();
        }
        Call<Partnerships> getCiriesCall = BaseClient.getApi().partnershipsCall();

        getCiriesCall.enqueue(new Callback<Partnerships>() {
            @Override
            public void onResponse(Call<Partnerships> call, Response<Partnerships> response) {
                Log.d("ppppppppppp", "onResponse: "+response.body().getPartnerships().get(0).getTime());
                if (response.body() != null) {
                    if (response.body().getPartnerships().size() > 0) {

                        partnershipsBody = response.body().getPartnerships();
                        for (Partnerships.PartnershipsData city : partnershipsBody) {
                            partnerships.add(city.getTime());
                            partnershipId.add(city.getId());
                        }
                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, partnerships);

                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        partnershipSpinner.setAdapter(adapter);
                        partnershipSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                PARTNERSHIP_ID = String.valueOf(partnershipsBody.get(position).getId());
                                PARTNERSHIP_PRICE = String.valueOf(partnershipsBody.get(position).getPrice());
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Partnerships> call, Throwable t) {

            }
        });
    }

    private void getCategories() {
        if (categories != null && categories.size() > 1) {
            categories.clear();
        }
        Call<Category> getCiriesCall = BaseClient.getApi().categoryCall();

        getCiriesCall.enqueue(new Callback<Category>() {
            @Override
            public void onResponse(Call<Category> call, Response<Category> response) {
                if (response.body() != null) {
                    if (response.body().getCategories().size() > 0) {

                        categoriesBody = response.body().getCategories();
                        for (Category.CategoryData city : categoriesBody) {
                            categories.add(city.getName());
                            categoriesId.add(city.getId());
                        }

                        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, categories);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        categorySpinner.setAdapter(adapter);
                        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                CATEGORY_ID = String.valueOf(categoriesBody.get(position).getId());
                                subCategories = new ArrayList<>();
                                BaseClient.getApi().subCategoryCall(Integer.parseInt(CATEGORY_ID)).enqueue(new Callback<SubCategory>() {
                                    @Override
                                    public void onResponse(Call<SubCategory> call, Response<SubCategory> response) {
                                        if (response.body() != null) {
                                            if (response.body().getSub_categories().size() > 0) {

                                                subCategoriesBody = response.body().getSub_categories();
                                                for (SubCategory.SubCategoryData city : subCategoriesBody) {
                                                    subCategories.add(city.getSub_category());
                                                    subCategoriesId.add(city.getSub_category_id());
                                                }
                                                ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, subCategories);

                                                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                                subCategorySpinner.setAdapter(adapter);
                                                subCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                    @Override
                                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                        SUB_CATEGORY_ID = String.valueOf(subCategoriesBody.get(position).getSub_category_id());
                                                    }

                                                    @Override
                                                    public void onNothingSelected(AdapterView<?> parent) {

                                                    }
                                                });
                                            }
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<SubCategory> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Category> call, Throwable t) {

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
            image.setImageURI(resultUri);


            imageUri = resultUri;
            Log.d("caaaaaaaaal", "onActivityResult: "+imageUri.getPath());

        }

    }

    private void CropImage(Image image, Uri res_url) {
        UCrop.of(res_url, Uri.fromFile(new File(getActivity().getCacheDir(), image.getName())))
                .withOptions(options)
                .start(getActivity());
    }

    private void bitmapCompress(Uri resultUri) {
        final File thumbFilepathUri = new File(resultUri.getPath());

        try {
            thumbBitmap = new Compressor(getActivity())
                    .setQuality(50)
                    .compressToBitmap(thumbFilepathUri);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.addTheAd)
    void sendAd(){
        if (getArguments()!=null){
            updateAdd();
        }else
        adAdd();
    }

    private void adAdd() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("جار اضافة الاعلان");
        progressDialog.show();
        if (title.getText().equals("")||
                price.getText().equals("")||
                description.getText().equals("")||
                phone.getText().equals("")||
                PARTNERSHIP_ID.equals("")||
                CITY_ID.equals("")||
                CATEGORY_ID.equals("")||
                SUB_CATEGORY_ID.equals("")||
                imageUri==null){
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "برجاء ادخال جميع البيانات", Toast.LENGTH_SHORT).show();
        }else {
            File file2 = new File(imageUri.getPath());
            RequestBody surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
            MultipartBody.Part image = MultipartBody.Part.createFormData("image", String.valueOf(System.currentTimeMillis() + ".jpg"), surveyBody);
            RequestBody title1 = RequestBody.create(MediaType.parse("text/plain"), title.getText().toString());
            RequestBody USER_ID = RequestBody.create(MediaType.parse("text/plain"), SharedHelper.getKey(getActivity(), LoginActivity.USER_ID));
            RequestBody DESTRICT = RequestBody.create(MediaType.parse("text/plain"), SharedHelper.getKey(getActivity(), LoginActivity.DESTRICT));
            RequestBody price2 = RequestBody.create(MediaType.parse("text/plain"), price.getText().toString());
            RequestBody description2 = RequestBody.create(MediaType.parse("text/plain"), description.getText().toString());
            RequestBody phone2 = RequestBody.create(MediaType.parse("text/plain"), phone.getText().toString());
            RequestBody PARTNERSHIP_ID2 = RequestBody.create(MediaType.parse("text/plain"), PARTNERSHIP_ID);
            RequestBody CITY_ID2 = RequestBody.create(MediaType.parse("text/plain"), CITY_ID);
            RequestBody CATEGORY_ID2 = RequestBody.create(MediaType.parse("text/plain"), CATEGORY_ID);
            RequestBody SUB_CATEGORY_ID2 = RequestBody.create(MediaType.parse("text/plain"), SUB_CATEGORY_ID);

            Call<AdResponse> addAd = BaseClient.getApi().addAd(
                    USER_ID,
                    CATEGORY_ID2,
                    SUB_CATEGORY_ID2,
                    CITY_ID2,
                    DESTRICT,
                    title1,
                    description2,
                    price2,
                    phone2,
                    phone2,
                    PARTNERSHIP_ID2,
                    image);
            addAd.enqueue(new Callback<AdResponse>() {
                @Override
                public void onResponse(Call<AdResponse> call, Response<AdResponse> response) {
                    if (response.body().isSuccess())
                    Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    showPaymentDialog(PARTNERSHIP_PRICE,response.body().getId());
                }

                @Override
                public void onFailure(Call<AdResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.d("pppppppppp", "onFailure: "+t.getMessage());
                    Toast.makeText(getActivity(), "حدث خطا برجاء المحاولة مرة اخري", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }
    private void updateAdd() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("جار اضافة الاعلان");
        progressDialog.show();
        if (title.getText().equals("")||
                price.getText().equals("")||
                description.getText().equals("")||
                phone.getText().equals("")||
                PARTNERSHIP_ID.equals("")||
                CITY_ID.equals("")||
                CATEGORY_ID.equals("")||
                SUB_CATEGORY_ID.equals("")){
            progressDialog.dismiss();
            Toast.makeText(getActivity(), "برجاء ادخال جميع البيانات", Toast.LENGTH_SHORT).show();
        }else {
            RequestBody surveyBody = null;
            MultipartBody.Part image = null;
            if (imageUri!=null){
                File file2 = new File(imageUri.getPath());
                surveyBody = RequestBody.create(MediaType.parse("image/*"), file2);
               image = MultipartBody.Part.createFormData("image", String.valueOf(System.currentTimeMillis() + ".jpg"), surveyBody);

            }


            RequestBody title1 = RequestBody.create(MediaType.parse("text/plain"), title.getText().toString());
            RequestBody USER_ID = RequestBody.create(MediaType.parse("text/plain"), SharedHelper.getKey(getActivity(), LoginActivity.USER_ID));
            RequestBody DESTRICT = RequestBody.create(MediaType.parse("text/plain"), SharedHelper.getKey(getActivity(), LoginActivity.DESTRICT));
            RequestBody price2 = RequestBody.create(MediaType.parse("text/plain"), price.getText().toString());
            RequestBody description2 = RequestBody.create(MediaType.parse("text/plain"), description.getText().toString());
            RequestBody phone2 = RequestBody.create(MediaType.parse("text/plain"), phone.getText().toString());
            RequestBody PARTNERSHIP_ID2 = RequestBody.create(MediaType.parse("text/plain"), PARTNERSHIP_ID);
            RequestBody CITY_ID2 = RequestBody.create(MediaType.parse("text/plain"), CITY_ID);
            RequestBody CATEGORY_ID2 = RequestBody.create(MediaType.parse("text/plain"), CATEGORY_ID);
            RequestBody SUB_CATEGORY_ID2 = RequestBody.create(MediaType.parse("text/plain"), SUB_CATEGORY_ID);

            Call<DeleteResponse> addAd = BaseClient.getApi().upDateAd(
                    adId,
                    USER_ID,
                    CATEGORY_ID2,
                    SUB_CATEGORY_ID2,
                    CITY_ID2,
                    DESTRICT,
                    title1,
                    description2,
                    price2,
                    phone2,
                    phone2,
                    PARTNERSHIP_ID2,
                    image);
            addAd.enqueue(new Callback<DeleteResponse>() {
                @Override
                public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
                    Log.d("pppppppppp", "onResponse: "+response.body().getCode());
                    if (response.body().getCode()==200)
                        Toast.makeText(getActivity(), "تم تحديث الاعلاان", Toast.LENGTH_SHORT).show();

                    progressDialog.dismiss();

                }

                @Override
                public void onFailure(Call<DeleteResponse> call, Throwable t) {
                    progressDialog.dismiss();
                    Log.d("pppppppppp", "onFailure: "+t.getMessage());
                    Toast.makeText(getActivity(), "حدث خطا برجاء المحاولة مرة اخري", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


    private void showPaymentDialog (final String amount, final String id){
        AlertDialog.Builder payment = new AlertDialog.Builder(getActivity());
        payment.setTitle(getString(R.string.payment));
        payment.setMessage(getString(R.string.you_must_pay)+" "+amount+" "+getString(R.string.sar)+" "+getString(R.string.to_active_the_advertisement));

        payment.setPositiveButton(getString(R.string.payment), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                //Setting custom enter/exit animations
                CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();
                intentBuilder.setStartAnimations(getActivity(), android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                intentBuilder.setExitAnimations(getActivity(), android.R.anim.slide_out_right, android.R.anim.slide_in_left);

                intentBuilder.build().launchUrl(getActivity(), Uri.parse("http://my4sale.info/checkout.php?amount="+amount+"&adId="+id));
            }
        });

        payment.show();
    }

}
