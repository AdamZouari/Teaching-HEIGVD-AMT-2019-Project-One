package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

    @Test
    void itShouldBePossibleToCreateOrder(){
       Order order = Order.builder()
               .id(2)
               .idClient(1)
               .idOrderItem(1)
               .build();
        assertEquals(2, order.getId());
        assertEquals(1, order.getIdClient());
        assertEquals(1, order.getIdOrderItem());

    }


}