/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableColumn;
import koneksi.koneksi;
import koneksi.chekout_transaksi;

/**
 *
 * @author ahnaf
 */
public class cTransaksi extends javax.swing.JFrame {

    private DefaultTableModel model = null;
    private DefaultTableModel model2 = null;
    private PreparedStatement stat;
    private ResultSet rs;
    koneksi k = new koneksi();

    /**
     * Creates new form cMakanan
     */
    public void refreshTable() {
        model = new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("ID Masakan");
        model.addColumn("Nama Masakan");
        model.addColumn("Harga");
        model.addColumn("Stok");
        tabel_makanan.setModel(model);

        try {
            this.stat = k.getCon().prepareStatement("SELECT *\n"
                    + "FROM makanan\n"
                    + "WHERE id_makanan NOT IN (SELECT DISTINCT id_makanan FROM keranjang)");
            this.rs = this.stat.executeQuery();
            int i = 1;
            while (rs.next()) {
                Object[] data = {
                    i++,
                    rs.getInt("id_makanan"),
                    rs.getString("nama_makanan"),
                    rs.getInt("harga"),
                    rs.getInt("stok"),};
                model.addRow(data);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        TableColumn idTransaksiColumn = tabel_makanan.getColumnModel().getColumn(1);
        idTransaksiColumn.setMinWidth(0);
        idTransaksiColumn.setMaxWidth(0);
        idTransaksiColumn.setWidth(0);
        idTransaksiColumn.setPreferredWidth(0);
    }

    public void refreshTable2() {
        model2 = new DefaultTableModel();
        model2.addColumn("No.");
        model2.addColumn("ID Keranjang");
        model2.addColumn("ID Makanan");
        model2.addColumn("Nama Makanan");
        model2.addColumn("QTY");
        model2.addColumn("Subtotal");
        tabel_transaksi.setModel(model2);

        try {
            this.stat = k.getCon().prepareStatement("SELECT\n"
                    + "    k.id_keranjang,\n"
                    + "    k.id_makanan,\n"
                    + "    m.nama_makanan,\n"
                    + "    k.qty,\n"
                    + "    m.harga * k.qty AS subtotal\n"
                    + "FROM\n"
                    + "    keranjang k\n"
                    + "JOIN\n"
                    + "    makanan m ON k.id_makanan = m.id_makanan;");
            this.rs = this.stat.executeQuery();
            int i = 1;
            while (rs.next()) {
                Object[] data = {
                    i++,
                    rs.getInt("id_keranjang"),
                    rs.getInt("id_makanan"),
                    rs.getString("nama_makanan"),
                    rs.getInt("qty"),
                    rs.getInt("subtotal")
                };
                model2.addRow(data);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            int totalSubtotal = 0;
            for (int i = 0; i < model2.getRowCount(); i++) {
                int subtotal = (int) model2.getValueAt(i, 5);
                totalSubtotal += subtotal;
            }
            text_total.setText(String.valueOf(totalSubtotal));
        }
        TableColumn idTransaksiColumn = tabel_transaksi.getColumnModel().getColumn(1);
        idTransaksiColumn.setMinWidth(0);
        idTransaksiColumn.setMaxWidth(0);
        idTransaksiColumn.setWidth(0);
        idTransaksiColumn.setPreferredWidth(0);

        TableColumn idmakananColumn = tabel_transaksi.getColumnModel().getColumn(2);
        idmakananColumn.setMinWidth(0);
        idmakananColumn.setMaxWidth(0);
        idmakananColumn.setWidth(0);
        idmakananColumn.setPreferredWidth(0);
    }

    public void clear() {
        text_id_keranjang.setText("");
        text_id_menu.setText("");
        text_nama_menu.setText("");
        text_qty.setText("");
        text_total_bayar.setText("");
        text_nama_pelanggan.setText("");

    }

    public cTransaksi() {
        initComponents();
        k.connect();
        refreshTable();
        refreshTable2();
        jLabel12.setText("Welcome, " + chekout_transaksi.getUsername());
        text_id_menu.setVisible(false);
    }

    class transaksi extends cTransaksi {

        int id_transaksi, id_makanan, harga, jumlah_beli, total;
        String nama_pelanggan, tanggal, nama_makanan;

        public transaksi() {
            this.nama_pelanggan = text_nama_pelanggan.getText();
//            this.id_makanan= Integer.parseInt(text_id_masakan.getText().toString());

            try {
                Date date = new Date(); // Mendapatkan tanggal dan waktu saat ini
                DateFormat dateformat = new SimpleDateFormat("YYYY-MM-dd");
                this.tanggal = dateformat.format(date);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }

        }

    }

    class keranjang extends cTransaksi {

        int id_makanan, qty;

        keranjang() {
            this.id_makanan = Integer.parseInt(text_id_menu.getText().toString());
            this.qty = Integer.parseInt(spinQTY.getValue().toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel8 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btn_update = new javax.swing.JButton();
        btn_delete = new javax.swing.JButton();
        btn_reset = new javax.swing.JButton();
        btn_logout = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        text_total = new javax.swing.JTextField();
        text_qty = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        text_nama_pelanggan = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_makanan = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        spinQTY = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        text_id_menu = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        text_nama_menu = new javax.swing.JTextField();
        text_total_bayar = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btn_checkout = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        text_id_keranjang = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();

        jLabel8.setText("TANGGAL");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("MENU TRANSAKSI");

        jLabel3.setText("NAMA PELANGGAN");

        jLabel5.setText("ID KERANJANG");

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_transaksi);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_delete.setText("DELETE");
        btn_delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_deleteActionPerformed(evt);
            }
        });

        btn_reset.setText("RESET");
        btn_reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_resetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(btn_update, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67)
                .addComponent(btn_delete, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(btn_reset)
                .addContainerGap(42, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_update)
                    .addComponent(btn_delete)
                    .addComponent(btn_reset))
                .addGap(36, 36, 36))
        );

