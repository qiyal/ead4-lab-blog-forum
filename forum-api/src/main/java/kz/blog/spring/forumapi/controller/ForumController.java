package kz.blog.spring.forumapi.controller;

import kz.blog.spring.forumapi.service.impl.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/forum")
public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllForums() {
        return ResponseEntity.ok(forumService.getAllForum());
    }

    @GetMapping()
    public ResponseEntity<?> getForumById(Long forumId) {
        return ResponseEntity.ok(forumService.getForumById(forumId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchForumByTitle(@RequestParam("title") String title) {
        return ResponseEntity.ok(forumService.searchForumByTitle(title));
    }

}
