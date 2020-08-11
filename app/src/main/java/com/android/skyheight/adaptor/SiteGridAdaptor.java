package com.android.skyheight.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.model.SiteGridList;
import com.android.skyheight.model.SiteListModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class SiteGridAdaptor extends RecyclerView.Adapter<SiteGridAdaptor.ViewHolder> {
    private ArrayList<SiteGridList> siteGridLists;
    private Context context;
    private List<SiteGridList> sitelist;

    SiteGridList[] lists;
   public SiteGridAdaptor(List<SiteGridList> sitelist,Context context){
       this.sitelist =sitelist;
       this.context=context;
   }
    @NonNull
    @Override
    public SiteGridAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.sitelistview, parent, false);
        SiteGridAdaptor.ViewHolder viewHolder = new SiteGridAdaptor.ViewHolder(listItem);
        return viewHolder;
    }


    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final SiteGridAdaptor.ViewHolder holder, final int position) {

        holder.area_unit.setText(sitelist.get(position).getPrice());
        holder.location.setText(sitelist.get(position).getLocation());
        holder.site_image.setImageResource(Integer.parseInt(sitelist.get(position).getImage()));

        if (sitelist.get(position).getIsbooked()){
            holder.site_image.setImageResource(R.drawable.is_not_book);
            holder.status.setText("Not Book");
            holder.status.setTextColor(Color.parseColor("#5CDC5C"));
        }
        else {
            holder.site_image.setImageResource(R.drawable.is_booked);
            holder.status.setText("Booked");
            holder.status.setTextColor(Color.parseColor("#D83131"));
            holder.linear.setBackgroundColor(Color.parseColor("#E6E6FA"));
            holder.imageView.setBackgroundColor(Color.parseColor("#E6E6FA"));
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You Clicked" + sitelist.get(position).getPrice(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return sitelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
       public TextView area_unit;
       public TextView location;
       public TextView status;
       public ImageView site_image;
       public CardView cardView;
       public LinearLayout linear;
       public ImageView imageView;





        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.location=(TextView)itemView.findViewById(R.id.location);
            this.area_unit=(TextView)itemView.findViewById(R.id.area_unit);
            this.status=(TextView)itemView.findViewById(R.id.status);
            this.site_image=(ImageView)itemView.findViewById(R.id.site_image);
            this.cardView =(CardView)itemView.findViewById(R.id.cardview);
            this.linear =(LinearLayout) itemView.findViewById(R.id.linear);
            this.imageView =(ImageView) itemView.findViewById(R.id.imageview);
        }
    }
}
