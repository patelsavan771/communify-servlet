package autho;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class SignupServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws
            IOException, ServletException {

        res.setContentType("text/html");
        PrintWriter out = res.getWriter();

        String fname = req.getParameter("fname");
        String uname = req.getParameter("username");
        String pass = req.getParameter("password");

        try {
            Class.forName("oracle.jdbc.OracleDriver");
            Connection con;
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "asdf");
//            out.println(con + "<br>");
//            out.println("connection created...");

            PreparedStatement pst = con.prepareStatement("insert into login values (?,?,?)");

            pst.setString(1, uname);
            pst.setString(2, pass);
            pst.setString(3, fname);
//            System.out.println("hey lala..........");
            pst.executeUpdate();
            out.println("Successfully registered.");

//            con.commit();

            RequestDispatcher rd = req.getRequestDispatcher("index.html");
            rd.include(req, res);
            
//            pst.close();
//            con.close();
//            System.out.println("hey lala..........");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
