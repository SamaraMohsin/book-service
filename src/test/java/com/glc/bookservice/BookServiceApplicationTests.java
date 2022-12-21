package com.glc.bookservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Collection;

import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureJsonTesters
@SpringBootTest
@AutoConfigureMockMvc
class BookServiceApplicationTests {

	private MockMvc mvc;

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private BookController bookController;

	private JacksonTester<Book> jsonBook;
	private JacksonTester<Collection<Book>> jsonBooks;

	@BeforeEach
	public void setUp(){
		JacksonTester.initFields(this, new ObjectMapper());
		mvc = MockMvcBuilders.standaloneSetup(bookController).build();
	}


	@Test
	void contextLoads() {
	}

	//AC1:  When I enter the title, author, year of publication, and length of the book into the UI and hit submit, my book will saved to the list.
	@Test
	public void canCreateANewBook() throws Exception {
		Book book = new Book(1, "The Hobbit", "J.R.R. Tolkein", 1937, 320);
		mvc.perform(post("/books")
			.contentType(MediaType.APPLICATION_JSON)
			.content(jsonBook.write(book).getJson()))
			.andExpect(status().isOk());
	}

	//AC2:  When I click “View All Books” the application will display a list of all the books in my list.
	@Test
	public void canGetAllBooks() throws Exception {
		Book book1 = new Book(1, "The Hobbit", "J.R.R. Tolkein", 1937, 320);
		Book book2 = new Book(2, "It", "Stephen King", 1986, 1138);
		Collection<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		when(bookRepository.getAllBooks()).thenReturn(books);
		mvc.perform(get("/books/all")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(jsonBooks.write(books).getJson()));
	}


	
	//AC3
	@Test
	public void canGetBook() throws Exception{
		Book book1 = new Book(1,"The Hobbit", "J.r.r. Tolken", 1927, 328);
		Book book2 = new Book(2, "It", "Stephen King", 1986, 1138);

		when(bookRepository.getBook(1)).thenReturn(book1);
		mvc.perform(get("/book/?id=1")
		.contentType(MediaType.APPLICATION_JSON)
		.content(jsonBook.write(book1).getJson()))
		.andExpect(status().isOk())
		.andExpect(content().json(jsonBook.write(book1).getJson()));
	}

	
	/**
	 books 1,2
	 delete 1 ->  Deleted Successfully  

	 books 1,2
	 delete 3 -> Book id 1 Not Found
	 */


	//AC4
	@Test
	public void canDeleteBook() throws Exception{
		Book book1 = new Book(1, "The Hobbit", "J.R.R. Tolkein", 1937, 320);
		Book book2 = new Book(2, "It", "Stephen King", 1986, 1138);
		Book book3 = new Book(3, "It", "Stephen King", 1986, 1138);

		Collection<Book> books = new ArrayList<Book>();
		books.add(book1);
		books.add(book2);
		books.add(book3);
		// when(bookRepository.deleteBooks()).thenReturn("Book Deleted Successfully");
		
		mvc.perform(get("/books/delete/?id=1")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(content().json(jsonBooks.write(books).getJson()));


	}

	
}
