package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.exceptions.DuplicateKeyException;
import ch.heigvd.amt.projectone.model.Product;
import org.arquillian.container.chameleon.deployment.api.DeploymentParameters;
import org.arquillian.container.chameleon.deployment.maven.MavenBuild;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;


@RunWith(Arquillian.class)
@MavenBuild
@DeploymentParameters(testable = true)
public class ProductsManagerTest {

    @EJB
    ProductsManagerLocal productsManagerLocal;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateProduct() throws DuplicateKeyException {
        Product boxer = Product.builder().name("boxer").unitPrice(2.00).description("Bière Romande par excellence.").build();
        productsManagerLocal.create(boxer);
    }
}