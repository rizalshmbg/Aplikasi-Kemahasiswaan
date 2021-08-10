/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kemahasiswaan_10119010_10119028;

// import java swing
import javax.swing.*;

// fungsi import yg digunakan mySql
import java.sql.*;
import java.text.DecimalFormat;

// fungsi import yg digunakan untuk tanggal
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author RizalSihombing
 */
public class frame_simulasiNilaiAkhir extends javax.swing.JFrame {
    
    // deklarasi variable global untuk database
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    // deklarasi variable global untuk data
    String data[] = new String[18];

    /**
     * Creates new form frame_simulasiNilaiAkhir
     */
    public frame_simulasiNilaiAkhir() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_DataSimulasiNilaiAkhir.setModel(tableModel);
        settableload();
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        // membuat judul header
        return new javax.swing.table.DefaultTableModel (
            new Object[][] {},
            new String [] {"NAMA M.K", "% ABSEN", "% TUGAS", "% UTS", "% UAS", "ABSENSI",
                           "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS", "NILAI ABSEN",
                           "NILAI TUGAS", "NILAI UTS", "NILAI UAS", "NILAI AKHIR",
                           "INDEKS", "KET"}
        )
        // disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };
            
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };
    }
    
    private void settableload() {
        String stat = "";
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select kode_nilai_akhir, kode_mk, nama_mk, persentase_absen, persentase_tugas, persentase_uts,"
                        + "persentase_uas, absensi, tgs1, tgs2, tgs3, uts, uas,"
                        + "nilai_absen, nilai_tugas, nilai_uts, nilai_uas,"
                        + "nilai_akhir, indeks, keterangan from t_nilai_akhir";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()) {
                data[0] = res.getString(3);
                data[1] = res.getString(4);
                data[2] = res.getString(5);
                data[3] = res.getString(6);
                data[4] = res.getString(7);
                data[5] = res.getString(8);
                data[6] = res.getString(9);
                data[7] = res.getString(10);
                data[8] = res.getString(11);
                data[9] = res.getString(12);
                data[10] = res.getString(13);
                data[11] = res.getString(14);
                data[12] = res.getString(15);
                data[13] = res.getString(16);
                data[14] = res.getString(17);
                data[15] = res.getString(18);
                data[16] = res.getString(19);
                data[17] = res.getString(20);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    
    public char index(double nilaiAkhir) {
        char index;
        
        if (nilaiAkhir >= 80 && nilaiAkhir <= 100) {
            index = 'A';
        } else if (nilaiAkhir > 68 && nilaiAkhir <= 80) {
            index = 'B';
        } else if (nilaiAkhir > 56 && nilaiAkhir <= 68) {
            index = 'C';
        } else if (nilaiAkhir > 45 && nilaiAkhir <= 56) {
            index = 'D';
        } else {
            index = 'E';
        }
        return index;
    }
    
    public String keterangan(String index) {
        String keterangan;
        
        switch (index) {
            case "A" : 
            case "B" :
            case "C" :
                keterangan = "Lulus";
                break;       
            default : 
                keterangan = "Tidak Lulus";
                break;
        };
        return keterangan;
    }
    
    // fungsi membersihkan teks
    public void membersihkan_teks() {
        textField_PersentaseAbsen.setText("");
        textField_PersentaseTugas.setText("");
        textField_PersentaseUTS.setText("");
        textField_PersentaseUAS.setText("");
        textField_Kehadiran.setText("");
        textField_Tugas1.setText("");
        textField_Tugas2.setText("");
        textField_Tugas3.setText("");
        textField_KodeMataKuliah.setText("");
        textField_UTS.setText("");
        textField_UAS.setText("");
        comboBox_NamaMataKuliah.setSelectedIndex(0);
    }
    
    // fungsi menonaktifkan teks
    public void nonaktif_teks() {
        textField_PersentaseAbsen.setEnabled(false);
        textField_PersentaseTugas.setEnabled(false);
        textField_PersentaseUTS.setEnabled(false);
        textField_PersentaseUAS.setEnabled(false);
        textField_Kehadiran.setEnabled(false);
        textField_Tugas1.setEnabled(false);
        textField_Tugas2.setEnabled(false);
        textField_Tugas3.setEnabled(false);
        textField_UTS.setEnabled(false);
        textField_UAS.setEnabled(false);
        comboBox_NamaMataKuliah.setEnabled(false);
    }
    
    // fungsi aktif teks
    public void aktif_teks() {
        textField_PersentaseAbsen.setEnabled(true);
        textField_PersentaseTugas.setEnabled(true);
        textField_PersentaseUTS.setEnabled(true);
        textField_PersentaseUAS.setEnabled(true);
        textField_Kehadiran.setEnabled(true);
        textField_Tugas1.setEnabled(true);
        textField_Tugas2.setEnabled(true);
        textField_Tugas3.setEnabled(true);
        textField_UTS.setEnabled(true);
        textField_UAS.setEnabled(true);
        comboBox_NamaMataKuliah.setEnabled(true);
    }
    
    // fungsi menampilkan data di dalam masing-masing jTextField
    int row = 1;
    public void tampil_field() {
        
        
        row = tabel_DataSimulasiNilaiAkhir.getSelectedRow();
//        textField_NIM.setText(tableModel.getValueAt(row, 1).toString());
        textField_PersentaseAbsen.setText(tableModel.getValueAt(row, 1).toString());
        textField_PersentaseTugas.setText(tableModel.getValueAt(row, 2).toString());
        textField_PersentaseUTS.setText(tableModel.getValueAt(row, 3).toString());
        textField_PersentaseUAS.setText(tableModel.getValueAt(row, 4).toString());
        textField_Kehadiran.setText(tableModel.getValueAt(row, 5).toString());
        textField_Tugas1.setText(tableModel.getValueAt(row, 6).toString());
        textField_Tugas2.setText(tableModel.getValueAt(row, 7).toString());
        textField_Tugas3.setText(tableModel.getValueAt(row, 8).toString());
        comboBox_NamaMataKuliah.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        textField_UTS.setText(tableModel.getValueAt(row, 9).toString());
        textField_UAS.setText(tableModel.getValueAt(row, 10).toString());
        
        button_Ubah.setEnabled(true);
        button_Hapus.setEnabled(true);
        button_Simpan.setEnabled(false);
        button_Batal.setEnabled(true);
        button_Tambah.setEnabled(false);
        button_Keluar.setEnabled(false);
        aktif_teks();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Panel = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        comboBox_NamaMataKuliah = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        textField_KodeMataKuliah = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        textField_PersentaseAbsen = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        textField_PersentaseTugas = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        textField_PersentaseUTS = new javax.swing.JTextField();
        textField_PersentaseUAS = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_DataSimulasiNilaiAkhir = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        textField_Kehadiran = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textField_Tugas1 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textField_Tugas2 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        textField_Tugas3 = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        textField_UTS = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        textField_UAS = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        button_Tambah = new javax.swing.JButton();
        button_Ubah = new javax.swing.JButton();
        button_Hapus = new javax.swing.JButton();
        button_Simpan = new javax.swing.JButton();
        button_Batal = new javax.swing.JButton();
        button_Keluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Form Simulasi Nilai Akhir - Aplikasi Kemahasiswaan");
        setBackground(new java.awt.Color(186, 79, 84));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(74, 31, 61));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FORM SIMULASI NILAI AKHIR");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(410, 410, 410)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        Panel.setBackground(new java.awt.Color(186, 79, 84));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nama Mata Kuliah");

        comboBox_NamaMataKuliah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Nama Mata Kuliah ~" }));
        comboBox_NamaMataKuliah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_NamaMataKuliahActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Kode Mata Kuliah");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Persentase Absen");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Persentase Tugas");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Persentase UTS");

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Persentase UAS");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("%");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("%");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("%");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("%");

        tabel_DataSimulasiNilaiAkhir.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAMA M.K", "% ABSEN", "% TUGAS", "% UTS", "% UAS", "Absensi", "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS", "NILAI ABSEN", "NILAI TUGAS", "NILAI UTS", "NILAI UAS", "NILAI AKHIR", "INDEKS", "KETERANGAN"
            }
        ));
        tabel_DataSimulasiNilaiAkhir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_DataSimulasiNilaiAkhirMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_DataSimulasiNilaiAkhir);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Kehadiran");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Pertemuan");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Tugas 1");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Tugas 2");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Tugas 3");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("UTS");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("UAS");

        button_Tambah.setBackground(new java.awt.Color(186, 79, 84));
        button_Tambah.setForeground(new java.awt.Color(255, 255, 255));
        button_Tambah.setText("TAMBAH");
        button_Tambah.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_Tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_TambahActionPerformed(evt);
            }
        });

        button_Ubah.setBackground(new java.awt.Color(186, 79, 84));
        button_Ubah.setForeground(new java.awt.Color(255, 255, 255));
        button_Ubah.setText("UBAH");
        button_Ubah.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_Ubah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Ubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_UbahActionPerformed(evt);
            }
        });

        button_Hapus.setBackground(new java.awt.Color(186, 79, 84));
        button_Hapus.setForeground(new java.awt.Color(255, 255, 255));
        button_Hapus.setText("HAPUS");
        button_Hapus.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_Hapus.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HapusActionPerformed(evt);
            }
        });

        button_Simpan.setBackground(new java.awt.Color(186, 79, 84));
        button_Simpan.setForeground(new java.awt.Color(255, 255, 255));
        button_Simpan.setText("SIMPAN");
        button_Simpan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_Simpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_SimpanActionPerformed(evt);
            }
        });

        button_Batal.setBackground(new java.awt.Color(186, 79, 84));
        button_Batal.setForeground(new java.awt.Color(255, 255, 255));
        button_Batal.setText("BATAL");
        button_Batal.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_Batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_BatalActionPerformed(evt);
            }
        });

        button_Keluar.setBackground(new java.awt.Color(186, 79, 84));
        button_Keluar.setForeground(new java.awt.Color(255, 255, 255));
        button_Keluar.setText("KELUAR");
        button_Keluar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        button_Keluar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button_Keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_KeluarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PanelLayout = new javax.swing.GroupLayout(Panel);
        Panel.setLayout(PanelLayout);
        PanelLayout.setHorizontalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addGap(144, 144, 144)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textField_UAS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_Tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_Tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_Tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textField_UTS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addComponent(textField_Kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)))
                .addGap(279, 279, 279))
            .addGroup(PanelLayout.createSequentialGroup()
                .addGap(182, 182, 182)
                .addComponent(button_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(button_Ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(button_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(75, 75, 75)
                .addComponent(button_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73)
                .addComponent(button_Batal, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addComponent(button_Keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(PanelLayout.createSequentialGroup()
                            .addGap(38, 38, 38)
                            .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel5)
                                .addComponent(jLabel13)
                                .addComponent(jLabel14)
                                .addComponent(jLabel15))
                            .addGap(138, 138, 138)
                            .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textField_KodeMataKuliah)
                                .addComponent(comboBox_NamaMataKuliah, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelLayout.createSequentialGroup()
                                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(textField_PersentaseTugas, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_PersentaseAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_PersentaseUTS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(textField_PersentaseUAS, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel3)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel7))
                                    .addGap(21, 21, 21))))
                        .addGroup(PanelLayout.createSequentialGroup()
                            .addGap(10, 10, 10)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGap(216, 216, 216)))
        );
        PanelLayout.setVerticalGroup(
            PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelLayout.createSequentialGroup()
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(202, 202, 202)
                        .addComponent(jLabel16))
                    .addGroup(PanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(textField_Kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textField_Tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(textField_Tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(textField_Tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel18)
                            .addComponent(textField_UTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textField_UAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 165, Short.MAX_VALUE)
                .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Batal, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Keluar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Tambah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button_Ubah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(comboBox_NamaMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(18, 18, 18)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textField_KodeMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addGap(18, 18, 18)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel13)
                        .addComponent(textField_PersentaseAbsen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(18, 18, 18)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel14)
                        .addComponent(textField_PersentaseTugas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addGap(18, 18, 18)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel15)
                        .addComponent(textField_PersentaseUTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6))
                    .addGap(18, 18, 18)
                    .addGroup(PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textField_PersentaseUAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGap(32, 32, 32)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(47, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, 1156, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        nonaktif_teks();
        button_Simpan.setEnabled(false);
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
        button_Keluar.setEnabled(true);
        button_Batal.setEnabled(true);
        button_Tambah.setEnabled(true);
        
        // untuk comboBox nama mata kuliah
        textField_KodeMataKuliah.setEditable(false);
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select nama_mk from t_mata_kuliah";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                String namaMK = res.getString(1);
                comboBox_NamaMataKuliah.addItem(String.valueOf(namaMK));
            }
            res.close();
            stt.close();
            kon.close();
        } catch (Exception ex) {
              JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
        }
    }//GEN-LAST:event_formWindowOpened

    private void tabel_DataSimulasiNilaiAkhirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_DataSimulasiNilaiAkhirMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            tampil_field();
        }
    }//GEN-LAST:event_tabel_DataSimulasiNilaiAkhirMouseClicked

    private void comboBox_NamaMataKuliahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_NamaMataKuliahActionPerformed
        // TODO add your handling code here:
         // deklarasi variable
        int namaMK = comboBox_NamaMataKuliah.getSelectedIndex();
        
        if (namaMK==0) {
            textField_KodeMataKuliah.setText("");
        } else {
            try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select kode_mk, nama_mk from t_mata_kuliah "
                        + "where nama_mk= '"+comboBox_NamaMataKuliah.getSelectedItem()+"'";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                textField_KodeMataKuliah.setText(res.getString(1));
            }
            res.close();
            stt.close();
            kon.close();
            } catch (Exception ex) {
              JOptionPane.showMessageDialog(null,
                        ex.getMessage(), "Error",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_comboBox_NamaMataKuliahActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frame_utama frame_utama = new frame_utama();
        frame_utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void button_KeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_KeluarActionPerformed
        // TODO add your handling code here:
        frame_utama frame_utama = new frame_utama();
        frame_utama.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_button_KeluarActionPerformed

    private void button_BatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_BatalActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        button_Tambah.setEnabled(true);
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
        button_Simpan.setEnabled(false);
        button_Batal.setEnabled(true);
        button_Keluar.setEnabled(true);
        nonaktif_teks();
    }//GEN-LAST:event_button_BatalActionPerformed

    private void button_TambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_TambahActionPerformed
        // TODO add your handling code here:
        membersihkan_teks();
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
        button_Simpan.setEnabled(true);
        button_Keluar.setEnabled(false);
        button_Batal.setEnabled(true);
        button_Tambah.setEnabled(false);
        aktif_teks();
    }//GEN-LAST:event_button_TambahActionPerformed

    private void button_SimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_SimpanActionPerformed
        // TODO add your handling code here:
        // deklarasi variabel textField
        DecimalFormat df=new DecimalFormat("#.##");
        double Tugas1, Tugas2, Tugas3, UTS, UAS, persenAbsen, persenTugas, persenUTS, persenUAS;
        double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS, nilaiAkhir;
        char index;
        String convertIndexToStr, keterangan = null;
        
        // inisialisasi variable
        persenAbsen = Double.valueOf(textField_PersentaseAbsen.getText());
        persenTugas = Double.valueOf(textField_PersentaseTugas.getText());
        persenUTS = Double.valueOf(textField_PersentaseUTS.getText());
        persenUAS = Double.valueOf(textField_PersentaseUAS.getText());
        Tugas1 = Double.valueOf(textField_Tugas1.getText());
        Tugas2 = Double.valueOf(textField_Tugas2.getText());
        Tugas3 = Double.valueOf(textField_Tugas3.getText());
        UTS = Double.valueOf(textField_UTS.getText());
        UAS = Double.valueOf(textField_UAS.getText());
        
        // inisialisasi variable
        nilaiAbsen = (((Double.valueOf(textField_Kehadiran.getText()) / 14) * 100 * persenAbsen) / 100);
        nilaiTugas = (((Tugas1 + Tugas2 + Tugas3) / 3) * persenTugas / 100);
        nilaiUTS = UTS * persenUTS/100;
        nilaiUAS = UAS * persenUAS/100;
        nilaiAkhir = nilaiAbsen + nilaiTugas + nilaiUTS + nilaiUAS;
        index = index(nilaiAkhir);
        convertIndexToStr = String.valueOf(index);
        keterangan = keterangan(convertIndexToStr);
       
        String data[]=new String[18];
        if ((textField_PersentaseAbsen.getText().isEmpty()) || (comboBox_NamaMataKuliah.getSelectedIndex() == 0) || 
               (textField_KodeMataKuliah.getText().isEmpty()) || (textField_Kehadiran.getText().isEmpty())
                || (textField_PersentaseTugas.getText().isEmpty()) || (textField_PersentaseUTS.getText().isEmpty())
                || (textField_PersentaseUAS.getText().isEmpty()) || (textField_Tugas1.getText().isEmpty())
                || (textField_Tugas2.getText().isEmpty()) || (textField_Tugas3.getText().isEmpty())
                || (textField_UTS.getText().isEmpty()) || (textField_UAS.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null,
                            "Data Tidak Boleh Kosong, Silahkan Dilengkapi!");
            textField_PersentaseAbsen.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String    SQL = "INSERT INTO t_nilai_akhir(kode_mk,"
                                + "nama_mk,"
                                + "persentase_absen,"
                                + "persentase_tugas,"
                                + "persentase_uts,"
                                + "persentase_uas,"
                                + "absensi,"
                                + "tgs1,"
                                + "tgs2,"
                                + "tgs3,"
                                + "uts,"
                                + "uas,"
                                + "nilai_absen,"
                                + "nilai_tugas,"
                                + "nilai_uts,"
                                + "nilai_uas,"
                                + "nilai_akhir,"
                                + "indeks,"
                                + "keterangan) "
                                   + "VALUES "
                                + "( '"+textField_KodeMataKuliah.getText()+" ' ,"
                                + "'"+comboBox_NamaMataKuliah.getSelectedItem()+"',"
                                + "'"+textField_PersentaseAbsen.getText()+"',"
                                + "'"+textField_PersentaseTugas.getText()+"',"
                                + "'"+textField_PersentaseUTS.getText()+"',"
                                + "'"+textField_PersentaseUAS.getText()+"',"
                                + "'"+textField_Kehadiran.getText()+" ',"
                                + "'"+ Tugas1 +" ',"
                                + "'"+ Tugas2 +" ',"
                                + "'"+ Tugas3 +" ',"
                                + "'"+ UTS +" ',"
                                + "'"+ UAS +" ',"
                                + "'"+ df.format(nilaiAbsen) +" ',"
                                + "'"+ df.format(nilaiTugas) +" ',"
                                + "'"+ df.format(nilaiUTS) +" ',"
                                + "'"+ df.format(nilaiUAS) +" ',"
                                + "'"+ df.format(nilaiAkhir) +" ',"
                                + "'"+ index +" ',"
                                + "'"+ keterangan +" ')";
                                
                stt.executeUpdate(SQL);
                data[0] = String.valueOf(comboBox_NamaMataKuliah.getSelectedItem());
                data[1] = textField_PersentaseAbsen.getText();
                data[2] = textField_PersentaseTugas.getText();
                data[3] = textField_PersentaseUTS.getText();
                data[4] = textField_PersentaseUAS.getText();
                data[5] = textField_Kehadiran.getText();
                data[6] = String.valueOf(Tugas1);
                data[7] = String.valueOf(Tugas2);
                data[8] = String.valueOf(Tugas3);
                data[9] = String.valueOf(UTS);
                data[10] = String.valueOf(UAS);
                data[11] = String.valueOf(df.format(nilaiAbsen));
                data[12] = String.valueOf(df.format(nilaiTugas));
                data[13] = String.valueOf(df.format(nilaiUTS));
                data[14] = String.valueOf(df.format(nilaiUAS));
                data[15] = String.valueOf(df.format(nilaiAkhir));
                data[16] = String.valueOf(index);
                data[17] = String.valueOf(keterangan);
                tableModel.insertRow(0, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                button_Tambah.setEnabled(true);
                button_Ubah.setEnabled(false);
                button_Hapus.setEnabled(false);
                button_Simpan.setEnabled(false);
                button_Batal.setEnabled(true);
                button_Keluar.setEnabled(true);
                nonaktif_teks();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                    ex.getMessage(),"Error",
                    JOptionPane.INFORMATION_MESSAGE
                );
            }
        }
    }//GEN-LAST:event_button_SimpanActionPerformed

    private void button_UbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_UbahActionPerformed
        // TODO add your handling code here:
        // deklarasi variabel textField
        DecimalFormat df=new DecimalFormat("#.##");
        double Tugas1, Tugas2, Tugas3, UTS, UAS, persenAbsen, persenTugas, persenUTS, persenUAS;
        double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS, nilaiAkhir;
        char index;
        String convertIndexToStr, keterangan = null;
        
        // inisialisasi variable
        persenAbsen = Double.valueOf(textField_PersentaseAbsen.getText());
        persenTugas = Double.valueOf(textField_PersentaseTugas.getText());
        persenUTS = Double.valueOf(textField_PersentaseUTS.getText());
        persenUAS = Double.valueOf(textField_PersentaseUAS.getText());
        Tugas1 = Double.valueOf(textField_Tugas1.getText());
        Tugas2 = Double.valueOf(textField_Tugas2.getText());
        Tugas3 = Double.valueOf(textField_Tugas3.getText());
        UTS = Double.valueOf(textField_UTS.getText());
        UAS = Double.valueOf(textField_UAS.getText());
        
        // inisialisasi variable
        nilaiAbsen = (((Double.valueOf(textField_Kehadiran.getText()) / 14) * 100 * persenAbsen) / 100);
        nilaiTugas = (((Tugas1 + Tugas2 + Tugas3) / 3) * persenTugas / 100);
        nilaiUTS = UTS * persenUTS/100;
        nilaiUAS = UAS * persenUAS/100;
        nilaiAkhir = nilaiAbsen + nilaiTugas + nilaiUTS + nilaiUAS;
        index = index(nilaiAkhir);
        convertIndexToStr = String.valueOf(index);
        keterangan = keterangan(convertIndexToStr);
        
        if ((textField_PersentaseAbsen.getText().isEmpty()) || (comboBox_NamaMataKuliah.getSelectedIndex() == 0) || 
               (textField_KodeMataKuliah.getText().isEmpty()) || (textField_Kehadiran.getText().isEmpty())
                || (textField_PersentaseTugas.getText().isEmpty()) || (textField_PersentaseUTS.getText().isEmpty())
                || (textField_PersentaseUAS.getText().isEmpty()) || (textField_Tugas1.getText().isEmpty())
                || (textField_Tugas2.getText().isEmpty()) || (textField_Tugas3.getText().isEmpty())
                || (textField_UTS.getText().isEmpty()) || (textField_UAS.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan Dilengkapi!");
            textField_PersentaseAbsen.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE `t_nilai_akhir` "
                                + "SET `kode_mk`='"+textField_KodeMataKuliah.getText()+"',"
                                + "`nama_mk`='"+comboBox_NamaMataKuliah.getSelectedItem()+"',"
                                + "`persentase_absen`='"+textField_PersentaseAbsen.getText()+"',"
                                + "`persentase_tugas`='"+textField_PersentaseTugas.getText()+"',"
                                + "`persentase_uts`='"+textField_PersentaseUTS.getText()+"',"
                                + "`persentase_uas`='"+textField_PersentaseUAS.getText()+"',"
                                + "`absensi`='"+textField_Kehadiran.getText()+"',"
                                + "`tgs1`='"+Tugas1+"',"
                                + "`tgs2`='"+Tugas2+"',"
                                + "`tgs3`='"+Tugas3+"',"
                                + "`uts`='"+UTS+"',"
                                + "`uas`='"+UAS+"',"
                                + "`nilai_absen`='"+df.format(nilaiAbsen)+"',"
                                + "`nilai_tugas`='"+df.format(nilaiTugas)+"',"
                                + "`nilai_uts`='"+df.format(nilaiUTS)+"',"
                                + "`nilai_uas`='"+df.format(nilaiUAS)+"',"
                                + "`nilai_akhir`='"+df.format(nilaiAkhir)+"',"
                                + "`indeks`='"+index+"',"
                                + "`keterangan`='"+keterangan+"' "
                            + "WHERE "
                            + "`nama_mk`='"+tableModel.getValueAt(row, 0).toString()+"';";
                stt.executeUpdate(SQL);
                data[0] = String.valueOf(comboBox_NamaMataKuliah.getSelectedItem());
                data[1] = textField_PersentaseAbsen.getText();
                data[2] = textField_PersentaseTugas.getText();
                data[3] = textField_PersentaseUTS.getText();
                data[4] = textField_PersentaseUAS.getText();
                data[5] = textField_Kehadiran.getText();
                data[6] = String.valueOf(Tugas1);
                data[7] = String.valueOf(Tugas2);
                data[8] = String.valueOf(Tugas3);
                data[9] = String.valueOf(UTS);
                data[10] = String.valueOf(UAS);
                data[11] = String.valueOf(df.format(nilaiAbsen));
                data[12] = String.valueOf(df.format(nilaiTugas));
                data[13] = String.valueOf(df.format(nilaiUTS));
                data[14] = String.valueOf(df.format(nilaiUAS));
                data[15] = String.valueOf(df.format(nilaiAkhir));
                data[16] = String.valueOf(index);
                data[17] = String.valueOf(keterangan);
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                button_Tambah.setEnabled(true);
                button_Ubah.setEnabled(false);
                button_Hapus.setEnabled(false);
                button_Simpan.setEnabled(false);
                button_Batal.setEnabled(true);
                button_Keluar.setEnabled(true);
                nonaktif_teks();
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }//GEN-LAST:event_button_UbahActionPerformed

    private void button_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HapusActionPerformed
        // TODO add your handling code here:
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String  SQL = "DELETE FROM t_nilai_akhir "
                        + "WHERE " + "nama_mk='"+tableModel.getValueAt(row, 0).toString()+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            membersihkan_teks();
            button_Tambah.setEnabled(true);
            button_Ubah.setEnabled(false);
            button_Hapus.setEnabled(false);
            button_Simpan.setEnabled(false);
            button_Batal.setEnabled(true);
            button_Keluar.setEnabled(true);
            nonaktif_teks();
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }//GEN-LAST:event_button_HapusActionPerformed

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
            java.util.logging.Logger.getLogger(frame_simulasiNilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame_simulasiNilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame_simulasiNilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame_simulasiNilaiAkhir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame_simulasiNilaiAkhir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Panel;
    private javax.swing.JButton button_Batal;
    private javax.swing.JButton button_Hapus;
    private javax.swing.JButton button_Keluar;
    private javax.swing.JButton button_Simpan;
    private javax.swing.JButton button_Tambah;
    private javax.swing.JButton button_Ubah;
    private javax.swing.JComboBox<String> comboBox_NamaMataKuliah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable tabel_DataSimulasiNilaiAkhir;
    private javax.swing.JTextField textField_Kehadiran;
    private javax.swing.JTextField textField_KodeMataKuliah;
    private javax.swing.JTextField textField_PersentaseAbsen;
    private javax.swing.JTextField textField_PersentaseTugas;
    private javax.swing.JTextField textField_PersentaseUAS;
    private javax.swing.JTextField textField_PersentaseUTS;
    private javax.swing.JTextField textField_Tugas1;
    private javax.swing.JTextField textField_Tugas2;
    private javax.swing.JTextField textField_Tugas3;
    private javax.swing.JTextField textField_UAS;
    private javax.swing.JTextField textField_UTS;
    // End of variables declaration//GEN-END:variables
}
