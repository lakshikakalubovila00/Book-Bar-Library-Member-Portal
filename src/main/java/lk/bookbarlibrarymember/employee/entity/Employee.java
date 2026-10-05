package lk.bookbarlibrarymember.employee.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity // convert Employee class into Entity (persitence entity)
@Table(name="employee") //Specifies the primary table for the annotated entity

@Data //generate setter function , getter function , toString function
@AllArgsConstructor//all argument constructor
@NoArgsConstructor // Empty constructor

@JsonInclude(JsonInclude.Include.NON_NULL) //  do not pass null records

public class Employee {

    @Id // indicate primary key
    @GeneratedValue(strategy=GenerationType.IDENTITY) // auto increment
    private Integer id ;

    @NotNull
    private String fullname ;
    
    @NotNull
    private String callingname ;

    @Column(name = "mobilenumber" ,unique = true)
    @Length(min=10, max=10, message = "Mobile Number value must be 10")
    @NotNull
    private String mobilenumber;

    private String landnumber;

    @Column(name = "nic" ,unique = true)
    @Length(min=10, max=12, message = "NIC value length must be 10 or 12")
    @NotNull
    private String nic;

    @NotNull
    private String gender;

    @NotNull 
    private String address;
    
    @NotNull
    private LocalDate dob;

    private String note;
    
    @Column(name = "email" ,unique = true)
    @NotNull
    private String email;
    
    @Column(name = "empno" ,unique = true)
    @Length(max=8, message = "EmpNo Length must be 8")
    @NotNull
    private String empno;

    private Integer addeduserid ;
    private LocalDateTime addeddatetime;

    private Integer updateduserid; 
    private LocalDateTime updateddatetime;

    private Integer deleteduserid; 
    private LocalDateTime deleteddatetime;
    
    @NotNull
    private String civilstatus;

    private byte[] employeephoto;

    @ManyToOne
    // employee to employeestatus has one to many relationship
    @JoinColumn(name="employeestatus_id", referencedColumnName = "id")
    private EmployeeStatus employeestatus_id; 

    @ManyToOne
    // employee to designation has many to one relationship
    @JoinColumn(name="designation_id", referencedColumnName = "id")
    private Designation designation_id;
}
