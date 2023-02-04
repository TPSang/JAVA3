/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author TuanDuc
 */
public class Bai2 extends javax.swing.JFrame {
    ArrayList<HocSinh> arrHS = new ArrayList<>();
    int current = 0;
    String url = "jdbc:sqlserver://localhost; databaseName = PS10576_QLSV; user = sa";

    void dislay(int i)
    {
        HocSinh hs = arrHS.get(i);
        txtMaSV.setText(hs.getMASV());
        txtHoTen.setText(hs.getHOTEN());
        txtEmail.setText(hs.getEMAIL());
        txtSDT.setText(hs.getSDT());
        boolean gt = hs.getGIOITINH();
        if (gt == true)
            rdbNam.setSelected(true);
        else
            rdbNu.setSelected(true);
        txtaDiaChi.setText(hs.getDIACHI());
    }
    
    void ThemHSVaoAR()
    {
        try 
        {
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            String sql  = "Select * from SINHVIEN";
            ResultSet rs = st.executeQuery(sql);
            arrHS.clear();
            while(rs.next())
            {
                String masv = rs.getString(1);
                String hoten = rs.getString(2);
                String email = rs.getString(3);
                String sdt = rs.getString(4);
                boolean gt = rs.getBoolean(5);
                String diachi = rs.getString(6);
                HocSinh hs = new HocSinh(masv, hoten, email, sdt, gt, diachi);
                arrHS.add(hs);
            }
            con.close();
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    void clearText()
    {
        txtMaSV.setText(null);
        txtHoTen.setText(null);
        txtEmail.setText(null);
        txtSDT.setText(null);
        rdbNu.setSelected(false);
        rdbNam.setSelected(true);
        txtaDiaChi.setText(null);
        txtMaSV.requestFocus();
    }
    
    void xoa()
    {
        if (txtMaSV.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Nhập mã sinh viên");
            txtMaSV.requestFocus();
            txtMaSV.setText(null);
        }
        try {
            int r = JOptionPane.showConfirmDialog(this, "Bạn thật sự muốn xóa?", "Thông báo", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION)
            {
                Connection con = DriverManager.getConnection(url);
                String sql  = "delete from SINHVIEN where MASV = ?";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, txtMaSV.getText());
                st.execute();
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                clearText();
                arrHS.clear();
                ThemHSVaoAR();
                con.close();
            }
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Lỗi!");
        }
    }
    
    void them()
    {
        clearText();
    }
    
    void capNhat()
    {
        if (txtMaSV.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Nhập mã sinh viên");
            txtMaSV.requestFocus();
            txtMaSV.setText(null);
        }
        try 
        {
            Connection con = DriverManager.getConnection(url);
            String sql  = "update SINHVIEN set HOTEN = ?, EMAIL = ?, SDT = ?, GIOITINH = ?, DIACHI = ? where MASV = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtHoTen.getText());
            st.setString(2, txtEmail.getText());
            st.setString(3, txtSDT.getText());
            boolean gt = false;
            if (rdbNam.isSelected())
                gt = true;
            else
                gt = false;
            st.setBoolean(4, gt);
            st.setString(5, txtaDiaChi.getText());
            st.setString(6, txtMaSV.getText());
            st.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
            arrHS.clear();
            ThemHSVaoAR();
            con.close();
        } 
        catch (Exception e) 
        {
            System.out.println(e);
            JOptionPane.showMessageDialog(this, "Lỗi!");
        }
    }
    
    void luu()
    {
        if (txtMaSV.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Không bỏ trống mã sinh viên.");
            txtMaSV.requestFocus();
        }
        else
        {
            try 
            {
                Connection con = DriverManager.getConnection(url);
                String sql  = "insert into SINHVIEN values(?, ?, ?, ?, ?, ?)";
                PreparedStatement st = con.prepareStatement(sql);
                st.setString(1, txtMaSV.getText());
                st.setString(2, txtHoTen.getText());
                st.setString(3, txtEmail.getText());
                st.setString(4, txtSDT.getText());
                boolean gt = false;
                if (rdbNam.isSelected())
                    gt = true;
                else
                    gt = false;
                st.setBoolean(5, gt);
                st.setString(6, txtaDiaChi.getText());
                st.execute();
                JOptionPane.showMessageDialog(this, "Lưu thành công!");
                clearText();
                arrHS.clear();
                ThemHSVaoAR();
                con.close();
            } 
            catch (Exception ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "Lưu thất bại!");
            }
        }
    }
    
