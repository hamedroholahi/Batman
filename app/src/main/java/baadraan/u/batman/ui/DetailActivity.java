package baadraan.u.batman.ui;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import baadraan.u.batman.R;
import baadraan.u.batman.SnackMessage;
import baadraan.u.batman.db.DatabaseHelper;
import baadraan.u.batman.service.MoviePresenter;
import baadraan.u.batman.service.MovieSingle;
import baadraan.u.batman.service.MovieView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class DetailActivity extends AppCompatActivity implements MovieView.movieSingle {
    ImageView img , imgBack;
    ProgressBar pb;
    TextView title , year , genre , country , moreDetail , actors , director , writers,
            imdbRate , sumRate, boxOffice , detail ;
    MoviePresenter presenter;
    String imdbId;
    boolean internet;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        internet = isInternetConnection();
        init();
    }

    private void init(){
        img = (ImageView)findViewById(R.id.img_movie_detail);
        imgBack = (ImageView)findViewById(R.id.img_back_detail);
        title = (TextView)findViewById(R.id.tNamedetail);
        year = (TextView)findViewById(R.id.tYear);
        genre = (TextView)findViewById(R.id.tZhanr);
        country = (TextView)findViewById(R.id.tCountry);
        moreDetail = (TextView)findViewById(R.id.more_detail);
        actors = (TextView)findViewById(R.id.actors);
        director = (TextView)findViewById(R.id.director);
        writers = (TextView)findViewById(R.id.writers);
        imdbRate = (TextView)findViewById(R.id.txt_rate);
        sumRate = (TextView)findViewById(R.id.txt_sum_rate);
        boxOffice = (TextView)findViewById(R.id.boxoffice);
        detail = (TextView)findViewById(R.id.detail);
        pb = (ProgressBar)findViewById(R.id.pb);


        imdbId = getIntent().getStringExtra("imdbId");

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.coll);
        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorPrimary));

        if (internet){
            presenter = new MoviePresenter(this);
            presenter.getMovieById(imdbId);
        }else {
            DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
            MovieSingle movie = dbh.getMovieById(imdbId);
            setData(movie);
        }
    }

    @Override
    public void success(MovieSingle movie) {

        setData(movie);

        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
        dbh.update(movie);
    }

    @Override
    public void error(String error) {
        new SnackMessage(this , error , img).showMessage();
    }

    public  boolean isInternetConnection()
    {
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    private void setData(MovieSingle movie){
        Picasso.get().load(movie.getPoster()).into(img);
        Picasso.get().load(movie.getPoster()).into(imgBack, new Callback() {
            @Override
            public void onSuccess() {
                pb.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });
        title.setText(movie.getTitle());
        year.setText("سال : " + movie.getYear());
        genre.setText( "ژانر : " + movie.getGenre());
        country.setText("محصول کشور : " + movie.getCountry());
        moreDetail.setText(movie.getLanguage() + " - " + movie.getRuntime());
        actors.setText(movie.getActors());
        director.setText(movie.getDirector());
        writers.setText(movie.getWriter());
        imdbRate.setText(movie.getImdbRating());
        sumRate.setText(movie.getImdbVotes());
        boxOffice.setText("فروش : "+movie.getBoxOffice());
        detail.setText(movie.getPlot());
    }
}
