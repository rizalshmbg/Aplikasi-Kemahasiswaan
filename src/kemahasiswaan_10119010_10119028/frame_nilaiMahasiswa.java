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
public class frame_nilaiMahasiswa extends javax.swing.JFrame {
    
    // deklarasi variable global untuk database
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    // deklarasi variable global untuk data
    String data[] = new String[15];

    /**
     * Creates new form frame_nilaiMahasiswa
     */
    public frame_nilaiMahasiswa() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_DataNilaiMahasiswa.setModel(tableModel);
        settableload();
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        // membuat judul header
        return new javax.swing.table.DefaultTableModel (
            new Object[][] {},
            new String [] {"NAMA", "NAMA M.K", "ABSENSI", "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS",
                           "NILAI ABSEN", "NILAI TUGAS", "NILAI UTS", "NILAI UAS", "NILAI AKHIR",
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
            String SQL = "select kode_nilai, nim, kode_mk, nama, nama_mk, absensi,"
                        + "tgs1, tgs2, tgs3, uts, uas,"
                        + "nilai_absen, nilai_tugas, nilai_uts, nilai_uas,"
                        + "nilai_akhir, indeks, keterangan from t_nilai";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()) {
                data[0] = res.getString(4);
                data[1] = res.getString(5);
                data[2] = res.getString(6);
                data[3] = res.getString(7);
                data[4] = res.getString(8);
                data[5] = res.getString(9);
                data[6] = res.getString(10);
                data[7] = res.getString(11);
                data[8] = res.getString(12);
                data[9] = res.getString(13);
                data[10] = res.getString(14);
                data[11] = res.getString(15);
                data[12] = res.getString(16);
                data[13] = res.getString(17);
                data[14] = res.getString(18);
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
        textField_MasukkanData.setText("");
        textField_NIM.setText("");
        textField_Kehadiran.setText("");
        textField_Tugas1.setText("");
        textField_Tugas2.setText("");
        textField_Tugas3.setText("");
        //textField_NamaMataKuliah.setText("");
        textField_KodeMataKuliah.setText("");
        textField_UTS.setText("");
        textField_UAS.setText("");
        comboBox_NamaMataKuliah.setSelectedIndex(0);
        comboBox_Nama.setSelectedIndex(0);
    }
    
    // fungsi menonaktifkan teks
    public void nonaktif_teks() {
//        textField_MasukkanData.setEnabled(false);
        textField_Kehadiran.setEnabled(false);
        textField_Tugas1.setEnabled(false);
        textField_Tugas2.setEnabled(false);
        textField_Tugas3.setEnabled(false);
//        textField_KodeMataKuliah.setEnabled(false);
        textField_UTS.setEnabled(false);
        textField_UAS.setEnabled(false);
    }
    
    // fungsi aktif teks
    public void aktif_teks() {
//        textField_MasukkanData.setEnabled(true);
        textField_Kehadiran.setEnabled(true);
        textField_Tugas1.setEnabled(true);
        textField_Tugas2.setEnabled(true);
        textField_Tugas3.setEnabled(true);
        //textField_NamaMataKuliah.setEnabled(true);
//        textField_KodeMataKuliah.setEnabled(true);
        textField_UTS.setEnabled(true);
        textField_UAS.setEnabled(true);
    }
    
    // fungsi menampilkan data di dalam masing-masing jTextField
    int row = 1;
    public void tampil_field() {
        
        
        row = tabel_DataNilaiMahasiswa.getSelectedRow();
//        textField_NIM.setText(tableModel.getValueAt(row, 1).toString());
         comboBox_Nama.setSelectedItem(tableModel.getValueAt(row, 0).toString());
        textField_Kehadiran.setText(tableModel.getValueAt(row, 2).toString());
        textField_Tugas1.setText(tableModel.getValueAt(row, 3).toString());
        textField_Tugas2.setText(tableModel.getValueAt(row, 4).toString());
        textField_Tugas3.setText(tableModel.getValueAt(row, 5).toString());
        comboBox_NamaMataKuliah.setSelectedItem(tableModel.getValueAt(row, 1).toString());
//        textField_KodeMataKuliah.setText(tableModel.getValueAt(row, 7).toString());
        textField_UTS.setText(tableModel.getValueAt(row, 6).toString());
        textField_UAS.setText(tableModel.getValueAt(row, 7).toString());
//        textField_Angkatan.setText(tableModel.getValueAt(row, 10).toString());
        
        
        
        button_Ubah.setEnabled(true);
        button_Hapus.setEnabled(true);
        button_Simpan.setEnabled(false);
        button_Keluar.setEnabled(false);
        button_Tambah.setEnabled(false);
        button_Batal.setEnabled(true);
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
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        textField_MasukkanData = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        textField_NIM = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textField_Kehadiran = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        textField_Tugas1 = new javax.swing.JTextField();
        textField_Tugas2 = new javax.swing.JTextField();
        textField_Tugas3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        textField_KodeMataKuliah = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textField_UTS = new javax.swing.JTextField();
        textField_UAS = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_DataNilaiMahasiswa = new javax.swing.JTable();
        button_Tambah = new javax.swing.JButton();
        button_Ubah = new javax.swing.JButton();
        button_Hapus = new javax.swing.JButton();
        button_Simpan = new javax.swing.JButton();
        button_Batal = new javax.swing.JButton();
        button_Keluar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        comboBox_Nama = new javax.swing.JComboBox<>();
        comboBox_NamaMataKuliah = new javax.swing.JComboBox<>();
        textField_Angkatan = new com.toedter.calendar.JYearChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("FORM NILAI MAHASISWA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(377, 377, 377))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel1)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setText("Pencarian Data Mahasiswa");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Masukkan Data");

        textField_MasukkanData.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textField_MasukkanDataKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("NIM");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Nama");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Kehadiran");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Pertemuan");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Tugas 1");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Tugas 2");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setText("Tugas 3");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel11.setText("Nama Mata Kuliah");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel12.setText("Kode Mata Kuliah");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("UTS");

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel14.setText("UAS");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("Angkatan");