    void timKiem()
    {
        boolean kt = false;
        clearText();
        String ma = JOptionPane.showInputDialog(this, "Mã sinh viên");
        for (int i = 0; i < arrHS.size(); i++)
        {
            if (arrHS.get(i).getMASV().equalsIgnoreCase(ma))
            {
                kt = true;
                JOptionPane.showMessageDialog(this, "Tìm kiếm thành công!");
                txtMaSV.setText(arrHS.get(i).getMASV());
                txtHoTen.setText(arrHS.get(i).getHOTEN());
                txtEmail.setText(arrHS.get(i).getEMAIL());
                txtSDT.setText(arrHS.get(i).getSDT());
                boolean gt = arrHS.get(i).getGIOITINH();
                if (gt == true)
                    rdbNam.setSelected(true);
                else
                    rdbNu.setSelected(true);
                txtaDiaChi.setText(arrHS.get(i).getDIACHI());
                break;
            }
            else
            {
                kt = false;
            }
        }
        if (kt == false)
            JOptionPane.showMessageDialog(this, "Không tìm thấy " + ma);
    }
    
    public Bai2() {
        initComponents();
        setLocationRelativeTo(this);
        ThemHSVaoAR();
        dislay(current);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        butgroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        rdbNam = new javax.swing.JRadioButton();
        rdbNu = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtaDiaChi = new javax.swing.JTextArea();
        txtSDT = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnCuoi = new javax.swing.JButton();
        btnDau = new javax.swing.JButton();
        btnLuiDau = new javax.swing.JButton();
        btnLuiCuoi = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        btnTimKiem = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("QUẢN LÝ TÀI KHOẢN");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Mã sinh viên");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Họ tên");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Số điện thoại");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Giới tính");

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Địa chỉ");

        txtMaSV.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        butgroup1.add(rdbNam);
        rdbNam.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdbNam.setText("Nam");

        butgroup1.add(rdbNu);
        rdbNu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rdbNu.setText("Nữ");

        txtaDiaChi.setColumns(20);
        txtaDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtaDiaChi.setRows(5);
        jScrollPane1.setViewportView(txtaDiaChi);

        txtSDT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/plus.png"))); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/delete.png"))); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCapNhat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/Update.png"))); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });

        btnLuu.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLuu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/save.png"))); // NOI18N
        btnLuu.setText("Lưu");
        btnLuu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuuMouseClicked(evt);
            }
        });

        btnCuoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/next.png"))); // NOI18N
        btnCuoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCuoiMouseClicked(evt);
            }
        });

        btnDau.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/back.png"))); // NOI18N
        btnDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnDauMouseClicked(evt);
            }
        });

        btnLuiDau.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLuiDau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/first.png"))); // NOI18N
        btnLuiDau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuiDauMouseClicked(evt);
            }
        });

        btnLuiCuoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLuiCuoi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/last.png"))); // NOI18N
        btnLuiCuoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuiCuoiMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLuiCuoiMouseEntered(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel8.setText("Email");

        btnTimKiem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Lab5/search.png"))); // NOI18N
        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTimKiemMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuiDau, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLuiCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(43, 43, 43))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(55, 55, 55)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rdbNam)
                                        .addGap(18, 18, 18)
                                        .addComponent(rdbNu))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtSDT)
                                    .addComponent(txtMaSV)
                                    .addComponent(txtHoTen))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCapNhat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnLuu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(112, 112, 112)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCapNhat)
                    .addComponent(jLabel8)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rdbNam)
                        .addComponent(rdbNu)
                        .addComponent(btnTimKiem)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuiDau, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLuiCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCuoi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDauMouseClicked
        current = 0;
        dislay(current);
    }//GEN-LAST:event_btnDauMouseClicked

    private void btnCuoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCuoiMouseClicked
        current = arrHS.size() - 1;
        dislay(current);
    }//GEN-LAST:event_btnCuoiMouseClicked

    private void btnLuiCuoiMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuiCuoiMouseEntered

    }//GEN-LAST:event_btnLuiCuoiMouseEntered

    private void btnLuiCuoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuiCuoiMouseClicked
        current++;
        if (current >= arrHS.size())
        {
            JOptionPane.showMessageDialog(this, "Đang ở cuối danh sách!");
            return;
        }
        dislay(current);
    }//GEN-LAST:event_btnLuiCuoiMouseClicked

    private void btnLuiDauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuiDauMouseClicked
        current--;
        if (current < 0)
        {
            JOptionPane.showMessageDialog(this, "Đang ở đầu danh sách!");
            return;
        }
        dislay(current);
    }//GEN-LAST:event_btnLuiDauMouseClicked

    private void btnLuuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuuMouseClicked
        luu();
    }//GEN-LAST:event_btnLuuMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        xoa();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        them();
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked
        capNhat();
    }//GEN-LAST:event_btnCapNhatMouseClicked

    private void btnTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTimKiemMouseClicked
        timKiem();
    }//GEN-LAST:event_btnTimKiemMouseClicked

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
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Bai2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCapNhat;
    private javax.swing.JButton btnCuoi;
    private javax.swing.JButton btnDau;
    private javax.swing.JButton btnLuiCuoi;
    private javax.swing.JButton btnLuiDau;
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup butgroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdbNam;
    private javax.swing.JRadioButton rdbNu;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextArea txtaDiaChi;
    // End of variables declaration//GEN-END:variables
}
