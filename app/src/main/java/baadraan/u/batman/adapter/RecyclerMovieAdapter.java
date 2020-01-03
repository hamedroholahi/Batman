package baadraan.u.batman.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import baadraan.u.batman.R;
import baadraan.u.batman.service.ListMovie;

public class RecyclerMovieAdapter extends RecyclerView.Adapter<RecyclerMovieAdapter.ViewHold> {
    ArrayList<ListMovie>movies;

    OnClick onClick;

    public RecyclerMovieAdapter(ArrayList<ListMovie>movies){
        this.movies = movies;
    }
    @NonNull
    @Override
    public RecyclerMovieAdapter.ViewHold onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie , parent , false);
        return new ViewHold(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerMovieAdapter.ViewHold holder, int position) {
        final ListMovie movie = movies.get(position);

        Picasso.get().load(movie.getPoster()).into(holder.img, new Callback() {
            @Override
            public void onSuccess() {
                holder.pb.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());
        holder.type.setText(movie.getType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClick != null){
                    onClick.onClick(movie.getImdbID());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHold extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title , year , type;
        ProgressBar pb;
        public ViewHold(@NonNull View view) {
            super(view);
            img = (ImageView)view.findViewById(R.id.img_poster);
            title = (TextView)view.findViewById(R.id.title_movie);
            year = (TextView)view.findViewById(R.id.year_movie);
            type = (TextView)view.findViewById(R.id.type_movie);
            pb = (ProgressBar)view.findViewById(R.id.pb);
        }
    }
    public void update(ArrayList<ListMovie>movies){
        this.movies = movies;
        notifyDataSetChanged();
    }

    public interface OnClick{
        void onClick(String imdbId);
    }

    public void setOnClick(OnClick onClick){
        this.onClick = onClick;
    }

}
