package view;

import business.UserManager;
import core.Helper;
import entity.Users;

import javax.swing.*;

public class UserUI extends Layout {
    private JPanel container;
    private JTextField fld_username;
    private JTextField fld_pass;
    private JComboBox<Users.Role> cmb_user_role;
    private JButton btn_user_save;
    private UserManager userManager;
    private Users user;

    // UserView constructor'ı
    public UserUI(Users user) {
        this.user = user;
        this.userManager = new UserManager();
        this.add(container);
        this.initilizeGui(400, 300); // GUI'yi başlatır ve pencereyi oluşturur

        // cmb_user_role bileşenini doldurur
        for (Users.Role role : Users.Role.values()) {
            this.cmb_user_role.addItem(role);
        }

        // Mevcut kullanıcıyı güncelleme durumunda alanları doldurur
        if (this.user.getId() != 0) {
            this.fld_username.setText(this.user.getUsername());
            this.fld_pass.setText(this.user.getPassword());
            this.cmb_user_role.setSelectedItem(this.user.getRole());
        }

        // Kullanıcı kaydetme butonu tıklama olayı
        this.btn_user_save.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_username, this.fld_pass})) {
                Helper.showMsg("fill");
            } else {
                boolean result;

                // Kullanıcı bilgilerini alır
                this.user.setUsername(this.fld_username.getText());
                this.user.setPassword(this.fld_pass.getText());
                this.user.setRole((Users.Role) this.cmb_user_role.getSelectedItem());

                // Kullanıcıyı günceller veya yeni kullanıcı kaydeder
                if (this.user.getId() != 0) {
                    result = this.userManager.update(this.user);
                } else {
                    result = this.userManager.save(this.user);
                }

                // Sonuca göre mesaj gösterir ve pencereyi kapatır
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}