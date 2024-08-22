package com.songstaxx.service;

import com.songstaxx.model.Song;
import com.songstaxx.model.Version;
import com.songstaxx.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MusicServiceImpl implements MusicService {

    private final SongRepository songRepository;

    @Autowired
    public MusicServiceImpl(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Version> getLatestVersions() {
        return songRepository.findAll().stream()
                .map(song -> song.getVersions().stream()
                        .max((v1, v2) -> Integer.compare(v1.getVersionNumber(), v2.getVersionNumber()))
                        .orElse(null))
                .collect(Collectors.toList());
    }

    @Override
    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    @Override
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    @Override
    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

    @Override
    public Song getSongById(Long songId) {
        return songRepository.findById(songId).orElse(null);
    }
}
