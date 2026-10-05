package lk.bookbarlibrarymember.user.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lk.bookbarlibrarymember.employee.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Entity // specifies that the class is an entity
@Table(name="user") // Specifies the primary table for the annotated entity

@Data // generate setters, getters, toString
@AllArgsConstructor // all argument constructor
@NoArgsConstructor // default constructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class User {

@Id //PK
@GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
private Integer id;

@Column(name="username" , unique = true)
@NotNull
private String username;

@Column(name="password")
@NotNull
private String password;

@NotNull
private String email;

private String note;

@NotNull
private Boolean userstatus;

private LocalDateTime addeddatetime ;
private LocalDateTime updateddatetime;
private LocalDateTime deleteddatetime;

private byte[] userphoto;

@ManyToOne(optional = true)
@JoinColumn(name="employee_id", referencedColumnName = "id")
private Employee employee_id;

@ManyToMany // many to many relationship between user and role
@JoinTable(name="user_has_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
private Set<Role> roles;


}
