package ch.heigvd.amt.projectone.web;

import ch.heigvd.amt.projectone.services.dao.ProductsManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/products")
public class ProductsServlet extends HttpServlet {

    @EJB
    ProductsManager productsManager = new ProductsManager();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doPost(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setAttribute("products",productsManager.getAllProducts());
        request.getRequestDispatcher("/WEB-INF/pages/products.jsp").forward(request, response);
    }
}