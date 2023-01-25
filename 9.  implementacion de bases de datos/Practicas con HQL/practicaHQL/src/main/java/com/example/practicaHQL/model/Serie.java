package com.example.practicaHQL.model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Serie {
    @Id
    private Integer id;
    @OneToOne(cascade = CascadeType.ALL)
    private Genre genre_id;
    private Date endDate;
    private Timestamp createdAt;
    private Date releasedDate;
    @Column(length = 500)
    private String title;
    private Timestamp updatedAt;
}
