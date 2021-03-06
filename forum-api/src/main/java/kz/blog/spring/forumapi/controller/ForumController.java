package kz.blog.spring.forumapi.controller;

import io.swagger.annotations.ApiOperation;
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
    private final static String publicApi = "/public";
    private final static String privateApi = "/private";

    @ApiOperation(value = "Get all forums")
    @GetMapping(publicApi+ "/all")
    public ResponseEntity<?> getAllForums() {
        return ResponseEntity.ok(forumService.getAllForum());
    }

    @ApiOperation(value = "Get by forum id")
    @GetMapping(publicApi)
    public ResponseEntity<?> getForumById(@RequestParam("forumId") Long forumId) {
        return ResponseEntity.ok(forumService.getForumById(forumId));
    }

    @ApiOperation(value = "Search forums by title field")
    @GetMapping(publicApi + "/search")
    public ResponseEntity<?> searchForumByTitle(@RequestParam("title") String title) {
        System.out.println("search" + " " + title);
        return ResponseEntity.ok(forumService.searchForumByTitle(title));
    }

    @ApiOperation(value = "Create new forum")
    @PostMapping(privateApi + "/create")
    public ResponseEntity<?> createForum(@RequestBody Forum forum) {
        return ResponseEntity.ok(forumService.createForum(forum));
    }

    @ApiOperation(value = "Delete by forum id")
    @DeleteMapping(publicApi+ "/delete/{forumId}")
    public void deleteForum(@PathVariable("forumId") Long forumId) {
        forumService.deleteForum(forumId);
    }

    @ApiOperation(value = "Set forum owner by username")
    @PatchMapping(privateApi + "/change-owner")
    public ResponseEntity<?> updateForumOwnerByUsername(@RequestParam("forumId") Long forumId, @RequestParam("username") String username) {
        return ResponseEntity.ok(forumService.updateForumOwnerByUsername(forumId, username));
    }

    @ApiOperation(value = "Add member in forum")
    @PatchMapping(privateApi + "/add-member")
    public ResponseEntity<?> addMember(@RequestParam("forumId") Long forumId, @RequestParam("username") String username) {
        return ResponseEntity.ok(forumService.addMember(forumId, username));
    }

    @ApiOperation(value = "remove member in forum")
    @PatchMapping(privateApi+ "/remove-member")
    public ResponseEntity<?> removeMember(@RequestParam("forumId") Long forumId, @RequestParam("memberId") Long memberId) {
        return ResponseEntity.ok(forumService.removeMember(forumId, memberId));
    }
}
