package entity;

import java.util.Date;

public class Reservation {

    // Rezervasyon özelliklerini temsil eden değişkenler
    private int reservation_id;
    private int reservation_room_id;
    private String reservation_customer_name;
    private String reservation_customer_contact;
    private Date reservation_check_in_date;
    private Date reservation_check_out_date;
    private double reservation_total_price;
    private int reservation_guest_count_adult;
    private int reservation_guest_count_child;
    private String reservation_customer_email;
    private long reservation_customer_tc;
    private String reservation_customer_note;

    // Boş constructor
    public Reservation() {
    }

    // Tüm özelliklerle rezervasyon nesnesini başlatan constructor
    public Reservation(int reservation_id, int reservation_room_id, String reservation_customer_name, String reservation_customer_contact,
                       Date reservation_check_in_date, Date reservation_check_out_date, double reservation_total_price, int reservation_guest_count_adult,
                       int reservation_guest_count_child, String reservation_customer_email, long reservation_customer_tc, String reservation_customer_note) {
        this.reservation_id = reservation_id;
        this.reservation_room_id = reservation_room_id;
        this.reservation_customer_name = reservation_customer_name;
        this.reservation_customer_contact = reservation_customer_contact;
        this.reservation_check_in_date = reservation_check_in_date;
        this.reservation_check_out_date = reservation_check_out_date;
        this.reservation_total_price = reservation_total_price;
        this.reservation_guest_count_adult = reservation_guest_count_adult;
        this.reservation_guest_count_child = reservation_guest_count_child;
        this.reservation_customer_email = reservation_customer_email;
        this.reservation_customer_tc = reservation_customer_tc;
        this.reservation_customer_note = reservation_customer_note;
    }

    // Rezervasyon ID'sini döndürür
    public int getReservation_id() {
        return reservation_id;
    }

    // Rezervasyon ID'sini ayarlar
    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    // Rezervasyon oda ID'sini döndürür
    public int getReservation_room_id() {
        return reservation_room_id;
    }

    // Rezervasyon oda ID'sini ayarlar
    public void setReservation_room_id(int reservation_room_id) {
        this.reservation_room_id = reservation_room_id;
    }

    // Rezervasyon müşteri adını döndürür
    public String getReservation_customer_name() {
        return reservation_customer_name;
    }

    // Rezervasyon müşteri adını ayarlar
    public void setReservation_customer_name(String reservation_customer_name) {
        this.reservation_customer_name = reservation_customer_name;
    }

    // Rezervasyon müşteri iletişim bilgisini döndürür
    public String getReservation_customer_contact() {
        return reservation_customer_contact;
    }

    // Rezervasyon müşteri iletişim bilgisini ayarlar
    public void setReservation_customer_contact(String reservation_customer_contact) {
        this.reservation_customer_contact = reservation_customer_contact;
    }

    // Rezervasyon giriş tarihini döndürür
    public java.sql.Date getReservation_check_in_date() {
        return (java.sql.Date) reservation_check_in_date;
    }

    // Rezervasyon giriş tarihini ayarlar
    public void setReservation_check_in_date(Date reservation_check_in_date) {
        this.reservation_check_in_date = reservation_check_in_date;
    }

    // Rezervasyon çıkış tarihini döndürür
    public java.sql.Date getReservation_check_out_date() {
        return (java.sql.Date) reservation_check_out_date;
    }

    // Rezervasyon çıkış tarihini ayarlar
    public void setReservation_check_out_date(Date reservation_check_out_date) {
        this.reservation_check_out_date = reservation_check_out_date;
    }

    // Rezervasyon toplam fiyatını döndürür
    public double getReservation_total_price() {
        return reservation_total_price;
    }

    // Rezervasyon toplam fiyatını ayarlar
    public void setReservation_total_price(double reservation_total_price) {
        this.reservation_total_price = reservation_total_price;
    }

    // Rezervasyon yetişkin misafir sayısını döndürür
    public int getReservation_guest_count_adult() {
        return reservation_guest_count_adult;
    }

    // Rezervasyon yetişkin misafir sayısını ayarlar
    public void setReservation_guest_count_adult(int reservation_guest_count_adult) {
        this.reservation_guest_count_adult = reservation_guest_count_adult;
    }

    // Rezervasyon çocuk misafir sayısını döndürür
    public int getReservation_guest_count_child() {
        return reservation_guest_count_child;
    }

    // Rezervasyon çocuk misafir sayısını ayarlar
    public void setReservation_guest_count_child(int reservation_guest_count_child) {
        this.reservation_guest_count_child = reservation_guest_count_child;
    }

    // Rezervasyon müşteri e-posta adresini döndürür
    public String getReservation_customer_email() {
        return reservation_customer_email;
    }

    // Rezervasyon müşteri e-posta adresini ayarlar
    public void setReservation_customer_email(String reservation_customer_email) {
        this.reservation_customer_email = reservation_customer_email;
    }

    // Rezervasyon müşteri T.C. kimlik numarasını döndürür
    public long getReservation_customer_tc() {
        return reservation_customer_tc;
    }

    // Rezervasyon müşteri T.C. kimlik numarasını ayarlar
    public void setReservation_customer_tc(long reservation_customer_tc) {
        this.reservation_customer_tc = reservation_customer_tc;
    }

    // Rezervasyon müşteri notunu döndürür
    public String getReservation_customer_note() {
        return reservation_customer_note;
    }

    // Rezervasyon müşteri notunu ayarlar
    public void setReservation_customer_note(String reservation_customer_note) {
        this.reservation_customer_note = reservation_customer_note;
    }

}