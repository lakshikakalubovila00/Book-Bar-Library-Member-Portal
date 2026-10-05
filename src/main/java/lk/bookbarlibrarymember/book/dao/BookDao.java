package lk.bookbarlibrarymember.book.dao;

import lk.bookbarlibrarymember.book.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookDao extends JpaRepository<Book,Integer> {


    @Query(value = "select distinct  b.author from Book b where b.author is not null")
    List<String> findAllAuthors();

    @Query(value = "select distinct  b.publisher from Book b where b.publisher is not null")
    List<String> findAllPublishers();

    @Query(value = "select  distinct b.seriestitle from Book b where b.seriestitle is not null")
    List<String> findAllSeries();

    @Query(value = "select distinct  b.title from Book b where b.title is not null")
    List<String> findAllTitles();

    @Query(value = "select distinct  b.isbn from Book b where b.isbn is not null")
    List<String> findAllISBSNs();

    @Query(value = "select distinct  b.issn from Book b where b.issn is not null")
    List<String> findAllISSNs();

    @Query(value = "select distinct  b.displaycategory_id.name from Book b where b.displaycategory_id.name is not null")
    List<String> findAllDisplayCategories();

}
