package com.swamyms.webapp.entity.file;

import com.swamyms.webapp.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "files")
public class FileEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.UUID)  // Uses UUID for ID generation
//    private String id;
//
//    private String name;
    private Long size;
//    private String url;  // Optional: Can store URL or path if needed

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    @ReadOnlyProperty
    private String id;

    @ReadOnlyProperty
    @Column(name = "file_name")
    private String fileName;

    @ReadOnlyProperty
    @Column(name = "url")
    private String url;

    @ReadOnlyProperty
    @Column(name = "upload_date", updatable = false)
    private LocalDate uploadDate;

//    @ReadOnlyProperty
//    @Column(name = "user_id")
//    @GeneratedValue(strategy = GenerationType.UUID)
//    private String userId; // Assuming user_id is of type String

    @OneToOne // Establishing the one-to-one relationship with User
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false) // Ensuring user_id is not null
    private User user; // Reference to the User entity
    public FileEntity() {

    }

    @PrePersist
    protected void onCreate() {
        uploadDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        uploadDate = LocalDate.now();
    }
}
