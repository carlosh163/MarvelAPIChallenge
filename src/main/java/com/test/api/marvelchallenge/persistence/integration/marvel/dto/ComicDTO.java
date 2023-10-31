package com.test.api.marvelchallenge.persistence.integration.marvel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ComicDTO {

  private Long id;
  private String title;
  private String description;
  private String modified;
  private String resourceURI;
  private ThumbnailDTO thumbnailDTO;
}
