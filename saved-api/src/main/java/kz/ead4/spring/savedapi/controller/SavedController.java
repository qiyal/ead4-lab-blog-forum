package kz.ead4.spring.savedapi.controller;

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

    @PostMapping("/create")
    private ResponseEntity<?> createSaved(@RequestBody Saved saved) {
        return ResponseEntity.ok(savedService.createSaved(saved));
    }

    @GetMapping("")
    private ResponseEntity<?> getSavedById(@RequestParam("id") Long savedId) {
        return ResponseEntity.ok(savedService.getById(savedId));
    }

    @PatchMapping("/rename-title")
    private ResponseEntity<?> updateSavedTitle(@RequestParam("id") Long id, @RequestParam String title) {
        return ResponseEntity.ok(savedService.updateSavedTitle(id, title));
    }
}
