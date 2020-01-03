package baadraan.u.batman.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {
    private final  static  String DB_NAME = "Batman";
    private final static int DB_VERSION = 1;

    public static final String MYTB = "Movie";
    public static final String IMDBID = "ImdbId";
    public static final String TITLE = "Title";
    public static final String YEAR = "Year";
    public static final String POSTER = "Poster";
    public static final String TYPE = "Type";
    public static final String RUNTIME = "Runtime";
    public static final String GENRE = "Genre";
    public static final String DIRECTOR = "Director";
    public static final String WRITER = "WRITER";
    public static final String ACTORS = "Actors";
    public static final String PLOT = "Plot";
    public static final String LANGUAGE = "Language";
    public static final String COUNTRY = "Country";
    public static final String IMDBRATING = "ImdbRating";
    public static final String IMDBVOTE = "ImdbVote";
    public static final String BOXOFFICE = "Boxoffice";


    public DataBase(Context context){
        super(context , DB_NAME , null , DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase mydb) {
        mydb.execSQL("CREATE TABLE IF NOT EXISTS " + MYTB + " (" +
                IMDBID + " TEXT UNIQUE, " +
                TITLE + " TEXT , "+
                YEAR + " TEXT , "+
                POSTER + " TEXT , "+
                TYPE + " TEXT , " +
                RUNTIME + " TEXT , " +
                GENRE + " TEXT , " +
                DIRECTOR + " TEXT , " +
                WRITER + " TEXT , "  +
                ACTORS + " TEXT , " +
                PLOT + " TEXT , " +
                LANGUAGE + " TEXT , " +
                COUNTRY + " TEXT , " +
                IMDBRATING + " TEXT , " +
                IMDBVOTE + " TEXT , " +
                BOXOFFICE + " TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
