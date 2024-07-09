package entity;

import java.util.StringJoiner;

public class Users {
    // Kullanıcı özelliklerini temsil eden değişkenler
    private int id;
    private String username;
    private String password;
    private Role role;

    // Kullanıcı rolleri enum
    public enum Role {
        ADMIN,
        EMPLOYEE
    }

    // Boş constructor
    public Users() {
    }

    // Tüm özelliklerle kullanıcı nesnesini başlatan constructor
    public Users(int id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Kullanıcı ID'sini getirir
    public int getId() {
        return id;
    }

    // Kullanıcı ID'sini ayarlar
    public void setId(int id) {
        this.id = id;
    }

    // Kullanıcı adını getirir
    public String getUsername() {
        return username;
    }

    // Kullanıcı adını ayarlar
    public void setUsername(String username) {
        this.username = username;
    }

    // Kullanıcı şifresini getirir
    public String getPassword() {
        return password;
    }

    // Kullanıcı şifresini ayarlar
    public void setPassword(String password) {
        this.password = password;
    }

    // Kullanıcı rolünü getirir
    public Role getRole() {
        return role;
    }

    // Kullanıcı rolünü ayarlar
    public void setRole(Role role) {
        this.role = role;
    }

    // Kullanıcı detaylarını String olarak döndürür
    @Override
    public String toString() {
        return new StringJoiner(", ", Users.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("password='" + password + "'")
                .add("role=" + role)
                .toString();
    }
}
