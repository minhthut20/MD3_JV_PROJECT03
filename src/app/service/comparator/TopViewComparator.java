package app.service.comparator;

import app.model.Film;

import java.util.Comparator;

public class TopViewComparator implements Comparator<Film> {
    @Override
    public int compare(Film o1, Film o2) {
        return o2.getViewFilmList().size()-o1.getViewFilmList().size();
    }
}
