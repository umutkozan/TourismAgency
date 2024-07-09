package view;

import business.HotelManager;
import business.ReservationManager;
import business.RoomManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class EmployeeUI extends Layout {
    private Users user;
    private JTabbedPane tabbedPane;
    private JPanel container;
    private JTabbedPane tab_menu;
    private JTable tbl_hotel;
    private JButton btn_exit;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_city;
    private JTextField fld_hotel_email;
    private JTextField fld_region;
    private JTextField fld_hotel_phone;
    private JTextField fld_stars;
    private JButton btn_update;
    private JButton btn_add;
    private JButton btn_reset;
    private JPanel pnl_right_bottom;
    private JPanel pnl_left_bottom;
    private JButton btn_delete;
    private JCheckBox chck_ultra;
    private JCheckBox chck_hsd;
    private JCheckBox chck_yp;
    private JCheckBox chck_just_bed;
    private JCheckBox chck_room_breakfast;
    private JCheckBox chck_ahfc;
    private JCheckBox chck_tp;
    private JCheckBox chck_free_park;
    private JCheckBox chck_free_wifi;
    private JCheckBox chck_pool;
    private JCheckBox chck_gym;
    private JCheckBox chck_hotel_concierge;
    private JCheckBox chck_spa;
    private JCheckBox chck_room_service;
    private JPanel pnl_chck_pension_type;
    private JPanel pnl_chck_facility;
    private JPanel pnl_btn_update_save_clear;
    private JPanel pnl_room;
    private JScrollPane fld_hotel;
    private JPanel pnl_hotel;
    private JTable tbl_room;
    private JScrollPane scrl_room;
    private JPanel pnl_bottom;
    private JComboBox<Room.TYPE> cmb_room_type;
    private JComboBox<Room.SEASON> cmb_room_season_type;
    private JCheckBox chck_tv_yes;
    private JCheckBox chck_tv_no;
    private JCheckBox chck_console_yes;
    private JCheckBox chck_console_no;
    private JCheckBox chck_projecsion_yes;
    private JCheckBox chck_projecsion_no;
    private JTextField fld_room_stock;
    private JTextField fld_child_price;
    private JTextField fld_room_metrekare;
    private JCheckBox chck_room_bar_yes;
    private JCheckBox chck_room_bar_no;
    private JCheckBox chck_room_kasa_yes;
    private JCheckBox chck_room_kasa_no;
    private JTextField fld_adult_price;
    private JTextField fld_room_bed_count;
    private JButton btn_room_add;
    private JButton btn_room_update;
    private JButton btn_room_delete;
    private JButton btn_room_clear;
    private JComboBox cmb_room_search_otel_name;
    private JComboBox cmb_room_search_otel_city;
    private JPanel pnl_tv_chck;
    private JPanel pnl_console_chck;
    private JPanel pnl_projecsion_chck;
    private JPanel pnl_room_bar_chck;
    private JPanel pnl_room_kasa_chck;
    private JPanel pnl_room_button;
    private JPanel pnl_room_search;
    private JButton btn_room_search;
    private JComboBox<Room.PENSION> cmb_pension_type;
    private JComboBox<Hotel> cmb_hotel_room_add;
    private JFormattedTextField fld_strt_date;
    private JFormattedTextField fld_fnsh_date;
    private JButton btn_room_search_clear;
    private JTable tbl_reservation;
    private JPanel pnl_reservation;
    private JScrollPane scrl_reservation;
    private JButton rezervasyonHesaplamaAraciButton;
    private JButton btnOpenReservationUI;
    private DefaultTableModel tmdl_hotel = new DefaultTableModel();
    private DefaultTableModel tmdl_room = new DefaultTableModel();
    private DefaultTableModel tmdl_reservation = new DefaultTableModel();
    private Object[] col_hotel;
    private Object[] col_room;
    private Object[] col_reservation;
    private HotelManager hotelManager;
    private RoomManager roomManager;
    private ReservationManager reservationManager;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;

    private boolean isProcessing = false;

    // EmployeeUI yapıcı metodu
    public EmployeeUI(Users user) {
        this.user = user;
        this.hotelManager = new HotelManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.add(container);
        initilizeGui(1200, 800);
        this.setTitle("Turizm Acentesi");

        // Bileşenleri başlat ve verileri yükle
        loadComponent();
        loadRoomTable(null);
        loadHotelTable(null);
        initializeRoomComponent();
        loadHotelComponent();
        loadRoomComponent();

        // Tablolar için seçim dinleyicileri ayarla
        tableHotelRowSelected(tbl_hotel, this::loadHotelComponent);
        tableRoomRowSelected(tbl_room, this::loadRoomComponent);

        clearRoomForm();
        clearRoomSearchForm();
        loadRoomReservationAddMenu();
        loadReservationTableMenu();

        loadReservationTable(null);

        // Rezervasyon Hesaplama Aracı Butonuna İşlev Ekleme
        rezervasyonHesaplamaAraciButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReservationUI(null, null, LocalDate.now(), LocalDate.now().plusDays(1), null);
            }
        });

        // Yeni butonu başlat ve panele ekle
        btnOpenReservationUI = new JButton("Rezervasyon Hesaplama Aracı");
        pnl_bottom.add(btnOpenReservationUI); // Butonu uygun bir panele ekleyin

        // Butona tıklama eylemi ekleyin
        btnOpenReservationUI.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReservationUI();
            }
        });
    }

    // Rezervasyon tablosunu verilerle yükle
    public void loadReservationTable(ArrayList<Object[]> reservationList) {
        this.col_reservation = new Object[]{"Reservation ID", "Reservation Room ID", "Reservation Customer Name", "Reservation Start Date", "Reservation Finish Date",
                "Reservation Total Price", "Reservation Adult Count", "Reservation Child Count", "Reservation Customer Email", "Reservation Customer T.C.", "Reservation Customer Note"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(this.col_reservation.length, this.reservationManager.findAll());
        }
        this.createTable(this.tmdl_reservation, this.tbl_reservation, col_reservation, reservationList);
    }

    // Rezervasyon tablosu bağlam menüsünü yükle
    public void loadReservationTableMenu() {
        this.reservation_menu = new JPopupMenu();

        // Rezervasyon güncelleme menü öğesi ekle
        this.reservation_menu.add("Rezervasyon Güncelle").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            Reservation selectedReservation = reservationManager.getReservationById(selectReservationId);

            if (selectedReservation == null) {
                Helper.showMsg("error");
                return;
            }

            Room selectedRoom = roomManager.getRoomById(selectedReservation.getReservation_room_id());
            if (selectedRoom == null) {
                Helper.showMsg("error");
                return;
            }

            Hotel selectedHotel = hotelManager.getHotelById(selectedRoom.getRoom_hotel_id());
            if (selectedHotel == null) {
                Helper.showMsg("error");
                return;
            }

            LocalDate startDate = convertToLocalDateViaSqlDate(selectedReservation.getReservation_check_in_date());
            LocalDate endDate = convertToLocalDateViaSqlDate(selectedReservation.getReservation_check_out_date());

            openReservationUI(selectedHotel, selectedRoom, startDate, endDate, selectedReservation);
        });

        // Rezervasyon silme menü öğesi ekle
        this.reservation_menu.add("Rezervasyon Sil").addActionListener(e -> {
            int selectReservationId = this.getTableSelectedRow(tbl_reservation, 0);
            Reservation selectedReservation = reservationManager.getReservationById(selectReservationId);

            if (selectedReservation == null) {
                Helper.showMsg("error");
                return;
            }

            Room selectedRoom = roomManager.getRoomById(selectedReservation.getReservation_room_id());
            if (selectedRoom == null) {
                Helper.showMsg("error");
                return;
            }

            if (Helper.confirm("sure")) {
                reservationManager.deleteReservation(selectReservationId);

                // Oda stok sayısını güncelle
                int newStock = selectedRoom.getRoom_stock() + 1;
                reservationManager.updateRoomStock(selectedRoom.getRoom_id(), newStock);

                loadReservationTable(null);
                loadRoomTable(null);
            }
        });

        this.tbl_reservation.setComponentPopupMenu(reservation_menu);
        tableRowSelected(this.tbl_reservation, reservation_menu);
    }

    // Tarihi yerel tarihe dönüştürme
    private LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }

    // Oda tablosunu verilerle yükle
    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"Room ID", "Room Hotel ID","Room Season", "Room Pansiyon Type ",  "Room Type", "Room Price Adult", "Room Price Child", "Room Stock", "Room Bed Count",
                "Room Metrekare", "Room Has TV", "Room Has Minibar", "Room Has Console", "Room Has Safe", "Room Has Projector"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(this.col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.tmdl_room, this.tbl_room, col_room, roomList);
    }

    // Oda bileşenlerini başlatma
    private void initializeRoomComponent() {
        tbl_room.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadRoomComponent();
                }
            }
        });

        initializeSeasonComboBox();
        initializeRoomTypeComboBox();
        initializeHotelComboBox();
        initializePensionTypeComboBox();

        btn_room_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoom();
                clearRoomForm();
            }
        });

        btn_room_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateRoom();
                clearRoomForm();
            }
        });

        btn_room_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRoom();
                clearRoomForm();
            }
        });

        btn_room_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRoomForm();
            }
        });

        btn_room_search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                roomSearch();
            }
        });

        btn_room_search_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearRoomSearchForm();
                loadRoomTable(null);
            }
        });

        // Checkbox Listenerları
        chck_tv_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_tv_yes.isSelected()) {
                    chck_tv_no.setSelected(false);
                }
            }
        });

        chck_tv_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_tv_no.isSelected()) {
                    chck_tv_yes.setSelected(false);
                }
            }
        });

        chck_room_bar_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_room_bar_yes.isSelected()) {
                    chck_room_bar_no.setSelected(false);
                }
            }
        });

        chck_room_bar_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_room_bar_no.isSelected()) {
                    chck_room_bar_yes.setSelected(false);
                }
            }
        });

        chck_console_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_console_yes.isSelected()) {
                    chck_console_no.setSelected(false);
                }
            }
        });

        chck_console_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_console_no.isSelected()) {
                    chck_console_yes.setSelected(false);
                }
            }
        });

        chck_room_kasa_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_room_kasa_yes.isSelected()) {
                    chck_room_kasa_no.setSelected(false);
                }
            }
        });

        chck_room_kasa_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_room_kasa_no.isSelected()) {
                    chck_room_kasa_yes.setSelected(false);
                }
            }
        });

        chck_projecsion_yes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_projecsion_yes.isSelected()) {
                    chck_projecsion_no.setSelected(false);
                }
            }
        });

        chck_projecsion_no.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chck_projecsion_no.isSelected()) {
                    chck_projecsion_yes.setSelected(false);
                }
            }
        });
    }

    // Oda arama işlemi
    private void roomSearch() {
        String startDate = fld_strt_date.getText();
        String endDate = fld_fnsh_date.getText();

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate startLocalDate = LocalDate.parse(startDate, inputFormatter);
            LocalDate endLocalDate = LocalDate.parse(endDate, inputFormatter);

            String formattedStartDate = startLocalDate.format(outputFormatter);
            String formattedEndDate = endLocalDate.format(outputFormatter);

            Hotel selectedHotel = (Hotel) cmb_room_search_otel_name.getSelectedItem();
            String hotelName = selectedHotel != null ? selectedHotel.getHotel_name() : null;

            String city = (String) cmb_room_search_otel_city.getSelectedItem();

            ArrayList<Room> rooms = roomManager.searchRooms(formattedStartDate, formattedEndDate, city, hotelName);
            ArrayList<Object[]> roomData = roomManager.getForTable(col_room.length, rooms);
            loadRoomTable(roomData);
        } catch (DateTimeParseException dtpe) {
            dtpe.printStackTrace();
            Helper.showMsg("error");
        }
    }

    // Oda bileşenlerini yükleme
    private void loadRoomComponent() {
        int selectedRow = tbl_room.getSelectedRow();
        if (selectedRow != -1) {
            fld_room_bed_count.setText(tbl_room.getValueAt(selectedRow, 8).toString());
            fld_room_metrekare.setText(tbl_room.getValueAt(selectedRow, 9).toString());
            fld_adult_price.setText(tbl_room.getValueAt(selectedRow, 5).toString());
            fld_child_price.setText(tbl_room.getValueAt(selectedRow, 6).toString());
            fld_room_stock.setText(tbl_room.getValueAt(selectedRow, 7).toString());

            chck_tv_yes.setSelected((Boolean) tbl_room.getValueAt(selectedRow, 10));
            chck_tv_no.setSelected(!(Boolean) tbl_room.getValueAt(selectedRow, 10));

            chck_room_bar_yes.setSelected((Boolean) tbl_room.getValueAt(selectedRow, 11));
            chck_room_bar_no.setSelected(!(Boolean) tbl_room.getValueAt(selectedRow, 11));

            chck_console_yes.setSelected((Boolean) tbl_room.getValueAt(selectedRow, 12));
            chck_console_no.setSelected(!(Boolean) tbl_room.getValueAt(selectedRow, 12));

            chck_room_kasa_yes.setSelected((Boolean) tbl_room.getValueAt(selectedRow, 13));
            chck_room_kasa_no.setSelected(!(Boolean) tbl_room.getValueAt(selectedRow, 13));

            chck_projecsion_yes.setSelected((Boolean) tbl_room.getValueAt(selectedRow, 14));
            chck_projecsion_no.setSelected(!(Boolean) tbl_room.getValueAt(selectedRow, 14));

            cmb_room_type.setSelectedItem(Room.TYPE.valueOf(tbl_room.getValueAt(selectedRow, 4).toString()));
            cmb_room_season_type.setSelectedItem(Room.SEASON.valueOf(tbl_room.getValueAt(selectedRow, 2).toString()));
            cmb_pension_type.setSelectedItem(Room.PENSION.valueOf(tbl_room.getValueAt(selectedRow, 3).toString()));

            int hotelId = (int) tbl_room.getValueAt(selectedRow, 1);
            for (int i = 0; i < cmb_hotel_room_add.getItemCount(); i++) {
                Hotel hotel = (Hotel) cmb_hotel_room_add.getItemAt(i);
                if (hotel.getHotel_id() == hotelId) {
                    cmb_hotel_room_add.setSelectedItem(hotel);
                    break;
                }
            }
        }
    }

    // Oda rezervasyon ekleme menüsünü yükleme
    public void loadRoomReservationAddMenu() {
        this.room_menu = new JPopupMenu();
        this.room_menu.add("Rezervasyon Ekle").addActionListener(e -> {
            int selectRoomId = this.getTableSelectedRow(tbl_room, 0);
            Room selectedRoom = roomManager.getRoomById(selectRoomId);

            if (selectedRoom == null) {
                Helper.showMsg("error");
                return;
            }

            Hotel selectedHotel = hotelManager.getHotelById(selectedRoom.getRoom_hotel_id());

            if (selectedHotel == null) {
                Helper.showMsg("error");
                return;
            }

            String startDateStr = fld_strt_date.getText();
            String endDateStr = fld_fnsh_date.getText();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                LocalDate startDate = LocalDate.parse(startDateStr.trim(), formatter);
                LocalDate endDate = LocalDate.parse(endDateStr.trim(), formatter);

                openReservationUI(selectedHotel, selectedRoom, startDate, endDate, null);
            } catch (DateTimeParseException dtpe) {
                Helper.showMsg("Tarih formatınız bu şekilde olsun =  (dd/MM/yyyy).");
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);
        tableRowSelected(this.tbl_room, room_menu);
    }

    // Sezon türü combo box'ını başlatma
    private void initializeSeasonComboBox() {
        cmb_room_season_type.removeAllItems();
        ArrayList<Room.SEASON> seasons = roomManager.getAllSeasons();
        for (Room.SEASON season : seasons) {
            cmb_room_season_type.addItem(season);
        }
    }

    // Oda türü combo box'ını başlatma
    private void initializeRoomTypeComboBox() {
        cmb_room_type.removeAllItems();
        for (Room.TYPE type : Room.TYPE.values()) {
            cmb_room_type.addItem(type);
        }
    }

    // Otel combo box'ını başlatma
    private void initializeHotelComboBox() {
        cmb_hotel_room_add.removeAllItems();
        cmb_room_search_otel_name.removeAllItems();
        ArrayList<Hotel> hotels = roomManager.getAllHotels();
        for (Hotel hotel : hotels) {
            cmb_hotel_room_add.addItem(hotel);
            cmb_room_search_otel_name.addItem(hotel);
        }

        cmb_room_search_otel_name.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    Hotel selectedHotel = (Hotel) cmb_room_search_otel_name.getSelectedItem();
                    if (selectedHotel != null) {
                        initializeHotelCityComboBox(selectedHotel.getHotel_id());
                    }
                }
            }
        });
    }

    // Otelin bulunduğu şehirleri combo box'a ekleme
    private void initializeHotelCityComboBox(int hotelId) {
        cmb_room_search_otel_city.removeAllItems();
        ArrayList<String> cities = roomManager.getHotelCities(hotelId);
        for (String city : cities) {
            cmb_room_search_otel_city.addItem(city);
        }
    }

    // Pansiyon türü combo box'ını başlatma
    private void initializePensionTypeComboBox() {
        cmb_pension_type.removeAllItems();
        ArrayList<Room.PENSION> pensions = roomManager.getAllPensionTypes();
        for (Room.PENSION pension : pensions) {
            cmb_pension_type.addItem(pension);
        }
    }

    // Yeni oda ekleme
    private void addRoom() {
        Room.SEASON room_season = (Room.SEASON) cmb_room_season_type.getSelectedItem();
        Room.TYPE room_type = (Room.TYPE) cmb_room_type.getSelectedItem();
        Room.PENSION room_pension = (Room.PENSION) cmb_pension_type.getSelectedItem();
        double room_price_adult = Double.parseDouble(fld_adult_price.getText());
        double room_price_child = Double.parseDouble(fld_child_price.getText());
        int room_stock = Integer.parseInt(fld_room_stock.getText());
        int room_bed_count = Integer.parseInt(fld_room_bed_count.getText());
        int room_size = Integer.parseInt(fld_room_metrekare.getText());
        boolean room_has_tv = chck_tv_yes.isSelected();
        boolean room_has_minibar = chck_room_bar_yes.isSelected();
        boolean room_has_console = chck_console_yes.isSelected();
        boolean room_has_safe = chck_room_kasa_yes.isSelected();
        boolean room_has_projector = chck_projecsion_yes.isSelected();

        Hotel selectedHotel = (Hotel) cmb_hotel_room_add.getSelectedItem();
        int hotel_id = selectedHotel.getHotel_id();

        Room room = new Room(0, hotel_id, room_type, room_season, room_pension,
                room_price_adult, room_price_child, room_stock, room_bed_count,
                room_size, room_has_tv, room_has_minibar, room_has_console,
                room_has_safe, room_has_projector);

        boolean success = roomManager.addRoom(room);

        if (success) {
            loadRoomTable(null);
            Helper.showMsg("done");
            clearRoomForm();
        } else {
            Helper.showMsg("error");
        }
    }

    // Oda güncelleme
    private void updateRoom() {
        int selectedRow = tbl_room.getSelectedRow();
        if (selectedRow != -1) {
            int roomId = Integer.parseInt(tbl_room.getValueAt(selectedRow, 0).toString());
            Room room = roomManager.getRoomById(roomId);
            room.setRoom_season((Room.SEASON) cmb_room_season_type.getSelectedItem());
            room.setRoom_type((Room.TYPE) cmb_room_type.getSelectedItem());
            room.setRoom_pension_type((Room.PENSION) cmb_pension_type.getSelectedItem());
            room.setRoom_price_adult(Double.parseDouble(fld_adult_price.getText()));
            room.setRoom_price_child(Double.parseDouble(fld_child_price.getText()));
            room.setRoom_stock(Integer.parseInt(fld_room_stock.getText()));
            room.setRoom_bed_count(Integer.parseInt(fld_room_bed_count.getText()));
            room.setRoom_size(Integer.parseInt(fld_room_metrekare.getText()));
            room.setRoom_has_tv(chck_tv_yes.isSelected());
            room.setRoom_has_minibar(chck_room_bar_yes.isSelected());
            room.setRoom_has_console(chck_console_yes.isSelected());
            room.setRoom_has_safe(chck_room_kasa_yes.isSelected());
            room.setRoom_has_projector(chck_projecsion_yes.isSelected());

            Hotel selectedHotel = (Hotel) cmb_hotel_room_add.getSelectedItem();
            int hotel_id = selectedHotel.getHotel_id();
            room.setRoom_hotel_id(hotel_id);

            boolean success = roomManager.updateRoom(room);

            if (success) {
                loadRoomTable(null);
                Helper.showMsg("done");
                clearRoomForm();
            } else {
                Helper.showMsg("error");
            }
        }
    }

    // Oda silme
    private void deleteRoom() {
        int selectedRow = tbl_room.getSelectedRow();
        if (selectedRow != -1 && Helper.confirm("Emin misiniz?")) {
            int roomId = (int) tbl_room.getValueAt(selectedRow, 0);
            roomManager.deleteRoom(roomId);
            loadRoomTable(null);
            clearRoomForm();
            Helper.showMsg("done");
        }
    }

    // Oda formunu temizleme
    private void clearRoomForm() {
        fld_room_bed_count.setText("");
        fld_room_metrekare.setText("");
        fld_adult_price.setText("");
        fld_child_price.setText("");
        fld_room_stock.setText("");
        chck_tv_yes.setSelected(false);
        chck_tv_no.setSelected(false);
        chck_room_bar_yes.setSelected(false);
        chck_room_bar_no.setSelected(false);
        chck_console_yes.setSelected(false);
        chck_console_no.setSelected(false);
        chck_room_kasa_yes.setSelected(false);
        chck_room_kasa_no.setSelected(false);
        chck_projecsion_yes.setSelected(false);
        chck_projecsion_no.setSelected(false);
        cmb_room_type.setSelectedItem(null);
        cmb_room_season_type.setSelectedItem(null);
        cmb_hotel_room_add.setSelectedItem(null);
        cmb_pension_type.setSelectedItem(null);
    }

    // Oda arama formunu temizleme
    private void clearRoomSearchForm(){
        cmb_room_search_otel_name.setSelectedItem(null);
        cmb_room_search_otel_city.setSelectedItem(null);
    }

    // Genel bileşenleri yükleme
    private void loadComponent() {
        btn_exit.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                dispose();
                new LoginUI().setVisible(true);
            }
        });
    }

    // Otel tablosunu verilerle yükleme
    public void loadHotelTable(ArrayList<Object[]> hotelList) {
        this.col_hotel = new Object[]{"Hotel ID", "Hotel Name", "Address", "City", "Region", "Email", "Phone", "Stars", "Facilities", "Pension Types"};
        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(this.col_hotel.length, this.hotelManager.findAll());
        }

        this.createTable(this.tmdl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    // Otel bileşenlerini yükleme
    private void loadHotelComponent() {
        int selectedRow = tbl_hotel.getSelectedRow();
        if (selectedRow != -1) {
            fld_hotel_name.setText(tbl_hotel.getValueAt(selectedRow, 1).toString());
            fld_hotel_address.setText(tbl_hotel.getValueAt(selectedRow, 2).toString());
            fld_hotel_city.setText(tbl_hotel.getValueAt(selectedRow, 3).toString());
            fld_region.setText(tbl_hotel.getValueAt(selectedRow, 4).toString());
            fld_hotel_email.setText(tbl_hotel.getValueAt(selectedRow, 5).toString());
            fld_hotel_phone.setText(tbl_hotel.getValueAt(selectedRow, 6).toString());
            fld_stars.setText(tbl_hotel.getValueAt(selectedRow, 7).toString());

            // Tesis özelliklerini parse et
            String facilities = tbl_hotel.getValueAt(selectedRow, 8).toString();
            chck_free_park.setSelected(facilities.contains("Ücretsiz Park"));
            chck_free_wifi.setSelected(facilities.contains("Ücretsiz WiFi"));
            chck_pool.setSelected(facilities.contains("Yüzme Havuzu"));
            chck_gym.setSelected(facilities.contains("Fitness Center"));
            chck_hotel_concierge.setSelected(facilities.contains("Hotel Concierge"));
            chck_spa.setSelected(facilities.contains("SPA"));
            chck_room_service.setSelected(facilities.contains("7/24 Oda Servisi"));

            // Pansiyon türlerini parse et
            String pensionTypes = tbl_hotel.getValueAt(selectedRow, 9).toString();
            chck_ultra.setSelected(pensionTypes.contains("Ultra Her şey Dahil"));
            chck_hsd.setSelected(pensionTypes.contains("Her şey Dahil"));
            chck_room_breakfast.setSelected(pensionTypes.contains("Oda Kahvaltı"));
            chck_tp.setSelected(pensionTypes.contains("Tam Pansiyon"));
            chck_yp.setSelected(pensionTypes.contains("Yarım Pansiyon"));
            chck_just_bed.setSelected(pensionTypes.contains("Sadece Yatak"));
            chck_ahfc.setSelected(pensionTypes.contains("Alkol Hariç Full Credit"));
        }

        btn_update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHotel();
            }
        });

        for (ActionListener al : btn_add.getActionListeners()) {
            btn_add.removeActionListener(al);
        }

        btn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (!isProcessing) {
                        isProcessing = true;
                        try {
                            addHotel();
                        } finally {
                            isProcessing = false;
                        }
                    }
                });
            }
        });

        for (ActionListener al : btn_delete.getActionListeners()) {
            btn_delete.removeActionListener(al);
        }

        btn_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (!isProcessing) {
                        isProcessing = true;
                        try {
                            deleteHotel();
                        } finally {
                            isProcessing = false;
                        }
                    }
                });
            }
        });

        btn_reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearHotelForm();
            }
        });
    }

    // Otel güncelleme
    private void updateHotel() {
        int selectedRow = tbl_hotel.getSelectedRow();
        if (selectedRow != -1) {
            int hotelId = Integer.parseInt(tbl_hotel.getValueAt(selectedRow, 0).toString());
            Hotel hotel = hotelManager.getHotelById(hotelId);
            hotel.setHotel_name(fld_hotel_name.getText());
            hotel.setHotel_address(fld_hotel_address.getText());
            hotel.setHotel_city(fld_hotel_city.getText());
            hotel.setHotel_region(fld_region.getText());
            hotel.setHotel_email(fld_hotel_email.getText());
            hotel.setHotel_phone(fld_hotel_phone.getText());
            hotel.setHotel_stars(fld_stars.getText());

            Facility facility = hotel.getFacility();
            if (facility == null) {
                facility = new Facility();
            }
            facility.setFacility_free_park(chck_free_park.isSelected());
            facility.setFacility_free_wifi(chck_free_wifi.isSelected());
            facility.setFacility_pool(chck_pool.isSelected());
            facility.setFacility_gym(chck_gym.isSelected());
            facility.setFacility_concierge(chck_hotel_concierge.isSelected());
            facility.setFacility_SPA(chck_spa.isSelected());
            facility.setFacility_room_service(chck_room_service.isSelected());
            hotel.setFacility(facility);

            Pension pension = hotel.getPension();
            if (pension == null) {
                pension = new Pension();
            }
            pension.setPension_type_ultra(chck_ultra.isSelected());
            pension.setPension_type_hsd(chck_hsd.isSelected());
            pension.setPension_type_breakfast(chck_room_breakfast.isSelected());
            pension.setPension_type_tam(chck_tp.isSelected());
            pension.setPension_type_yarim(chck_yp.isSelected());
            pension.setPension_type_just_bed(chck_just_bed.isSelected());
            pension.setPension_type_ahfc(chck_ahfc.isSelected());
            hotel.setPension(pension);

            boolean success = hotelManager.updateHotel(hotel);
            if (success) {
                loadHotelTable(null);  // Veritabanından güncellenmiş verileri yükleyin
                Helper.showMsg("done");
                clearHotelForm();
                initializeHotelComboBox(); // ComboBox'u güncelle
                clearRoomSearchForm();
                clearRoomForm();
            } else {
                Helper.showMsg("error");
            }
        }
    }

    // Yeni otel ekleme
    private void addHotel() {
        String hotelName = fld_hotel_name.getText();
        String hotelAddress = fld_hotel_address.getText();
        String hotelCity = fld_hotel_city.getText();
        String hotelMail = fld_hotel_email.getText();
        String hotelRegion = fld_region.getText();
        String hotelPhone = fld_hotel_phone.getText();
        String hotelStar = fld_stars.getText();
        Facility facility = getSelectedFacilityType();
        Pension pension = getSelectedPensionType();

        Hotel newHotel = new Hotel(0, hotelName, hotelAddress, hotelCity, hotelRegion,
                hotelMail, hotelPhone, hotelStar, facility.getFacility_id(), pension.getPension_type_id(),
                facility, pension);

        boolean success = hotelManager.addHotel(newHotel);

        if (success) {
            loadHotelTable(null);
            Helper.showMsg("done");
            clearHotelForm();
            initializeHotelComboBox(); // ComboBox'u güncelle
            clearRoomSearchForm();
            clearRoomForm();
        } else {
            Helper.showMsg("error");
        }
    }

    // Otel silme
    private void deleteHotel() {
        int selectedRow = tbl_hotel.getSelectedRow();
        if (selectedRow != -1 && Helper.confirm("Emin misiniz?")) {
            int hotelId = (int) tbl_hotel.getValueAt(selectedRow, 0);
            hotelManager.deleteHotel(hotelId);
            loadHotelTable(null);
            clearHotelForm();
            initializeHotelComboBox(); // ComboBox'u güncelle
            clearRoomSearchForm();
            clearRoomForm();
            Helper.showMsg("done");
        }
    }

    // Otel formunu temizleme
    private void clearHotelForm() {
        fld_hotel_name.setText("");
        fld_hotel_address.setText("");
        fld_hotel_city.setText("");
        fld_region.setText("");
        fld_hotel_email.setText("");
        fld_hotel_phone.setText("");
        fld_stars.setText("");
        chck_free_park.setSelected(false);
        chck_free_wifi.setSelected(false);
        chck_pool.setSelected(false);
        chck_gym.setSelected(false);
        chck_hotel_concierge.setSelected(false);
        chck_spa.setSelected(false);
        chck_room_service.setSelected(false);
        chck_ultra.setSelected(false);
        chck_hsd.setSelected(false);
        chck_room_breakfast.setSelected(false);
        chck_tp.setSelected(false);
        chck_yp.setSelected(false);
        chck_just_bed.setSelected(false);
        chck_ahfc.setSelected(false);
    }

    // Seçilen tesis özelliklerini alma
    private Facility getSelectedFacilityType() {
        Facility facility = new Facility();
        facility.setFacility_free_park(chck_free_park.isSelected());
        facility.setFacility_free_wifi(chck_free_wifi.isSelected());
        facility.setFacility_pool(chck_pool.isSelected());
        facility.setFacility_gym(chck_gym.isSelected());
        facility.setFacility_concierge(chck_hotel_concierge.isSelected());
        facility.setFacility_SPA(chck_spa.isSelected());
        facility.setFacility_room_service(chck_room_service.isSelected());
        hotelManager.addFacility(facility); // Tesis bilgilerini veritabanına ekle
        return facility;
    }

    // Seçilen pansiyon türlerini alma
    private Pension getSelectedPensionType() {
        Pension pension = new Pension();
        pension.setPension_type_ultra(chck_ultra.isSelected());
        pension.setPension_type_hsd(chck_hsd.isSelected());
        pension.setPension_type_breakfast(chck_room_breakfast.isSelected());
        pension.setPension_type_tam(chck_tp.isSelected());
        pension.setPension_type_yarim(chck_yp.isSelected());
        pension.setPension_type_just_bed(chck_just_bed.isSelected());
        pension.setPension_type_ahfc(chck_ahfc.isSelected());
        hotelManager.addPension(pension); // Pansiyon türünü veritabanına ekle
        return pension;
    }

    // Rezervasyon UI formunu açan metot
    private void openReservationUI(Hotel selectedHotel, Room selectedRoom, LocalDate startDate, LocalDate endDate, Reservation existingReservation) {
        JFrame frame = new JFrame("Rezervasyon Görüntüle");
        ReservationUI reservationUI = new ReservationUI(frame, selectedHotel, selectedRoom, startDate, endDate, existingReservation, this::refreshReservationTable);
        frame.add(reservationUI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Rezervasyon UI formunu açan metot (buton için)
    private void openReservationUI() {
        Hotel selectedHotel = getSelectedHotel(); // Seçili oteli al
        Room selectedRoom = getSelectedRoom(); // Seçili odayı al
        if (selectedHotel == null || selectedRoom == null) {
            Helper.showMsg("Lütfen bir otel ve oda seçin.");
            return;
        }

        LocalDate startDate = LocalDate.now(); // Başlangıç tarihini bugünün tarihi olarak ayarla
        LocalDate endDate = startDate.plusDays(1); // Bitiş tarihini başlangıç tarihinden bir gün sonrasına ayarla

        JFrame frame = new JFrame("Rezervasyonları Görüntüle");
        ReservationUI reservationUI = new ReservationUI(frame, selectedHotel, selectedRoom, startDate, endDate, null, this::refreshReservationTable);
        frame.add(reservationUI);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Seçili oteli döndürür
    private Hotel getSelectedHotel() {
        int selectedRow = tbl_hotel.getSelectedRow();
        if (selectedRow != -1) {
            int hotelId = (int) tbl_hotel.getValueAt(selectedRow, 0);
            return hotelManager.getHotelById(hotelId);
        }
        return null;
    }

    // Seçili odayı döndürür
    private Room getSelectedRoom() {
        int selectedRow = tbl_room.getSelectedRow();
        if (selectedRow != -1) {
            int roomId = (int) tbl_room.getValueAt(selectedRow, 0);
            return roomManager.getRoomById(roomId);
        }
        return null;
    }

    // Rezervasyon tablosunu yenilemek için callback metodu
    private void refreshReservationTable() {
        loadReservationTable(null);
    }

    // UI bileşenlerini oluşturma
    private void createUIComponents() throws ParseException {
        this.fld_strt_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_strt_date.setText("01/01/2019");
        this.fld_fnsh_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_fnsh_date.setText("01/01/2025");
    }
}