package ch.heigvd.amt.projectone.web;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LogoutServletTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    LogoutServlet servlet;

    @BeforeEach
    public void setup() {
        servlet = new LogoutServlet();
        when(request.getContextPath()).thenReturn("projectone");
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void doGet() throws ServletException, IOException {
        servlet.doGet(request, response);
        verify(session, atLeastOnce()).invalidate();
        verify(response, atLeastOnce()).sendRedirect(request.getContextPath() + "/login");
    }

}