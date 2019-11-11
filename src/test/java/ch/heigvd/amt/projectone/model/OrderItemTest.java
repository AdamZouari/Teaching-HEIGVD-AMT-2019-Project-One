package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderItemTest {


    @Test
    void itShouldBePossibleToCreateOrderItem(){
        OrderItem orderItem = OrderItem.builder()
                .id(2)
                .productId(4)
                .quantity(100)
                .build();
        assertEquals(2, orderItem.getId());
        assertEquals(4, orderItem.getProductId());
        assertEquals(100, orderItem.getQuantity());

    }

}