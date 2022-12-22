package com.glc.bookservice;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import lombok.Getter;

@Getter
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
    public String deleteBook(int id){
        
        if(repository.get(id) != null ){
            this.repository.remove(id);
            return "Deleted Successfully";
        }else{
            return "Book id "+id+" Not Found";
        }
    }

    @Override
    public Book updateBook(int id, Book b){
        
        if(repository.get(id) != null ){
             this.repository.put(id, b);
            return this.repository.get(id);
        }else{
            return new Book();
        }
    }


}
