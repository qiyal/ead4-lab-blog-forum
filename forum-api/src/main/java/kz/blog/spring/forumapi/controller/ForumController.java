package kz.blog.spring.forumapi.controller;

import kz.blog.spring.forumapi.model.Forum;
import kz.blog.spring.forumapi.service.impl.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> getForumById(@RequestParam("forumId") Long forumId) {
        return ResponseEntity.ok(forumService.getForumById(forumId));
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchForumByTitle(@RequestParam("title") String title) {
        System.out.println("search" + " " + title);
        return ResponseEntity.ok(forumService.searchForumByTitle(title));
    }

    @PostMapping("/create")
    public ResponseEntity<?> createForum(@RequestBody Forum forum) {
        return ResponseEntity.ok(forumService.createForum(forum));
    }

    @DeleteMapping("/delete/{forumId}")
    public void deleteForum(@PathVariable("forumId") Long forumId) {
        forumService.deleteForum(forumId);
    }

    @PatchMapping("/change-owner")
    public void updateForumOwnerByUsername(@RequestParam("forumId") Long forumId, @RequestParam("username") String username) {
        forumService.updateForumOwnerByUsername(forumId, username);
    }

    @PatchMapping("/add-member")
    public void addMember(@RequestParam("forumId") Long forumId, @RequestParam("username") String username) {
        forumService.addMember(forumId, username);
    }

    @PatchMapping("/remove-member")
    public void removeMember(@RequestParam("forumId") Long forumId, @RequestParam("memberId") Long memberId) {
        forumService.removeMember(forumId, memberId);
    }
}
