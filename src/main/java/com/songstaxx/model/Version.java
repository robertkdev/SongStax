package com.songstaxx.model;

import javax.persistence.*;

@Entity
public class Version {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String versionName;
    private String filePath;

    @ManyToOne
    @JoinColumn(name = "song_id")
    private Song song;

    // Getters and Setters
}