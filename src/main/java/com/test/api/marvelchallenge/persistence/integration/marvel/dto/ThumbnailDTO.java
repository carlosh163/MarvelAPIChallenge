package com.test.api.marvelchallenge.persistence.integration.marvel.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ThumbnailDTO {

  private String path;
  private String extension;
}
