/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

/**
 *
 * @author User
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author User
 */
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.swing.*;

public class UBname extends JPanel {

    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
    Statement st = con.createStatement();
    ResultSet rs = null;
    public String s = "";
    public String reg = "";
    private final JTextField tf;
    @SuppressWarnings("rawtypes")
    private final JComboBox combo = new JComboBox();
    Button b;
    private final Vector<String> v = new Vector<String>();

    @SuppressWarnings({"rawtypes", "unchecked"})
    public UBname(JFrame obj1, String t, String t1) throws SQLException {
        super(new BorderLayout());
        s = t;
        reg = t1;
        combo.setEditable(true);
        b = new Button("Click Me !!");
        b.setBounds(50, 50, 50, 20);
        b.setBackground(Color.cyan);
        b.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String j="";
                String x = (String) combo.getSelectedItem();
                System.out.println(x);
                try {
                    rs = st.executeQuery("select ISBN from books where Book_Name='" + x + "'");
                    rs.next();
                    j=Long.toString(rs.getLong(1));
                } catch (SQLException ex) {
                    Logger.getLogger(UBname.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (s.equals("issue")) {
                    try {
                        issue(j);
                    } catch (SQLException ex) {
                        Logger.getLogger(UIsbn.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MessagingException ex) {
                        Logger.getLogger(UBname.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.equals("change")) {
                    change(j);
                } else {
                    try {
                        delete(j);
                    } catch (SQLException ex) {
                        Logger.getLogger(UIsbn.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                obj1.setVisible(false);
            }

        });
        tf = (JTextField) combo.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        String text = tf.getText().toUpperCase();
                        if (text.length() == 0) {
                            combo.hidePopup();
                            setModel(new DefaultComboBoxModel(v), "");
                        } else {
                            DefaultComboBoxModel m = getSuggestedModel(v, text);
                            if (m.getSize() == 0 || hide_flag) {
                                combo.hidePopup();
                                hide_flag = false;
                            } else {
                                setModel(m, text);
                                combo.showPopup();
                            }
                        }
                    }
                });
            }

            public void keyPressed(KeyEvent e) {
                String text = tf.getText().toUpperCase();
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_ENTER) {
                    if (!v.contains(text)) {
                        v.addElement(text);
                        Collections.sort(v);
                        setModel(getSuggestedModel(v, text), text);
                    }
                    hide_flag = true;
                } else if (code == KeyEvent.VK_ESCAPE) {
                    hide_flag = true;
                } else if (code == KeyEvent.VK_RIGHT) {
                    for (int i = 0; i < v.size(); i++) {
                        String str = v.elementAt(i);
                        if (str.startsWith(text)) {
                            combo.setSelectedIndex(-1);
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }
        });
        st = con.createStatement();
        rs = st.executeQuery("select Book_Name from books");
        while (rs.next()) {
            v.addElement(rs.getString("Book_Name").toUpperCase());
        }
        setModel(new DefaultComboBoxModel(v), "");
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(BorderFactory.createTitledBorder("AutoSuggestion Box"));
        p.add(combo, BorderLayout.NORTH);
        p.add(b, BorderLayout.AFTER_LINE_ENDS);
        add(p);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setPreferredSize(new Dimension(300, 150));
    }

    public void delete(String x) throws SQLException {
        ResultSet rs = st.executeQuery("select * from books where ISBN=" + x);
        rs.next();
        int iq = rs.getInt(5);
        if (iq != 0) {
            JOptionPane.showMessageDialog(null, "This book cannot be deleted");
        } else {
            st.executeUpdate("delete from books where ISBN=" + x);
            JOptionPane.showMessageDialog(null, "Book Removed");
        }
        Startup obj = new Startup();
        obj.setTitle("StartUp");
        //obj.setLocation(400, 400);
        obj.setVisible(true);
    }

    public void change(String x) {
        Change obj = new Change(x);
        obj.setTitle("Changing Menu");
        //obj.setLocation(400, 400);
        obj.setVisible(true);
    }

    public void issue(String x) throws SQLException, MessagingException 
    {
        //First of all I am going to remove all the people for whom 24 Hrs have expired
        ResultSet rs1;
        rs=st.executeQuery("select * from wait");
        int c=0;
        while(rs.next())
        {
            int i=rs.getInt("ISBN");
            String r=rs.getString("reg");
            String q=rs.getString("emsent");
            int p=rs.getInt("position");
            if(q!=null)
            {
                rs1=st.executeQuery("select timediff(now(),'"+q+"')");
                rs1.next();
                String diff=rs1.getString(1);
                int t=Integer.parseInt(diff.substring(0, diff.indexOf(':')));
                if(t>24)
                {
                    q="delete from wait where ISBN="+i+" and reg='"+r+"'";
                    st.executeUpdate(q);
                    st.executeUpdate("update wait set position=position-1 where ISBN="+x+" and position >"+p);//updated the position for the rest of the people
                }
            }
            c++;
            rs=st.executeQuery("select * from wait");
            int cnt=c;
            while(cnt>0)
            {
                rs.next();
                cnt--;
            }
        }
        
        //Now the next step is to check whether the book can be issued or not that is it a reference book or not
        rs=st.executeQuery("select isReference from books where ISBN ="+x);
        rs.next();
        int flag=rs.getInt(1);
        if(flag==0)//can be issued
        {
            rs=st.executeQuery("select count(*) from wait where ISBN="+x+" group by ISBN");
            if(!rs.next()) //No one is there in the waiting list
            {
                issue_end(x);
            }
            else
            {
                int cnt=rs.getInt(1);//No. of people in the waiting list for that book
                rs=st.executeQuery("select * from wait where ISBN="+x+" and reg='"+reg+"'");//checking is the current reg in wait list or not
                if(rs.next()) //If he is there in the wait list
                {
                    String e=rs.getString("emsent");
                    if(e==null) //If he was not called
                    {
                        JOptionPane.showMessageDialog(null, "Sorry the book is not yet available. You will notifed when it is available");
                    }
                    else //If he was called
                    {
                        int p=rs.getInt("position");
                        st.executeUpdate("delete from wait where ISBN="+x+" and reg='"+reg+"' and position="+p); //removed the person from the wait list
                        st.executeUpdate("update wait set position=position-1 where ISBN="+x+" and position >"+p);//updated the position for the rest of the people
                        issue_end(x); //Now after this it is simple issue
                    }
                    
                }
                else //If he is not there in the wait list
                {
                    rs=st.executeQuery("select * from books");
                    rs.next();
                    int i=rs.getInt("iquantity");
                    int o=rs.getInt("oquantity");
                    if(i+cnt<o) //If book can be issued normally 
                    {
                        issue_end(x);
                    }
                    else //Book cannot be issued normally
                    {
                        rs=st.executeQuery("select min(Due_Date) from issue where ISBN="+x);
                        rs.next();
                        String d=rs.getString(1);
                        JOptionPane.showMessageDialog(null, "You can get the book on " + d);
                        if(cnt<10)
                        {
                            if (JOptionPane.showConfirmDialog(null, "Do you want to stay on the waiting list ?") == 0) 
                            {
                                st.executeUpdate("insert into wait(ISBN,reg,position) values("+x+",'"+reg+"',"+(cnt+1)+")");
                                JOptionPane.showMessageDialog(null, "You have been added to the waiting list your wait list number is:" + (cnt + 1));
                            }
                        }
                        else // If already 10 people are there on the wait list
                        {
                            JOptionPane.showMessageDialog(null, "Sorry 10 people are already there on the wait list");
                        }
                    }
                }
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Sorry this book cannot be issued as this is a Reference book");
        }
        Startup obj = new Startup();
        obj.setTitle("StartUp");
        // obj.setLocation(400, 400);
        obj.setVisible(true);
        this.setVisible(false);
    }

    public void issue_end(String x) throws SQLException, MessagingException 
    {
        rs=st.executeQuery("select * from books where ISBN="+x);
        rs.next();
        int i=rs.getInt("iquantity");
        int o=rs.getInt("oquantity");
        String ti,au;
        ti=rs.getString("Book_Name"); //Book-Name
        au=rs.getString("Author_Name");//Author-Name
        if(i<o) //Book can be issued because it is available
        {
            String n,due,e;
            st.executeUpdate("insert into renew values("+x+",'"+reg+"',0)");
            st.executeUpdate("update students set BOOK_COUNT=BOOK_COUNT+1 where reg='"+reg+"'");
            st.executeUpdate("update books set iquantity=iquantity+1 where ISBN="+x);
            st.executeUpdate("insert into issue(ISBN,reg) values ("+x+",'"+reg+"')");
            st.executeUpdate("update issue set Issue_Date=CURDATE() where ISBN="+x+" and reg='"+reg+"'");
            st.executeUpdate("update issue set Due_Date=DATE_ADD(Issue_Date,INTERVAL 14 DAY) where ISBN="+x+" and reg='"+reg+"'");
            rs=st.executeQuery("select * from students where reg='"+reg+"'");
            rs.next();
            n=rs.getString("name"); //Student-Name
            e=rs.getString("email");//Student-email id
            rs=st.executeQuery("select Due_Date from issue where ISBN="+x+" and reg='"+reg+"'");
            rs.next();
            due=rs.getString(1);//Book Due-Date
            JavaEmail ob = new JavaEmail(e, "Checkouts", "<p><strong>Dear " + n + "(" + reg + ")</strong></p>\n"
                        + "<p>The following items have been checked out:</p>\n"
                        + "<p>Title: " + ti + "</p>\n"
                        + "<p>Author: " + au + "</p>\n"
                        + "<p>ISBN: " + x + "</p>\n"
                        + "<p>Due Date: " + due + "</p>\n"
                        + "<p>Thank You ,</p>\n"
                        + "<p><strong>Librarian, VIT-AP Library.</strong></p>"
                        + "<p>\"Use your own ID Card for your own transactions.\"</p>");
            ob.start();
            JOptionPane.showMessageDialog(null, "Issued !! ");
            return;
        }
        else //book cannot be issued //this part will only be executed for the first person on the wait list for that book
        {
            
            int cnt=0;
            rs=st.executeQuery("select min(Due_Date) from issue where ISBN="+x);
            rs.next();
            String d=rs.getString(1);
            JOptionPane.showMessageDialog(null, "You can get the book on " + d);
            if(cnt<10)
            {
                if (JOptionPane.showConfirmDialog(null, "Do you want to stay on the waiting list ?") == 0) 
                {
                    st.executeUpdate("insert into wait(ISBN,reg,position) values("+x+",'"+reg+"',"+(cnt+1)+")");
                    JOptionPane.showMessageDialog(null, "You have been added to the waiting list your wait list number is:" + (cnt + 1));
                }
            }
           else // If already 10 people are there on the wait list
            {
                JOptionPane.showMessageDialog(null, "Sorry 10 people are already there on the wait list");
            }
            return;
        }
    }
    private boolean hide_flag = false;

    private void setModel(DefaultComboBoxModel mdl, String str) {
        combo.setModel(mdl);
        combo.setSelectedIndex(-1);
        tf.setText(str);
    }

    @SuppressWarnings("rawtypes")
    private static DefaultComboBoxModel getSuggestedModel(java.util.List<String> list, String text) {
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list) {
            if (s.contains(text)) {
                m.addElement(s);
            }
        }
        return m;
    }
}
