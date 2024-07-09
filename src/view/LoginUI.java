package view;

import business.UserManager;
import core.Helper;
import entity.Users;

import javax.swing.*;

public class LoginUI extends Layout {
    private JPanel container;
    private JPanel pnl_top;
    private JPanel pnl_buttom;
    private JTextField fld_username;
    private JLabel lbl_username;
    private JPasswordField fld_pass;
    private JButton btn_login;
    private JLabel lbl_pass;
    private final UserManager userManager;

    // LoginView constructor'ı
    public LoginUI() {
        this.userManager = new UserManager();

        // Container panelini ekler
        this.add(container);
        // GUI'yi başlatır ve pencereyi oluşturur
        this.initilizeGui(400, 400);

        // Giriş butonuna tıklama olayını tanımlar
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.fld_username, this.fld_pass};
            // Boş alan kontrolü yapar
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            } else {
                // Kullanıcıyı giriş bilgilerine göre kontrol eder
                Users loginUser = this.userManager.findByLogin(this.fld_username.getText(), new String(this.fld_pass.getPassword()));
                if (loginUser == null) {
                    Helper.showMsg("notFound");
                } else {
                    // Kullanıcı rolüne göre farklı pencereler açar
                    if (loginUser.getRole() == Users.Role.ADMIN) {
                        new AdminUI(loginUser).setVisible(true);
                    } else {
                        new EmployeeUI(loginUser).setVisible(true);
                    }
                    dispose(); // Login penceresini kapatır
                }
            }
        });
    }
}