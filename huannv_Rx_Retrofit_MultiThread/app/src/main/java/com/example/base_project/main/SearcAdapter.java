package com.example.base_project.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.base_project.R;
import com.example.base_project.main.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.base_project.Utils.KEY_IMAGE;

public class SearcAdapter extends RecyclerView.Adapter<SearcAdapter.ViewHolder> {
    private Context context;
    private List<Movie> listData = new ArrayList<>();
    private OnClickListener onClickListener;

    public SearcAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    public List<Movie> getListData() {
        return listData;
    }

    public void setListData(List<Movie> listData) {
        this.listData = listData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item = listData.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return listData == null ? 0 : listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.img_item_search)
        ImageView itemSearchImageView;
        @BindView(R.id.tv_title)
        TextView titleTextView;
        @BindView(R.id.tv_release_date)
        TextView releaseDateTextView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClickItem(getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(Movie item) {
            String urlImage = KEY_IMAGE + item.getPosterPath();
            Glide.with(itemSearchImageView).load(urlImage)
                    .into(itemSearchImageView);
            titleTextView.setText(item.getTitle());
            releaseDateTextView.setText(item.getReleaseDate());
        }
    }

    public interface OnClickListener {
        void onClickItem(int position);
    }
}
