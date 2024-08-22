package com.songstaxx.service;

import com.songstaxx.model.Song;
import com.songstaxx.model.Version;

import java.util.List;

public interface MusicService {
    List<Version> getLatestVersions();
    List<Song> getAllSongs();
    Song saveSong(Song song);
    void deleteSong(Long id);
    Song getSongById(Long songId);
}
