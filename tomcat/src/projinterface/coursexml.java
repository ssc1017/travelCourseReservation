package projinterface;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.io.FileWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "coursexml", urlPatterns = { "/coursexml.htm" })
public class coursexml extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
        String sql = "select coursename,coursedetail,coursetype,coursebitmap from course";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        File file=new File("C:/Users/Ë¼³¬/workspace/tomcat/WebContent","course.xml");
        
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/androidproj?serverTimezone=UTC&useSSL=true","root","8733611314");
			st = con.createStatement();
			rs=st.executeQuery(sql);
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
	        if(file.exists())
	        	file.delete();
	        file.createNewFile();
	        FileWriter fw=new FileWriter(file);
	        fw.write("<?xml version=\"1.0\" encoding=\"gb2312\"?>\n");
	        fw.write("<courses>\n");
			while(rs.next()){
				fw.write("<course>\n");
				fw.write("<title>\n");
				fw.write(rs.getString(1));
				fw.write("\n</title>\n");
				fw.write("<detail>\n");
				fw.write(rs.getString(2));
				fw.write("\n</detail>\n");
				fw.write("<type>\n");
				fw.write(rs.getString(3));
				fw.write("\n</type>\n");
				fw.write("<image>\n");
				fw.write(rs.getString(4));
				fw.write("\n</image>\n");
				fw.write("</course>\n");
			}
			fw.write("</courses>");
			fw.flush();
			fw.close();
		
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


    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	doGet(request,response);
    }

}