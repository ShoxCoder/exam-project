package com.example.examproject.entity;

import com.example.examproject.entity.template.AbsEntity;
import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = false)
public class UserStatus extends AbsEntity {

    @ManyToOne
    private Test testTitle;
    @ManyToOne
    private  User users;
    @Column(nullable = false)
    private double userStatus;

}
