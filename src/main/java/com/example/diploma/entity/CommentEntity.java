package com.example.diploma.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author anna
 */
@Entity
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentEntity {
    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pk;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity author;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "comments_text")
    private String text;
    @Column(name = "ad_id")
    private int ad;

    public CommentEntity(UserEntity author, LocalDateTime createdAt, String text, int ad) {
        this.author = author;
        this.createdAt = createdAt;
        this.text = text;
        this.ad = ad;
    }
}
