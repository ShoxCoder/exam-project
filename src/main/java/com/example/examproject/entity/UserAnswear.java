package com.example.examproject.entity;

import com.example.examproject.entity.template.AbsEntity;
import lombok.*;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class UserAnswear extends AbsEntity {


    @ManyToOne
    private User userName;
    @ManyToOne
    private Test testTitle;
    @OneToOne(cascade = CascadeType.ALL)
    private TestQuestion testQuestion;


    private String givenAnswear;


}
