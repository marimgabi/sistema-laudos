package com.example.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String username;

    private String password;

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    @Column(name = "data_inclusao")
    private Date dataInclusao;

    @Column(name = "data_ateracao")
    private Date dataAlteracao;

    private String status;
}
