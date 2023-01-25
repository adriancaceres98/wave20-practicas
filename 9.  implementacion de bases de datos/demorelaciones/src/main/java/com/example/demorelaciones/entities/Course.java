package com.example.demorelaciones.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter @Setter
@Entity
@Table(name = "courses")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToMany(mappedBy = "likedCourses")
  Set<Student> likes;
}
