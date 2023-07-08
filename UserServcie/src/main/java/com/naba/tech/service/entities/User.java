package com.naba.tech.service.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @Column(name ="ID")
    private String userId;

    @Column(name ="NAME")
    private String name;

    @Column(name ="EMAIL")
    private String email;

    @Column(name ="ABOUT")
    private String about;


    @Transient
    List<Rating> ratings=new ArrayList<>();
}
