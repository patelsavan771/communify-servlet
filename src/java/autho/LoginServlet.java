package autho;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String uname = req.getParameter("username");
        String pass = req.getParameter("password");

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con;
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "asdf");

//            out.println("connection created...");

            PreparedStatement pst = con.prepareStatement("select * from login where username=? and password=?");

            pst.setString(1, uname);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                out.println("login successful.");
                Cookie cookie = new Cookie("username", uname);
                res.addCookie(cookie);
                RequestDispatcher rd = req.getRequestDispatcher("profile");
                rd.forward(req, res);

            } else {
                out.println("invalid user login");
                RequestDispatcher rd = req.getRequestDispatcher("index.html");
                rd.include(req, res);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }        
    }
}
