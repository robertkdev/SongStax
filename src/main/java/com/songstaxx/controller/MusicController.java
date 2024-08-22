package com.songstaxx.controller;

import com.songstaxx.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MusicController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Welcome to SongStaxx!");
        model.addAttribute("songs", musicService.getAllSongs());
        return "home";
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadMusic(@RequestParam("file") MultipartFile file, Model model) {
        // Logic to handle file upload
        model.addAttribute("message", "File uploaded successfully!");
        return "upload";
    }

    @GetMapping("/playlists")
    public String playlists(Model model) {
        // Logic to retrieve and display playlists
        return "playlists";
    }

    @GetMapping("/version-history")
    public String versionHistory(@RequestParam("songId") Long songId, Model model) {
        // Logic to retrieve and display version history
        return "version-history";
    }
}
