package com.songstaxx.controller;

import com.songstaxx.model.Song;
import com.songstaxx.model.Version;
import com.songstaxx.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class MusicController {

    private final MusicService musicService;

    @Autowired
    public MusicController(MusicService musicService) {
        this.musicService = musicService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("songs", musicService.getAllSongs());
        return "home";
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadMusic(@RequestParam("file") MultipartFile file,
                              @RequestParam("versionName") String versionName,
                              @RequestParam("songId") Long songId,
                              Model model) {
        try {
            // Save the file locally
            String uploadDir = "uploads/";
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String filePath = uploadDir + file.getOriginalFilename();
            File destinationFile = new File(filePath);
            file.transferTo(destinationFile);

            // Add the new version to the song
            Song song = musicService.getSongById(songId);
            if (song == null) {
                model.addAttribute("message", "Song not found");
                return "upload";
            }

            Version version = new Version();
            version.setVersionName(versionName);
            version.setFilePath(filePath);
            version.setSong(song);

            song.getVersions().add(version);
            musicService.saveSong(song);

        } catch (IOException e) {
            model.addAttribute("message", "File upload failed: " + e.getMessage());
            return "upload";
        }

        model.addAttribute("message", "File uploaded successfully!");
        return "redirect:/";
    }

    @GetMapping("/playlists")
    public String playlists(Model model) {
        // Logic to retrieve and display playlists
        return "playlists";
    }

    @GetMapping("/version-history")
    public String versionHistory(@RequestParam("songId") Long songId, Model model) {
        Song song = musicService.getSongById(songId);
        if (song == null) {
            model.addAttribute("message", "Song not found");
            return "home";
        }
        model.addAttribute("versions", song.getVersions());
        return "version-history";
    }
}
