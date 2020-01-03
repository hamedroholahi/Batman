package baadraan.u.batman.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import baadraan.u.batman.service.ListMovie;
import baadraan.u.batman.service.MovieSingle;

public class DatabaseHelper {
    private SQLiteDatabase mydb;

    public DatabaseHelper (Context context){
        mydb = new DataBase(context).getWritableDatabase();
    }



    public void insertToTable(ArrayList<ListMovie>movies){
        for (int i =0 ; i<movies.size() ; i++) {
            ContentValues cv = new ContentValues();
            cv.put(DataBase.IMDBID, movies.get(i).getImdbID());
            cv.put(DataBase.TITLE, movies.get(i).getTitle());
            cv.put(DataBase.YEAR, movies.get(i).getYear());
            cv.put(DataBase.POSTER, movies.get(i).getPoster());
            cv.put(DataBase.TYPE , movies.get(i).getType());

            mydb.insert(DataBase.MYTB , null , cv);
        }

        mydb.close();
    }
//    public int getCountCart(){
//        Cursor cursor = mydb.rawQuery("select " + DataBase.CARDID + " from " + DataBase.MYTB ,null);
//        return cursor.getCount();
//    }


    public ArrayList<ListMovie> getMovieList(){
        Cursor cursor = mydb.rawQuery("select * from " + DataBase.MYTB , null);
        int i = 0;
        ArrayList<ListMovie> movies = new ArrayList<>(cursor.getCount());
        while (cursor.moveToNext()){
            ListMovie movie = new ListMovie();
            movie.setImdbID(cursor.getString(cursor.getColumnIndex(DataBase.IMDBID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(DataBase.TITLE)));
            movie.setYear(cursor.getString(cursor.getColumnIndex(DataBase.YEAR)));
            movie.setPoster(cursor.getString(cursor.getColumnIndex(DataBase.POSTER)));
            movie.setType(cursor.getString(cursor.getColumnIndex(DataBase.TYPE)));
            movies.add(movie);
            i++;
        }

        cursor.close();
        mydb.close();

        return movies;

    }

    public MovieSingle getMovieById(String imdbId){
        Cursor cursor = mydb.rawQuery(" select * from " + DataBase.MYTB + " where " + DataBase.IMDBID + " = '" + imdbId +"'", null);

        MovieSingle movie = new MovieSingle();
        while (cursor.moveToNext()){
            movie.setImdbID(cursor.getString(cursor.getColumnIndex(DataBase.IMDBID)));
            movie.setTitle(cursor.getString(cursor.getColumnIndex(DataBase.TITLE)));
            movie.setYear(cursor.getString(cursor.getColumnIndex(DataBase.YEAR)));
            movie.setPoster(cursor.getString(cursor.getColumnIndex(DataBase.POSTER)));
            movie.setType(cursor.getString(cursor.getColumnIndex(DataBase.TYPE)));
            movie.setRuntime(cursor.getString(cursor.getColumnIndex(DataBase.RUNTIME)));
            movie.setGenre(cursor.getString(cursor.getColumnIndex(DataBase.GENRE)));
            movie.setDirector(cursor.getString(cursor.getColumnIndex(DataBase.DIRECTOR)));
            movie.setActors(cursor.getString(cursor.getColumnIndex(DataBase.ACTORS)));
            movie.setWriter(cursor.getString(cursor.getColumnIndex(DataBase.WRITER)));
            movie.setPlot(cursor.getString(cursor.getColumnIndex(DataBase.PLOT)));
            movie.setLanguage(cursor.getString(cursor.getColumnIndex(DataBase.LANGUAGE)));
            movie.setCountry(cursor.getString(cursor.getColumnIndex(DataBase.COUNTRY)));
            movie.setImdbRating(cursor.getString(cursor.getColumnIndex(DataBase.IMDBRATING)));
            movie.setImdbVotes(cursor.getString(cursor.getColumnIndex(DataBase.IMDBVOTE)));
            movie.setBoxOffice(cursor.getString(cursor.getColumnIndex(DataBase.BOXOFFICE)));
        }

        cursor.close();

        return movie;
    }

    public void update (MovieSingle movieSingle){
        ContentValues cv = new ContentValues();
        cv.put(DataBase.RUNTIME,movieSingle.getRuntime());
        cv.put(DataBase.ACTORS,movieSingle.getActors());
        cv.put(DataBase.DIRECTOR, movieSingle.getDirector());
        cv.put(DataBase.WRITER,movieSingle.getWriter());
        cv.put(DataBase.GENRE,movieSingle.getGenre());
        cv.put(DataBase.PLOT,movieSingle.getPlot());
        cv.put(DataBase.LANGUAGE,movieSingle.getLanguage());
        cv.put(DataBase.COUNTRY,movieSingle.getCountry());
        cv.put(DataBase.IMDBRATING,movieSingle.getImdbRating());
        cv.put(DataBase.IMDBVOTE,movieSingle.getImdbVotes());
        cv.put(DataBase.BOXOFFICE,movieSingle.getBoxOffice());

        mydb.update(DataBase.MYTB, cv, DataBase.IMDBID + " = '" + movieSingle.getImdbID() + "'", null);



    }
}
