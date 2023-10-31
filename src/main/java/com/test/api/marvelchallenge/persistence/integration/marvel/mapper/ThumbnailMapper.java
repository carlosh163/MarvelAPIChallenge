package com.test.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ThumbnailDTO;

public class ThumbnailMapper {

  public static ThumbnailDTO toDto (JsonNode thumbnailNode) {
    if (thumbnailNode == null) {
      throw new IllegalArgumentException("El nodo json no puede ser null");
    }
    return ThumbnailDTO.builder()
        .path(thumbnailNode.get("path").asText())
        .extension(thumbnailNode.get("extension").asText())
        .build();
  }
}
