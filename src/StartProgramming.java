import business.UserManager;
import core.Helper;
import view.LoginUI;
public class StartProgramming {
    public static void main(String[] args) {
       // tema yükler
        Helper.setTheme();
        // UserManager nesnesini oluşturur
        UserManager userManager = new UserManager();
        // LoginView nesnesini oluşturur ve görünür hale getirir
        LoginUI LOGINUI = new LoginUI();
        LOGINUI.setVisible(true);
    }
}