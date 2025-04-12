package com.example.entities;

import com.example.Enums.EnumStatus;
import com.example.Enums.converter.EnumStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private String email;

    private String username;

    private String password;

//    @ManyToOne
//    @JoinColumn(name = "id_role")
//    private Role role;

    @Column(name = "data_inclusao")
    private Date dataInclusao;

    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    @Convert(converter = EnumStatusConverter.class)
    private EnumStatus status;
}
