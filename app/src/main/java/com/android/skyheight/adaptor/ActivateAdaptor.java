package com.android.skyheight.adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.activity.ActivateActivity;
import com.android.skyheight.activity.ProfileDetailActivity;
import com.android.skyheight.activity.SiteDetailActivity;
import com.android.skyheight.model.UserList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ActivateAdaptor extends RecyclerView.Adapter<ActivateAdaptor.ViewHolder> {
    private Context context;
    private ArrayList<UserList> userlist;
    List<UserList> list2;
    private clickevents clickevents;

    public ActivateAdaptor(Context context, ArrayList<UserList> userlist, List<UserList> list2){
        this.context = context;
        this.userlist=userlist;
        this.list2=list2;
    }

    @NonNull
    @Override
    public ActivateAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(parent.getContext());
        View listItem =inflater.inflate(R.layout.activate_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(@NonNull ActivateAdaptor.ViewHolder holder, int position) {
        final UserList mylist=userlist.get(position);
        holder.user_name.setText(mylist.getUser_name());
        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivateActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) mylist);
                intent.putExtra("BUNDLE", args);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView user_image;
        public TextView user_name;
        public TextView activate;
        public RelativeLayout relativeLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.user_image=(ImageView)itemView.findViewById(R.id.user_image);
            this.user_name=(TextView)itemView.findViewById(R.id.username2);
            this.activate=(TextView)itemView.findViewById(R.id.activate);
            this.relativeLayout=(RelativeLayout)itemView.findViewById(R.id.relative3);

        }
    }
    public interface clickevents{
            void onActivateClicked(UserList userList);
    }
}
