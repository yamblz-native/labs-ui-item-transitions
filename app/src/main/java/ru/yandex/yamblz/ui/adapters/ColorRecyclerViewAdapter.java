package ru.yandex.yamblz.ui.adapters;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ru.yandex.yamblz.R;
import ru.yandex.yamblz.ui.other.OnColorItemClickListener;


/**
 * Created by Volha on 10.08.2016.
 */

public class ColorRecyclerViewAdapter extends RecyclerView.Adapter<ColorRecyclerViewAdapter.ColorHolderItem>{

    private List<Integer> colors = new ArrayList<>();
    private Random rand = new Random();
    private OnColorItemClickListener onColorItemClickListener;

    public ColorRecyclerViewAdapter(OnColorItemClickListener onColorItemClickListener) {
        this.onColorItemClickListener = onColorItemClickListener;
    }

    @Override
    public ColorHolderItem onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ColorHolderItem holder = new ColorHolderItem(inflater.inflate(R.layout.color_list_item, parent, false));
        holder.itemView.setOnClickListener(v -> {
                onColorItemClickListener.onColorClick(colors.get(holder.getAdapterPosition()), v);
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ColorHolderItem holder, int position) {
        holder.bind(createColorForPosition(position));
    }

    private int createColorForPosition(int position) {
        if (position >= colors.size()) {
            int color = Color.rgb(rand.nextInt(255),rand.nextInt(255),rand.nextInt(255));
            colors.add(color);
            return color;
        }
        return colors.get(position);
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }

    class ColorHolderItem extends RecyclerView.ViewHolder {

        public ColorHolderItem(View itemView) {
            super(itemView);
        }

        public void bind(int color) {
            ((TextView)itemView).setText("#".concat(Integer.toHexString(color)));
            itemView.setBackgroundColor(color);
            ViewCompat.setTransitionName(itemView, "color" + color);
        }
    }
}
