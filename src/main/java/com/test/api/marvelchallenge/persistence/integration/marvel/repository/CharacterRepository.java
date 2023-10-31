package com.test.api.marvelchallenge.persistence.integration.marvel.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvelchallenge.dto.MyPageable;
import com.test.api.marvelchallenge.persistence.integration.marvel.mapper.CharacterMapper;
import com.test.api.marvelchallenge.persistence.integration.marvel.MarvelAPIConfig;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO.CharacterInfoDTO;
import com.test.api.marvelchallenge.service.HttpClientService;
import jakarta.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
@RequiredArgsConstructor
public class CharacterRepository {

  private final MarvelAPIConfig marvelAPIConfig;

  private final HttpClientService httpClientService;

  @Value("${integration.marvel.base-path}")
  private String basePath;

  private String characterPath;

  @PostConstruct
  private void setPath () {
    characterPath = basePath.concat("/").concat("characters");
  }
  public List<CharacterDTO> findAll(MyPageable pageable, String name, int[] comics, int[] series) {
    Map<String, String> marvelQueryParams = getQueryParamsForFindAll(pageable, name, comics, series);

    JsonNode response = httpClientService.doGet(characterPath, marvelQueryParams, JsonNode.class);

    return CharacterMapper.toDtoList(response);
  }

  private Map<String, String> getQueryParamsForFindAll(MyPageable pageable, String name, int[] comics, int[] series) {
    Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

    marvelQueryParams.put("offset", Long.toString(pageable.offset()));
    marvelQueryParams.put("limit", Long.toString(pageable.limit()));

    if(StringUtils.hasText(name)) {
      marvelQueryParams.put("name", name);
    }

    if(comics != null) {
      String comicAsString = this.joinIntArray(comics);
      marvelQueryParams.put("comics", comicAsString);
    }

    if(series != null) {
      String seriesAsString = this.joinIntArray(series);
      marvelQueryParams.put("series", seriesAsString);
    }

    return marvelQueryParams;
  }

  private String joinIntArray(int[] intArray) {

    List<String> stringArray = IntStream.of(intArray).boxed()
        .map(Object::toString)
        .collect(Collectors.toList());
    return String.join(",", stringArray);
  }

  public CharacterInfoDTO findInfoById(Long characterId) {
    Map<String, String> marvelQueryParams = marvelAPIConfig.getAuthenticationQueryParams();

    String finalUrl = characterPath.concat("/").concat(Long.toString(characterId));

    JsonNode response = httpClientService.doGet(finalUrl, marvelQueryParams, JsonNode.class);
    return CharacterMapper.toInfoDtoList(response).get(0);
  }
}
