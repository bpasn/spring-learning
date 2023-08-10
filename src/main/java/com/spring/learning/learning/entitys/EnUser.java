package com.spring.learning.learning.entitys;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity(name = "m_user")
public class EnUser extends BaseEntiry{
    @Column(nullable = false,unique = true,length = 60)
    private String email;

    @Column(nullable = false,length = 120)
    private String password;

    @Column(nullable = false,length = 120)
    private String name;

    @OneToOne(mappedBy = "user",orphanRemoval = true)
    private EnSocial social;

    @OneToMany(mappedBy = "user",orphanRemoval = true)
    private List<EnAddress> address = new ArrayList<>();
}
