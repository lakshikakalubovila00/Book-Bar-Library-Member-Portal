package lk.bookbarlibrarymember.book.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

@Entity
@Table(name = "book")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;

    @Column(name = "bookno", unique = true)
    @Length( max=8, message = "Book No length must be 8")
    @NotNull
    private String bookno;

    private String title ;
    private String author ;
    private String edition ;
    private Year editionyear ; //date time
    private String publisher ;
    private String isbn ; //(13)
    private String issn ; // (8)
    private String seriestitle ;
    private String seriesno ; // int
    private String ddcno ;
    private String callno ;
    private String pages ; // int
    private String description ;
    private byte[] coverimage ;
    private String note ;
    private Year startedyear ;
    private String volume;
    private String issueno;
    private LocalDate publicationdate;
    private BigDecimal initialprice ;

    @ManyToOne
    @JoinColumn(name = "resourcetype_id", referencedColumnName = "id")
    private ResourceType resourcetype_id ;

    @ManyToOne
    @JoinColumn(name = "magazinefrequency_id", referencedColumnName = "id")
    private MagazineFrequency magazinefrequency_id;

    @ManyToOne
    @JoinColumn(name = "newspaperedition_id", referencedColumnName = "id")
    private NewsPaperEdition newspaperedition_id;

    @ManyToOne
    @JoinColumn(name = "newspaperfrequency_id", referencedColumnName = "id")
    private NewsPaperFrequency newspaperfrequency_id;

    @ManyToOne
    @JoinColumn(name = "journalfrequency_id", referencedColumnName = "id")
    private JournalFrequency journalfrequency_id;

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id")
    private Language language_id ;

    @ManyToOne
    @JoinColumn(name = "displaycategory_id", referencedColumnName = "id")
    private DisplayCategory displaycategory_id ;

    @ManyToOne
    @JoinColumn(name = "bookstatus_id", referencedColumnName = "id")
    private BookStatus bookstatus_id ;

    private LocalDateTime addeddatetime ;
    private LocalDateTime updateddatetime ;
    private LocalDateTime deleteddatetime ;

    private Integer addeduserid ;
    private Integer updateduserid ;
    private Integer deleteduserid ;

}
