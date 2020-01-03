package baadraan.u.batman.service;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("?apikey=3e974fca&s=batman")
    Observable<ResponseSearch> getMoviesBatman();

    @GET("?apikey=3e974fca")
    Observable<MovieSingle> getMovieById(@Query("i")String imdbId);

}
