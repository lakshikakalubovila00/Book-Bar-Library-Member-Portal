package lk.bookbarlibrarymember;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BookbarlibrarymemberApplication {

	public static void main(String[] args) {

        SpringApplication.run(BookbarlibrarymemberApplication.class, args);
        System.out.println("Start Application - The Book Bar Library Member Application");
	}


}
