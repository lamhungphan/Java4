package com.fpoly.java4_youtube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`video`")
public class Video {
    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "href")
    private String href;

    @Column(name = "poster")
    private String poster;

    @Column(name = "`views`")
    private Integer views;

    @Column(name = "shares")
    private Integer shares;

    @Column(name = "description")
    private String description;

    @Column(name = "isActive")
    private Boolean isActive;
}
