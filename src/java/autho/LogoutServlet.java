package autho;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) 
            throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        
        Cookie c = new Cookie("username", "");
        c.setMaxAge(0);
        
        res.addCookie(c);
        out.println("Successfully logged out.");
        RequestDispatcher rd = req.getRequestDispatcher("index.html");
        rd.include(req, res); 
    }
}