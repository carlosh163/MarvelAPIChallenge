package com.test.api.marvelchallenge.persistence.integration.marvel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.CharacterDTO.CharacterInfoDTO;
import com.test.api.marvelchallenge.persistence.integration.marvel.dto.ThumbnailDTO;
import java.util.ArrayList;
import java.util.List;

public class CharacterMapper {

  public static List<CharacterDTO> toDtoList(JsonNode rootNode) {
    ArrayNode resultsNode = getResultsNode(rootNode);

    List<CharacterDTO> characters = new ArrayList<>();
    resultsNode.elements().forEachRemaining(node -> {
      characters.add(CharacterMapper.toDto(node));
    });

    return characters;
  }

  private static CharacterDTO toDto(JsonNode characterNode) {
    if (characterNode == null) {
      throw new IllegalArgumentException("El nodo json no puede ser null");
    }

    return CharacterDTO.builder()
        .id(Long.parseLong(characterNode.get("id").asText()))
        .name(characterNode.get("name").asText())
        .description(characterNode.get("description").asText())
        .modified(characterNode.get("modified").asText())
        .resourceURI(characterNode.get("resourceURI").asText())
        .build();
  }

  private static ArrayNode getResultsNode(JsonNode rootNode) {
    if (rootNode == null) {
      throw new IllegalArgumentException("El nodo json no puede ser null");
    }

    JsonNode dataNode = rootNode.get("data");
    return (ArrayNode) dataNode.get("results");
  }

  public static List<CharacterDTO.CharacterInfoDTO> toInfoDtoList(JsonNode response) {

    ArrayNode resultsNode = getResultsNode(response);
    List<CharacterDTO.CharacterInfoDTO> characters = new ArrayList<>();
    resultsNode.elements().forEachRemaining(node -> {
      characters.add(CharacterMapper.toInfoDto(node));
    });

    return characters;
  }

  private static CharacterDTO.CharacterInfoDTO toInfoDto(JsonNode characterNode) {
    if (characterNode == null) {
      throw new IllegalArgumentException("El nodo json no puede ser null");
    }

    ThumbnailDTO thumbnailDTO = ThumbnailMapper.toDto(characterNode.get("thumbnail"));

    String image = thumbnailDTO.getPath().concat(".").concat(thumbnailDTO.getExtension());

    return CharacterInfoDTO.builder()
        .imagePath(image)
        .description(characterNode.get("description").asText())
        .build();
  }
}
