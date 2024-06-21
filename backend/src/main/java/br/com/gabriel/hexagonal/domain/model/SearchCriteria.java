package br.com.gabriel.hexagonal.domain.model;

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
        return new SearchCriteria(term, sort, size, page);
    }
}
