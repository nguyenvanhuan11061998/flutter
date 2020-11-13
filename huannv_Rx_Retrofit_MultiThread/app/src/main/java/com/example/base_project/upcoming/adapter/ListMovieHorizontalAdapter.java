package com.example.base_project.upcoming.adapter;

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

public class ListMovieHorizontalAdapter extends RecyclerView.Adapter<ListMovieHorizontalAdapter.ViewHolder> {
    private List<Movie> listMovie = new ArrayList<>();
    private Context context;
    private ClickItemListener clickItemListener;

    public ListMovieHorizontalAdapter(Context context, List<Movie> listMovie, ClickItemListener clickItemListener) {
        this.listMovie = listMovie;
        this.context = context;
        this.clickItemListener = clickItemListener;
    }

    public void setListMovie(List<Movie> listMovie) {
        this.listMovie = listMovie;
        notifyDataSetChanged();
    }

    public List<Movie> getListMovie() {
        return listMovie;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item = listMovie.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.image_movie)
        ImageView movieImageView;
        @BindView(R.id.tv_movie_name)
        TextView movieNameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickItemListener != null) {
                        clickItemListener.onClickMovie(getAdapterPosition());
                    }
                }
            });
        }

        public void bindData(Movie item) {
            String urlImage = KEY_IMAGE + item.getPosterPath();
            Glide.with(movieImageView)
                    .load(urlImage)
                    .into(movieImageView);
            movieNameTextView.setText(item.getTitle());

        }
    }

    public interface ClickItemListener {
        void onClickMovie (int position);
    }
}
