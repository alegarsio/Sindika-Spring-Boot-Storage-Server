package com.storageserver.model;



import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import com.storageserver.dto.DocumentVersion;
import java.time.LocalDateTime;
import java.util.Set;


@Data
@Entity
@Table(name = "documents")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Integer id; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bucket_id") 
    private DocumentBucket bucket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uploaded_by", nullable = false)
    private Pengelola uploadedBy;

   
    @Column(name = "user_friendly_name")
    private String userFriendlyName;

    @Column(nullable = false)
    private String fileUrl;

    private String fileType;
    private Long fileSize; 

    @CreationTimestamp
    private LocalDateTime createdAt;

    
    @Column(columnDefinition = "TEXT") 
    private String contentEmbedding;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProcessingStatus status = ProcessingStatus.Pending;

    @Lob
    private String processingError;

    @OneToMany(mappedBy = "document", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DocumentVersion> versions;    
}
