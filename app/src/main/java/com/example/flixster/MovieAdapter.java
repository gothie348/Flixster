package com.example.flixster;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.models.Movie;
import com.example.flixster.models.config;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{

    // list of movies
    ArrayList<Movie> movies;
    // config needed for image urls
    config config;
    // context for rendering
    Context context;


    //initialize with list

    public MovieAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
        
    }

    public com.example.flixster.models.config getConfig() {
        return config;
    }

    public void setConfig(com.example.flixster.models.config config) {
        this.config = config;
    }

    // creates and inflates a new view
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // get the context and create the inflater
        context = parent.getContext();
        LayoutInflater inflater =LayoutInflater.from(context);
        // create the view using the item _movie layout
        View movieView = inflater.inflate(R.layout.item_movie,parent, false);
        // return a new ViewHolder
        return new ViewHolder(movieView);
    }
 // binds an inflated view to a new item
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // get the movie data at the specified position
        Movie movie= movies.get(position);
        //populate the view with the movie data
        holder.tvTitle.setText(movie.getTitle());
        holder.tvOverview.setText(movie.getOverview());

        // build url for poster image
        String imageUrl = config.getImageUrl(config.getPosterSize(),movie.getPosterPath());

        // load image using glide
        Glide.with(context)
                .load( imageUrl)
                .bitmapTransform( new RoundedCornersTransformation( context , 15 ,0))
                .placeholder(R.drawable.flicks_backdrop_placeholder)
                .error(R.drawable.flicks_backdrop_placeholder)
                .into(holder.ivPosterImage );

    }
// returns the total number of items in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    // create the viewHolder as a static inner class
    public static class ViewHolder extends RecyclerView.ViewHolder {

        // track view objects
        ImageView ivPosterImage;
        TextView tvTitle;
        TextView tvOverview;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //lookup view objects by id
            ivPosterImage=(ImageView) itemView.findViewById(R.id.ivPosterimage);
            tvOverview=(TextView) itemView.findViewById(R.id.tvOverview);
            tvTitle =(TextView) itemView.findViewById(R.id.tvTitle);
        }
    }

}

