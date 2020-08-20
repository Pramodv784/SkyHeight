package com.android.skyheight.adaptor;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.activity.SiteDetailActivity;
import com.android.skyheight.model.AddressModel;
import com.android.skyheight.model.SiteListModel;
import com.android.skyheight.model.UserDetail;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import static com.bumptech.glide.gifdecoder.GifHeaderParser.TAG;

public class SiteListAdaptor extends RecyclerView.Adapter<SiteListAdaptor.ViewHolder> {
    private ArrayList<SiteListModel> siteListModels;
    private Context context;
    List<SiteListModel> list;
    ArrayList<AddressModel> address = new ArrayList<AddressModel>();
    //ArrayList<UserDetail> owner = new ArrayList<UserDetail>();
    ArrayList<SiteListModel> sitlist= new ArrayList<SiteListModel>();
    private SiteListModel siteListModel;
    String useName;
    private String owner;
   UserDetail owner3;
    public SiteListAdaptor( Context context,ArrayList<SiteListModel> siteListModels,List<SiteListModel> list) {
        this.context = context;
        this.siteListModels=siteListModels;
        this.list=list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.sitelist, parent, false);
        SiteListAdaptor.ViewHolder viewHolder = new SiteListAdaptor.ViewHolder(listItem);
        return viewHolder;
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final SiteListModel siteModel=list.get(position);



        Log.e(TAG, "data>>>>>: "+useName );
        holder.site_size.setText(siteModel.getArea());
       holder.site_owner.setText(useName);
        holder.site_name.setText( siteModel.getName());
      if(siteModel.getSite_location()!=null){
          holder.site_address.setText(siteModel.site_location.getAddress());
      }
      else {
          holder.site_address.setText("");
      }

        //holder.site_image.setImageResource(Integer.parseInt(mylist.getImage()));

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SiteDetailActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) siteModel);
                intent.putExtra("BUNDLE", args);
                context.startActivity(intent);
                Toast.makeText(v.getContext(), "You Clicked" + siteModel.getPrice(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView site_image;
        public TextView site_name;
        public TextView site_size;
        public TextView site_owner;
        public TextView site_address;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.site_image = (ImageView) itemView.findViewById(R.id.image);
            this.site_name = (TextView) itemView.findViewById(R.id.name);
            this.site_address = (TextView) itemView.findViewById(R.id.site_address);
            this.site_owner = (TextView) itemView.findViewById(R.id.owner1);
            this.site_size = (TextView) itemView.findViewById(R.id.size);
            cardView = (CardView) itemView.findViewById(R.id.cardview);

        }
    }
}