package com.test.api.marvelchallenge.persistence.integration.marvel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CharacterDTO {

  private Long id;
  private String name;
  private String description;
  private String modified;
  private String resourceURI;

  @Getter
  @Setter
  @Builder
  public static class CharacterInfoDTO {

    private String imagePath;
    private String description;
  }
}
