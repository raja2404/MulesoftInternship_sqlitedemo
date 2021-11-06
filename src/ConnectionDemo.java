import java.sql.*;
import java.util.*;
public class ConnectionDemo {
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			Class.forName("org.sqlite.JDBC");
			Connection con = DriverManager.getConnection("jdbc:sqlite:movies.db");
			if(con != null) {
				Statement stmt = con.createStatement();
				//creating table
				stmt.executeUpdate("create table movies(mname text primary key,actor text,actress text,director text,yor int)");
				System.out.println("table created");
				
				//inserting data
				// 1st method
				int n = stmt.executeUpdate("insert into movies values('Akhanda', 'Balakrishna','Pragya', 'Boyapati', 2021)");
				System.out.println(n);
				// 2nd method
				PreparedStatement pstmt = con.prepareStatement("insert into movies values(?,?,?,?,?)");
				System.out.println("Enter the Movie Name");
				String mname = sc.nextLine();
				System.out.println("Enter the Name of Actor");
				String actor = sc.nextLine();
				System.out.println("Enter the Name of Actress");
				String actress = sc.nextLine();
				System.out.println("Enter the Name of Director");
				String director = sc.nextLine();
				System.out.println("Enter Year of Release");
				int yor = sc.nextInt();
				pstmt.setString(1, mname);
				pstmt.setString(2, actor);
				pstmt.setString(3, actress);
				pstmt.setString(4, director);
				pstmt.setInt(5, yor);
				int n1 = pstmt.executeUpdate();
				System.out.println(n1);
				
				//update
				String sql = "update movies set actor='NBK' where mname='Akhanda'";
				int n2 = stmt.executeUpdate(sql);
				System.out.println(n2+"records updated");
				
				//delete
				String sql1 = "delete from movies where mname='khiladi'";
				int n3 = stmt.executeUpdate(sql1);
				System.out.println(n3+"records deleted");
				
				//Retriving
				PreparedStatement pstmt1 = con.prepareStatement("select * from movies");
				ResultSet rs = pstmt1.executeQuery();
				System.out.println("mname\tactor\tactress\tdirector\tyor");
				System.out.println("______________________________________________________");
				while(rs.next()) {
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getInt(5));
				}
			
				PreparedStatement pstmt2 = con.prepareStatement("select * from movies where actor = ?");
				System.out.println("Enter the Name of Actor to search with");
				String a = sc.nextLine();
				pstmt2.setString(1, a);
				ResultSet rs1 = pstmt2.executeQuery();
				System.out.println("mname\tactor\tactress\tdirector\tyor");
				System.out.println("______________________________________________________");
				while(rs1.next()) {
					System.out.println(rs1.getString(1)+"\t"+rs1.getString(2)+"\t"+rs1.getString(3)+"\t"+rs1.getString(4)+"\t"+rs1.getInt(5));
				}
				//int n = stmt.executeUpdate("insert into products values")
				con.close();	
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
}
