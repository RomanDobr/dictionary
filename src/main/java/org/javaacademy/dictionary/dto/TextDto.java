package org.javaacademy.dictionary.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class TextDto<T> {
    @NonNull
    private Integer size;
    @NonNull
    private Integer totalSize;
    @NonNull
    private Integer startElementIndex;
    @NonNull
    private Integer endElementIndex;
    @NonNull
    private T content;
}
