package dao;

import core.DatabaseConnection;
import entity.Facility;
import entity.Hotel;
import entity.Pension;

import java.sql.*;
import java.util.ArrayList;

public class HotelRepository {

    private Connection con;

    public HotelRepository() {
        this.con = DatabaseConnection.getInstance();
    }

    // Tüm otelleri döndürür
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String query = "SELECT * FROM public.hotel";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotel_id(rs.getInt("hotel_id"));
                hotel.setHotel_name(rs.getString("hotel_name"));
                hotel.setHotel_address(rs.getString("hotel_address"));
                hotel.setHotel_city(rs.getString("hotel_city"));
                hotel.setHotel_region(rs.getString("hotel_region"));
                hotel.setHotel_email(rs.getString("hotel_email"));
                hotel.setHotel_phone(rs.getString("hotel_phone"));
                hotel.setHotel_stars(rs.getString("hotel_stars"));
                hotels.add(hotel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    // Otel bilgilerini günceller
    public boolean update(Hotel hotel) {
        String query = "UPDATE public.hotel SET hotel_name = ?, hotel_address = ?, hotel_city = ?, hotel_region = ?, hotel_email = ?, hotel_phone = ?, hotel_stars = ? WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_address());
            pr.setString(3, hotel.getHotel_city());
            pr.setString(4, hotel.getHotel_region());
            pr.setString(5, hotel.getHotel_email());
            pr.setString(6, hotel.getHotel_phone());
            pr.setInt(7, Integer.parseInt(hotel.getHotel_stars()));
            pr.setInt(8, hotel.getHotel_id());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Otel ID'sine göre tesis ID'sini döndürür
    public int getFacilityIdByHotelId(int hotelId) {
        String query = "SELECT hotel_facility_id FROM public.hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("hotel_facility_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Tesis bilgilerini günceller
    public boolean updateFacility(Facility facility, int hotelId) {
        String query = "UPDATE public.facility SET facility_free_park = ?, facility_free_wifi = ?, facility_pool = ?, facility_gym = ?, facility_concierge = ?, facility_spa = ?, facility_room_service = ? WHERE facility_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setBoolean(1, facility.isFacility_free_park());
            pr.setBoolean(2, facility.isFacility_free_wifi());
            pr.setBoolean(3, facility.isFacility_pool());
            pr.setBoolean(4, facility.isFacility_gym());
            pr.setBoolean(5, facility.isFacility_concierge());
            pr.setBoolean(6, facility.isFacility_SPA());
            pr.setBoolean(7, facility.isFacility_room_service());
            pr.setInt(8, facility.getFacility_id());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Otel ID'sine göre pansiyon ID'sini döndürür
    public int getPensionIdByHotelId(int hotelId) {
        String query = "SELECT hotel_pension_type_id FROM public.hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return rs.getInt("hotel_pension_type_id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Pansiyon bilgilerini günceller
    public boolean updatePension(Pension pension, int hotelId) {
        String query = "UPDATE public.pension SET pension_type_ultra = ?, pension_type_hsd = ?, pension_type_breakfast = ?, pension_type_tam = ?, pension_type_yarim = ?, pension_type_just_bed = ?, pension_type_ahfc = ? WHERE pension_type_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setBoolean(1, pension.isPension_type_ultra());
            pr.setBoolean(2, pension.isPension_type_hsd());
            pr.setBoolean(3, pension.isPension_type_breakfast());
            pr.setBoolean(4, pension.isPension_type_tam());
            pr.setBoolean(5, pension.isPension_type_yarim());
            pr.setBoolean(6, pension.isPension_type_just_bed());
            pr.setBoolean(7, pension.isPension_type_ahfc());
            pr.setInt(8, pension.getPension_type_id());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Bir oteli siler
    public void delete(int hotelId) {
        String query = "DELETE FROM public.hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Otel ID'sine göre otel bilgilerini döndürür
    public Hotel findById(int hotelId) {
        String query = "SELECT * FROM public.hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                Hotel hotel = new Hotel();
                hotel.setHotel_id(rs.getInt("hotel_id"));
                hotel.setHotel_name(rs.getString("hotel_name"));
                hotel.setHotel_address(rs.getString("hotel_address"));
                hotel.setHotel_city(rs.getString("hotel_city"));
                hotel.setHotel_region(rs.getString("hotel_region"));
                hotel.setHotel_email(rs.getString("hotel_email"));
                hotel.setHotel_phone(rs.getString("hotel_phone"));
                hotel.setHotel_stars(rs.getString("hotel_stars"));
                return hotel;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Yeni bir tesis ekler
    public void addFacility(Facility facility, int hotelId) {
        String query = "INSERT INTO public.facility (facility_hotel_id, facility_free_park, facility_free_wifi, facility_pool, facility_gym, facility_concierge, facility_spa, facility_room_service) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pr.setInt(1, hotelId);
            pr.setBoolean(2, facility.isFacility_free_park());
            pr.setBoolean(3, facility.isFacility_free_wifi());
            pr.setBoolean(4, facility.isFacility_pool());
            pr.setBoolean(5, facility.isFacility_gym());
            pr.setBoolean(6, facility.isFacility_concierge());
            pr.setBoolean(7, facility.isFacility_SPA());
            pr.setBoolean(8, facility.isFacility_room_service());
            pr.executeUpdate();

            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                facility.setFacility_id(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Yeni bir pansiyon ekler
    public boolean addPension(Pension pension, int hotelId) {
        String query = "INSERT INTO public.pension (pension_type_name, pension_type_hotel_id, pension_type_ultra, pension_type_hsd, pension_type_breakfast, pension_type_tam, pension_type_yarim, pension_type_just_bed, pension_type_ahfc) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pr.setString(1, "Pension Type Name");  // Bu satırı kendi mantığınıza göre güncelleyin
            pr.setInt(2, hotelId);
            pr.setBoolean(3, pension.isPension_type_ultra());
            pr.setBoolean(4, pension.isPension_type_hsd());
            pr.setBoolean(5, pension.isPension_type_breakfast());
            pr.setBoolean(6, pension.isPension_type_tam());
            pr.setBoolean(7, pension.isPension_type_yarim());
            pr.setBoolean(8, pension.isPension_type_just_bed());
            pr.setBoolean(9, pension.isPension_type_ahfc());
            boolean result = pr.executeUpdate() > 0;

            ResultSet rs = pr.getGeneratedKeys();
            if (rs.next()) {
                pension.setPension_type_id(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Yeni bir otel ekler
    public boolean addHotel(Hotel hotel) {
        String query = "INSERT INTO public.hotel (hotel_name, hotel_address, hotel_city, hotel_region, hotel_email, hotel_phone, hotel_stars, hotel_facility_id, hotel_pension_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setString(1, hotel.getHotel_name());
            pr.setString(2, hotel.getHotel_address());
            pr.setString(3, hotel.getHotel_city());
            pr.setString(4, hotel.getHotel_region());
            pr.setString(5, hotel.getHotel_email());
            pr.setString(6, hotel.getHotel_phone());
            pr.setString(7, hotel.getHotel_stars());
            pr.setInt(8, hotel.getHotel_facility_id());
            pr.setInt(9, hotel.getHotel_pension_type_id());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
