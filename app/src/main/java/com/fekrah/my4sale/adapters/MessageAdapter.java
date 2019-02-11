package com.fekrah.my4sale.adapters;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.LoginActivity;
import com.fekrah.my4sale.helper.GetTimeAgo;
import com.fekrah.my4sale.helper.SharedHelper;
import com.fekrah.my4sale.models.Message;
import com.fekrah.my4sale.models.Messages;
import com.stfalcon.frescoimageviewer.ImageViewer;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int VIEW_TYPE_ITEM_FORM = 3;
    private final int VIEW_TYPE_ITEM_FROM_ME = 0;
    private final int VIEW_TYPE_ITEM_TO_ME = 2;
    private final int VIEW_TYPE_LOADING = 1;

    private List<Messages.MessageData> mMessagesList;
    String roomId;
    private Context context;
    int currentPosition;
    Dialog messsageDialog;
    String receiverId;

    public MessageAdapter(List<Messages.MessageData> mMessagesList, Context context, String receiverId) {
        this.mMessagesList = mMessagesList;
        this.roomId = roomId;
        this.context = context;
        this.receiverId = receiverId;

    }

    @Override
    public int getItemViewType(int position) {
        Messages.MessageData message = mMessagesList.get(position);

        if (message.getReceiver_id().equals(receiverId)) {
            return VIEW_TYPE_ITEM_FROM_ME ;
        } else {
            return VIEW_TYPE_ITEM_TO_ME;
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM_FROM_ME) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_from_me, parent, false);
            return new MessageFromMeViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_message_to_me, parent, false);
            return new MessageToMeViewHolder(view);
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        final Messages.MessageData message = mMessagesList.get(position);
        if (holder instanceof MessageFromMeViewHolder) {
            final MessageFromMeViewHolder fromMeViewHolder = (MessageFromMeViewHolder) holder;
            fromMeViewHolder.messageText.setText(message.getMessage());

            fromMeViewHolder.messageTime.setText(message.getCreated_at());

        } else if (holder instanceof MessageToMeViewHolder) {
            final MessageToMeViewHolder toMeViewHolder = (MessageToMeViewHolder) holder;
            toMeViewHolder.messageText.setText(message.getMessage());

            toMeViewHolder.messageTime.setText(message.getCreated_at());

            }
    }

    private void setClipboard(Context context, String text) {
        if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }

    public void add(List<Messages.MessageData> message) {
        mMessagesList.addAll(message);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMessagesList.size();
    }

    public void clear() {
        mMessagesList.clear();
        notifyDataSetChanged();
    }

    public class MessageFromMeViewHolder extends RecyclerView.ViewHolder {


        TextView messageTime;
        TextView messageText;
        SimpleDraweeView messageImage;
        View main;

        public MessageFromMeViewHolder(View itemView) {
            super(itemView);
            main = itemView;
            messageText = itemView.findViewById(R.id.message_text_view_of_message);
            messageImage = itemView.findViewById(R.id.photo_image_view_of_message_from_me);
            messageTime = itemView.findViewById(R.id.message_time_from_me);
        }
    }

    public class MessageToMeViewHolder extends RecyclerView.ViewHolder {
        TextView messageTime;
        TextView messageText;
        SimpleDraweeView messageImage;
        View main;

        public MessageToMeViewHolder(View itemView) {
            super(itemView);
            main = itemView;
            messageTime = itemView.findViewById(R.id.message_time_to_me);
            messageImage = itemView.findViewById(R.id.photo_image_view_of_message_to_me);
            messageText = itemView.findViewById(R.id.message_text_view_of_message_tome);

        }
    }



}
