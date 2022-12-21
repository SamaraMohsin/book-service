package com.glc.bookservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository implements IBookRepository<Book>{
    private Map<Integer, Book> repository;

    public BookRepository() {
        this.repository = new HashMap<>();
    }

    @Override
    public void save(Book book){
        repository.put(book.getId(), book);
    }

    @Override
    public Collection<Book> getAllBooks(){
        return repository.values();
    }
    
    @Override
    public Book getBook(int id){
        return this.repository.get(id);
    }

    @Override
    public boolean deleteBook(int id){
        return this.repository.get(id).getId() == id;
        // return this.repository.remove(id) == 1;
        // return "Deleted Successfully";
    }
}
