package org.javaacademy.dictionary.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.dictionary.dto.TextDto;
import org.javaacademy.dictionary.dto.WordDtoRq;
import org.javaacademy.dictionary.dto.WordDtoRs;
import org.javaacademy.dictionary.dto.WordTextDtoRs;
import org.javaacademy.dictionary.service.WordService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dictionary")
@RequiredArgsConstructor
@CacheConfig(cacheNames = "findAll")
public class DictionaryController {
 private final WordService wordService;

 @GetMapping
 @Cacheable(cacheNames = "findAll")
 @CachePut(cacheNames = "findAll", condition = "#refresh==true")
 public List<WordDtoRs> getDictionary(@RequestParam(required = false) Boolean refresh) {
   return wordService.getAll();
 }

 @GetMapping("/word")
 @Cacheable(cacheNames = "findAll")
 @CachePut(cacheNames = "findAll", condition = "#refresh==true")
 public WordDtoRs getWordByKey(@RequestParam String word, @RequestParam(required = false) Boolean refresh){
  return wordService.getByWord(word);
 }

 @PostMapping
 @CacheEvict(cacheNames = "findAll", allEntries = true)
 public ResponseEntity<WordDtoRs> createWord(@RequestBody WordDtoRq dtoRq) {
  return ResponseEntity.status(HttpStatus.CREATED).body(wordService.create(dtoRq));
 }

 @PutMapping
 public ResponseEntity<?> updateWord(@RequestParam String word, @RequestBody WordDtoRq dtoRq) {
   wordService.update(word, dtoRq);
   return ResponseEntity.status(HttpStatus.ACCEPTED).build();
 }

 @DeleteMapping("/{word}")
 public ResponseEntity<?> deleteWord(@PathVariable String word) {
   boolean result = wordService.deleteByWord(word);
   return result ? ResponseEntity.status(HttpStatus.ACCEPTED).build()
                 : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
 }

 @PatchMapping("/{word}")
 public ResponseEntity<?> patchWord(@PathVariable String word, @RequestBody WordDtoRq dtoRq) {
  return ResponseEntity.status(HttpStatus.ACCEPTED).body(wordService.patch(word, dtoRq));
 }

 @GetMapping("/{word}/text")
 @Cacheable(cacheNames = "findAll")
 @CachePut(cacheNames = "findAll", condition = "#refresh==true")
 public TextDto<List<WordTextDtoRs>> getText(@PathVariable String word,
                                             @RequestParam Integer startIndex,
                                             @RequestParam Integer pageSize,
                                             @RequestParam(required = false) Boolean refresh) {
  return wordService.getWordTexts(word, startIndex, pageSize);
 }
}
