package com.glc.bookservice;

import java.util.Collection;

import org.springframework.http.ResponseEntity;

public interface IBookRepository<T> {
    public void save(T t);

    public Collection<T> getAllBooks();

    public T getBook(int i);

    public String deleteBook(int id);

    public T updateBook(int i, T b);
}
