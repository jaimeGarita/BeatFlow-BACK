package com.backend.beatflow.model;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@CrossOrigin
@Table(name="USER")
public class User {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String email;
    private String userName;
    private String password;
}
