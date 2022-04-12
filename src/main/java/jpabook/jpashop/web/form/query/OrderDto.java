package jpabook.jpashop.web.form.query;

import jpabook.jpashop.domain.status.OrderStatus;
import jpabook.jpashop.repository.order.query.OrderItemQueryDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId;
    private String name;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<OrderItemQueryDto> orderItems;

    public OrderDto(Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.name = name;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
    }
}
