package com.example.usermanagement.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 4,message = "Name must be more than 4")
    @Column(columnDefinition = "varchar(10) not null")
    @Check(constraints = "name > 4")
    private String name;

    @NotEmpty
    @Size(min = 4,message = "Username must be more than 4")
    @Column(columnDefinition = "varchar(10) not null unique")
    @Check(constraints = "username > 4")
    private String username;

    @NotEmpty(message = "Password must not be empty")
    @Size(min = 8, message = "Password must be more than 8 characters")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$", message = "Password must contain letter and number")

    @Column(columnDefinition = "varchar (15) not null")
    @Check(constraints = "LENGTH(password) >= 8")
    @Check(constraints = "password ~ '^(?=.*[0-9])(?=.*[a-zA-Z]).{6,}$'")
    private String password;

    @NotEmpty(message = "Email must not be empty")
    @Email(message = "Email should be contain '@' ")

    @Column(columnDefinition = "varchar(40) not null unique ")
    @Check(constraints = "email LIKE '%@%'")
    private String email;


    @NotEmpty(message = "Role must not be empty")
    @Pattern(regexp = "ADMIN|USER", message = "Role must be either ADMIN or USER")

    @Column(columnDefinition = "varchar(8) not null ")
    @Check(constraints = "role IN ('ADMIN','USER) ")
    private String role;

    @NotNull(message = "Age cannot be null.")
//    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "Age must be a positive integer.")

    @Column(columnDefinition = "int not null")
//    @Check(constraints = "age ~ '^(0|[1-9][0-9]*)$'")
    private int age ;

}
