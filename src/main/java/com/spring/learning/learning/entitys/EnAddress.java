package com.spring.learning.learning.entitys;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
@EqualsAndHashCode(callSuper = true)
@Entity(name = "m_address")
@Data
public class EnAddress  extends BaseEntiry{


    private String line1;
    private String line2;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private EnUser user;
}
