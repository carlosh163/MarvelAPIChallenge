package com.test.api.marvelchallenge.web.controller;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvelchallenge.service.CharacterService;
import java.util.List;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@RequiredArgsConstructor
public class CharacterController {

  private final CharacterService characterService;

  @GetMapping
  public ResponseEntity<List<CharacterDTO>> findAll(
      @RequestParam(required = false) String name,
      @RequestParam(required = false) int[] comics,
      @RequestParam(required = false) int[] series,
      @RequestParam(defaultValue = "0") long offset,
      @RequestParam(defaultValue = "10") long limit) {
    MyPageable pageable = new MyPageable(offset, limit);
    return ResponseEntity.ok(characterService.findAll(pageable, name, comics, series));
  }

  @GetMapping("/{characterId}")
  public ResponseEntity<CharacterDTO.CharacterInfoDTO> findInfoById(
      @PathVariable("characterId") Long characterId) {
    return ResponseEntity.ok(characterService.findInfoById(characterId));
  }
}
