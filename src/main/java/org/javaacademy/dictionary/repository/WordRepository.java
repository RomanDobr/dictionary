package org.javaacademy.dictionary.repository;

import org.javaacademy.dictionary.entity.Word;
import org.javaacademy.dictionary.entity.WordText;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class WordRepository {
  private final Map<Word, List<WordText>> dictionary = new TreeMap<>(Comparator.comparing(Word::getWord));

  public Word add(Word word) {
    dictionary.put(word, word.getWordText());
    return word;
  }

  public List<Word> findAll() {
    return new ArrayList<>(dictionary.keySet().stream().toList());
  }

  public Word findWord(String findWord) {
    return wordHelper(findWord);
  }

  public void updateWord(String wordNew, List<WordText> wordText) {
    Word word = wordHelper(wordNew);
    dictionary.remove(word, word.getWordText());
    dictionary.put(word, wordText);
  }

  public boolean deleteWord(String word) {
    Word wordDel = wordHelper(word);
    return dictionary.remove(wordDel, wordDel.getWordText());
  }

  private Word wordHelper(String newWord) {
    return dictionary.keySet().stream()
            .filter(word -> word.getWord().equalsIgnoreCase(newWord))
            .findFirst()
            .orElseThrow();
  }
}
