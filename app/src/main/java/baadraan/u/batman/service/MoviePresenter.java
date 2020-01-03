package baadraan.u.batman.service;

import baadraan.u.batman.db.DatabaseHelper;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviePresenter {
    MovieView.movieList viewList;
    MovieView.movieSingle viewSingle;

    public MoviePresenter(MovieView.movieList viewList){
        this.viewList =viewList;
    }

    public MoviePresenter(MovieView.movieSingle viewSingle){
        this.viewSingle = viewSingle;
    }

    public void getMovies(){
        ApiClient.getInstance().getRetrofit().create(MovieService.class).getMoviesBatman()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<ResponseSearch>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseSearch responseSearch) {
                        try {
                            if (responseSearch.Response){
                                if (responseSearch.Search.size()>0){
                                    viewList.success(responseSearch.Search);
                                }else {
                                    viewList.error("فیلمی یافت نشد...!");
                                }
                            }else {
                                viewList.error("فیلمی یافت نشد...!");
                            }
                        }catch (Exception e){}
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewList.error("خطا در برقراری ارتباط..!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getMovieById(String imdbId){
        ApiClient.getInstance().getRetrofit().create(MovieService.class).getMovieById(imdbId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<MovieSingle>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MovieSingle movieSingle) {
                        try {
                            if (movieSingle.isResponse()){
                                viewSingle.success(movieSingle);
                            }else{
                                viewSingle.error("فیلمی یافت نشد...!");
                            }
                        }catch (Exception e){}
                    }

                    @Override
                    public void onError(Throwable e) {
                        viewSingle.error("خطا در برقراری ارتباط...!");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
