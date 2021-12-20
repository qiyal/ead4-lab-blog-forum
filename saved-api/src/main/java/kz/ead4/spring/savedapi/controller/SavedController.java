package kz.ead4.spring.savedapi.controller;

import io.swagger.annotations.ApiOperation;
import kz.ead4.spring.savedapi.model.Saved;
import kz.ead4.spring.savedapi.model.SavedPost;
import kz.ead4.spring.savedapi.service.impl.SavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved")
public class SavedController {
    @Autowired
    private SavedService savedService;

    private final static String publicApi = "/public";
    private final static String privateApi = "/private";

    @ApiOperation(value = "Create new saved")
    @PostMapping(privateApi + "/create")
    private ResponseEntity<?> createSaved(@RequestBody Saved saved) {
        return ResponseEntity.ok(savedService.createSaved(saved));
    }

    @ApiOperation(value = "Get by saved id")
    @GetMapping(publicApi + "")
    private ResponseEntity<?> getSavedById(@RequestParam("id") Long savedId) {
        return ResponseEntity.ok(savedService.getById(savedId));
    }

    @ApiOperation(value = "Update saved title")
    @PatchMapping(privateApi + "/rename-title")
    private ResponseEntity<?> updateSavedTitle(@RequestParam("id") Long id, @RequestParam("title") String title) {
        return ResponseEntity.ok(savedService.updateSavedTitle(id, title));
    }

    @ApiOperation(value = "Delete by saved id")
    @DeleteMapping(privateApi + "/delete/{id}")
    private void deleteSaved(@PathVariable("id") Long id) {
        savedService.deletePost(id);
    }

    @ApiOperation(value = "Search post by title in saved")
    @GetMapping(publicApi + "/search")
    private ResponseEntity<?> searchPostByTitleInSaved(@RequestParam("id") Long id, @RequestParam("title") String title) {
        return ResponseEntity.ok(savedService.searchPostByTitleInSaved(id, title));
    }

    @ApiOperation(value = "Get saved by owner id")
    @GetMapping(publicApi + "/getByOwnerId/{ownerId}")
    private ResponseEntity<?> getSavedByOwnerId(@PathVariable("ownerId") Long ownerId) {
        return ResponseEntity.ok(savedService.getByOwnerId(ownerId));
    }

    @PostMapping(privateApi + "/add-post")
    private ResponseEntity<?> addPost(@RequestBody SavedPost savedPost) {
        return ResponseEntity.ok(savedService.addPost(savedPost));
    }
}
