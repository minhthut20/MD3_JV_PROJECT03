package app.service.film;

import app.config.Config;
import app.model.Category;
import app.model.Film;
import app.service.IGenericService;
import database.DataBase;

import java.util.ArrayList;
import java.util.List;

public class FilmServiceIMPL implements IGenericService<Film> {
    List<Film> filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);

    @Override
    public List<Film> findAll() {
        return filmList;
    }

    @Override
    public void save(Film film) {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        Film film1 = findById(film.getFilmId());
        if (film1 ==null){
            filmList.add(film);
        }else {
            int index = filmList.indexOf(film1);
            filmList.set(index,film);
        }
        new Config<Film>().writeFile(DataBase.PATH_FILM,filmList);
    }

    @Override
    public Film findById(int id) {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        for (int i = 0; i < filmList.size(); i++) {
            Film film = filmList.get(i);
            if (film.getFilmId()==id){
                return film;
            }
        }
        return null;
    }

    @Override
    public void delete(int id) {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        filmList.remove(findById(id));
        new Config<Film>().writeFile(DataBase.PATH_FILM,filmList);
    }

    public List<Film> findByName(String name) {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        List<Film> searchFilm = new ArrayList<>();
        for (int i = 0; i < filmList.size(); i++) {
            if (filmList.get(i).getFilmName().toLowerCase().contains(name.toLowerCase())){
                searchFilm.add(filmList.get(i));
            }
        }
        return searchFilm;
    }

    public List<Film> findByCategory(String category) {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        List<Film> result = new ArrayList<>();
        for (Film film:filmList) {
            if (film.getCategory().getCategoryName().toLowerCase().contains(category.toLowerCase())){
                result.add(film);
            }
        }
        return result;
    }

    public List<Film> findSeriesFilmList() {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        List<Film> result = new ArrayList<>();
        for (Film film: filmList) {
            if (film.getEpisodeList().size()>1){
                result.add(film);
            }
        }
        return result;
    }

    public List<Film> findSingleFilmList() {
        filmList = new Config<Film>().readFromFile(DataBase.PATH_FILM);
        List<Film> result = new ArrayList<>();
        for (Film film: filmList) {
            if (film.getEpisodeList().size()==1){
                result.add(film);
            }
        }
        return result;
    }
}
