/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

<<<<<<< HEAD
import java.sql.PreparedStatement;
=======
import java.util.ArrayList;import java.sql.PreparedStatement;
>>>>>>> a3f39e5a81ae320dd2b5b520baa8c2760a385a51
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Feedback;

/**
 *
 * @author lenovo_thinkpad
 */
public class FeedbackDAO implements IBaseService{

    @Override
    public ArrayList<Object> getAll() {
        ArrayList<Object> feedbacks = new ArrayList<>();
        java.sql.Connection Connect = null;
        PreparedStatement Statement = null;
        ResultSet Result = null;
<<<<<<< HEAD
        String sql = "select * from Feedback";
        System.out.println(Connection.getConnection());
        
=======
        String sql = "SELECT * FROM Feedback";
        System.out.println(Connection.getConnection());
>>>>>>> a3f39e5a81ae320dd2b5b520baa8c2760a385a51
        try {
            Connect = Connection.getConnection(); // Open 1 connect với Database của mình
            Statement = Connect.prepareStatement(sql); // Biên dịch câu SQL ở trên
            Result = Statement.executeQuery(); // Chạy và thực thi câu SQL
            // next từng phần tử khi tìm thấy cho đến khi đến row cuối cùng thì sẽ dừng vòng lặp while
            while (Result.next()) {
<<<<<<< HEAD
                
                Feedback feedback = new Feedback();
=======
                Feedback feedback = new Feedback(Result.getInt(1), // tạo mợi object của mình và bắt add vào list
                        Result.getString(2),
                        Result.getInt(3));
>>>>>>> a3f39e5a81ae320dd2b5b520baa8c2760a385a51
                feedbacks.add(feedback); // add vào list
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            Connection.closeResultSet(Result);
            Connection.closePreparedStatement(Statement);
            Connection.closeConnection(Connect);
        }
<<<<<<< HEAD
        return null;
=======
        return feedbacks;
>>>>>>> a3f39e5a81ae320dd2b5b520baa8c2760a385a51
    }

    @Override
    public Object getOne(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Object object, Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
