package org.javaacademy.dictionary.dto;

import lombok.Data;
import lombok.NonNull;

@Data
public class WordTextDtoRs {
  @NonNull
  private String word;
  @NonNull
  private String text;
}
