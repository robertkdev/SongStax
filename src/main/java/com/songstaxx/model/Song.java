package com.songstaxx.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String artist;
    private String album;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<Version> versions;


}