package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.model.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductsManagerLocal extends IDAO<Product> {
    List<Product> getAllProducts(int currentPage, int recordsPerPage);
    List<Product> getAllProducts();
    int getIdByName(String username);
    int getNumberOfRows();
}
