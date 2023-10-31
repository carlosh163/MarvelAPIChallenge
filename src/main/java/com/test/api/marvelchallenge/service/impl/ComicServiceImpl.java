package com.test.api.marvelchallenge.service.impl;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.repository.ComicRepository;
import com.test.api.marvelchallenge.service.ComicService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComicServiceImpl implements ComicService {

  private final ComicRepository comicRepository;
  @Override
  public List<ComicDTO> findAll(MyPageable pageable, Long characterId) {
    return comicRepository.findAll(pageable, characterId);
  }

  @Override
  public ComicDTO findById(Long comicId) {
    return comicRepository.findById(comicId);
  }
}
