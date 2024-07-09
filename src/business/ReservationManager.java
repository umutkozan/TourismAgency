package business;

import dao.ReservationRepository;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {
    private ReservationRepository reservationRepository;

    // Constructor, ReservationDao nesnesini başlatır
    public ReservationManager() {
        this.reservationRepository = new ReservationRepository();
    }

    // ID'ye göre rezervasyon döndürür
    public Reservation getReservationById(int roomId) {
        return reservationRepository.findById(roomId);
    }

    // Yeni bir rezervasyon ekler
    public boolean addReservation(Reservation reservation) {
        boolean success = reservationRepository.addReservation(reservation);
        if (success) {
            // Oda stokunu güncelle, 1 eksilt
            reservationRepository.updateRoomStock(reservation.getReservation_room_id(), -1);
        }
        return success;
    }

    // Oda stokunu günceller
    public boolean updateRoomStock(int roomId, int stockChange) {
        return reservationRepository.updateRoomStock(roomId, stockChange);
    }

    // Rezervasyon bilgilerini günceller
    public boolean updateReservation(Reservation reservation) {
        return reservationRepository.updateReservation(reservation);
    }

    // Rezervasyonu siler
    public boolean deleteReservation(int reservationId) {
        reservationRepository.deleteReservation(reservationId);
        return false;
    }

    // Rezervasyon verilerini tablo formatında döndürür
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for (Reservation obj : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getReservation_id();
            rowObject[i++] = obj.getReservation_room_id();
            rowObject[i++] = obj.getReservation_customer_name();
            rowObject[i++] = obj.getReservation_check_in_date();
            rowObject[i++] = obj.getReservation_check_out_date();
            rowObject[i++] = obj.getReservation_total_price();
            rowObject[i++] = obj.getReservation_guest_count_adult();
            rowObject[i++] = obj.getReservation_guest_count_child();
            rowObject[i++] = obj.getReservation_customer_email();
            rowObject[i++] = obj.getReservation_customer_tc();
            rowObject[i++] = obj.getReservation_customer_note();

            reservationList.add(rowObject);
        }
        return reservationList;
    }

    // Tüm rezervasyonları döndürür
    public ArrayList<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    // Arama işlevselliğini uygulamak için search metodu
    public ArrayList<Reservation> search(String keyword) {
        return reservationRepository.search(keyword);
    }
}
