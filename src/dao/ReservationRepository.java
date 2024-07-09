package dao;

import core.DatabaseConnection;
import entity.Reservation;

import java.sql.*;
import java.util.ArrayList;

public class ReservationRepository {
    private Connection con;

    // Constructor, Db bağlantısını başlatır
    public ReservationRepository() {
        this.con = DatabaseConnection.getInstance();
    }

    // ID'ye göre rezervasyon bulur
    public Reservation findById(int reservationId) {
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, reservationId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Yeni bir rezervasyon ekler
    public boolean addReservation(Reservation reservation) {
        String query = "INSERT INTO public.reservation (reservation_room_id, reservation_customer_name, reservation_customer_contact, " +
                "reservation_check_in_date, reservation_check_out_date, reservation_total_price, reservation_guest_count_adult, " +
                "reservation_guest_count_child, reservation_customer_email, reservation_customer_tc, reservation_customer_note) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, reservation.getReservation_room_id());
            pr.setString(2, reservation.getReservation_customer_name());
            pr.setString(3, reservation.getReservation_customer_contact());
            pr.setDate(4, new Date(reservation.getReservation_check_in_date().getTime()));
            pr.setDate(5, new Date(reservation.getReservation_check_out_date().getTime()));
            pr.setDouble(6, reservation.getReservation_total_price());
            pr.setInt(7, reservation.getReservation_guest_count_adult());
            pr.setInt(8, reservation.getReservation_guest_count_child());
            pr.setString(9, reservation.getReservation_customer_email());
            pr.setLong(10, reservation.getReservation_customer_tc());
            pr.setString(11, reservation.getReservation_customer_note());

            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Rezervasyon bilgilerini günceller
    public boolean updateReservation(Reservation reservation) {
        String query = "UPDATE public.reservation SET reservation_room_id = ?, reservation_customer_name = ?, " +
                "reservation_customer_contact = ?, reservation_check_in_date = ?, reservation_check_out_date = ?, " +
                "reservation_total_price = ?, reservation_guest_count_adult = ?, reservation_guest_count_child = ?, " +
                "reservation_customer_email = ?, reservation_customer_tc = ?, reservation_customer_note = ? " +
                "WHERE reservation_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, reservation.getReservation_room_id());
            pr.setString(2, reservation.getReservation_customer_name());
            pr.setString(3, reservation.getReservation_customer_contact());
            pr.setDate(4, new Date(reservation.getReservation_check_in_date().getTime()));
            pr.setDate(5, new Date(reservation.getReservation_check_out_date().getTime()));
            pr.setDouble(6, reservation.getReservation_total_price());
            pr.setInt(7, reservation.getReservation_guest_count_adult());
            pr.setInt(8, reservation.getReservation_guest_count_child());
            pr.setString(9, reservation.getReservation_customer_email());
            pr.setLong(10, reservation.getReservation_customer_tc());
            pr.setString(11, reservation.getReservation_customer_note());
            pr.setInt(12, reservation.getReservation_id());

            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Rezervasyonu siler
    public void deleteReservation(int reservationId) {
        String query = "DELETE FROM public.reservation WHERE reservation_id=?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, reservationId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Oda stokunu günceller
    public boolean updateRoomStock(int roomId, int newStock) {
        String query = "UPDATE public.room SET room_stock = ? WHERE room_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, newStock);
            pr.setInt(2, roomId);
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Tüm rezervasyonları döndürür
    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY reservation_id ASC");
    }

    // Verilen sorguya göre rezervasyonları seçer
    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservations.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // Anahtar kelimeye göre rezervasyonları arar
    public ArrayList<Reservation> search(String keyword) {
        String query = "SELECT * FROM public.reservation WHERE " +
                "reservation_customer_name ILIKE ? OR " +
                "reservation_customer_email ILIKE ? OR " +
                "reservation_customer_contact ILIKE ?";
        ArrayList<Reservation> reservations = new ArrayList<>();
        try (PreparedStatement pr = con.prepareStatement(query)) {
            String searchKeyword = "%" + keyword + "%";
            pr.setString(1, searchKeyword);
            pr.setString(2, searchKeyword);
            pr.setString(3, searchKeyword);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                reservations.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    // ResultSet'ten rezervasyon nesnesini oluşturur
    private Reservation match(ResultSet rs) throws SQLException {
        int reservation_id = rs.getInt("reservation_id");
        int reservation_room_id = rs.getInt("reservation_room_id");
        String reservation_customer_name = rs.getString("reservation_customer_name");
        String reservation_customer_contact = rs.getString("reservation_customer_contact");
        Date reservation_check_in_date = rs.getDate("reservation_check_in_date");
        Date reservation_check_out_date = rs.getDate("reservation_check_out_date");
        double reservation_total_price = rs.getDouble("reservation_total_price");
        int reservation_guest_count_adult = rs.getInt("reservation_guest_count_adult");
        int reservation_guest_count_child = rs.getInt("reservation_guest_count_child");
        String reservation_customer_email = rs.getString("reservation_customer_email");
        long reservation_customer_tc = rs.getLong("reservation_customer_tc");
        String reservation_customer_note = rs.getString("reservation_customer_note");

        return new Reservation(
                reservation_id,
                reservation_room_id,
                reservation_customer_name,
                reservation_customer_contact,
                reservation_check_in_date,
                reservation_check_out_date,
                reservation_total_price,
                reservation_guest_count_adult,
                reservation_guest_count_child,
                reservation_customer_email,
                reservation_customer_tc,
                reservation_customer_note
        );
    }
}
