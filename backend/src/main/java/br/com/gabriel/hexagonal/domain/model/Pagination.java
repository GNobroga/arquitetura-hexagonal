package br.com.gabriel.hexagonal.domain.model;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Pagination<T> {
    
    private int size;

    private int page;

    private long totalElements;

    private List<T> items;

    public static <T> Pagination<T> with(int size, int page, long totalElements, List<T> items) {
        return new Pagination<>(size, page, totalElements, items);
    }
}
