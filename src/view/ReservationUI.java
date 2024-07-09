package view;

import business.ReservationManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ReservationUI extends JPanel {
    private JTextField fld_reservation_hotel_name;
    private JTextField fld_reservation_hotel_address;
    private JTextField fld_reservation_hotel_phone;
    private JTextField fld_reservation_hotel_facility;
    private JTextField fld_reservation_hotel_room_type;
    private JTextField fld_reservation_season_type;
    private JTextField fld_reservation_room_type;
    private JTextField fld_reservation_pension_type;
    private JTextField fld_reservation_adult_price;
    private JTextField fld_reservation_child_price;
    private JTextField fld_reservation_customer_name;
    private JTextField fld_reservation_customer_phone;
    private JTextField fld_reservation_email;
    private JTextField fld_reservation_customer_note;
    private JTextField fld_reservation_total_price;
    private JButton hesaplaButton;
    private JButton rezervasyonKaydetButton;
    private JTextField fld_reservation_customer_tc;
    private JPanel container;
    private JTextField fld_reservation_day;
    private JTextField fld_reservation_adult_count;
    private JTextField fld_reservation_child_count;

    private ReservationManager reservationManager;
    private Reservation existingReservation; // Mevcut rezervasyonu tutar
    private Hotel selectedHotel;
    private Room selectedRoom;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isPriceCalculated = false; // Fiyatın hesaplanıp hesaplanmadığını kontrol eden bayrak
    private Runnable refreshReservationTableCallback;

    // Constructor, yeni rezervasyon için
    public ReservationUI(JFrame frame, Hotel selectedHotel, Room selectedRoom, LocalDate startDate, LocalDate endDate, Runnable refreshReservationTableCallback) {
        this(frame, selectedHotel, selectedRoom, startDate, endDate, null, refreshReservationTableCallback);
    }

    // Constructor, varolan rezervasyonu güncellemek için
    public ReservationUI(JFrame frame, Hotel selectedHotel, Room selectedRoom, LocalDate startDate, LocalDate endDate, Reservation existingReservation, Runnable refreshReservationTableCallback) {
        this.selectedHotel = selectedHotel;
        this.selectedRoom = selectedRoom;
        this.startDate = startDate;
        this.endDate = endDate;
        this.existingReservation = existingReservation;
        this.refreshReservationTableCallback = (Runnable) refreshReservationTableCallback;
        reservationManager = new ReservationManager();
        this.add(container);
        frame.setSize(600, 700); // GUI'yi başlatır
        frame.add(this); // JPanel'i JFrame'e ekleyin

        // Bileşenleri yükler
        loadReservationComponent(selectedHotel, selectedRoom, startDate, endDate);

        // Varolan rezervasyon verilerini yükler
        if (existingReservation != null) {
            loadExistingReservationData(existingReservation);
        }
    }

    // Rezervasyon bileşenlerini yükler
    public void loadReservationComponent(Hotel selectedHotel, Room selectedRoom, LocalDate startDate, LocalDate endDate) {
        // Otel bilgilerini doldurma
        fld_reservation_hotel_name.setText(selectedHotel.getHotel_name());
        fld_reservation_hotel_address.setText(selectedHotel.getHotel_address());
        fld_reservation_hotel_phone.setText(selectedHotel.getHotel_phone());
        fld_reservation_hotel_facility.setText(selectedHotel.getFacility() != null ? selectedHotel.getFacility().toString() : "");

        // Oda bilgilerini doldurma
        fld_reservation_hotel_room_type.setText(selectedRoom.getRoom_type().name());
        fld_reservation_season_type.setText(selectedRoom.getRoom_season().toString());
        fld_reservation_room_type.setText(selectedRoom.getRoom_type().name());
        fld_reservation_pension_type.setText(selectedRoom.getRoom_pension_type().toString());
        fld_reservation_adult_price.setText(String.valueOf(selectedRoom.getRoom_price_adult()));
        fld_reservation_child_price.setText(String.valueOf(selectedRoom.getRoom_price_child()));

        // Oda özelliklerini string olarak hazırlama
        StringBuilder roomFeatures = new StringBuilder();

        if (selectedRoom.isRoom_has_tv()) {
            roomFeatures.append("TV, ");
        }
        if (selectedRoom.isRoom_has_minibar()) {
            roomFeatures.append("Minibar, ");
        }
        if (selectedRoom.isRoom_has_console()) {
            roomFeatures.append("Console, ");
        }
        if (selectedRoom.isRoom_has_safe()) {
            roomFeatures.append("Safe, ");
        }
        if (selectedRoom.isRoom_has_projector()) {
            roomFeatures.append("Projector, ");
        }

        // Oda özelliklerinin sonundaki virgülü kaldırma
        if (roomFeatures.length() > 0) {
            roomFeatures.setLength(roomFeatures.length() - 2);
        }
        // Oda özelliklerini fld_reservation_room_type textfield'ına ekleme
        fld_reservation_room_type.setText(" (" + roomFeatures.toString() + ")");

        // Tarih farkını hesaplama ve gün bilgisini doldurma
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        fld_reservation_day.setText(String.valueOf(daysBetween));

        // Fiyat hesaplama butonu tıklama olayı
        hesaplaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Pansiyon tipi ve dönem tipi fiyatlarını al
                    String pensionType = fld_reservation_pension_type.getText();
                    String seasonType = fld_reservation_season_type.getText();

                    // Yetişkin ve çocuk fiyatlarını al
                    double adultPrice = Double.parseDouble(fld_reservation_adult_price.getText());
                    double childPrice = Double.parseDouble(fld_reservation_child_price.getText());

                    // Gün sayısını al
                    int days = Integer.parseInt(fld_reservation_day.getText());

                    // Yetişkin ve çocuk sayısını belirle
                    int adultCount = Integer.parseInt(fld_reservation_adult_count.getText());
                    int childCount = Integer.parseInt(fld_reservation_child_count.getText());

                    // Gecelik fiyatı belirle
                    double nightlyRate = adultPrice * adultCount + childPrice * childCount;

                    // Toplam fiyatı hesapla
                    double totalPrice = nightlyRate * days;

                    // Toplam fiyatı fld_reservation_total_price textfield'ına yazdır
                    fld_reservation_total_price.setText(String.format("%.2f", totalPrice));

                    isPriceCalculated = true; // Fiyat hesaplandığını belirten bayrağı true yap
                } catch (NumberFormatException ex) {
                    Helper.showMsg("error");
                }
            }
        });

        // Rezervasyon kaydet butonu tıklama olayı
        rezervasyonKaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isPriceCalculated) {
                    Helper.showMsg("Lütfen Fiyat Hesaplayınız.");
                    return;
                }

                if (areAllFieldsFilled()) {
                    try {
                        // Müşteri bilgilerini al
                        String customerName = fld_reservation_customer_name.getText();
                        String customerPhone = fld_reservation_customer_phone.getText();
                        String customerEmail = fld_reservation_email.getText();
                        String customerNote = fld_reservation_customer_note.getText();
                        long customerTC = Long.parseLong(fld_reservation_customer_tc.getText());

                        // Virgülü noktaya çevirerek double değeri parse et
                        double totalPrice = Double.parseDouble(fld_reservation_total_price.getText().replace(",", "."));

                        int adultCount = Integer.parseInt(fld_reservation_adult_count.getText());
                        int childCount = Integer.parseInt(fld_reservation_child_count.getText());

                        // Rezervasyon objesi oluştur
                        Reservation reservation = new Reservation();
                        reservation.setReservation_room_id(selectedRoom.getRoom_id());
                        reservation.setReservation_customer_name(customerName);
                        reservation.setReservation_customer_contact(customerPhone);
                        reservation.setReservation_check_in_date(java.sql.Date.valueOf(startDate));
                        reservation.setReservation_check_out_date(java.sql.Date.valueOf(endDate));
                        reservation.setReservation_total_price(totalPrice);
                        reservation.setReservation_guest_count_adult(adultCount);
                        reservation.setReservation_guest_count_child(childCount);
                        reservation.setReservation_customer_email(customerEmail);
                        reservation.setReservation_customer_tc(customerTC);
                        reservation.setReservation_customer_note(customerNote);

                        boolean success;
                        if (existingReservation != null) {
                            // Mevcut rezervasyonu güncelle
                            reservation.setReservation_id(existingReservation.getReservation_id());
                            success = reservationManager.updateReservation(reservation);
                        } else {
                            // Yeni rezervasyon ekle
                            success = reservationManager.addReservation(reservation);
                            // Oda stok sayısını güncelle
                            int newStock = selectedRoom.getRoom_stock() - 1;
                            reservationManager.updateRoomStock(selectedRoom.getRoom_id(), newStock);
                        }

                        if (success) {
                            Helper.showMsg("done");
                            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(container);
                            if (frame != null) {
                                frame.dispose(); // Pencereyi kapat
                            }
                            if (refreshReservationTableCallback != null) {
                                refreshReservationTableCallback.run(); // Callback'i çalıştır
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Rezervasyon kaydı hata verdi.", "Hata", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        Helper.showMsg("fill");
                    } catch (Exception ex) {
                        Helper.showMsg("error");
                    }
                } else {
                    Helper.showMsg("fill");
                }
            }
        });
    }

    // Tüm alanların doldurulup doldurulmadığını kontrol eder
    private boolean areAllFieldsFilled() {
        return !fld_reservation_customer_name.getText().isEmpty() &&
                !fld_reservation_customer_phone.getText().isEmpty() &&
                !fld_reservation_email.getText().isEmpty() &&
                !fld_reservation_customer_tc.getText().isEmpty() &&
                !fld_reservation_customer_note.getText().isEmpty() &&
                !fld_reservation_total_price.getText().isEmpty() &&
                !fld_reservation_adult_count.getText().isEmpty() &&
                !fld_reservation_child_count.getText().isEmpty();
    }

    // Varolan rezervasyon verilerini yükler
    private void loadExistingReservationData(Reservation reservation) {
        fld_reservation_customer_name.setText(reservation.getReservation_customer_name());
        fld_reservation_customer_phone.setText(reservation.getReservation_customer_contact());
        fld_reservation_email.setText(reservation.getReservation_customer_email());
        fld_reservation_customer_tc.setText(String.valueOf(reservation.getReservation_customer_tc()));
        fld_reservation_customer_note.setText(reservation.getReservation_customer_note());
        fld_reservation_total_price.setText(String.valueOf(reservation.getReservation_total_price()));
        fld_reservation_adult_count.setText(String.valueOf(reservation.getReservation_guest_count_adult()));
        fld_reservation_child_count.setText(String.valueOf(reservation.getReservation_guest_count_child()));
    }
}
