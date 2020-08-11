package com.android.skyheight.adaptor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.android.skyheight.R;
import com.android.skyheight.activity.SiteDetailActivity;
import com.android.skyheight.activity.SiteListView;
import com.android.skyheight.helper.FavDB;
import com.android.skyheight.model.SiteListModel;
import com.android.skyheight.utils.Prefrence;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import static com.android.skyheight.R.drawable.heart_checked;
import static com.android.skyheight.R.drawable.is_booked;
import static com.android.skyheight.R.drawable.site_list;
public class SiteListAdaptor extends RecyclerView.Adapter<SiteListAdaptor.ViewHolder> {
    private ArrayList<SiteListModel> siteListModels;
    private Context context;
    private SiteListModel siteListModel;
    SiteListModel[] list;
    public SiteListAdaptor(SiteListModel[] list ,Context context) {
        this.list = list;
        this.context=context;
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
        final SiteListModel mylist = list[position];
        holder.site_size.setText(mylist.getPlot_size());
        holder.site_owner.setText(mylist.getPlot_owner());
        holder.site_price.setText("₹ " + mylist.getPrice()+" sq/m");
        holder.site_image.setImageResource(Integer.parseInt(mylist.getPlot_image()));





        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SiteDetailActivity.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST", (Serializable) mylist);
                intent.putExtra("BUNDLE", args);
                context.startActivity(intent);
                Toast.makeText(v.getContext(), "You Clicked" + mylist.getPrice(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView site_image;
        public TextView site_price;
        public TextView site_size;
        public TextView site_owner;
        public TextView site_address;
        public CardView cardView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.site_image = (ImageView) itemView.findViewById(R.id.image);
            this.site_price = (TextView) itemView.findViewById(R.id.price);
            this.site_address = (TextView) itemView.findViewById(R.id.address);
            this.site_owner = (TextView) itemView.findViewById(R.id.owner);
            this.site_size = (TextView) itemView.findViewById(R.id.size);

            cardView = (CardView) itemView.findViewById(R.id.cardview);

        }
    }




}