package com.test.api.marvelchallenge.web.controller;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.test.api.marvelchallenge.service.ComicService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comics")
@RequiredArgsConstructor
public class ComicController {

  private final ComicService comicService;
  @GetMapping
  public ResponseEntity<List<ComicDTO>> findAll(
      @RequestParam(required = false) Long characterId,
      @RequestParam(defaultValue = "0") long offset,
      @RequestParam(defaultValue = "10") long limit) {
    MyPageable pageable = new MyPageable(offset, limit);

    return ResponseEntity.ok(comicService.findAll(pageable, characterId));

  }

  @GetMapping("/{comicId}")
  public ResponseEntity<ComicDTO> findAllById(
      @PathVariable Long comicId) {
    return ResponseEntity.ok(comicService.findById(comicId));
  }
}
