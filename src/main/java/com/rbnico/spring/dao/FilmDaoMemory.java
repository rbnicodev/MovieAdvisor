package com.rbnico.spring.dao;

import com.rbnico.spring.config.AppConfig;
import com.rbnico.spring.model.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class FilmDaoMemory implements FilmDao{

    public List<Film> films = new ArrayList<>();

    @Autowired
    private AppConfig appConfig;

    @PostConstruct
    public void init() {
        films = UtilFilmFileReader.readFile(appConfig.getFile(), appConfig.getSeparator(), appConfig.getListSeparator());
    }

    @Override
    public Film findById(long id) {
        Optional<Film> result =
                films
                        .stream()
                        .filter(f -> f.getId() == id)
                        .findFirst();
        return result.orElse(null);
    }

    @Override
    public Collection<Film> findAll() {
        return films;
    }

    @Override
    public void insert(Film film) {
        films.add(film);
    }

    @Override
    public void edit(Film film) {
        int index = getIndexOf(film.getId());
        if (index != -1)
            films.set(index, film);
    }

    @Override
    public void delete(long id) {
        int index = getIndexOf(id);
        if (index != -1)
            films.remove(index);
    }

    private int getIndexOf(long id) {
        boolean found = false;
        int index = 0;

        while (!found && index < films.size()) {
            if (films.get(index).getId() == id)
                found = true;
            else
                index++;
        }
        return (found) ? index : -1;
    }
}
