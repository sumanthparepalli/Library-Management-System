package library;


import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import library.JavaEmail;
import library.Startup;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */

public class Notif_Email extends Thread
{
    public static void send_notif() throws SQLException, InterruptedException
    {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
        Statement st = con.createStatement();
        ResultSet rs,rs1;
        int c=0;
       rs=st.executeQuery("select ISBN,reg,due_date,curdate()-due_date from issue");
       while(rs.next())
        {
            long x=rs.getLong("ISBN");
            String reg=rs.getString("reg");
            String due=rs.getString("due_date");
            int di=rs.getInt(4);
            rs=st.executeQuery("select * from students where reg='"+reg+"'");
            rs.next();
            String n=rs.getString("name");
            String e=rs.getString("email");
            rs=st.executeQuery("select * from books where ISBN="+x);
            rs.next();
            String ti=rs.getString("Book_Name");
            String au=rs.getString("Author_Name");
            if(di>=-2&&di<=0) //It means that he needs to be notified for due date
            {
                JavaEmail ob = new JavaEmail(e, "Advance Notice of Item Due", "<p><strong>Dear " + n + "(" + reg + ")</strong></p>\n"
                        + "<p>The following item will be due soon:</p>\n"
                        + "<p>Title: " + ti + "</p>\n"
                        + "<p>Author: " + au + "</p>\n"
                        + "<p>ISBN: " + x + "</p>\n"
                        + "<p>Due Date: " + due + "</p>\n"
                        + "<p>Thank You ,</p>\n"
                        + "<p><strong>Librarian, VIT-AP Library.</strong></p>"
                        + "<p>\"Use your own ID Card for your own transactions.\"</p>");
                ob.start();
            }
            if(di>0) //Means he has neither renewed or returned the book.Tell him that
            {
                JavaEmail ob = new JavaEmail(e, "Item Due Reminder", "<p><strong>Dear " + n + "(" + reg + ")</strong></p>\n"
                        + "<p>The following item is now due:</p>\n"
                        + "<p>Title: " + ti + "</p>\n"
                        + "<p>Author: " + au + "</p>\n"
                        + "<p>ISBN: " + x + "</p>\n"
                        + "<p>Due Date: " + due + "</p>\n"
                        + "<p>Thank You ,</p>\n"
                        + "<p><strong>Librarian, VIT-AP Library.</strong></p>"
                        + "<p>\"Use your own ID Card for your own transactions.\"</p>");
                ob.start();
            }
            c++;
            int cnt=c;
            rs=st.executeQuery("select ISBN,reg,due_date,curdate()-due_date from issue");
            while(cnt>0)
            {
                rs.next();
                cnt--;
            }
        }
    }
//    public static void main(String[] args) throws SQLException, InterruptedException 
//    {
//        Notif_Email obj=new Notif_Email();
//        obj.start();
//    }

    @Override
    public void run() 
    {
        
        try {
            send_notif();
        } catch (SQLException ex) {
            Logger.getLogger(Notif_Email.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(Notif_Email.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
