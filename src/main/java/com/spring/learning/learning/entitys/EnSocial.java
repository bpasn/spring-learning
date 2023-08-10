package com.spring.learning.learning.entitys;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.Length;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "m_social")
@Data
public class EnSocial extends BaseEntiry{
    @Column(length = 120)
    private String facebook;
    @Column(length = 120)
    private String instagram;
    @Column(length = 120)
    private String tiktok;

    @OneToOne
    @JoinColumn(name = "m_user_id",nullable = false)
    private EnUser user;

}
