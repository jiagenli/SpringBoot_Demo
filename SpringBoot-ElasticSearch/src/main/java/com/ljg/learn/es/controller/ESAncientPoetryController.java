package com.ljg.learn.es.controller;

import com.ljg.learn.es.model.AncientPoetry;
import com.ljg.learn.es.service.ESAncientPoetryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/es/ancientPoetry")
public class ESAncientPoetryController {
    private final ESAncientPoetryService esAncientPoetryService;

    @PostMapping
    public ResponseEntity<Boolean> add(@RequestBody AncientPoetry ancientPoetry) {
        return ResponseEntity.ok(esAncientPoetryService.addAncientPoetry(ancientPoetry));
    }

    @GetMapping("{id}")
    public ResponseEntity<AncientPoetry> get(@PathVariable String id) {
        return ResponseEntity.ok(esAncientPoetryService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<AncientPoetry>> findAncientPoetry(String keyword) {
        return ResponseEntity.ok(esAncientPoetryService.findAncientPoetry(keyword));
    }
}
