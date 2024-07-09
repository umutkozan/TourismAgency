package view;

import core.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Layout extends JFrame {

    // GUI'yi başlatır ve pencere oluşturur
    public void initilizeGui(int width, int height) {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Turizm Acente Sistemi");
        this.setSize(width, height);
        this.setLocation(Helper.getLocationPoint("x", this.getSize()), Helper.getLocationPoint("y", this.getSize()));
        this.setVisible(true);
    }

    // Tablo oluşturur ve veri ekler
    public void createTable(DefaultTableModel model, JTable table, Object[] columns, ArrayList<Object[]> rows) {
        model.setColumnIdentifiers(columns); // Kolon başlıklarını ayarlar
        table.setModel(model); // Modeli tabloya bağlar
        table.getTableHeader().setReorderingAllowed(false); // Kolonların yer değiştirmesini engeller
        table.getColumnModel().getColumn(0).setPreferredWidth(75); // İlk kolon genişliğini ayarlar
        table.setEnabled(false); // Tabloyu düzenlenemez hale getirir

        // Tabloyu temizler
        DefaultTableModel clearModel = (DefaultTableModel) table.getModel();
        clearModel.setRowCount(0);

        // Eğer satır yoksa boş bir liste oluşturur
        if (rows == null) {
            rows = new ArrayList<>();
        }

        // Satırları tabloya ekler
        for (Object[] row : rows) {
            model.addRow(row);
        }
    }

    // Seçili tablo satırını döndürür
    public int getTableSelectedRow(JTable table, int index) {
        return Integer.parseInt(table.getValueAt(table.getSelectedRow(), index).toString());
    }

    // Tablo satırı seçildiğinde sağ tıklama menüsünü gösterir
    public void tableRowSelected(JTable table, JPopupMenu popupMenu) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row, selected_row);
                if (SwingUtilities.isRightMouseButton(e)) {
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });
    }

    // Otel tablosunda satır seçildiğinde belirli bir işlemi yürütür
    public void tableHotelRowSelected(JTable table, Runnable action) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selectedRow, selectedRow);
                action.run();
            }
        });
    }

    // Oda tablosunda satır seçildiğinde belirli bir işlemi yürütür
    public void tableRoomRowSelected(JTable table, Runnable action) {
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int selectedRow = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selectedRow, selectedRow);
                action.run();
            }
        });
    }
}