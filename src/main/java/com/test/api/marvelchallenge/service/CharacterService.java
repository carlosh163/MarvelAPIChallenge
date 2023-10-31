package com.test.api.marvelchallenge.service;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO.CharacterInfoDTO;
import java.util.List;

public interface CharacterService {

  List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series);

  CharacterInfoDTO findInfoById(Long characterId);
}
