package com.example.demoSpringSec;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.boot.archive.scan.internal.ScanResultImpl;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", unique = true, nullable = false)
    private String post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
