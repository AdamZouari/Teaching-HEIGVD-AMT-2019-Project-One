package ch.heigvd.amt.projectone.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/", "/home",  "/products", "/orders", "/profile"} )
public class LoginFilter implements Filter {
    public void destroy() {
    }


    /**
     * Filtre permettant de rediriger les url marquees ci-dessus vers /login si l'utilisateur n'est pas identifié
     * @param req requete
     * @param resp reponse
     * @param chain 
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) req;
        HttpServletResponse httpServletResponse = (HttpServletResponse) resp;

        HttpSession session = httpServletRequest.getSession();

        if (session.getAttribute("user") == null){
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
        } else {
            chain.doFilter(httpServletRequest, httpServletResponse);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
