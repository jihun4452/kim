package cotest.kim.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookForm {
    private Long id;
    private String name;
    private String author;
    private String isbn;
    private int price;
    private int stockQuantity;

}
