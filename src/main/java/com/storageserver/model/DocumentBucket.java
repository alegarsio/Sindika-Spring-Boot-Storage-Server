package com.storageserver.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "document_buckets")
public class DocumentBucket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id; 

    
    @Column(nullable = false)
    private String name;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pengelola_id", nullable = false)
    private Pengelola pengelola;

   
    @Column(name = "allowed_file_types")
    private String allowedFileTypes;
}
