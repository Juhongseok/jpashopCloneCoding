package jpabook.jpashop.web.form.query;

import jpabook.jpashop.domain.status.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderFlat {
    private Long id;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus status;

    private String itemName;
    private int price;
    private int count;

    public OrderFlat(Long id, String name, LocalDateTime orderDate, OrderStatus status, String itemName, int price, int count) {
        this.id = id;
        this.name = name;
        this.orderDate = orderDate;
        this.status = status;
        this.itemName = itemName;
        this.price = price;
        this.count = count;
    }
}
