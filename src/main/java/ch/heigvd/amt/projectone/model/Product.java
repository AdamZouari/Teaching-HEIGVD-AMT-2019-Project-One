package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Product {

    private int id;
    private String name;
    private double unitPrice;
    private String description;

    /**
     * Constructeur
     * @param id
     * @param name
     * @param unitPrice
     * @param description
     */
    public Product(int id, String name, double unitPrice, String description) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.description = description;
    }

    /**
     * Constructeur
     * @param name
     * @param unitPrice
     * @param description
     */
    public Product(String name, double unitPrice, String description) {
       this(-1,name,unitPrice,description);
    }
}
