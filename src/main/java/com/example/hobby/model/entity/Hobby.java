package com.example.hobby.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "hobbies")
public class Hobby extends BaseEntity {

    private String title;
    private PictureEntity photo;
    private CategoryHobby category;
    private String description;
    private User user;

    public Hobby() {
    }

    @OneToOne(cascade = CascadeType.MERGE)
    public PictureEntity getPhoto() {
        return photo;
    }

    public void setPhoto(PictureEntity photo) {
        this.photo = photo;
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @ManyToOne
    public CategoryHobby getCategory() {
        return category;
    }

    public void setCategory(CategoryHobby category) {
        this.category = category;
    }

    @Column(nullable = false)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
