package projinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "register", urlPatterns = { "/register.htm" })
public class register extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        String act = request.getParameter("act");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String gender = request.getParameter("gender");
        String age = request.getParameter("age");
        String tele = request.getParameter("tele");
        String mail = request.getParameter("mail");
        String sql = "insert into tourist(touristact,touristpw,touristname,touristgender,touristage,touristtele,touristmail) "+
                     "values('"+act+"','"+pw+"','"+name+"','"+gender+"','"+Integer.parseInt(age)+"','"+tele+"','"+mail+"')";
        Connection con = null;
        Statement st = null;
        int result;
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
			st = con.createStatement();
			result=st.executeUpdate(sql);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			if(result==0)out.println("fail");
			else out.println("success");
			out.flush();
			out.close();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				st.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

    }

}