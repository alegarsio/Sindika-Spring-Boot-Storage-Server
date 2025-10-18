package com.storageserver.dto;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import com.storageserver.model.Document;;

/**
 * Document Version Model 
 * is representing document versioning table
 */
@Data
@Entity
@Table(name = "document_versions")
public class DocumentVersion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private Long id; 
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document;

    @Column(name = "version_number")
    private int versionNumber;

   
    @Column(name = "file_url")
    private String fileUrl;

   
    @Column(name = "file_size")
    private Long fileSize;

    private String notes;

    
    @Column(name = "created_by", nullable = false)
    private Integer createdBy; 
    
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}


