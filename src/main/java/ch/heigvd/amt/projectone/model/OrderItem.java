package ch.heigvd.amt.projectone.model;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class OrderItem {

    private int id;
    private int productId;
    private int quantity;

    /**
     * Constructeur
     * @param id
     * @param productId
     * @param quantity
     */
    public OrderItem(int id, int productId, int quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public OrderItem(int productId, int quantity) {
        this(-1,productId,quantity);
    }
}
