package com.fekrah.my4sale.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.fekrah.my4sale.R;
import com.fekrah.my4sale.activities.ChatActivity;
import com.fekrah.my4sale.helper.GetTimeAgo;
import com.fekrah.my4sale.models.Room;


import java.util.List;


public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatsViewHolder> {

    private List<Room> rooms;
    private List<String> roomsKey;
    private Context context;

    public ChatsAdapter(List<Room> rooms, List<String> roomsKey, Context context) {
        this.rooms = rooms;
        this.roomsKey = roomsKey;
        this.context = context;
    }

    @NonNull
    @Override
    public ChatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_chat_list_item, parent, false);
        return new ChatsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChatsViewHolder holder, final int i) {
        final Room room = rooms.get(i);

        holder.roomName.setText(room.getChatWithName());
        holder.mainView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context,ChatActivity.class);
                intent.putExtra("receiverId",room.getChatWith());
                intent.putExtra("receiverName",room.getChatWithName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }


    public class ChatsViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView roomImage;
        TextView roomName;
        TextView roomTime;
        TextView roomLastMessage;
        View mainView;

        public ChatsViewHolder(@NonNull View itemView) {
            super(itemView);

            mainView = itemView;
            roomLastMessage = itemView.findViewById(R.id.room_chat_last_message);
            roomImage = itemView.findViewById(R.id.room_chat_image);
            roomName = itemView.findViewById(R.id.room_chat_name);
            roomTime = itemView.findViewById(R.id.room_chat_time);
        }
    }

    public void addRoom(Room userID) {


    }


    public void addRooms(List<Room> rooms) {
        this.rooms.addAll(rooms);
        notifyDataSetChanged();
    }

    public void addRoom(int index, Room userID) {
        rooms.add(index, userID);
        notifyDataSetChanged();
    }

    public int indexOfRoom(String key) {
        return roomsKey.indexOf(key);
    }

    public void removeRoom(int position) {
        rooms.remove(position);
        notifyDataSetChanged();
    }

    public void addKey(int index,String key){
        roomsKey.add(index,key);
        notifyDataSetChanged();
    }

    public void removeKey(int p){
        roomsKey.remove(p);
    }
}
