package com.android.skyheight.adaptor;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.skyheight.R;
import com.android.skyheight.helper.FavDB;
import com.android.skyheight.model.FavItem;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {
    private Context context;
    private List<FavItem> favItemList;
    private FavDB favDB;
    private DatabaseReference refLike;
    public FavAdapter(Context context, List<FavItem> favItemList){
        this.context = context;
        this.favItemList = favItemList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.from(parent.getContext()).inflate(R.layout.sitelist,
                parent, false);
        favDB = new FavDB(context);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.site_size.setText(favItemList.get(position).getPlot_size());
        holder.site_owner.setText(favItemList.get(position).getPlot_owner());
        holder.site_price.setText("₹ "+favItemList.get(position).getPrice());
        holder.site_image.setImageResource(Integer.parseInt(favItemList.get(position).getPlot_image()));
    }
    @Override
    public int getItemCount() {
        return favItemList.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView site_image;
        public TextView site_price;
        public TextView site_size;
        public TextView site_owner;
        public TextView site_address;
        public CardView cardView;
        public Button favBtn;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.site_image =(ImageView)itemView.findViewById(R.id.image);
            this.site_price =(TextView)itemView.findViewById(R.id.price);
            this.site_address =(TextView)itemView.findViewById(R.id.address);
            this.site_owner =(TextView)itemView.findViewById(R.id.owner);
            this.site_size =(TextView)itemView.findViewById(R.id.size);
            this.favBtn=(Button) itemView.findViewById(R.id.favBtn);
            cardView =(CardView) itemView.findViewById(R.id.cardview);
            refLike = FirebaseDatabase.getInstance().getReference().child("likes");
            //remove from fav after click
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);
                    final DatabaseReference upvotesRefLike = refLike.child(favItemList.get(position).getKey_id());
                    favDB.remove_fav(favItem.getKey_id());
                    removeItem(position);

                    upvotesRefLike.runTransaction(new Transaction.Handler() {
                        @NonNull
                        @Override
                        public Transaction.Result doTransaction(@NonNull final MutableData mutableData) {
                            try {
                                Integer currentValue = mutableData.getValue(Integer.class);
                                if (currentValue == null) {
                                    mutableData.setValue(1);
                                } else {
                                    mutableData.setValue(currentValue - 1);
                                }
                            } catch (Exception e) { throw e;
                            }
                            return Transaction.success(mutableData);
                        }
                        @Override
                        public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                            System.out.println("Transaction completed");
                        }
                    });
                }
            });
        }
    }
    private void removeItem(int position) {
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,favItemList.size());
    }
}