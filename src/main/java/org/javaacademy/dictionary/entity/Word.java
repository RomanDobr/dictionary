package org.javaacademy.dictionary.entity;

import lombok.Data;
import lombok.NonNull;
import java.util.List;

@Data
public class Word {
  @NonNull
  private String word;
  @NonNull
  private List<WordText> wordText;
}
