package jpabook.jpashop.web;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderSearch;
import jpabook.jpashop.domain.status.OrderStatus;
import jpabook.jpashop.repository.order.query.OrderQueryRepository;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import jpabook.jpashop.web.form.query.OrderDto;
import jpabook.jpashop.web.form.query.OrderFlat;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderQueryRepository orderQueryRepository;

    @GetMapping("/order")
    public String createOrderForm(Model model) {
        model.addAttribute("members", memberService.findMembers());
        model.addAttribute("items", itemService.findItems());
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(
            @RequestParam Long memberId,
            @RequestParam Long itemId,
            @RequestParam int count
    ) {
        orderService.order(memberId, itemId, count);
        return "redirect:/orders";
    }

//    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

//    @GetMapping("/orders")
    public String orderListV2(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<OrderDto> orders = orderService.findOrdersWithMember(orderSearch);

        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @GetMapping("/orders")
    public String orderListV3(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<OrderFlat> orders = orderService.findOrdersFlat(orderSearch);

        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }

    @PostMapping("/api/orders")
    public String orders(@RequestBody Member member, Model model){

        System.out.println("name = " + member.getName());
        List<OrderFlat> result = orderQueryRepository.findOrdersFlatWithName(member.getName());
        for (OrderFlat orderFlat : result) {
            System.out.println("orderFlat = " + orderFlat);
        }
        model.addAttribute("orders", result);
        return "order/orderList :: #orderTable";
    }

    @Data
    static class Member{
        String name;
    }
}