        tabel_DataNilaiMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NAMA", "NAMA M.K", "ABSENSI", "Tgs 1", "Tgs 2", "Tgs 3", "UTS", "UAS", "NILAI ABSEN", "NILAI TUGAS", "NILAI UTS", "NILAI UAS", "NILAI AKHIR", "INDEKS", "KETERANGAN"
            }
        ));
        tabel_DataNilaiMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_DataNilaiMahasiswaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_DataNilaiMahasiswa);

        button_Tambah.setText("TAMBAH");
        button_Tambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_TambahMouseClicked(evt);
            }
        });

        button_Ubah.setText("UBAH");
        button_Ubah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_UbahMouseClicked(evt);
            }
        });

        button_Hapus.setText("HAPUS");
        button_Hapus.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_HapusMouseClicked(evt);
            }
        });

        button_Simpan.setText("SIMPAN");
        button_Simpan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_SimpanMouseClicked(evt);
            }
        });

        button_Batal.setText("BATAL");
        button_Batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_BatalMouseClicked(evt);
            }
        });

        button_Keluar.setText("KELUAR");
        button_Keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_KeluarMouseClicked(evt);
            }
        });

        comboBox_Nama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Nama Mahasiswa ~" }));
        comboBox_Nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_NamaActionPerformed(evt);
            }
        });

        comboBox_NamaMataKuliah.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "~ Pilih Nama Mata Kuliah ~" }));
        comboBox_NamaMataKuliah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBox_NamaMataKuliahActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addGap(158, 158, 158)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textField_Tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField_Tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(textField_Kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel7))
                                            .addComponent(comboBox_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(textField_NIM, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 378, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel15)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(2, 2, 2)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel14)
                                                            .addComponent(jLabel13))))
                                                .addGap(47, 47, 47))
                                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGap(140, 140, 140)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(textField_KodeMataKuliah)
                                            .addComponent(comboBox_NamaMataKuliah, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(textField_Angkatan, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(textField_UAS, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(textField_UTS, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 53, Short.MAX_VALUE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(textField_Tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(124, 124, 124)
                                        .addComponent(textField_MasukkanData, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2))
                        .addContainerGap())
                    .addComponent(jSeparator3)))
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(button_Tambah)
                .addGap(160, 160, 160)
                .addComponent(button_Ubah)
                .addGap(175, 175, 175)
                .addComponent(button_Hapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button_Simpan)
                .addGap(124, 124, 124)
                .addComponent(button_Batal)
                .addGap(111, 111, 111)
                .addComponent(button_Keluar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textField_MasukkanData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(textField_Tugas3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(textField_NIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(comboBox_NamaMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(textField_KodeMataKuliah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12)
                            .addComponent(comboBox_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(textField_Kehadiran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(textField_UTS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(textField_Tugas1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textField_UAS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(textField_Tugas2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(23, 23, 23)
                                .addComponent(textField_Angkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)))
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button_Tambah)
                    .addComponent(button_Ubah)
                    .addComponent(button_Hapus)
                    .addComponent(button_Simpan)
                    .addComponent(button_Batal)
                    .addComponent(button_Keluar))
                .addGap(0, 12, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_TambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_TambahMouseClicked
        // TODO add your handling code here:
        membersihkan_teks();
        textField_Kehadiran.requestFocus();
        button_Batal.setEnabled(true);
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
        button_Simpan.setEnabled(true);
        button_Keluar.setEnabled(false);
        button_Tambah.setEnabled(false);
        aktif_teks();
    }//GEN-LAST:event_button_TambahMouseClicked

    private void button_SimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SimpanMouseClicked
        // TODO add your handling code here:
        // deklarasi variabel textField
        DecimalFormat df=new DecimalFormat("#.##");
        double Tugas1, Tugas2, Tugas3, UTS, UAS;
        double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS, nilaiAkhir;
        char index;
        String convertIndexToStr, keterangan = null;
        
        // inisialisasi variable
        Tugas1 = Double.valueOf(textField_Tugas1.getText());
        Tugas2 = Double.valueOf(textField_Tugas2.getText());
        Tugas3 = Double.valueOf(textField_Tugas3.getText());
        UTS = Double.valueOf(textField_UTS.getText());
        UAS = Double.valueOf(textField_UAS.getText());
        
        // inisialisasi variable
        nilaiAbsen = (((Double.valueOf(textField_Kehadiran.getText()) / 14) * 100 * 5) / 100);
        nilaiTugas = (((Tugas1 + Tugas2 + Tugas3) / 3) * 25 / 100);
        nilaiUTS = UTS * 0.3;
        nilaiUAS = UAS * 0.4;
        nilaiAkhir = nilaiAbsen + nilaiTugas + nilaiUTS + nilaiUAS;  
        index = index(nilaiAkhir);
        convertIndexToStr = String.valueOf(index);
        keterangan = keterangan(convertIndexToStr);
        
        String data[]=new String[15];
        if ((textField_NIM.getText().isEmpty()) || (comboBox_Nama.getSelectedIndex() == 0) || 
                (textField_Kehadiran.getText().isEmpty()) || (textField_Tugas1.getText().isEmpty()) || (textField_Tugas2.getText().isEmpty())
                || (textField_Tugas3.getText().isEmpty()) || (comboBox_NamaMataKuliah.getSelectedIndex() == 0)
                || (textField_KodeMataKuliah.getText().isEmpty()) || (textField_UTS.getText().isEmpty())
                || (textField_UAS.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null,
                            "Data Tidak Boleh Kosong, Silahkan Dilengkapi!");
            textField_NIM.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String    SQL = "INSERT INTO t_nilai(nim,"
                                + "kode_mk,"
                                + "nama,"
                                + "nama_mk,"
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
                                + "keterangan,"
                                + "angkatan) "
                                   + "VALUES "
                                + "( '"+textField_NIM.getText()+" ' ,"
                                + "'"+textField_KodeMataKuliah.getText()+"',"
                                + "'"+comboBox_Nama.getSelectedItem()+"',"
                                + "'"+comboBox_NamaMataKuliah.getSelectedItem()+"',"
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
                                + "'"+ keterangan +" ',"
                                + "'"+ textField_Angkatan.getYear() +" ')";
                
                stt.executeUpdate(SQL);
                data[0] = String.valueOf(comboBox_Nama.getSelectedItem());
                data[1] = String.valueOf(comboBox_NamaMataKuliah.getSelectedItem());
                data[2] = textField_Kehadiran.getText();
                data[3] = String.valueOf(Tugas1);
                data[4] = String.valueOf(Tugas2);
                data[5] = String.valueOf(Tugas3);
                data[6] = String.valueOf(UTS);
                data[7] = String.valueOf(UAS);
                data[8] = String.valueOf(df.format(nilaiAbsen));
                data[9] = String.valueOf(df.format(nilaiTugas));
                data[10] = String.valueOf(df.format(nilaiUTS));
                data[11] = String.valueOf(df.format(nilaiUAS));
                data[12] = String.valueOf(df.format(nilaiAkhir));
                data[13] = String.valueOf(index);
                data[14] = String.valueOf(keterangan);
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
    }//GEN-LAST:event_button_SimpanMouseClicked

    private void tabel_DataNilaiMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_DataNilaiMahasiswaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            tampil_field();
        }
    }//GEN-LAST:event_tabel_DataNilaiMahasiswaMouseClicked

    private void button_UbahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_UbahMouseClicked
        // TODO add your handling code here:
        DecimalFormat df=new DecimalFormat("#.##");
        double Tugas1, Tugas2, Tugas3, UTS, UAS;
        double nilaiAbsen, nilaiTugas, nilaiUTS, nilaiUAS, nilaiAkhir;
        String indeks = null, keterangan = null;
        
        // inisialisasi variable
        Tugas1 = Double.valueOf(textField_Tugas1.getText());
        Tugas3 = Double.valueOf(textField_Tugas2.getText());
        Tugas2 = Double.valueOf(textField_Tugas3.getText());
        UTS = Double.valueOf(textField_UTS.getText());
        UAS = Double.valueOf(textField_UAS.getText());
        
        // inisialisasi variable
        nilaiAbsen = (((Double.valueOf(textField_Kehadiran.getText()) / 14) * 100 * 5) / 100);
        nilaiTugas = (((Tugas1 + Tugas2 + Tugas3) / 3) * 25 / 100);
        nilaiUTS = UTS * 0.3;
        nilaiUAS = UAS * 0.4;
        nilaiAkhir = nilaiAbsen + nilaiTugas + nilaiUTS + nilaiUAS;
        
        if (nilaiAkhir >= 80 && nilaiAkhir <=100) {
            indeks = "A";
        } else if (nilaiAkhir >=68 && nilaiAkhir <= 79) {
            indeks = "B";
        } else if (nilaiAkhir >=56 && nilaiAkhir <= 67) {
            indeks = "C";
        } else if (nilaiAkhir >=45 && nilaiAkhir <= 55) {
            indeks = "D";
        } else if (nilaiAkhir >= 0 && nilaiAkhir <= 44) {
            indeks = "E";
        }
        
        if (indeks.equals("A") || indeks.equals("B") || indeks.equals("C")){
            keterangan = "Lulus";
        } else {
            keterangan = "Tidak Lulus";
        }
        
        int tahunAngkatan = textField_Angkatan.getYear();
        
        if ((textField_NIM.getText().isEmpty()) || (comboBox_Nama.getSelectedIndex() == 0) || 
                (textField_Kehadiran.getText().isEmpty()) || (textField_Tugas1.getText().isEmpty()) || (textField_Tugas2.getText().isEmpty())
                || (textField_Tugas3.getText().isEmpty()) || (comboBox_NamaMataKuliah.getSelectedIndex() == 0)
                || (textField_KodeMataKuliah.getText().isEmpty()) || (textField_UTS.getText().isEmpty())
                || (textField_UAS.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan Dilengkapi!");
            textField_NIM.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE `t_nilai` "
                                + "SET `nim`='"+textField_NIM.getText()+"',"
                                + "`kode_mk`='"+textField_KodeMataKuliah.getText()+"',"
                                + "`nama`='"+comboBox_Nama.getSelectedItem()+"',"
                                + "`nama_mk`='"+comboBox_NamaMataKuliah.getSelectedItem()+"',"
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
                                + "`indeks`='"+indeks+"',"
                                + "`keterangan`='"+keterangan+"',"
                                + "`angkatan`='"+tahunAngkatan+"' "
                            + "WHERE "
                            + "`nama`='"+tableModel.getValueAt(row, 0).toString()+"';";
                stt.executeUpdate(SQL);
                data[0] = String.valueOf(comboBox_Nama.getSelectedItem());
                data[1] = String.valueOf(comboBox_NamaMataKuliah.getSelectedItem());
                data[2] = textField_Kehadiran.getText();
                data[3] = String.valueOf(Tugas1);
                data[4] = String.valueOf(Tugas2);
                data[5] = String.valueOf(Tugas3);
                data[6] = String.valueOf(UTS);
                data[7] = String.valueOf(UAS);
                data[8] = String.valueOf(df.format(nilaiAbsen));
                data[9] = String.valueOf(df.format(nilaiTugas));
                data[10] = String.valueOf(df.format(nilaiUTS));
                data[11] = String.valueOf(df.format(nilaiUAS));
                data[12] = String.valueOf(df.format(nilaiAkhir));
                data[13] = String.valueOf(indeks);
                data[14] = String.valueOf(keterangan);
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
    }//GEN-LAST:event_button_UbahMouseClicked

    private void button_HapusMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_HapusMouseClicked
        // TODO add your handling code here:
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String  SQL = "DELETE FROM t_nilai "
                        + "WHERE " + "nim='"+tableModel.getValueAt(row, 0).toString()+"'";
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
    }//GEN-LAST:event_button_HapusMouseClicked

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
        frame_utama frame_utama = new frame_utama();
        frame_utama.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void button_KeluarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_KeluarMouseClicked
        // TODO add your handling code here:
        frame_utama frame_utama = new frame_utama();
        frame_utama.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_button_KeluarMouseClicked

    private void button_BatalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_BatalMouseClicked
        // TODO add your handling code here:
        membersihkan_teks();
        button_Tambah.setEnabled(true);
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
        button_Simpan.setEnabled(false);
        button_Batal.setEnabled(true);
        button_Keluar.setEnabled(true);
        nonaktif_teks();
    }//GEN-LAST:event_button_BatalMouseClicked

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        textField_NIM.setEditable(false);
        
        textField_Kehadiran.setEnabled(false);
        textField_Tugas1.setEnabled(false);
        textField_Tugas2.setEnabled(false);
        textField_Tugas3.setEnabled(false);
        textField_UTS.setEnabled(false);
        textField_UAS.setEnabled(false);
        
        textField_KodeMataKuliah.setEditable(false);
        textField_Angkatan.setEnabled(false);
        // untuk comboBox nama
        try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select nama from t_mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                String namaMhs = res.getString(1);
                comboBox_Nama.addItem(String.valueOf(namaMhs));
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
        
        // untuk comboBox nama mata kuliah
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

    private void comboBox_NamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBox_NamaActionPerformed
        // TODO add your handling code here:
        // deklarasi variable
        int namaMhs = comboBox_Nama.getSelectedIndex();
        
        if (namaMhs==0) {
            textField_NIM.setText("");
        } else {
            try {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(database, user, pass);
            Statement stt = kon.createStatement();
            String SQL = "select nama, nim from t_mahasiswa "
                        + "where nama= '"+comboBox_Nama.getSelectedItem()+"'";
            ResultSet res = stt.executeQuery(SQL);
            while (res.next()) {
                textField_NIM.setText(res.getString(2));
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
    }//GEN-LAST:event_comboBox_NamaActionPerformed

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

    private void textField_MasukkanDataKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textField_MasukkanDataKeyReleased
        // TODO add your handling code here:
        if (textField_MasukkanData.getText().isEmpty()) {
          tableModel.setRowCount(0);
          settableload();
      } else {
        // menghapus seluruh isi data di dalam jtable
        tableModel.setRowCount(0);
        // gunakan query untuk mencari
        try
        {
            Class.forName(driver);
            Connection kon = DriverManager.getConnection(
                             database,
                             user,
                             pass);
            Statement stt = kon.createStatement();
            String SQL = "select kode_nilai, nim, kode_mk, nama, nama_mk, absensi,"
                        + "tgs1, tgs2, tgs3, uts, uas,"
                        + "nilai_absen, nilai_tugas, nilai_uts, nilai_uas,"
                        + "nilai_akhir, indeks, keterangan from t_nilai where nama like '%"+textField_MasukkanData.getText()+"%'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(4);
                data[1] = res.getString(5);
                data[2] = res.getString(6);
                data[3] = res.getString(7);
                data[4] = res.getString(8);
                data[5] = res.getString(9);
                data[6] = res.getString(10);
                data[7] = res.getString(11);
                data[8] = res.getString(12);
                data[9] = res.getString(13);
                data[10] = res.getString(14);
                data[11] = res.getString(15);
                data[12] = res.getString(16);
                data[13] = res.getString(17);
                data[14] = res.getString(18);
                tableModel.addRow(data);
            }
            res.close();
            stt.close();
            kon.close();
        }
        catch (Exception ex)
        {
            System.err.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error",
                    JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
      }
    }//GEN-LAST:event_textField_MasukkanDataKeyReleased

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
            java.util.logging.Logger.getLogger(frame_nilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame_nilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame_nilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame_nilaiMahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame_nilaiMahasiswa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton button_Batal;
    private javax.swing.JButton button_Hapus;
    private javax.swing.JButton button_Keluar;
    private javax.swing.JButton button_Simpan;
    private javax.swing.JButton button_Tambah;
    private javax.swing.JButton button_Ubah;
    private javax.swing.JComboBox<String> comboBox_Nama;
    private javax.swing.JComboBox<String> comboBox_NamaMataKuliah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tabel_DataNilaiMahasiswa;
    private com.toedter.calendar.JYearChooser textField_Angkatan;
    private javax.swing.JTextField textField_Kehadiran;
    private javax.swing.JTextField textField_KodeMataKuliah;
    private javax.swing.JTextField textField_MasukkanData;
    private javax.swing.JTextField textField_NIM;
    private javax.swing.JTextField textField_Tugas1;
    private javax.swing.JTextField textField_Tugas2;
    private javax.swing.JTextField textField_Tugas3;
    private javax.swing.JTextField textField_UAS;
    private javax.swing.JTextField textField_UTS;
    // End of variables declaration//GEN-END:variables
}
