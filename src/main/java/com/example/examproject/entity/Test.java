package com.example.examproject.entity;

import com.example.examproject.entity.template.AbsEntity;
import lombok.*;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.time.Duration;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Test extends AbsEntity {

    @Column(nullable = false)
    private String testTitle;
    @Column(nullable = false)
    @ManyToMany
    private Set<Class> classes;

    @ManyToOne
    private Subject subjectName;
    @Column(nullable = false,unique = true)
    private String duration;
    @Column(nullable = false)
    private Integer totalQuestion;

    private double marksPerRightAnswear;

    private double marksPerWrongAnswear;

    private String testStatus;


}
