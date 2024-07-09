package business;

import dao.HotelRepository;
import entity.Facility;
import entity.Hotel;
import entity.Pension;

import java.util.ArrayList;

public class HotelManager {

    private final HotelRepository hotelDao;

    public HotelManager() {
        this.hotelDao = new HotelRepository();
    }

    // Tüm otelleri döndürür
    public ArrayList<Hotel> findAll() {
        return this.hotelDao.findAll();
    }

    // Otel verilerini tablo formatında döndürür
    public ArrayList<Object[]> getForTable(int size, ArrayList<Hotel> hotels) {
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for (Hotel obj : hotels) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getHotel_name();
            rowObject[i++] = obj.getHotel_address();
            rowObject[i++] = obj.getHotel_city();
            rowObject[i++] = obj.getHotel_region();
            rowObject[i++] = obj.getHotel_email();
            rowObject[i++] = obj.getHotel_phone();
            rowObject[i++] = obj.getHotel_stars();

            // Tesis bilgileri
            Facility facility = obj.getFacility();
            rowObject[i++] = facility != null ? facility.toString() : "N/A";

            // Pansiyon türleri
            Pension pension = obj.getPension();
            rowObject[i++] = pension != null ? pension.toString() : "N/A";

            hotelList.add(rowObject);
        }
        return hotelList;
    }

    // Otel bilgilerini günceller
    public boolean updateHotel(Hotel hotel) {
        boolean isUpdated = this.hotelDao.update(hotel);

        if (isUpdated) {
            Facility facility = hotel.getFacility();
            if (facility.getFacility_id() == 0) {
                facility.setFacility_id(this.hotelDao.getFacilityIdByHotelId(hotel.getHotel_id()));
            }
            boolean facilityUpdated = this.hotelDao.updateFacility(facility, hotel.getHotel_id());

            Pension pension = hotel.getPension();
            if (pension.getPension_type_id() == 0) {
                pension.setPension_type_id(this.hotelDao.getPensionIdByHotelId(hotel.getHotel_id()));
            }
            boolean pensionUpdated = this.hotelDao.updatePension(pension, hotel.getHotel_id());

            return facilityUpdated && pensionUpdated;
        }

        return false;
    }

    // Yeni bir otel ekler
    public boolean addHotel(Hotel hotel) {
        boolean success = this.hotelDao.addHotel(hotel);
        if (success) {
            // Tesisi ve pansiyonu eklerken otelin ID'sini kullanın
            this.hotelDao.addFacility(hotel.getFacility(), hotel.getHotel_id());
            this.hotelDao.addPension(hotel.getPension(), hotel.getHotel_id());
        }
        return success;
    }

    // Bir oteli siler
    public boolean deleteHotel(int hotelId) {
        this.hotelDao.delete(hotelId);
        return false;
    }

    // Otel ID'sine göre otel bilgilerini döndürür
    public Hotel getHotelById(int hotelId) {
        return hotelDao.findById(hotelId);
    }

    // Yeni bir tesis ekler
    public void addFacility(Facility facility) {
        int hotelId = 1;
        this.hotelDao.addFacility(facility, hotelId);
    }

    // Yeni bir pansiyon ekler
    public void addPension(Pension pension) {
        int hotelId = 1;
        this.hotelDao.addPension(pension, hotelId);
    }
}
