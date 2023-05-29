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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    //TODO Generar el salting y el pepper, primero el salting una vez tenga el salting aplicarlo a la pass, hacer un hash de todo eso y luego volver a hacerlo con el pepper

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<RoleModel> roles;


}
