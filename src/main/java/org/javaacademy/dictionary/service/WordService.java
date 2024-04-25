package org.javaacademy.dictionary.service;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.javaacademy.dictionary.dto.TextDto;
import org.javaacademy.dictionary.dto.WordDtoRq;
import org.javaacademy.dictionary.dto.WordDtoRs;
import org.javaacademy.dictionary.dto.WordTextDtoRs;
import org.javaacademy.dictionary.entity.Word;
import org.javaacademy.dictionary.repository.WordRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class WordService {
  @NonNull
  private WordRepository wordRepository;

  public WordDtoRs create(WordDtoRq dtoRq) {
    Word newWord = convertToEntity(dtoRq);
    wordRepository.add(newWord);
    return convertToDtoRs(newWord, LocalDateTime.now());
  }

  @SneakyThrows
  public List<WordDtoRs> getAll() {
    Thread.sleep(3000);
    return wordRepository.findAll().stream().map(word -> convertToDtoRs(word)).toList();
  }

  @SneakyThrows
  public WordDtoRs getByWord(String result) {
    Thread.sleep(3000);
    Word word = wordRepository.findWord(result);
    return convertToDtoRs(word);
  }

  public void update(String wordUpdate, WordDtoRq dtoRq) {
      wordRepository.updateWord(wordUpdate, dtoRq.getWordText());
  }

  public boolean deleteByWord(String word) {
      return wordRepository.deleteWord(word);
  }

  public WordDtoRs patch(String wordKey, WordDtoRq updateDto) {
    Word oldWord = wordRepository.findWord(wordKey);
    oldWord.setWord(updateDto.getWord() != null ? updateDto.getWord() : oldWord.getWord());
    oldWord.setWordText(updateDto.getWordText() != null ? updateDto.getWordText() : oldWord.getWordText());
    wordRepository.updateWord(oldWord.getWord(), oldWord.getWordText());
    return convertToDtoRs(oldWord);
  }

  @SneakyThrows
  public TextDto<List<WordTextDtoRs>> getWordTexts(String wordKey, Integer startIndex, Integer wordTextSize) {
    Thread.sleep(3000);
    Word word = wordRepository.findWord(wordKey);
    int countTextTotal = word.getWordText().size();
    List<WordTextDtoRs> wordTexts = word.getWordText().stream()
            .skip(startIndex)
            .limit(wordTextSize)
            .map(wordText -> new WordTextDtoRs(word.getWord(), wordText.getText()))
            .toList();
    return new TextDto<>(wordTexts.size(),
            countTextTotal,
            startIndex,
            startIndex + wordTextSize,
            wordTexts);
  }

  private Word convertToEntity(WordDtoRq dtoRq) {
    return new Word(dtoRq.getWord(), dtoRq.getWordText());
  }
  private WordDtoRs convertToDtoRs(Word word) {
        return convertToDtoRs(word, null);
    }

  private WordDtoRs convertToDtoRs(Word word, LocalDateTime timeApply) {
    return new WordDtoRs(
                word.getWord(),
                word.getWordText(),
                timeApply);
  }
}
