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

// fungsi import yg digunakan untuk tanggal
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author RizalSihombing
 */
public class frame_mahasiswa extends javax.swing.JFrame {
    
    // deklarasi variable global untuk database
    koneksi dbsetting;
    String driver, database, user, pass;
    Object tabel;
    
    // deklarasi variable global untuk data
    String data[] = new String[5];

    /**
     * Creates new form frame_mahasiswa
     */
    public frame_mahasiswa() {
        initComponents();
        
        dbsetting = new koneksi();
        driver = dbsetting.SettingPanel("DBDriver");
        database = dbsetting.SettingPanel("DBDatabase");
        user = dbsetting.SettingPanel("DBUsername");
        pass = dbsetting.SettingPanel("DBPassword");
        
        tabel_DataMahasiswa.setModel(tableModel);
        settableload();
    }
    
    private javax.swing.table.DefaultTableModel tableModel = getDefaultTabelModel();
    private javax.swing.table.DefaultTableModel getDefaultTabelModel() {
        // membuat judul header
        return new javax.swing.table.DefaultTableModel (
            new Object[][] {},
            new String [] {"NIM", "NAMA", "TEMPAT LAHIR", "TANGGAL LAHIR", "ALAMAT"}
        )
        // disable perubahan pada grid
        {
            boolean[] canEdit = new boolean[] {
                false, false, false, false, false
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
            String SQL = "select * from t_mahasiswa";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next()) {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
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
    
    // fungsi membersihkan teks
    public void membersihkan_teks() {
        textField_MasukkanData.setText("");
        textField_NIM.setText("");
        textField_Nama.setText("");
        textField_TempatLahir.setText("");
        textField_TanggalLahir.setCalendar(null);
        textArea_Alamat.setText("");
    }
    
    // fungsi menonaktifkan teks
    public void nonaktif_teks() {
//        textField_MasukkanData.setEnabled(false);
        textField_NIM.setEnabled(false);
        textField_Nama.setEnabled(false);
        textField_TempatLahir.setEnabled(false);
        textField_TanggalLahir.setEnabled(false);
        textArea_Alamat.setEnabled(false);
    }
    
    // fungsi aktif teks
    public void aktif_teks() {
//        textField_MasukkanData.setEnabled(true);
        textField_NIM.setEnabled(true);
        textField_Nama.setEnabled(true);
        textField_TempatLahir.setEnabled(true);
        textField_TanggalLahir.setEnabled(true);
        textArea_Alamat.setEnabled(true);
    }
    
    // fungsi menampilkan data di dalam masing-masing jTextField
    int row = 0;
    public void tampil_field() {
        row = tabel_DataMahasiswa.getSelectedRow();
        textField_NIM.setText(tableModel.getValueAt(row, 0).toString());
        textField_Nama.setText(tableModel.getValueAt(row, 1).toString());
        textField_TempatLahir.setText(tableModel.getValueAt(row, 2).toString());
//        textField_TanggalLahir.setText(tableModel.getValueAt(row, 3).toString());
        String s = (String)(tableModel.getValueAt(row, 3));
        try{
            SimpleDateFormat f=new SimpleDateFormat("yyyy-M-dd"); 
            java.util.Date d=f.parse(s);
            textField_TanggalLahir.setDate(d);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        textArea_Alamat.setText(tableModel.getValueAt(row, 4).toString());
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
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        textField_MasukkanData = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textField_NIM = new javax.swing.JTextField();
        textField_Nama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textField_TempatLahir = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textArea_Alamat = new javax.swing.JTextArea();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_DataMahasiswa = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();
        button_Tambah = new javax.swing.JButton();
        button_Ubah = new javax.swing.JButton();
        button_Hapus = new javax.swing.JButton();
        button_Simpan = new javax.swing.JButton();
        button_Batal = new javax.swing.JButton();
        button_Keluar = new javax.swing.JButton();
        textField_TanggalLahir = new com.toedter.calendar.JDateChooser();

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
        jLabel1.setText("FORM MAHASISWA");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(34, Short.MAX_VALUE))
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
        jLabel6.setText("Tempat Lahir");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Tanggal Lahir");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setText("Alamat");

        textArea_Alamat.setColumns(20);
        textArea_Alamat.setRows(5);
        jScrollPane1.setViewportView(textArea_Alamat);

        tabel_DataMahasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIM", "NAMA", "TEMPAT LAHIR", "TANGGAL LAHIR", "ALAMAT"
            }
        ));
        tabel_DataMahasiswa.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tabel_DataMahasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_DataMahasiswaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_DataMahasiswa);

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
        button_Hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_HapusActionPerformed(evt);
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
        button_Batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_BatalActionPerformed(evt);
            }
        });

        button_Keluar.setText("KELUAR");
        button_Keluar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button_KeluarMouseClicked(evt);
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
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textField_TempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(textField_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(105, 105, 105)
                                .addComponent(textField_NIM, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textField_TanggalLahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane2)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(35, 35, 35)
                                .addComponent(textField_MasukkanData, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(button_Tambah)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addComponent(button_Ubah)
                        .addGap(45, 45, 45)
                        .addComponent(button_Hapus)
                        .addGap(51, 51, 51)
                        .addComponent(button_Simpan)
                        .addGap(50, 50, 50)
                        .addComponent(button_Batal)
                        .addGap(59, 59, 59)
                        .addComponent(button_Keluar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textField_MasukkanData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(textField_NIM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(textField_Nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(textField_TempatLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(button_Tambah)
                            .addComponent(button_Ubah)
                            .addComponent(button_Hapus)
                            .addComponent(button_Simpan)
                            .addComponent(button_Batal)
                            .addComponent(button_Keluar)))
                    .addComponent(textField_TanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 13, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void button_TambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_TambahMouseClicked
        // TODO add your handling code here:
        membersihkan_teks();
        textField_NIM.requestFocus();
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
        button_Tambah.setEnabled(false);
        button_Simpan.setEnabled(true);
        button_Keluar.setEnabled(false);
        aktif_teks();
    }//GEN-LAST:event_button_TambahMouseClicked

    private void button_SimpanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_SimpanMouseClicked
        // TODO add your handling code here:
        String data[]=new String[5];
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String tanggallahir = String.valueOf(f.format(textField_TanggalLahir.getDate()));
        if ((textField_NIM.getText().isEmpty()) || (textField_Nama.getText().isEmpty()) || 
                (textField_TempatLahir.getText().isEmpty()) || (tanggallahir.isEmpty()) || 
                (textArea_Alamat.getText().isEmpty())) {
            JOptionPane.showMessageDialog(null,
                            "Data Tidak Boleh Kosong, Silahkan Dilengkapi!");
            textField_NIM.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String    SQL = "INSERT INTO t_mahasiswa(nim,"
                                + "nama,"
                                + "tempat_lahir,"
                                + "tgl_lahir,"
                                + "alamat) "
                                   + "VALUES "
                                + "( '"+textField_NIM.getText()+"',"
                                + "'"+textField_Nama.getText()+" ' ,"
                                + "'"+textField_TempatLahir.getText()+" ',"
                                + "'"+tanggallahir+" ',"
                                + "'"+textArea_Alamat.getText()+" ')";
                
                stt.executeUpdate(SQL);
                data[0] = textField_NIM.getText();
                data[1] = textField_Nama.getText();
                data[2] = textField_TempatLahir.getText();
                data[3] = tanggallahir;
                data[4] = textArea_Alamat.getText();
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

    private void tabel_DataMahasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_DataMahasiswaMouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 1) {
            tampil_field();
        }
    }//GEN-LAST:event_tabel_DataMahasiswaMouseClicked

    private void button_UbahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button_UbahMouseClicked
        // TODO add your handling code here:
        String NIM = textField_NIM.getText();
        String Nama = textField_Nama.getText();
        String TempatLahir = textField_TempatLahir.getText();
        SimpleDateFormat f=new SimpleDateFormat("yyyy-MM-dd");
        String tanggallahir = String.valueOf(f.format(textField_TanggalLahir.getDate()));
        String Alamat = textArea_Alamat.getText();
        
        if ((NIM.isEmpty()) || (Nama.isEmpty()) || (TempatLahir.isEmpty()) || (tanggallahir.isEmpty()) || (Alamat.isEmpty())) {
            JOptionPane.showMessageDialog(null, "Data Tidak Boleh Kosong, Silahkan Dilengkapi!");
            textField_NIM.requestFocus();
        } else {
            try {
                Class.forName(driver);
                Connection kon = DriverManager.getConnection(database, user, pass);
                Statement stt = kon.createStatement();
                String SQL = "UPDATE `t_mahasiswa` "
                                + "SET `nim`='"+NIM+"',"
                                + "`nama`='"+Nama+"',"
                                + "`tempat_lahir`='"+TempatLahir+"',"
                                + "`tgl_lahir`='"+tanggallahir+"',"
                                + "`alamat`='"+Alamat+"' "
                            + "WHERE "
                            + "`nim`='"+tableModel.getValueAt(row, 0).toString()+"';";
                stt.executeUpdate(SQL);
                data[0] = NIM;
                data[1] = Nama;
                data[2] = TempatLahir;
                data[3] = tanggallahir;
                data[4] = Alamat;
                tableModel.removeRow(row);
                tableModel.insertRow(row, data);
                stt.close();
                kon.close();
                membersihkan_teks();
                button_Simpan.setEnabled(false);
                button_Tambah.setEnabled(true);
                button_Ubah.setEnabled(false);
                button_Hapus.setEnabled(false);
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
            String  SQL = "DELETE FROM t_mahasiswa "
                        + "WHERE " + "nim='"+tableModel.getValueAt(row, 0).toString()+"'";
            stt.executeUpdate(SQL);
            tableModel.removeRow(row);
            stt.close();
            kon.close();
            button_Simpan.setEnabled(false);
            button_Hapus.setEnabled(false);
            button_Ubah.setEnabled(false);
            button_Tambah.setEnabled(true);
            button_Keluar.setEnabled(true);
            membersihkan_teks();
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

    private void button_HapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_HapusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_HapusActionPerformed

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
            String SQL = "select * from t_mahasiswa where nama like '%"+textField_MasukkanData.getText()+"%'";
            ResultSet res = stt.executeQuery(SQL);
            while(res.next())
            {
                data[0] = res.getString(1);
                data[1] = res.getString(2);
                data[2] = res.getString(3);
                data[3] = res.getString(4);
                data[4] = res.getString(5);
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

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        nonaktif_teks();
        button_Tambah.setEnabled(true);
        button_Simpan.setEnabled(false);
        button_Ubah.setEnabled(false);
        button_Hapus.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void button_BatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_BatalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_BatalActionPerformed

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
            java.util.logging.Logger.getLogger(frame_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frame_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frame_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frame_mahasiswa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frame_mahasiswa().setVisible(true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable tabel_DataMahasiswa;
    private javax.swing.JTextArea textArea_Alamat;
    private javax.swing.JTextField textField_MasukkanData;
    private javax.swing.JTextField textField_NIM;
    private javax.swing.JTextField textField_Nama;
    private com.toedter.calendar.JDateChooser textField_TanggalLahir;
    private javax.swing.JTextField textField_TempatLahir;
    // End of variables declaration//GEN-END:variables
}
