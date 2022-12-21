package com.glc.bookservice;

import java.util.Collection;

public interface IBookRepository<T> {
    public void save(T t);

    public Collection<T> getAllBooks();

    public T getBook(int i);

    public boolean deleteBook(int id);
}
