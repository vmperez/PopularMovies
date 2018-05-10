package com.vmperez.popularmovies.utilities;

import java.net.URL;

public final class PopularMoviesImageUtils {

    private static final String LOG_TAG = PopularMoviesImageUtils.class.getSimpleName();

    public static URL getSmallArtForMovie(String moviePosterPath) {

        return NetworkUtils.getSmallArtUrlForMovie(moviePosterPath);
    }

    public static URL getLargeArtForMovie(String moviePosterPath) {

        return NetworkUtils.getDefaultArtUrlForMovie(moviePosterPath);
    }
}