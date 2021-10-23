package kz.ead4.spring.savedapi.controller;

import io.swagger.annotations.ApiOperation;
import kz.ead4.spring.savedapi.model.Saved;
import kz.ead4.spring.savedapi.service.impl.SavedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saved")
public class SavedController {
    @Autowired
    private SavedService savedService;

    @ApiOperation(value = "Create new saved")
    @PostMapping("/create")
    private ResponseEntity<?> createSaved(@RequestBody Saved saved) {
        return ResponseEntity.ok(savedService.createSaved(saved));
    }

    @ApiOperation(value = "Get by saved id")
    @GetMapping("")
    private ResponseEntity<?> getSavedById(@RequestParam("id") Long savedId) {
        return ResponseEntity.ok(savedService.getById(savedId));
    }

    @ApiOperation(value = "Update saved title")
    @PatchMapping("/rename-title")
    private ResponseEntity<?> updateSavedTitle(@RequestParam("id") Long id, @RequestParam("title") String title) {
        return ResponseEntity.ok(savedService.updateSavedTitle(id, title));
    }

    @ApiOperation(value = "Delete by saved id")
    @DeleteMapping("/delete/{id}")
    private void deleteSaved(@PathVariable("id") Long id) {
        savedService.deletePost(id);
    }

    @ApiOperation(value = "Search post by title in saved")
    @GetMapping("/search")
    private ResponseEntity<?> searchPostByTitleInSaved(@RequestParam("id") Long id, @RequestParam("title") String title) {
        return ResponseEntity.ok(savedService.searchPostByTitleInSaved(id, title));
    }
}
