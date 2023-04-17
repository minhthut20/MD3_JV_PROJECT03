package app.controller;

import app.model.Film;
import app.service.comparator.TopLikeComparator;
import app.service.comparator.TopViewComparator;
import app.service.film.FilmServiceIMPL;


import java.util.ArrayList;
import java.util.List;

public class FilmController {
    FilmServiceIMPL filmServiceIMPL = new FilmServiceIMPL();

    public  List<Film> getSeriesFilmList() {
        return filmServiceIMPL.findSeriesFilmList();
    }

    public List<Film> getListFilm(){
        return filmServiceIMPL.findAll();
    }

    public void addFilm(Film film){
        filmServiceIMPL.save(film);
    }
    public Film findById(int id){
        return filmServiceIMPL.findById(id);
    }

    public List<Film> findByName(String name){
        return filmServiceIMPL.findByName(name);
    }
    public Film detailFilm(int id){
        return filmServiceIMPL.findById(id);
    }
    public void update(Film film){
        filmServiceIMPL.save(film);
    }

    public void deleteById(int deleteId) {
        filmServiceIMPL.delete(deleteId);
    }

    public List<Film> findByCategory(String category) {
        return filmServiceIMPL.findByCategory(category);
    }

    public List<Film> getSingleFilmList() {
        return filmServiceIMPL.findSingleFilmList();
    }
    public List<Film> getSortByView(){
        List<Film> result = new ArrayList<>(getListFilm());
        TopViewComparator topViewComparator = new TopViewComparator();
        result.sort(topViewComparator);
        return result;
    }
    public List<Film> getSortByLike(){
        List<Film> result = new ArrayList<>(getListFilm());
        TopLikeComparator topLikeComparator = new TopLikeComparator();
        result.sort(topLikeComparator);
        return result;
    }

    public void watchFilm(Film film) {
        filmServiceIMPL.save(film);
    }

    public void likeUnlikeFilm(Film film) {
        filmServiceIMPL.save(film);
    }
}
