package com.android.skyheight.adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.skyheight.R;
import com.android.skyheight.model.PlotListModel;
import com.android.skyheight.model.UserList;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class PlotListAdaptor extends RecyclerView.Adapter<PlotListAdaptor.ViewHolder> {
    private Context context;
    private ArrayList<PlotListModel> plotlist;
    private List<PlotListModel> sitelist;

    PlotListModel[] lists;
   public PlotListAdaptor(Context context,List<PlotListModel> sitelist,
                          ArrayList<PlotListModel> plotlist){
       this.sitelist =sitelist;
       this.context=context;
       this.plotlist=plotlist;
   }
    @NonNull
    @Override
    public PlotListAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.sitelistview, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }


    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull final PlotListAdaptor.ViewHolder holder, final int position) {
        final PlotListModel mylist=plotlist.get(position);

            holder.plot_number.setText(mylist.getPlot_number());
            holder.description.setText(mylist.getDescription());
            holder.size.setText(mylist.getSize());
            holder.status.setText(mylist.getStatus());


        if (mylist.getStatus().equals("true")){
            holder.status.setText("Buy");
            holder.status.setTextColor(Color.parseColor("#5CDC5C"));
        }
        else {
            holder.status.setText("Booked");
            holder.status.setTextColor(
                    Color.parseColor("#D83131"));
        }
       /* if (sitelist.get(position).getIsbooked()){
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
        }*/

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "You Clicked" + sitelist.get(position).getPlot_number(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return sitelist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
       public TextView plot_number;
       public TextView status;
       public CardView cardView;
       public LinearLayout linear;
       public TextView description;
       public TextView size;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.plot_number=(TextView)itemView.findViewById(R.id.plot_number);
            this.description=(TextView)itemView.findViewById(R.id.plot_description);
            this.status=(TextView)itemView.findViewById(R.id.status);
            this.size=(TextView)itemView.findViewById(R.id.plot_size);
            this.cardView =(CardView)itemView.findViewById(R.id.cardview);
            this.linear =(LinearLayout) itemView.findViewById(R.id.linear);

        }
    }
}
