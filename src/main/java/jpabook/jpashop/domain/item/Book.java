package jpabook.jpashop.domain.item;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Book extends Item{
    private String author;
    private String isbn;

    public Book(String author, String isbn) {
        this.author = author;
        this.isbn = isbn;
    }

    public Book(String author, String isbn, String itemName, int itemPrice, int itemStockQuantity) {
        super(itemName, itemPrice, itemStockQuantity);
        this.author = author;
        this.isbn = isbn;
    }
}
