package com.project.ditha.callapi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<PlaceViewHolder> {
    private Context context;
    private int[] images;
    private String[] placeNames;
    private String[] placeGuide;

    MyAdapter(Context context, int[] images, String[] placeNames, String[] placeGuide) {
        this.context = context;
        this.images = images;
        this.placeNames = placeNames;
        this.placeGuide = placeGuide;
    }

    @Override
    public PlaceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item_row, parent, false);
        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PlaceViewHolder holder, int position) {
        holder.placeName.setText(placeNames[position]);
        holder.place.setImageResource(images[position]);

        holder.place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(placeGuide[holder.getAdapterPosition()]));
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return images.length;
    }
}

class PlaceViewHolder extends RecyclerView.ViewHolder {

    ImageView place;
    TextView placeName;

    PlaceViewHolder(View itemView) {
        super(itemView);

        place = itemView.findViewById(R.id.ivPlace);
        placeName = itemView.findViewById(R.id.tvPlaceName2);
    }
}
