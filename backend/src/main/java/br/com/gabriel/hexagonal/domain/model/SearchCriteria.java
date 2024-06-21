package br.com.gabriel.hexagonal.domain.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchCriteria {
    
    private String term;
    private String sort = "ASC";
    private int size;
    private int page;

    public static SearchCriteria with(String term, String sort, int size, int page) {
        page = Math.max(page, 0);
        size = Math.max(1, size);
        if (Objects.isNull(sort) || !sort.equalsIgnoreCase("asc") || !sort.equalsIgnoreCase("desc")) {
            sort = "asc";
        } else {
            sort = sort.toLowerCase();
        }
        return new SearchCriteria(term, sort, size, page);
    }
}
