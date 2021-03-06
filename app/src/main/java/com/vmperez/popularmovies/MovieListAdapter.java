package com.vmperez.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.vmperez.popularmovies.data.MovieInfo;
import com.vmperez.popularmovies.utilities.PopularMoviesImageUtils;

import java.net.URL;
import java.util.List;

/**
 * {@link MovieListAdapter} exposes a list of movies
 * from a {@link android.database.Cursor} to a {@link android.support.v7.widget.RecyclerView}.
 */
class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListAdapterViewHolder> {

    private static final int VIEW_TYPE_LANDSCAPE = 0;
    private static final int VIEW_TYPE_PORTRAIT = 1;
    private final String TAG = MovieListAdapter.class.getSimpleName();
    final private MovieListAdapterOnClickHandler mClickHandler;
    private List<MovieInfo> mMovieInfoList;
    private boolean mUseLandscapeLayout;
    private Context mContext;


    public MovieListAdapter(MovieListAdapterOnClickHandler clickHandler) {
        mClickHandler = clickHandler;
        // mUseLandscapeLayout = mContext.getResources().getBoolean(R.bool.use_landscape_layout);
    }

    @NonNull
    @Override
    public MovieListAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        mContext = viewGroup.getContext();

        int layoutIdForListItem = R.layout.movielist_item;
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View view = inflater.inflate(layoutIdForListItem, viewGroup, false);
        return new MovieListAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapterViewHolder movieListAdapterViewHolder, int position) {

        MovieInfo selectedMovie = mMovieInfoList.get(position);
        String moviePosterPath = selectedMovie.getPosterPath();
        URL posterUrl = PopularMoviesImageUtils.getLargeArtForMovie(moviePosterPath);
        String posterUrlString = posterUrl.toString();
        Picasso.with(mContext).load(posterUrlString).into(movieListAdapterViewHolder.mMoviePoster);
    }

    @Override
    public int getItemCount() {
        if (null == mMovieInfoList) {
            return 0;
        }
        return mMovieInfoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mUseLandscapeLayout && position == 0) {
            return VIEW_TYPE_LANDSCAPE;
        } else {
            return VIEW_TYPE_PORTRAIT;
        }
    }

    public void setMovieData(List<MovieInfo> movieData) {
        mMovieInfoList = movieData;
        notifyDataSetChanged();
    }

    public interface MovieListAdapterOnClickHandler {
        void onClick(MovieInfo selectedMovie);
    }

    public class MovieListAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final ImageView mMoviePoster;

        MovieListAdapterViewHolder(View view) {
            super(view);
            mMoviePoster = view.findViewById(R.id.movie_poster);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int adapterPosition = getAdapterPosition();
            MovieInfo selectedMovie = mMovieInfoList.get(adapterPosition);
            mClickHandler.onClick(selectedMovie);
        }
    }

}