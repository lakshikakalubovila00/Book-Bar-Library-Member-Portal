package lk.bookbarlibrarymember.borrow.entity;

import jakarta.persistence.*;
import lk.bookbarlibrarymember.member.entity.Member;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity // convert  class into Entity (persitence entity)
@Table(name="borrow") //Specifies the primary table for the annotated entity

@Data //generate setter function , getter function , toString function
@AllArgsConstructor//all argument constructor
@NoArgsConstructor // Empty constructor
public class Borrow {

    @Id // indicate primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // auto increment
    private Integer id ;

    private LocalDate borrowdate ;

    private LocalDate handoverduedate ;

    private String borrowcode ;

    private String note ;

    @ManyToOne
    @JoinColumn(name="borrowstatus_id", referencedColumnName = "id")
    private BorrowStatus borrowstatus_id ;

    @ManyToOne
    @JoinColumn(name="member_id", referencedColumnName = "id")
    private Member member_id ;

    private BigDecimal fullfineamount ;

    // main side's link in the association used for mappedBy. can identify the relationship by this.
    // orphanRemovel- need to reomve book copies from the list
    // cascade - need to add and access to association
    @OneToMany(mappedBy = "borrow_id" , orphanRemoval = true, cascade = CascadeType.ALL)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<BorrowHasBookCopy> borrowHasBookCopiesList;

    private LocalDateTime addeddatetime ;
    private LocalDateTime updateddatetime ;
    private LocalDateTime deleteddatetime ;

    private Integer addeduserid ;
    private Integer updateduserid ;
    private Integer deleteduserid ;



}
