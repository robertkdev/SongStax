package com.songstaxx.service;

import com.songstaxx.model.Song;
import com.songstaxx.model.Version;
import com.songstaxx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicService {

    @Autowired
    private SongRepository songRepository;

    public List<Version> getLatestVersions() {
        return songRepository.findAll().stream()
                .map(song -> song.getVersions().stream()
                        .max((v1, v2) -> Integer.compare(v1.getVersionNumber(), v2.getVersionNumber()))
                        .orElse(null))
                .collect(Collectors.toList());
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }
}
