package JavaSql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JavaSql {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String sql=null;
		String fname=null;
		String lname=null; 
		String Address=null; 
		String City=null;
		String State=null;
		String Zip=null; 
		String Phone=null;
		

		System.out.println("What would you like to do? add(a) update(u) delete(d) find(f)");
		int studentID=0;
		String input = sc.next();
		if(input.equals("a")){
			System.out.println("Enter first name: ");
			fname=sc.next();
			System.out.println("Enter last name? ");
			lname=sc.next();
			System.out.println("Enter Address");
			Address=sc.next();
			System.out.println("Enter City");
			City=sc.next();
			System.out.println("Enter state");
			State=sc.next();
			System.out.println("Enter  ZIP");
			Zip =sc.next();
			System.out.println("Enter Phone");
			Phone =sc.next();
			sql = "insert into MCStudents(FirstName,LastName,Address,City,State,Zip,Phone)values(?,?,?,?,?,?,?)";

			System.out.println(sql);
		}
		
		else if(input.equals("u"))
		{
			System.out.println("what's the student ID you want to update? ");
			studentID= sc.nextInt();
			System.out.println("Enter what you would like to change the address to: (do not use spaces)");
			Address = sc.next();
			
			sql="UPDATE MCStudents SET Address = ? 	WHERE StudentID=?";
		}
		

		Connection con = null;
		
		
		
		ResultSet rs = null;
		PreparedStatement pstmt=null;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/MC?"+ "user=root&password=password");
            pstmt = con.prepareStatement(sql);
                		
            if(input.equals("a")){
            pstmt.setString(1, fname);
            pstmt.setString(2, lname);
            pstmt.setString(3, Address);
            pstmt.setString(4, City);
            pstmt.setString(5, State);
            pstmt.setString(6, Zip);
            pstmt.setString(7, Phone);
            pstmt.executeUpdate();
            }else if(input.equals("u")){
            	pstmt.setString(1, Address);
            	pstmt.setInt(2,studentID);
            	pstmt.executeUpdate();

        		}
            }
		/*
		while(rs.next()){
			System.out.print(rs.getString("FirstName") + "\t");
			System.out.print(rs.getString("LastName"));
			System.out.println();
		}
		*/
		catch (SQLException e){

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				//rs.close();
				pstmt.close();
				con.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}
