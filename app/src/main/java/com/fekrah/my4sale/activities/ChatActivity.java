package com.fekrah.my4sale.activities;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.esafirm.imagepicker.features.ImagePicker;
import com.esafirm.imagepicker.model.Image;
import com.facebook.drawee.view.SimpleDraweeView;

import com.fekrah.my4sale.R;
import com.fekrah.my4sale.adapters.MessageAdapter;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.Message;
import com.fekrah.my4sale.models.Messages;
import com.fekrah.my4sale.models.SendMessageResponse;
import com.fekrah.my4sale.models.User;
import com.fekrah.my4sale.server.BaseClient;
import com.github.clans.fab.FloatingActionButton;
import com.yalantis.ucrop.UCrop;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.zelory.compressor.Compressor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab_send_message)
    FloatingActionButton mSendMessageButton;

    @BindView(R.id.chat_input_message)
    EditText mInputMessage;

//    @BindView(R.id.send_message_view)
//    View sendMessageView;

    @BindView(R.id.friend_chat_recycler_view)
    RecyclerView messagesRecyclerView;

    View view;
    TextView userName;
    SimpleDraweeView userImage;

    User user;

    String receiverId;

    String userId;

    List<Messages.MessageData> messages;
    MessageAdapter adapter;
    LinearLayoutManager messagesLinearLayoutManager;

    private Bitmap thumbBitmap = null;

    private static final int FIRST_REQUEST = 0;
    private static final int SECOND_REQUEST = 1;
    UCrop.Options options;
    private byte[] imageBytes;

    private static final String TAG = "ChatActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
        }
        final LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (inflater != null) {
            view = inflater.inflate(R.layout.chat_custombar, null);
            userName = view.findViewById(R.id.task_room_name);
            userImage = view.findViewById(R.id.task_room_image);
        }
        if (actionBar != null) {
            actionBar.setCustomView(view);
        }

        messages = new ArrayList<>();
        messagesLinearLayoutManager = new LinearLayoutManager(this);
        adapter = new MessageAdapter(messages, this, receiverId);
        messagesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        messagesLinearLayoutManager.setStackFromEnd(true);
        // messagesLinearLayoutManager.setReverseLayout(true);
        //linearLayoutManager.setReverseLayout(true);
        messagesLinearLayoutManager.setSmoothScrollbarEnabled(true);
        messagesRecyclerView.setHasFixedSize(true);
        //messagesRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 0));
        messagesRecyclerView.setLayoutManager(messagesLinearLayoutManager);

        messagesRecyclerView.setAdapter(adapter);

        mSendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseClient.getApi().sendMessage(
                        SharedHelper.getKey(ChatActivity.this,LoginActivity.USER_ID),
                        receiverId,
                        mInputMessage.getText().toString()
                ).enqueue(new Callback<SendMessageResponse>() {
                    @Override
                    public void onResponse(Call<SendMessageResponse> call, Response<SendMessageResponse> response) {
                        if (response.body().isSuccess()){
                            getMessages();
                            mInputMessage.setText("");
                        }
                    }

                    @Override
                    public void onFailure(Call<SendMessageResponse> call, Throwable t) {

                    }
                });
            }
        });

        options = new UCrop.Options();
        options.setToolbarColor(ContextCompat.getColor(this, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));
        getMessages();

    }

    private void getMessages() {
        Intent intent = getIntent();
        receiverId  = intent.getStringExtra("receiverId");
        userName.setText(intent.getStringExtra("receiverName"));
        BaseClient.getApi().getMessagesCall(SharedHelper.getKey(getApplicationContext(),LoginActivity.USER_ID),receiverId)
                .enqueue(new Callback<Messages>() {
            @Override
            public void onResponse(Call<Messages> call, Response<Messages> response) {
                if (response.body().getData().size()>0){
                    adapter.clear();
                    adapter.add(response.body().getData());
                }

            }

            @Override
            public void onFailure(Call<Messages> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            //Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            return;
        }
        String destinationFileName = "SAMPLE_CROPPED_IMAGE_NAME" + ".jpg";

        if (ImagePicker.shouldHandle(requestCode, resultCode, data)) {

            Image image = ImagePicker.getFirstImageOrNull(data);
            Uri res_url = Uri.fromFile(new File((image.getPath())));
            CropImage(image, res_url);

        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            //  if (resultUri!=null)
            assert resultUri != null;
            bitmapCompress(resultUri);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            thumbBitmap.compress(Bitmap.CompressFormat.JPEG, 75, byteArrayOutputStream);
            imageBytes = byteArrayOutputStream.toByteArray();
            //uploadThumbImage(imageBytes);
            Log.d(TAG, "onActivityResult: " + Arrays.toString(imageBytes));
        }


    }


    private void CropImage(Image image, Uri res_url) {
        UCrop.of(res_url, Uri.fromFile(new File(getCacheDir(), image.getName())))
                .withOptions(options)
                .start(ChatActivity.this);
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

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void backFinish(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fileList();
    }
}

