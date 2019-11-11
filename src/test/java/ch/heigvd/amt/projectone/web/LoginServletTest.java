package ch.heigvd.amt.projectone.web;

import ch.heigvd.amt.projectone.services.dao.ClientsManagerLocal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServletTest {

    @Mock
    RequestDispatcher requestDispatcher;
    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;

    LoginServlet servlet;

    @Mock
    ClientsManagerLocal clientsManagerLocal;


    @BeforeEach
    public void setup() {
        servlet = new LoginServlet();

    }
    /*@Test
    void doPostShouldLoginWithGoodCredentials() throws ServletException, IOException {


        when(request.getParameter("username")).thenReturn("azouari");
        when(request.getParameter("password")).thenReturn("azouari");

        servlet.doPost(request,response);
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/home?id=1");

    }*/

   /* @Test
    void doPostShouldFailWithEmptyCredentials() throws ServletException, IOException {


        when(request.getParameter("username")).thenReturn("");
        when(request.getParameter("password")).thenReturn("");

        servlet.doPost(request,response);

        verify(request, atLeastOnce()).setAttribute("error", "Mot de passe ou username vide !");
        verify(request.getRequestDispatcher("/WEB-INF/pages/login.jsp"), atLeastOnce()).forward(request, response);

    }*/


    @Test
    void doGet() throws ServletException, IOException {

        when(request.getRequestDispatcher(anyString())).thenReturn(requestDispatcher);
        servlet.doGet(request,response);
        verify(request).getRequestDispatcher("/WEB-INF/pages/login.jsp");


    }
}