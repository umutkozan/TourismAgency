package core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Singleton Desing Pattern
    private static DatabaseConnection instance = null;  // Tek bir Db nesnesi
    private Connection connection = null;  // Veritabanı bağlantısı
    private final String DB_URL = "jdbc:postgresql://localhost:5432/tourism";  // Veritabanı URL'si
    private final String DB_USER = "postgres";  // Veritabanı kullanıcı adı
    private final String DB_PASS = "123";  // Veritabanı şifresi

    // Yapıcı metod, başka yerden çağrılamaz
    private DatabaseConnection(){
        try {
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);  // Bağlantıyı kurar
        } catch (SQLException e) {
            e.printStackTrace();  // Hata olursa ekrana yazdırır
        }
    }

    // Veritabanı bağlantısını döndürür
    public Connection getConnection() {
        return connection;
    }

    // Db sınıfının tek bir örneğini döndürür, gerekiyorsa yeni oluşturur
    public static Connection getInstance() {
        try {
            if (instance == null || instance.getConnection().isClosed()) {
                instance = new DatabaseConnection();  // Eğer nesne yoksa veya bağlantı kapalıysa, yeni nesne oluşturur
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());  // Hata mesajını ekrana yazdırır
        }

        return instance.getConnection();  // Bağlantıyı geri döndürür
    }
}