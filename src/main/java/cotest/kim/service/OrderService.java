package cotest.kim.service;

import cotest.kim.domain.Delivery;
import cotest.kim.domain.Member;
import cotest.kim.domain.Order;
import cotest.kim.domain.OrderItem;
import cotest.kim.domain.item.Item;
import cotest.kim.repository.ItemRepository;
import cotest.kim.repository.MemberReposiroty;
import cotest.kim.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberReposiroty memberReposiroty;
    private final ItemRepository itemRepository;

    //주문

    @Transactional
    public Long order(Long memberId, Long itemId,int count ){
        Member member=memberReposiroty.findOne(memberId);
        Item item=itemRepository.findOne(itemId);

        Delivery delivery=new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        Order order = Order.createOrder(member, delivery, orderItem);

        orderRepository.save(order);
        return order.getId();
    }

    //취소
    public void cancelOrder(Long orderId){
        Order order= orderRepository.findOne(orderId);
        order.cancel();

    }

    //검색
/*    public List<Order> findOrders(OrderSearch orderSearch){
        return orderRepository.findAll(orderSearch);
    }*/
}
