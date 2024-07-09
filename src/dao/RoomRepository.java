package dao;

import core.DatabaseConnection;
import entity.Hotel;
import entity.Room;

import java.sql.*;
import java.util.ArrayList;

public class RoomRepository {

    private Connection con;

    // Bu sınıfın yapıcı metodu, DatabaseConnection sınıfını kullanarak veritabanı bağlantısını başlatır
    public RoomRepository() {
        this.con = DatabaseConnection.getInstance();
    }

    // Tüm mevcut odaları veritabanından getirir
    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room WHERE room_stock > 0 ORDER BY room_id ASC");
    }

    // Verilen SQL sorgusuna göre odaları seçer
    public ArrayList<Room> selectByQuery(String query) {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                rooms.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    // Belirtilen ID'ye göre bir oda bulur
    public Room findById(int roomId) {
        String query = "SELECT * FROM public.room WHERE room_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, roomId);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                return match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Yeni bir oda ekler
    public boolean addRoom(Room room) {
        String query = "INSERT INTO public.room (room_hotel_id, room_type, room_price_adult, room_price_child, room_stock, " +
                "room_bed_count, room_size, room_has_tv, room_has_minibar, room_has_console, room_has_safe, room_has_projector, " +
                "room_season_type, room_pension_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pr = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pr.setInt(1, room.getRoom_hotel_id());
            pr.setString(2, room.getRoom_type().name());
            pr.setDouble(3, room.getRoom_price_adult());
            pr.setDouble(4, room.getRoom_price_child());
            pr.setInt(5, room.getRoom_stock());
            pr.setInt(6, room.getRoom_bed_count());
            pr.setInt(7, room.getRoom_size());
            pr.setBoolean(8, room.isRoom_has_tv());
            pr.setBoolean(9, room.isRoom_has_minibar());
            pr.setBoolean(10, room.isRoom_has_console());
            pr.setBoolean(11, room.isRoom_has_safe());
            pr.setBoolean(12, room.isRoom_has_projector());
            pr.setString(13, room.getRoom_season().name());
            pr.setString(14, room.getRoom_pension_type().name());

            int insertedRows = pr.executeUpdate();
            if (insertedRows > 0) {
                ResultSet generatedKeys = pr.getGeneratedKeys();
                if (generatedKeys.next()) {
                    room.setRoom_id(generatedKeys.getInt(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Bir odanın bilgilerini günceller
    public boolean update(Room room) {
        String query = "UPDATE public.room SET room_hotel_id = ?, room_type = ?, room_price_adult = ?, room_price_child = ?, room_stock = ?, " +
                "room_bed_count = ?, room_size = ?, room_has_tv = ?, room_has_minibar = ?, room_has_console = ?, room_has_safe = ?, room_has_projector = ?, " +
                "room_season_type = ?, room_pension_type = ? WHERE room_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, room.getRoom_hotel_id());
            pr.setString(2, room.getRoom_type().name());
            pr.setDouble(3, room.getRoom_price_adult());
            pr.setDouble(4, room.getRoom_price_child());
            pr.setInt(5, room.getRoom_stock());
            pr.setInt(6, room.getRoom_bed_count());
            pr.setInt(7, room.getRoom_size());
            pr.setBoolean(8, room.isRoom_has_tv());
            pr.setBoolean(9, room.isRoom_has_minibar());
            pr.setBoolean(10, room.isRoom_has_console());
            pr.setBoolean(11, room.isRoom_has_safe());
            pr.setBoolean(12, room.isRoom_has_projector());
            pr.setString(13, room.getRoom_season().name());
            pr.setString(14, room.getRoom_pension_type().name());
            pr.setInt(15, room.getRoom_id());
            return pr.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Bir odayı veritabanından siler
    public void delete(int roomId) {
        String query = "DELETE FROM public.room WHERE room_id=?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, roomId);
            pr.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ResultSet'ten oda nesnesini oluşturur
    private Room match(ResultSet rs) throws SQLException {
        int room_id = rs.getInt("room_id");
        int room_hotel_id = rs.getInt("room_hotel_id");
        Room.TYPE room_type = Room.TYPE.valueOf(rs.getString("room_type"));
        double room_price_adult = rs.getDouble("room_price_adult");
        double room_price_child = rs.getDouble("room_price_child");
        int room_stock = rs.getInt("room_stock");
        int room_bed_count = rs.getInt("room_bed_count");
        int room_size = rs.getInt("room_size");
        boolean room_has_tv = rs.getBoolean("room_has_tv");
        boolean room_has_minibar = rs.getBoolean("room_has_minibar");
        boolean room_has_console = rs.getBoolean("room_has_console");
        boolean room_has_safe = rs.getBoolean("room_has_safe");
        boolean room_has_projector = rs.getBoolean("room_has_projector");
        Room.SEASON room_season = Room.SEASON.valueOf(rs.getString("room_season_type"));
        Room.PENSION room_pension_type = Room.PENSION.valueOf(rs.getString("room_pension_type"));

        Room room = new Room(room_id, room_hotel_id, room_type, room_season, room_pension_type,
                room_price_adult, room_price_child, room_stock, room_bed_count, room_size,
                room_has_tv, room_has_minibar, room_has_console, room_has_safe, room_has_projector);

        return room;
    }

    // Tüm sezonları getirir
    public ArrayList<Room.SEASON> getAllSeasons() {
        ArrayList<Room.SEASON> seasons = new ArrayList<>();
        for (Room.SEASON season : Room.SEASON.values()) {
            seasons.add(season);
        }
        return seasons;
    }

    // Tüm otelleri getirir
    public ArrayList<Hotel> getAllHotels() {
        ArrayList<Hotel> hotels = new ArrayList<>();
        String query = "SELECT hotel_id, hotel_name FROM public.hotel";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int hotel_id = rs.getInt("hotel_id");
                String hotel_name = rs.getString("hotel_name");
                hotels.add(new Hotel(hotel_id, hotel_name, "", "", "", "", 0, ""));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotels;
    }

    // Tüm pansiyon türlerini getirir
    public ArrayList<Room.PENSION> getAllPensionTypes() {
        ArrayList<Room.PENSION> pensionTypes = new ArrayList<>();
        for (Room.PENSION pension : Room.PENSION.values()) {
            pensionTypes.add(pension);
        }
        return pensionTypes;
    }

    // Otelin bulunduğu şehirleri getirir
    public ArrayList<String> getHotelCities(int hotelId) {
        ArrayList<String> cities = new ArrayList<>();
        String query = "SELECT DISTINCT hotel_city FROM public.hotel WHERE hotel_id = ?";
        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, hotelId);
            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                cities.add(rs.getString("hotel_city"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    // Oda arama kriterlerine göre odaları getirir
    public ArrayList<Room> searchRooms(String startDate, String endDate, String city, String hotelName) {
        ArrayList<Room> rooms = new ArrayList<>();

        String query = "WITH AvailableRoomCounts AS ( " +
                "    SELECT r.room_id, r.room_stock + COUNT(res.reservation_id) AS available_stock " +
                "    FROM public.room r " +
                "    LEFT JOIN public.reservation res " +
                "    ON r.room_id = res.reservation_room_id " +
                "    AND (res.reservation_check_out_date < ?::DATE OR res.reservation_check_in_date > ?::DATE) " +
                "    GROUP BY r.room_id " +
                ") " +
                "SELECT r.*, ar.available_stock " +
                "FROM public.room r " +
                "JOIN public.hotel h ON r.room_hotel_id = h.hotel_id " +
                "JOIN AvailableRoomCounts ar ON r.room_id = ar.room_id " +
                "WHERE ar.available_stock > 0 ";

        if (city != null && !city.isEmpty()) {
            query += "AND h.hotel_city = ? ";
        }

        if (hotelName != null && !hotelName.isEmpty()) {
            query += "AND h.hotel_name = ? ";
        }

        query += "ORDER BY r.room_id ASC";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            int paramIndex = 1;

            pr.setString(paramIndex++, startDate);
            pr.setString(paramIndex++, endDate);

            if (city != null && !city.isEmpty()) {
                pr.setString(paramIndex++, city);
            }

            if (hotelName != null && !hotelName.isEmpty()) {
                pr.setString(paramIndex++, hotelName);
            }

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                Room room = this.match(rs);
                room.setRoom_stock(rs.getInt("available_stock"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
}
