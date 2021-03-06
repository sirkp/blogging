package com.example.bloggingApp.entities;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "articles")
public class Article  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid", unique = true, nullable = false)
    private String uuid;

    @Column(name = "title", nullable = false, length = 512)
    private String title;

    @Column(name = "content", nullable = false)
    @Lob
    private String content;

    @Column(name = "published_at")
    @CreationTimestamp
    Date publishedDate;

    @Column(name = "slug", nullable = false, length = 512, unique = true)
    private String slug;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_user", nullable = false)
    @Setter(AccessLevel.NONE)
    private User user;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable(name = "articles_tags"
            , joinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id")
            , inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
    @Setter(AccessLevel.NONE)
    private Set<Tag> tags = new HashSet<>();

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
        tags.stream().forEach((tag) -> this.addTag(tag));
    }

    public void addTag(Tag tag) {
        this.getTags().add(tag);
        tag.getArticles().add(this);
    }

    public void removeTag(Tag tag) {
        this.getTags().remove(tag);
        tag.getArticles().remove(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getArticles().add(this);
    }

    public void removeUser() {
        this.user = null;
        user.getArticles().remove(this);
    }
}
