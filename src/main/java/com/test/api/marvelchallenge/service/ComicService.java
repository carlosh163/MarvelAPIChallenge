package com.test.api.marvelchallenge.service;

import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import java.util.List;

public interface ComicService {

  List<ComicDTO> findAll(MyPageable pageable, Long characterId);

  ComicDTO findById(Long comicId);
}
