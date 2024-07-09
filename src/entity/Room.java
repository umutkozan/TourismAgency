package entity;

import java.util.StringJoiner;

public class Room {

    // Oda özelliklerini temsil eden değişkenler
    private int room_id;
    private int room_hotel_id;
    private TYPE room_type;
    private SEASON room_season;
    private PENSION room_pension_type;
    private double room_price_adult;
    private double room_price_child;
    private int room_stock;
    private int room_bed_count;
    private int room_size;
    private boolean room_has_tv;
    private boolean room_has_minibar;
    private boolean room_has_console;
    private boolean room_has_safe;
    private boolean room_has_projector;

    // Boş constructor
    public Room() {
    }

    // Tüm özelliklerle oda nesnesini başlatan constructor
    public Room(int room_id, int room_hotel_id, TYPE room_type, SEASON room_season, PENSION room_pension_type, double room_price_adult,
                double room_price_child, int room_stock, int room_bed_count, int room_size, boolean room_has_tv, boolean room_has_minibar,
                boolean room_has_console, boolean room_has_safe, boolean room_has_projector) {
        this.room_id = room_id;
        this.room_hotel_id = room_hotel_id;
        this.room_type = room_type;
        this.room_season = room_season;
        this.room_pension_type = room_pension_type;
        this.room_price_adult = room_price_adult;
        this.room_price_child = room_price_child;
        this.room_stock = room_stock;
        this.room_bed_count = room_bed_count;
        this.room_size = room_size;
        this.room_has_tv = room_has_tv;
        this.room_has_minibar = room_has_minibar;
        this.room_has_console = room_has_console;
        this.room_has_safe = room_has_safe;
        this.room_has_projector = room_has_projector;
    }

    // Oda ID'sini döndürür
    public int getRoom_id() {
        return room_id;
    }

    // Oda ID'sini ayarlar
    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    // Odanın bağlı olduğu otelin ID'sini döndürür
    public int getRoom_hotel_id() {
        return room_hotel_id;
    }

    // Odanın bağlı olduğu otelin ID'sini ayarlar
    public void setRoom_hotel_id(int room_hotel_id) {
        this.room_hotel_id = room_hotel_id;
    }

    // Oda türünü döndürür
    public TYPE getRoom_type() {
        return room_type;
    }

    // Oda türünü ayarlar
    public void setRoom_type(TYPE room_type) {
        this.room_type = room_type;
    }

    // Oda sezonunu döndürür
    public SEASON getRoom_season() {
        return room_season;
    }

    // Oda sezonunu ayarlar
    public void setRoom_season(SEASON room_season) {
        this.room_season = room_season;
    }

    // Oda pansiyon türünü döndürür
    public PENSION getRoom_pension_type() {
        return room_pension_type;
    }

    // Oda pansiyon türünü ayarlar
    public void setRoom_pension_type(PENSION room_pension_type) {
        this.room_pension_type = room_pension_type;
    }

    // Oda yetişkin fiyatını döndürür
    public double getRoom_price_adult() {
        return room_price_adult;
    }

    // Oda yetişkin fiyatını ayarlar
    public void setRoom_price_adult(double room_price_adult) {
        this.room_price_adult = room_price_adult;
    }

    // Oda çocuk fiyatını döndürür
    public double getRoom_price_child() {
        return room_price_child;
    }

    // Oda çocuk fiyatını ayarlar
    public void setRoom_price_child(double room_price_child) {
        this.room_price_child = room_price_child;
    }

    // Oda stok sayısını döndürür
    public int getRoom_stock() {
        return room_stock;
    }

    // Oda stok sayısını ayarlar
    public void setRoom_stock(int room_stock) {
        this.room_stock = room_stock;
    }

    // Oda yatak sayısını döndürür
    public int getRoom_bed_count() {
        return room_bed_count;
    }

    // Oda yatak sayısını ayarlar
    public void setRoom_bed_count(int room_bed_count) {
        this.room_bed_count = room_bed_count;
    }

    // Oda boyutunu döndürür
    public int getRoom_size() {
        return room_size;
    }

    // Oda boyutunu ayarlar
    public void setRoom_size(int room_size) {
        this.room_size = room_size;
    }

    // Oda televizyon olup olmadığını döndürür
    public boolean isRoom_has_tv() {
        return room_has_tv;
    }

    // Oda televizyon olup olmadığını ayarlar
    public void setRoom_has_tv(boolean room_has_tv) {
        this.room_has_tv = room_has_tv;
    }

    // Oda minibar olup olmadığını döndürür
    public boolean isRoom_has_minibar() {
        return room_has_minibar;
    }

    // Oda minibar olup olmadığını ayarlar
    public void setRoom_has_minibar(boolean room_has_minibar) {
        this.room_has_minibar = room_has_minibar;
    }

    // Oda oyun konsolu olup olmadığını döndürür
    public boolean isRoom_has_console() {
        return room_has_console;
    }

    // Oda oyun konsolu olup olmadığını ayarlar
    public void setRoom_has_console(boolean room_has_console) {
        this.room_has_console = room_has_console;
    }

    // Oda kasa olup olmadığını döndürür
    public boolean isRoom_has_safe() {
        return room_has_safe;
    }

    // Oda kasa olup olmadığını ayarlar
    public void setRoom_has_safe(boolean room_has_safe) {
        this.room_has_safe = room_has_safe;
    }

    // Oda projektör olup olmadığını döndürür
    public boolean isRoom_has_projector() {
        return room_has_projector;
    }

    // Oda projektör olup olmadığını ayarlar
    public void setRoom_has_projector(boolean room_has_projector) {
        this.room_has_projector = room_has_projector;
    }

    // Oda detaylarını String olarak döndürür
    @Override
    public String toString() {
        return new StringJoiner(", ", Room.class.getSimpleName() + "[", "]")
                .add("room_id=" + room_id)
                .add("room_hotel_id=" + room_hotel_id)
                .add("room_type=" + room_type)
                .add("room_season=" + room_season)
                .add("room_pension_type=" + room_pension_type)
                .add("room_price_adult=" + room_price_adult)
                .add("room_price_child=" + room_price_child)
                .add("room_stock=" + room_stock)
                .add("room_bed_count=" + room_bed_count)
                .add("room_size=" + room_size)
                .add("room_has_tv=" + room_has_tv)
                .add("room_has_minibar=" + room_has_minibar)
                .add("room_has_console=" + room_has_console)
                .add("room_has_safe=" + room_has_safe)
                .add("room_has_projector=" + room_has_projector)
                .toString();
    }

    // Oda türleri
    public enum TYPE {
        SINGLE_ROOM,
        DOUBLE_ROOM,
        JUNIOR_SUITE_ROOM,
        SUITE_ROOM
    }

    // Pansiyon türleri
    public enum PENSION {
        ULTRA_HER_SEY_DAHIL,
        HER_SEY_DAHIL,
        ODA_KAHVALTI,
        TAM_PANSIYON,
        YARIM_PANSIYON,
        SADECE_YATAK,
        ALKOL_HARIC_FULL_CREDIT
    }

    // Sezon türleri
    public enum SEASON {
        WINTER("01/01/2021 - 31/05/2021"),
        SUMMER("01/06/2021 - 01/12/2021");

        private final String dateRange;

        SEASON(String dateRange) {
            this.dateRange = dateRange;
        }

        public String getDateRange() {
            return dateRange;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", "[", "]")
                    .add("'" + dateRange + "'")
                    .toString();
        }
    }
}