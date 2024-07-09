package core;

import javax.swing.*;
import java.awt.*;

public class Helper {

    // Belirtilen temayı uygular ve uyarı pencerelerini Türkçeleştirir
    public static void setTheme() {
        optionPaneTR();  // Uyarı pencerelerini Türkçeleştirir
        try {
            // Metal temasını uygula
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (Exception e) {
            System.out.println("Metal teması uygulanamadı: " + e.getMessage());
        }
    }

    // Mesaj kutusu gösterir
    public static void showMsg(String str) {
        String msg;
        String title;
        switch (str) {
            case "fill" -> {
                msg = "Lütfen tüm alanları doldurunuz!";
                title = "Hata!";
            }
            case "done" -> {
                msg = "İşlem Başarılı!";
                title = "Sonuç";
            }
            case "notFound" -> {
                msg = "Kayıt Bulunamadı!";
                title = "Bulunamadı";
            }
            case "error" -> {
                msg = "Hatalı işlem yaptınız!";
                title = "Hata";
            }
            default -> {
                msg = str;
                title = "Mesaj";
            }
        }
        JOptionPane.showMessageDialog(null, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    // Onay penceresi gösterir ve sonucu döner
    public static boolean confirm(String str) {
        String msg;
        if (str.equals("sure")) {
            msg = "Bu işlemi yapmak istediğine emin misin?";
        } else {
            msg = str;
        }
        return JOptionPane.showConfirmDialog(null, msg, "Emin misin?", JOptionPane.YES_NO_OPTION) == 0;
    }

    // Metin alanının boş olup olmadığını kontrol eder
    public static boolean isFieldEmpty(JTextField field) {
        return field.getText().trim().isEmpty();
    }

    // Metin alanı listesinin herhangi birinin boş olup olmadığını kontrol eder
    public static boolean isFieldListEmpty(JTextField[] fieldList) {
        for (JTextField field : fieldList) {
            if (isFieldEmpty(field)) {
                return true;
            }
        }
        return false;
    }

    // Verilen türdeki konum noktasını hesaplar (x veya y)
    public static int getLocationPoint(String type, Dimension size) {
        return switch (type) {
            case "x" -> (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
            case "y" -> (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
            default -> 0;
        };
    }

    // Uyarı pencerelerinin düğme metinlerini Türkçeleştirir
    public static void optionPaneTR() {
        UIManager.put("OptionPane.okButtonText", "Tamam");
        UIManager.put("OptionPane.yesButtonText", "Evet");
        UIManager.put("OptionPane.noButtonText", "Hayır");
    }
}
