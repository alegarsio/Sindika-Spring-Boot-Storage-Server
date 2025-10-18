package com.storageserver.model;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Set;


/**
 * (c) Alegrarsio Gifta Lesmana
 * com.storageserver.model.Pengelola
 * Represent data and record of pengelola table
 */

@Data
@Entity
@Table(name = "pengelola")
public class Pengelola {
    
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Integer id; 


    @Column(nullable = false)
    private String nama;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Pengelola parent;

    
    @OneToMany(mappedBy = "parent")
    private Set<Pengelola> children;
}
