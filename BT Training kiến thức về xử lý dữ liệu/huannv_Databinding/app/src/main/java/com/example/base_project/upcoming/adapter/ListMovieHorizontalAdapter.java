package com.example.base_project.upcoming.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.example.base_project.BR;
import com.example.base_project.R;
import com.example.base_project.main.model.Movie;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

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
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.item_movie, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie item = listMovie.get(position);
        holder.binding.setVariable(BR.movie, item);
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return listMovie == null ? 0 : listMovie.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewDataBinding binding;

        public ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            ButterKnife.bind(this, itemView);
            this.binding = binding;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickItemListener != null) {
                        clickItemListener.onClickMovie(getAdapterPosition());
                    }
                }
            });
        }
    }

    public interface ClickItemListener {
        void onClickMovie (int position);
    }
}
