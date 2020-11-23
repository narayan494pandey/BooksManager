package com.java.jpa.test;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.java.jpa.domain.Book;

public class Main {
	static EntityManagerFactory factory;
	static EntityManager entityManager;

	public static void main(String[] args) {
		// BEGIN
		begin();
		// CRUD
				// create();
				// update();
				// find();
				query();
				//remove();
		// END
		end();

	}

	private static void begin() {
		factory = Persistence.createEntityManagerFactory("BookUnit");
		entityManager = factory.createEntityManager();
		entityManager.getTransaction().begin();
	}

	private static void create() {
		Book book = new Book();
		book.setTitle("thinking in Java");
		book.setAuthor("mike");
		book.setPrice(350);
		entityManager.persist(book);
	}

	private static void update() {
		Book existBook = new Book();
		existBook.setBook_id(3);
		existBook.setTitle("Learn Java");
		existBook.setAuthor("mike shaw");
		existBook.setPrice(350);
		entityManager.merge(existBook);
	}

	private static void find() {
		Book book = entityManager.find(Book.class, 2);
		System.out.println(book.getTitle());
		System.out.println(book.getAuthor());
		System.out.println(book.getPrice());
	}

	
	private static void query() {
		String jpql = "select b from Book b where b.price <650";
		Query query = entityManager.createQuery(jpql);
		@SuppressWarnings("unchecked")
		List<Book> listBooks = query.getResultList();
		for (Book book : listBooks) {
			System.out.println(book.getTitle() + " " + book.getAuthor() + " " + book.getPrice());
		}
	}

	private static void remove() {
		Integer primaryKey = 2;
		Book reference = entityManager.getReference(Book.class, primaryKey);
		entityManager.remove(reference);
	}

	private static void end() {
		entityManager.getTransaction().commit();
		entityManager.close();
		factory.close();
	}

}
