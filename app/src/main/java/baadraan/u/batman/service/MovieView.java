package baadraan.u.batman.service;

import java.util.ArrayList;

public interface MovieView {
    interface movieList {

        void success(ArrayList<ListMovie> movies);

        void error(String error);
    }

    interface movieSingle{
        void success(MovieSingle movie);

        void error(String error);
    }
}
