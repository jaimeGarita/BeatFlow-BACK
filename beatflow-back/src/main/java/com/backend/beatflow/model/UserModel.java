package com.backend.beatflow.model;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@CrossOrigin
@Table(name="USER")
public class UserModel {
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    private String email;
    private String userName;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_authority",
        joinColumns = @JoinColumn(name="user_id", referencedColumnName = "idUser"),
        inverseJoinColumns = @JoinColumn(name="authority_id", referencedColumnName = "id")
    )
    private List<Authority> authorities;

    public UserModel(String email, String userName, String password, List<Authority> authorities) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    

}
