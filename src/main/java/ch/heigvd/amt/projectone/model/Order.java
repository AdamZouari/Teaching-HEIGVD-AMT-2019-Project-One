package ch.heigvd.amt.projectone.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;


@Builder
@Getter
@EqualsAndHashCode
public class Order {

    private int id;
    private int idClient;
    private int idOrderItem;

    /**
     * Constructeur
     * @param id
     * @param idClient
     * @param idOrderItem
     */
    public Order(int id, int idClient, int idOrderItem){
        this.id=id;
        this.idClient=idClient;
        this.idOrderItem = idOrderItem;
    }
}