        btn_logout.setText("LOGOUT");
        btn_logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_logoutActionPerformed(evt);
            }
        });

        jLabel9.setText("QTY");

        text_total.setEnabled(false);
        text_total.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_totalActionPerformed(evt);
            }
        });

        text_qty.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_qtyActionPerformed(evt);
            }
        });

        jLabel10.setText("TOTAL");

        text_nama_pelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_nama_pelangganActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tabel_makanan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabel_makanan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_makananMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_makanan);

        jLabel4.setText("ID Menu");

        jLabel6.setText("QTY");

        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel7.setText("DAFTAR MENU ");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(text_id_menu)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(text_nama_menu)
                                    .addComponent(spinQTY, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE))))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 469, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(text_nama_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(spinQTY, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1)
                        .addGap(100, 100, 100)
                        .addComponent(text_id_menu, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        text_total_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_total_bayarActionPerformed(evt);
            }
        });

        jLabel11.setText("TOTAL BAYAR");

        btn_checkout.setText("CHECKOUT");
        btn_checkout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_checkoutActionPerformed(evt);
            }
        });

        jLabel2.setText("Keranjang");

        jLabel12.setText("Welcome, ");

        text_id_keranjang.setEditable(false);
        text_id_keranjang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_id_keranjangActionPerformed(evt);
            }
        });

        jMenu1.setText("Member");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        jMenu2.setText("User");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Menu");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu3ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu3);

        jMenu5.setText("Riwayat Transaksi");
        jMenu5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu5MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu5);

        jMenu4.setText("Transaksi");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu4);

        jMenu6.setText("Laporan Penjualan");
        jMenu6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu6MouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenu6);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(262, 262, 262)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_logout, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72))
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btn_checkout, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(text_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(text_total, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addGap(33, 33, 33)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(text_nama_pelanggan, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                                .addComponent(text_id_keranjang)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                                            .addComponent(text_total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel2))
                                .addGap(148, 148, 148)))
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(btn_logout))
                        .addGap(58, 58, 58)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(text_nama_pelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(text_id_keranjang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(text_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(text_total_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_checkout)
                        .addGap(110, 110, 110)
                        .addComponent(jLabel2))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_checkoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_checkoutActionPerformed
        // TODO add your handling code here:
        try {
            int paytotal = Integer.parseInt(text_total_bayar.getText());
            int total = Integer.parseInt(text_total.getText());
            String cashierId = chekout_transaksi.getCashierId();
            String memberName = text_nama_pelanggan.getText();
            String memberId = null;
            if (!memberName.isEmpty()) {
                PreparedStatement memberStat = k.getCon().prepareStatement("SELECT id_member FROM member WHERE TRIM(nama_member) COLLATE utf8mb4_general_ci = TRIM(?) COLLATE utf8mb4_general_ci");
                memberStat.setString(1, memberName);
                ResultSet memberRs = memberStat.executeQuery();
                if (memberRs.next()) {
                    memberId = memberRs.getString("id_member");
                    total = (int) (total - (total * 0.1));
                    text_total.setText(Integer.toString(total));
                    if (paytotal < total || paytotal == 0) {
                        JOptionPane.showMessageDialog(null, "Total bayar kurang");
                    } else if (cashierId == null) {
                        JOptionPane.showMessageDialog(null, "Mohon Login terlebih dahulu");
                    } else {
                        java.util.Date date = new java.util.Date();
                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                        this.stat = k.getCon().prepareStatement("INSERT INTO transaksi (id_user, id_member, tanggal, total, total_bayar) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                        stat.setString(1, cashierId);
                        stat.setString(2, memberId);
                        stat.setDate(3, sqlDate);
                        stat.setInt(4, total);
                        stat.setInt(5, paytotal);
                        stat.executeUpdate();

                        // Dapatkan ID transaksi yang baru saja dihasilkan
                        ResultSet generatedKeys = stat.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int transactionId = generatedKeys.getInt(1);

                            // Set TransactionID to TransactionData
                            chekout_transaksi.setTransactionID(String.valueOf(transactionId));

                            // Ambil semua item di keranjang
                            for (int i = 0; i < tabel_transaksi.getRowCount(); i++) {
                                String itemId = tabel_transaksi.getValueAt(i, 2).toString();
                                int qty = Integer.parseInt(tabel_transaksi.getValueAt(i, 4).toString());
                                int subtotal = Integer.parseInt(tabel_transaksi.getValueAt(i, 5).toString());

                                // Masukkan data ke tabel detailtransactions
                                String detailQuery = "INSERT INTO detailtransaksi (id_transaksi, id_makanan, qty, subtotal) VALUES (?, ?, ?, ?)";
                                PreparedStatement detailPstmt = k.getCon().prepareStatement(detailQuery);
                                detailPstmt.setInt(1, transactionId);
                                detailPstmt.setString(2, itemId);
                                detailPstmt.setInt(3, qty);
                                detailPstmt.setInt(4, subtotal);
                                detailPstmt.executeUpdate();
                            }

                            // Kosongkan tabel carts
                            String truncateQuery = "TRUNCATE TABLE keranjang";
                            PreparedStatement truncatePstmt = k.getCon().prepareStatement(truncateQuery);
                            truncatePstmt.executeUpdate();
                        }

                        JOptionPane.showMessageDialog(null, "Checkout Berhasil");
                        refreshTable();
                        refreshTable2();
                        clear();
                        this.setVisible(false);
                        new cNota().setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Member tidak ditemukan");
                }
            } else if (memberName.isEmpty()) {
                if (paytotal < total || paytotal == 0) {
                    JOptionPane.showMessageDialog(null, "Total bayar kurang");
                } else if (cashierId == null) {
                    JOptionPane.showMessageDialog(null, "Mohon Login terlebih dahulu");
                } else {
                    java.util.Date date = new java.util.Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    this.stat = k.getCon().prepareStatement("INSERT INTO transaksi (id_user, id_member, tanggal, total, total_bayar) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                    stat.setString(1, cashierId);
                    stat.setString(2, null);
                    stat.setDate(3, sqlDate);
                    stat.setInt(4, total);
                    stat.setInt(5, paytotal);
                    stat.executeUpdate();

                    // Dapatkan ID transaksi yang baru saja dihasilkan
                    ResultSet generatedKeys = stat.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int transactionId = generatedKeys.getInt(1);

                        // Set TransactionID to TransactionData
                        chekout_transaksi.setTransactionID(String.valueOf(transactionId));

                        // Ambil semua item di keranjang
                        for (int i = 0; i < tabel_transaksi.getRowCount(); i++) {
                            String itemId = tabel_transaksi.getValueAt(i, 2).toString();
                            int qty = Integer.parseInt(tabel_transaksi.getValueAt(i, 4).toString());
                            int subtotal = Integer.parseInt(tabel_transaksi.getValueAt(i, 5).toString());

                            // Masukkan data ke tabel detailtransactions
                            String detailQuery = "INSERT INTO detailtransaksi (id_transaksi, id_makanan, qty, subtotal) VALUES (?, ?, ?, ?)";
                            PreparedStatement detailPstmt = k.getCon().prepareStatement(detailQuery);
                            detailPstmt.setInt(1, transactionId);
                            detailPstmt.setString(2, itemId);
                            detailPstmt.setInt(3, qty);
                            detailPstmt.setInt(4, subtotal);
                            detailPstmt.executeUpdate();
                        }

                        // Kosongkan tabel carts
                        String truncateQuery = "TRUNCATE TABLE keranjang";
                        PreparedStatement truncatePstmt = k.getCon().prepareStatement(truncateQuery);
                        truncatePstmt.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(null, "Checkout Berhasil");
                    refreshTable();
                    refreshTable2();
                    clear();
                    this.setVisible(false);
                    new cNota().setVisible(true);
                }
            }
        } catch (NumberFormatException nbr) {
            JOptionPane.showMessageDialog(null, "Value yang dibutuhkan tidak valid");
        } catch (SQLException ex) {
            Logger.getLogger(cTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_checkoutActionPerformed

    private void btn_deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_deleteActionPerformed
        // TODO add your handling code here:
        try {
            if (text_id_keranjang.getText() == null || text_id_keranjang.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Value Input tidak valid");
            } else {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Apakah anda yakin akan menghapus data keranjang ini?", "Warning", JOptionPane.YES_NO_OPTION);
                if (dialogResult == JOptionPane.YES_OPTION) {
                    this.stat = k.getCon().prepareStatement("delete from keranjang where id_keranjang=?");
                    stat.setString(1, text_id_keranjang.getText().toString());
                    stat.executeUpdate();
                    clear();
                    refreshTable();
                    refreshTable2();
                    JOptionPane.showMessageDialog(null, "Berhasil Hapus Keranjang");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btn_deleteActionPerformed

    private void btn_resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_resetActionPerformed
        // TODO add your handling code here:
        clear();
    }//GEN-LAST:event_btn_resetActionPerformed

    private void btn_logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_logoutActionPerformed
        // TODO add your handling code here:
        cLogin log = new cLogin();
        log.setVisible(true);
        this.setVisible(false);
        chekout_transaksi.clearUserSession();
    }//GEN-LAST:event_btn_logoutActionPerformed

    private void text_totalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_totalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_totalActionPerformed

    private void text_qtyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_qtyActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_qtyActionPerformed

    private void text_nama_pelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_nama_pelangganActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_nama_pelangganActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu3ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jMenu3ActionPerformed

    private void tabel_makananMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_makananMouseClicked
        text_id_menu.setText(model.getValueAt(tabel_makanan.getSelectedRow(), 1).toString());
        text_nama_menu.setText(model.getValueAt(tabel_makanan.getSelectedRow(), 2).toString());
        int maxValue = (int) model.getValueAt(tabel_makanan.getSelectedRow(), 4);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(1, 1, maxValue, 1);
        spinQTY.setModel(spinnerModel);

    }//GEN-LAST:event_tabel_makananMouseClicked

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        cRegistrasiMember mbr = new cRegistrasiMember();
        mbr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu1MouseClicked

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        cMakanan mkn = new cMakanan();
        mkn.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
        cRegistrasi rgr = new cRegistrasi();
        rgr.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        cTransaksi tran = new cTransaksi();
        tran.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu4MouseClicked

    private void jMenu5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu5MouseClicked
        cRiwayat rwt = new cRiwayat();
        rwt.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu5MouseClicked

    private void text_total_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_total_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_total_bayarActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try {
            keranjang ker = new keranjang();
            if (ker.id_makanan <= 0 || ker.qty <= 0) {
                JOptionPane.showMessageDialog(null, "Value Input tidak valid");
            } else {
                this.stat = k.getCon().prepareStatement("insert into keranjang values (?,?,?)");
                stat.setInt(1, 0);
                stat.setInt(2, ker.id_makanan);
                stat.setInt(3, ker.qty);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Berhasil Input Keranjang");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Value Input tidak valid");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } finally {
            refreshTable();
            refreshTable2();
            clear();
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void text_id_keranjangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_id_keranjangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_text_id_keranjangActionPerformed

    private void tabel_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transaksiMouseClicked
        // TODO add your handling code here:
        text_id_keranjang.setText(model2.getValueAt(tabel_transaksi.getSelectedRow(), 1).toString());
        text_qty.setText(model2.getValueAt(tabel_transaksi.getSelectedRow(), 4).toString());

    }//GEN-LAST:event_tabel_transaksiMouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        try {
            if (text_qty.getText() == null || text_qty.getText().isEmpty()
                    || text_id_keranjang.getText() == null || text_id_keranjang.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Value Input tidak valid");
            } else {
                this.stat = k.getCon().prepareStatement("update keranjang set qty=? where id_keranjang=?");
                stat.setInt(1, Integer.parseInt(text_qty.getText()));
                stat.setInt(2, Integer.parseInt(text_id_keranjang.getText()));
                stat.executeUpdate();
                refreshTable2();
                refreshTable();
                clear();
                JOptionPane.showMessageDialog(null, "Berhasil Update Keranjang");
            }
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Value Input tidak valid");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

    }//GEN-LAST:event_btn_updateActionPerformed

    private void jMenu6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu6MouseClicked
        cLaporan lp = new cLaporan();
        lp.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jMenu6MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cTransaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new cTransaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btn_checkout;
    public javax.swing.JButton btn_delete;
    public javax.swing.JButton btn_logout;
    public javax.swing.JButton btn_reset;
    public javax.swing.JButton btn_update;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner spinQTY;
    private javax.swing.JTable tabel_makanan;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JTextField text_id_keranjang;
    private javax.swing.JTextField text_id_menu;
    private javax.swing.JTextField text_nama_menu;
    private javax.swing.JTextField text_nama_pelanggan;
    private javax.swing.JTextField text_qty;
    private javax.swing.JTextField text_total;
    private javax.swing.JTextField text_total_bayar;
    // End of variables declaration//GEN-END:variables
}
