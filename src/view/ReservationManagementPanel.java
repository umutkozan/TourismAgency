package view;

import business.ReservationManager;
import business.HotelManager;
import business.RoomManager;
import core.Helper;
import entity.Hotel;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationManagementPanel extends JPanel {
    private JTable reservationTable;
    private DefaultTableModel reservationTableModel;
    private JButton addReservationButton;
    private JButton editReservationButton;
    private JButton deleteReservationButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private ReservationManager reservationManager;
    private HotelManager hotelManager;
    private RoomManager roomManager;

    public ReservationManagementPanel() {
        reservationManager = new ReservationManager();
        hotelManager = new HotelManager();
        roomManager = new RoomManager();
        initializeUI();
        loadReservationTable();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        // Search Panel
        JPanel searchPanel = new JPanel();
        searchTextField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search:"));
        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        // Table
        reservationTableModel = new DefaultTableModel();
        reservationTable = new JTable(reservationTableModel);
        reservationTableModel.addColumn("Reservation ID");
        reservationTableModel.addColumn("Room ID");
        reservationTableModel.addColumn("Customer Name");
        reservationTableModel.addColumn("Total Price");
        reservationTableModel.addColumn("Adult Count");
        reservationTableModel.addColumn("Child Count");
        reservationTableModel.addColumn("Customer Email");
        reservationTableModel.addColumn("Customer TC");
        reservationTableModel.addColumn("Customer Note");

        JScrollPane tableScrollPane = new JScrollPane(reservationTable);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addReservationButton = new JButton("New Reservation");
        editReservationButton = new JButton("Edit Reservation");
        deleteReservationButton = new JButton("Delete Reservation");

        buttonPanel.add(addReservationButton);
        buttonPanel.add(editReservationButton);
        buttonPanel.add(deleteReservationButton);

        // Adding components to the panel
        add(searchPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Adding action listeners
        addReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open reservation view for new reservation
                openReservationView(null);
            }
        });

        editReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open reservation view for editing the selected reservation
                int selectedRow = reservationTable.getSelectedRow();
                if (selectedRow != -1) {
                    int reservationId = (int) reservationTableModel.getValueAt(selectedRow, 0);
                    Reservation reservation = reservationManager.getReservationById(reservationId);
                    openReservationView(reservation);
                } else {
                    Helper.showMsg("Please select the reservation you want to edit.");
                }
            }
        });

        deleteReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Delete the selected reservation
                int selectedRow = reservationTable.getSelectedRow();
                if (selectedRow != -1) {
                    int reservationId = (int) reservationTableModel.getValueAt(selectedRow, 0);
                    reservationManager.deleteReservation(reservationId);
                    loadReservationTable();
                } else {
                    Helper.showMsg("Please select the reservation you want to delete.");
                }
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Search for reservations
                String keyword = searchTextField.getText();
                loadReservationTable(keyword);
            }
        });
    }

    private void loadReservationTable() {
        loadReservationTable(null);
    }

    private void loadReservationTable(String keyword) {
        ArrayList<Reservation> reservations;
        if (keyword == null || keyword.isEmpty()) {
            reservations = reservationManager.findAll();
        } else {
            // Implement search functionality
            reservations = reservationManager.search(keyword);
        }

        reservationTableModel.setRowCount(0);
        for (Reservation reservation : reservations) {
            Object[] rowData = new Object[]{
                    reservation.getReservation_id(),
                    reservation.getReservation_room_id(),
                    reservation.getReservation_customer_name(),
                    reservation.getReservation_check_in_date(),
                    reservation.getReservation_check_out_date(),
                    reservation.getReservation_total_price(),
                    reservation.getReservation_guest_count_adult(),
                    reservation.getReservation_guest_count_child(),
                    reservation.getReservation_customer_email(),
                    reservation.getReservation_customer_tc(),
                    reservation.getReservation_customer_note()
            };
            reservationTableModel.addRow(rowData);
        }
    }

    private void openReservationView(Reservation reservation) {
        JFrame frame = new JFrame("Reservation View");

        // If updating an existing reservation, populate the view with existing data
        if (reservation != null) {
            Room selectedRoom = roomManager.getRoomById(reservation.getReservation_room_id());
            Hotel selectedHotel = hotelManager.getHotelById(selectedRoom.getRoom_hotel_id());
            LocalDate startDate = convertToLocalDateViaSqlDate(reservation.getReservation_check_in_date());
            LocalDate endDate = convertToLocalDateViaSqlDate(reservation.getReservation_check_out_date());
            ReservationUI reservationUI = new ReservationUI(frame, selectedHotel, selectedRoom, startDate, endDate, (Runnable) reservation);
            frame.add(reservationUI);
        } else {
            // For new reservations, open a blank reservation view
            ReservationUI reservationUI = new ReservationUI(frame, new Hotel(), new Room(), LocalDate.now(), LocalDate.now().plusDays(1), null);
            frame.add(reservationUI);
        }

        frame.setSize(600, 700);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    // Convert SQL Date to LocalDate
    private LocalDate convertToLocalDateViaSqlDate(java.sql.Date dateToConvert) {
        return dateToConvert.toLocalDate();
    }
}
