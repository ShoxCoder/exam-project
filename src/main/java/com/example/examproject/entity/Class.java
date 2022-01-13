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
@EqualsAndHashCode(callSuper = true)
public class Class extends AbsEntity {
    @Column(nullable = false)
    private String className;



}
