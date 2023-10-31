package com.test.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ComicDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ThumbnailDTO;
import java.util.ArrayList;
import java.util.List;

public class ComicMapper {

  public static List<ComicDTO> toDtoList(JsonNode rootNode) {
    ArrayNode resultsNode = getResultsNode(rootNode);

    List<ComicDTO> comics = new ArrayList<>();
    resultsNode.elements().forEachRemaining(node -> {
      comics.add(ComicMapper.toDto(node));
    });

    return comics;
  }

  private static ComicDTO toDto(JsonNode comicNode) {
    if (comicNode == null) {
      throw new IllegalArgumentException("El nodo json no puede ser null");
    }

    return ComicDTO.builder()
        .id(comicNode.get("id").asLong())
        .title(comicNode.get("title").asText())
        .description(comicNode.get("description").asText())
        .modified(comicNode.get("modified").asText())
        .resourceURI(comicNode.get("resourceURI").asText())
        .thumbnailDTO(ThumbnailMapper.toDto(comicNode.get("thumbnail")))
        .build();
  }

  private static ArrayNode getResultsNode(JsonNode rootNode) {
    if (rootNode == null) {
      throw new IllegalArgumentException("El nodo json no puede ser null");
    }

    JsonNode dataNode = rootNode.get("data");
    return (ArrayNode) dataNode.get("results");
  }
}
