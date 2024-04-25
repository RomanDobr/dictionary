package org.javaacademy.dictionary.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.dictionary.entity.WordText;
import java.util.List;

@Data
@NoArgsConstructor
public class WordDtoRq {
  private String word;
  private List<WordText> wordText;
}
