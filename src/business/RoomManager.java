package business;

import dao.RoomRepository;
import entity.Hotel;
import entity.Room;

import java.util.ArrayList;

public class RoomManager {

    private RoomRepository roomDao;

    // Constructor, RoomDao nesnesini başlatır
    public RoomManager() {
        this.roomDao = new RoomRepository();
    }

    // Tüm odaları döndürür
    public ArrayList<Room> findAll() {
        return roomDao.findAll();
    }

    // ID'ye göre oda döndürür
    public Room getRoomById(int roomId) {
        return roomDao.findById(roomId);
    }

    // Yeni bir oda ekler
    public boolean addRoom(Room room) {
        return roomDao.addRoom(room);
    }

    // Oda bilgilerini günceller
    public boolean updateRoom(Room room) {
        return roomDao.update(room);
    }

    // Odayı siler
    public void deleteRoom(int roomId) {
        roomDao.delete(roomId);
    }

    // Oda verilerini tablo formatında döndürür
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms) {
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room obj : rooms) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getRoom_hotel_id();
            rowObject[i++] = obj.getRoom_season().name();
            rowObject[i++] = obj.getRoom_pension_type().name();
            rowObject[i++] = obj.getRoom_type().name();
            rowObject[i++] = obj.getRoom_price_adult();
            rowObject[i++] = obj.getRoom_price_child();
            rowObject[i++] = obj.getRoom_stock();
            rowObject[i++] = obj.getRoom_bed_count();
            rowObject[i++] = obj.getRoom_size();
            rowObject[i++] = obj.isRoom_has_tv();
            rowObject[i++] = obj.isRoom_has_minibar();
            rowObject[i++] = obj.isRoom_has_console();
            rowObject[i++] = obj.isRoom_has_safe();
            rowObject[i++] = obj.isRoom_has_projector();

            roomList.add(rowObject);
        }
        return roomList;
    }

    // Tüm sezonları döndürür
    public ArrayList<Room.SEASON> getAllSeasons() {
        return roomDao.getAllSeasons();
    }

    // Tüm otelleri döndürür
    public ArrayList<Hotel> getAllHotels() {
        return roomDao.getAllHotels();
    }

    // Tüm pansiyon türlerini döndürür
    public ArrayList<Room.PENSION> getAllPensionTypes() {
        return roomDao.getAllPensionTypes();
    }

    // Otelin bulunduğu şehirleri döndürür
    public ArrayList<String> getHotelCities(int hotelId) {
        return roomDao.getHotelCities(hotelId);
    }

    // Belirtilen kriterlere göre odaları arar
    public ArrayList<Room> searchRooms(String startDate, String endDate, String city, String hotelName) {
        return roomDao.searchRooms(startDate, endDate, city, hotelName);
    }

}