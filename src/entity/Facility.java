package entity;

public class Facility {
    // Tesis özelliklerini temsil eden değişkenler
    private int facility_id;
    private int facility_hotel_id;
    private boolean facility_free_park;
    private boolean facility_free_wifi;
    private boolean facility_pool;
    private boolean facility_gym;
    private boolean facility_concierge;
    private boolean facility_SPA;
    private boolean facility_room_service;

    // Boş constructor
    public Facility() {

    }

    // Tesis ID'sini getirir
    public int getFacility_id() {
        return facility_id;
    }

    // Tesis ID'sini ayarlar
    public void setFacility_id(int facility_id) {
        this.facility_id = facility_id;
    }

    // Tesisin bağlı olduğu otelin ID'sini getirir
    public int getFacility_hotel_id() {
        return facility_hotel_id;
    }

    // Tesisin bağlı olduğu otelin ID'sini ayarlar
    public void setFacility_hotel_id(int facility_hotel_id) {
        this.facility_hotel_id = facility_hotel_id;
    }

    // Ücretsiz park olup olmadığını kontrol eder
    public boolean isFacility_free_park() {
        return facility_free_park;
    }

    // Ücretsiz park özelliğini ayarlar
    public void setFacility_free_park(boolean facility_free_park) {
        this.facility_free_park = facility_free_park;
    }

    // Ücretsiz WiFi olup olmadığını kontrol eder
    public boolean isFacility_free_wifi() {
        return facility_free_wifi;
    }

    // Ücretsiz WiFi özelliğini ayarlar
    public void setFacility_free_wifi(boolean facility_free_wifi) {
        this.facility_free_wifi = facility_free_wifi;
    }

    // Yüzme havuzu olup olmadığını kontrol eder
    public boolean isFacility_pool() {
        return facility_pool;
    }

    // Yüzme havuzu özelliğini ayarlar
    public void setFacility_pool(boolean facility_pool) {
        this.facility_pool = facility_pool;
    }

    // Fitness center olup olmadığını kontrol eder
    public boolean isFacility_gym() {
        return facility_gym;
    }

    // Fitness center özelliğini ayarlar
    public void setFacility_gym(boolean facility_gym) {
        this.facility_gym = facility_gym;
    }

    // Concierge hizmeti olup olmadığını kontrol eder
    public boolean isFacility_concierge() {
        return facility_concierge;
    }

    // Concierge hizmeti özelliğini ayarlar
    public void setFacility_concierge(boolean facility_concierge) {
        this.facility_concierge = facility_concierge;
    }

    // SPA olup olmadığını kontrol eder
    public boolean isFacility_SPA() {
        return facility_SPA;
    }

    // SPA özelliğini ayarlar
    public void setFacility_SPA(boolean facility_SPA) {
        this.facility_SPA = facility_SPA;
    }

    // Oda servisi olup olmadığını kontrol eder
    public boolean isFacility_room_service() {
        return facility_room_service;
    }

    // Oda servisi özelliğini ayarlar
    public void setFacility_room_service(boolean facility_room_service) {
        this.facility_room_service = facility_room_service;
    }

    // Tesis özelliklerini bir String olarak döndürür
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (facility_free_park) sb.append("Ücretsiz Park, ");
        if (facility_free_wifi) sb.append("Ücretsiz WiFi, ");
        if (facility_pool) sb.append("Yüzme Havuzu, ");
        if (facility_gym) sb.append("Fitness Center, ");
        if (facility_concierge) sb.append("Hotel Concierge, ");
        if (facility_SPA) sb.append("SPA, ");
        if (facility_room_service) sb.append("7/24 Oda Servisi, ");

        if (sb.length() > 0) {
            sb.setLength(sb.length() - 2); // Sondaki virgül ve boşluğu kaldırır
        }

        return sb.toString();
    }
}