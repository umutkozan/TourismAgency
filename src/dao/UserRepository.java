package dao;

import core.DatabaseConnection;
import entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepository {

    private Connection connection;

    // Bu sınıfın yapıcı metodu, DatabaseConnection sınıfını kullanarak veritabanı bağlantısını başlatır
    public UserRepository() {
        this.connection = DatabaseConnection.getInstance();
    }

    // Belirtilen ID'ye göre kullanıcı bilgilerini getirir
    public Users getById(int id) {
        Users obj = null;
        String query = "SELECT * FROM public.users WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // Tüm kullanıcıları veritabanından getirir
    public ArrayList<Users> findAll() {
        ArrayList<Users> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.users";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // Kullanıcı adı ve şifreye göre kullanıcıyı bulur
    public Users findByLogin(String username, String password) {
        Users user = null;
        String query = "SELECT * FROM users WHERE user_name = ? AND user_pass = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new Users(
                            rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("user_pass"),
                            Users.Role.valueOf(rs.getString("user_role").toUpperCase())
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // Parametre olarak verilen SQL sorgusunu çalıştırır ve sonuçları döndürür
    public ArrayList<Users> selectByQuery(String query) {
        ArrayList<Users> userList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return userList;
    }

    // Yeni bir kullanıcı ekler
    public boolean save(Users user) {
        String query = "INSERT INTO public.users (user_name, user_pass, user_role) VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().name());

            return pr.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kullanıcı bilgilerini günceller
    public boolean update(Users user) {
        String query = "UPDATE public.users SET user_name = ? , user_pass = ? , user_role = ? WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().name());
            pr.setInt(4, user.getId());

            return pr.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kullanıcıyı veritabanından siler
    public boolean delete(int user_id) {
        String query = "DELETE FROM public.users WHERE user_id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, user_id);
            return pr.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // ResultSet'ten kullanıcı nesnesini oluşturur
    public Users match(ResultSet rs) throws SQLException {
        Users obj = new Users();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_pass"));
        obj.setRole(Users.Role.valueOf(rs.getString("user_role").toUpperCase()));
        return obj;
    }

}