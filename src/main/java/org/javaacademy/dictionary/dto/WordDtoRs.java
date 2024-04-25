package org.javaacademy.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.javaacademy.dictionary.entity.WordText;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WordDtoRs {
  private String word;
  private List<WordText> wordText;
  private LocalDateTime timeApply;

}
