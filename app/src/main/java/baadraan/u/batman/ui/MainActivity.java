package baadraan.u.batman.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import baadraan.u.batman.R;
import baadraan.u.batman.SnackMessage;
import baadraan.u.batman.adapter.RecyclerMovieAdapter;
import baadraan.u.batman.db.DatabaseHelper;
import baadraan.u.batman.service.ListMovie;
import baadraan.u.batman.service.MoviePresenter;
import baadraan.u.batman.service.MovieView;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;

public class MainActivity extends AppCompatActivity implements MovieView.movieList {
    RecyclerView recycler;
    MoviePresenter presenter;
    RecyclerMovieAdapter adapter;
    Boolean internet;
    RelativeLayout rlNointernet;
    SwipeRefreshLayout swipe;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        recycler = (RecyclerView)findViewById(R.id.recycler_main);
        rlNointernet = (RelativeLayout) findViewById(R.id.rl_nointernet);
        swipe = (SwipeRefreshLayout)findViewById(R.id.swipe);

        swipe.setRefreshing(true);

        recycler.setLayoutManager(new GridLayoutManager(getApplicationContext() , 3 , RecyclerView.VERTICAL , false));
        recycler.setHasFixedSize(true);
        adapter = new RecyclerMovieAdapter(new ArrayList<ListMovie>(0));
        recycler.setAdapter(adapter);


        load();

        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                load();
            }
        });



        adapter.setOnClick(new RecyclerMovieAdapter.OnClick() {
            @Override
            public void onClick(String imdbId) {
                Intent intent = new Intent(MainActivity.this , DetailActivity.class);
                intent.putExtra("imdbId" , imdbId);
                startActivity(intent);
            }
        });
    }
    @Override
    public void success(ArrayList<ListMovie> movies) {
        swipe.setRefreshing(false);
        adapter.update(movies);

        DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
        dbh.insertToTable(movies);
    }

    @Override
    public void error(String error) {
        new SnackMessage(this , error , rlNointernet).showMessage();

    }

    private void load(){
        internet = isInternetConnection();
        if (internet){
            hideView(rlNointernet);
            presenter = new MoviePresenter(this);
            presenter.getMovies();
        }else{
            swipe.setRefreshing(false);
            showView(rlNointernet);

            DatabaseHelper dbh = new DatabaseHelper(getApplicationContext());
            ArrayList<ListMovie>movies = dbh.getMovieList();
            adapter.update(movies);
        }
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

    private void hideView(View view){
        Animation animOut = AnimationUtils.loadAnimation(this , R.anim.top_to_bottom);
        view.setAnimation(animOut);
        view.setVisibility(View.GONE);
    }

    private void showView(View view){
        Animation animIn = AnimationUtils.loadAnimation(this , R.anim.bottom_to_top);
        view.setAnimation(animIn);
        view.setVisibility(View.VISIBLE);
    }
}
