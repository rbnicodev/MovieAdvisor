package com.rbnico.spring.dao;

import com.rbnico.spring.model.Film;

import java.util.Collection;

public interface FilmDao {
    public Film findById(long id);
    public Collection<Film> findAll();
    public void insert(Film film);
    public void edit(Film film);
    public void delete(long id);
}
