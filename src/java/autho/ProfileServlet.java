package autho;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ProfileServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        Cookie[] cookies = req.getCookies();

        if (cookies == null) {
            out.println("First Login <br>");
            out.println("<a href='index.html'>Login here to see profile</a>");
        } else {
            out.println("In Profile Servlet.<br>");
            out.println("welcome " + cookies[0].getValue());
            
            // fatch fname from data base and show welcome first name
            
            out.println("<a href='logout'>Logout</a>");
        }
    }

}
