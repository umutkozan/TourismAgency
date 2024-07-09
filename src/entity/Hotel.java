package entity;

import java.util.StringJoiner;

public class Hotel {
    // Otel özelliklerini temsil eden değişkenler
    private int hotel_id;
    private String hotel_name;
    private String hotel_address;
    private String hotel_city;
    private String hotel_region;
    private String hotel_email;
    private String hotel_phone;
    private String hotel_stars;
    private int hotel_facility_id;
    private int hotel_pension_type_id;
    private Facility facility;
    private Pension pension;

    // Constructor, otel ID ve otel adı ile başlatır
    public Hotel(int hotel_id, String hotel_name, String text, String fldHotelCityText, String fldHotelEmailText, String fldHotelPhoneText, int i, String fldRegionText) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
    }

    // Boş constructor
    public Hotel() {
    }

    // Constructor, tüm özelliklerle otel nesnesini başlatır
    public Hotel(int hotel_id, String hotel_name, String hotel_address, String hotel_city, String hotel_region, String hotel_email,
                 String hotel_phone, String hotel_stars, int hotel_facility_id, int hotel_pension_type_id, Facility facility, Pension pension) {
        this.hotel_id = hotel_id;
        this.hotel_name = hotel_name;
        this.hotel_address = hotel_address;
        this.hotel_city = hotel_city;
        this.hotel_region = hotel_region;
        this.hotel_email = hotel_email;
        this.hotel_phone = hotel_phone;
        this.hotel_stars = hotel_stars;
        this.hotel_facility_id = hotel_facility_id;
        this.hotel_pension_type_id = hotel_pension_type_id;
        this.facility = facility;
        this.pension = pension;
    }

    // Otel ID'sini döndürür
    public int getHotel_id() {
        return hotel_id;
    }

    // Otel ID'sini ayarlar
    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    // Otel adını döndürür
    public String getHotel_name() {
        return hotel_name;
    }

    // Otel adını ayarlar
    public void setHotel_name(String hotel_name) {
        this.hotel_name = hotel_name;
    }

    // Otel adresini döndürür
    public String getHotel_address() {
        return hotel_address;
    }

    // Otel adresini ayarlar
    public void setHotel_address(String hotel_address) {
        this.hotel_address = hotel_address;
    }

    // Otelin bulunduğu şehri döndürür
    public String getHotel_city() {
        return hotel_city;
    }

    // Otelin bulunduğu şehri ayarlar
    public void setHotel_city(String hotel_city) {
        this.hotel_city = hotel_city;
    }

    // Otelin bulunduğu bölgeyi döndürür
    public String getHotel_region() {
        return hotel_region;
    }

    // Otelin bulunduğu bölgeyi ayarlar
    public void setHotel_region(String hotel_region) {
        this.hotel_region = hotel_region;
    }

    // Otel e-posta adresini döndürür
    public String getHotel_email() {
        return hotel_email;
    }

    // Otel e-posta adresini ayarlar
    public void setHotel_email(String hotel_email) {
        this.hotel_email = hotel_email;
    }

    // Otel telefon numarasını döndürür
    public String getHotel_phone() {
        return hotel_phone;
    }

    // Otel telefon numarasını ayarlar
    public void setHotel_phone(String hotel_phone) {
        this.hotel_phone = hotel_phone;
    }

    // Otelin yıldız sayısını döndürür
    public String getHotel_stars() {
        return hotel_stars;
    }

    // Otelin yıldız sayısını ayarlar
    public void setHotel_stars(String hotel_stars) {
        this.hotel_stars = hotel_stars;
    }

    // Otelin tesis ID'sini döndürür
    public int getHotel_facility_id() {
        return hotel_facility_id;
    }

    // Otelin tesis ID'sini ayarlar
    public void setHotel_facility_id(int hotel_facility_id) {
        this.hotel_facility_id = hotel_facility_id;
    }

    // Otelin pansiyon türü ID'sini döndürür
    public int getHotel_pension_type_id() {
        return hotel_pension_type_id;
    }

    // Otelin pansiyon türü ID'sini ayarlar
    public void setHotel_pension_type_id(int hotel_pension_type_id) {
        this.hotel_pension_type_id = hotel_pension_type_id;
    }

    // Otelin tesis özelliklerini döndürür
    public Facility getFacility() {
        return facility;
    }

    // Otelin tesis özelliklerini ayarlar
    public void setFacility(Facility facility) {
        this.facility = facility;
    }

    // Otelin pansiyon özelliklerini döndürür
    public Pension getPension() {
        return pension;
    }

    // Otelin pansiyon özelliklerini ayarlar
    public void setPension(Pension pension) {
        this.pension = pension;
    }

    // Combobox'ta sadece otel adının görünmesini sağlar
    @Override
    public String toString() {
        return hotel_name;
    }

    // Otel detaylarını String olarak döndürür
    public String toDetailedString() {
        return new StringJoiner(", ", Hotel.class.getSimpleName() + "[", "]")
                .add("hotel_id=" + hotel_id)
                .add("hotel_name='" + hotel_name + "'")
                .add("hotel_address='" + hotel_address + "'")
                .add("hotel_city='" + hotel_city + "'")
                .add("hotel_region='" + hotel_region + "'")
                .add("hotel_email='" + hotel_email + "'")
                .add("hotel_phone='" + hotel_phone + "'")
                .add("hotel_stars='" + hotel_stars + "'")
                .add("hotel_facility_id=" + hotel_facility_id)
                .add("hotel_pension_type_id=" + hotel_pension_type_id)
                .add("facility=" + facility)
                .add("pension=" + pension)
                .toString();
    }
}