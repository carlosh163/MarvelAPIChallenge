package com.test.api.marvelchallenge.service.impl;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO.CharacterInfoDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.repository.CharacterRepository;
import com.test.api.marvelchallenge.service.CharacterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CharacterServiceImpl implements CharacterService {

  private final CharacterRepository characterRepository;
  @Override
  public List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
    return characterRepository.findAll(pageable, name, comics, series);
  }

  @Override
  public CharacterInfoDTO findInfoById(Long characterId) {
    return characterRepository.findInfoById(characterId);
  }
}
