package com.sparta.nyangdangback.comment.entity;

import com.sparta.nyangdangback.blog.entity.Blog;
import com.sparta.nyangdangback.blog.entity.TimeStamped;
import com.sparta.nyangdangback.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity // Comment Entity 클래스
@NoArgsConstructor
@Getter
public class Comment extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id; // 댓글 식별 id값
    private String comment; // 댓글

    @ManyToOne
    @JoinColumn(name = "blogid")
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @Builder
    public Comment(String comment, Blog blog, User user) {
        this.comment = comment;
        this.blog = blog;
        this.user = user;
    }

    // 댓글 수정 후, 수정사항 반영
    public void updateComment(String comment) {
        this.comment = comment;
    }
}
