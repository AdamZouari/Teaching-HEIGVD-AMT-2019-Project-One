package ch.heigvd.amt.projectone.services.dao;

import ch.heigvd.amt.projectone.exceptions.DuplicateKeyException;
import ch.heigvd.amt.projectone.model.OrderItem;
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
public class OrderItemManagerTest {


    @EJB
    OrderItemManagerLocal orderItemManagerLocal;

    @Test
    @Transactional(TransactionMode.ROLLBACK)
    public void itShouldBePossibleToCreateAnOrder() throws DuplicateKeyException {
        OrderItem orderItem = OrderItem.builder().productId(1).quantity(200).build();
        orderItemManagerLocal.create(orderItem);
    }
}