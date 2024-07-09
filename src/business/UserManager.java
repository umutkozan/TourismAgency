package business;

import core.Helper;
import dao.UserRepository;
import entity.Users;

import java.util.ArrayList;

public class UserManager {

    private final UserRepository userDao;

    // Constructor, UserDao nesnesini başlatır
    public UserManager() {
        this.userDao = new UserRepository();
    }

    // Kullanıcı adı ve şifreye göre kullanıcıyı bulur
    public Users findByLogin(String username, String password) {
        return this.userDao.findByLogin(username, password);
    }

    // ID'ye göre kullanıcıyı döndürür
    public Users getById(int id) {
        return this.userDao.getById(id);
    }

    // Kullanıcı verilerini tablo formatında döndürür
    public ArrayList<Object[]> getForTable(int size, ArrayList<Users> userList) {
        ArrayList<Object[]> userRowList = new ArrayList<>();
        for (Users user : userList) {
            Object[] rowObject = new Object[size];
            int i = 0;
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getPassword();
            rowObject[i++] = user.getRole();
            userRowList.add(rowObject);
        }
        return userRowList;
    }

    // Tüm kullanıcıları döndürür
    public ArrayList<Users> findAll() {
        return this.userDao.findAll();
    }

    // Yeni bir kullanıcı kaydeder
    public boolean save(Users user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    // Kullanıcı bilgilerini günceller
    public boolean update(Users user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + " ID kayıtlı user bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }

    // Kullanıcıyı siler
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı user bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

    // Belirtilen role göre kullanıcıları arar
    public ArrayList<Users> searchForTable(String role) {
        String select = "SELECT * FROM public.users";
        ArrayList<String> whereList = new ArrayList<>();

        if (role != null) {
            whereList.add("user_role ='" + role + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if (whereStr.length() > 0) {
            query = query + " WHERE " + whereStr;
        }

        return this.userDao.selectByQuery(query);
    }
}