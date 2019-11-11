package ch.heigvd.amt.projectone.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void itShouldBePossibleToCreateProduct(){
        Product product = Product.builder()
                .id(2)
                .name("Boxer")
                .unitPrice(2.00)
                .description("Bière Romande par excellence.")
                .build();
        assertEquals("Boxer", product.getName());
        assertEquals(2.00, product.getUnitPrice());

    }

}