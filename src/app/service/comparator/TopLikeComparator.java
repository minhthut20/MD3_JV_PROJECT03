package app.service.comparator;

import app.model.Film;

import java.util.Comparator;

public class TopLikeComparator implements Comparator<Film> {
    @Override
    public int compare(Film o1, Film o2) {
        return o2.getLikeFilmList().size()-o1.getLikeFilmList().size();
    }
}
