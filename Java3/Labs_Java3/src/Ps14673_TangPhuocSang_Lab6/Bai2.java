/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab6;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author TuanDuc
 */
public class Bai2 extends javax.swing.JFrame{
    String url = "jdbc:sqlserver://localhost; databaseName = PS10576_QLHocSinh; user = sa";
    String[] str = {"MAHOCSINH", "TENHOCSINH", "DIACHI", "TENCHAME", "DIENTHOAI", "MATIEUCHUAN", "PHI"};
    DefaultTableModel model = new DefaultTableModel(str, 0);
    
    void col(int a, int b)
    {
        for (int i = a; i <= b; i++)
        {
            tblHS.getColumnModel().getColumn(i).setMinWidth(0);
            tblHS.getColumnModel().getColumn(i).setMaxWidth(0);
        }
    }
    void bang()
    {
        try {
            Connection con = DriverManager.getConnection(url);
            Statement st = con.createStatement();
            String sql = "select HOCSINH.*, PHI from HOCSINH, TIEUCHUAN where HOCSINH.MATIEUCHUAN = TIEUCHUAN.MATIEUCHUAN";
            ResultSet rs = st.executeQuery(sql);
            while (rs.next())
            {
                Vector data = new Vector();
                data.add(rs.getString("MAHOCSINH"));
                data.add(rs.getString("TENHOCSINH"));
                data.add(rs.getString("DIACHI"));
                data.add(rs.getString("TENCHAME"));
                data.add(rs.getString("DIENTHOAI"));
                data.add(rs.getString("MATIEUCHUAN"));
                data.add(rs.getString("PHI"));
                model.addRow(data);
            }
            tblHS.setModel(model);
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
            JOptionPane.showMessageDialog(this, "Lỗi!");
        }
        col(2, 4);
    }
    
    void macDinh(boolean kt)
    {
        txtMa.setEnabled(kt);
        txtTen.setEnabled(kt);
        txtaDiaChi.setEnabled(kt);
        txtTenChaMe.setEnabled(kt);
        txtDienThoai.setEnabled(kt);
        cbxTieuChuan.setEnabled(kt);
        cbxPhi.setEnabled(kt);
    }
    
    void moi()
    {
        macDinh(true);
        txtMa.setText(null);
        txtTen.setText(null);
        txtaDiaChi.setText(null);
        txtTenChaMe.setText(null);
        txtDienThoai.setText(null);
        cbxTieuChuan.setSelectedIndex(0);
        cbxPhi.setSelectedIndex(0);
        txtMa.requestFocus();
    }
    
    void combobox()
    {
        try {
            Connection con = DriverManager.getConnection(url);
            String sql = "select * from TIEUCHUAN";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next())
            {
                cbxTieuChuan.addItem(rs.getString(1));
                cbxPhi.addItem(rs.getInt(2));
            }
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    void hienThi()
    {
        String ma = (String)tblHS.getValueAt(tblHS.getSelectedRow(), 0);
        String ten = (String)tblHS.getValueAt(tblHS.getSelectedRow(), 1);
        String dc = (String)tblHS.getValueAt(tblHS.getSelectedRow(), 2);
        String tencm = (String)tblHS.getValueAt(tblHS.getSelectedRow(), 3);
        String dt = (String)tblHS.getValueAt(tblHS.getSelectedRow(), 4);
        String tc = (String)tblHS.getValueAt(tblHS.getSelectedRow(), 5);
        int phi = Integer.parseInt((String)tblHS.getValueAt(tblHS.getSelectedRow(), 6));
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtaDiaChi.setText(dc);
        txtTenChaMe.setText(tencm);
        txtDienThoai.setText(dt);
        cbxTieuChuan.setSelectedItem(tc);
        cbxPhi.setSelectedItem((int)phi);
        macDinh(false);
    }
    
    void thoat()
    {
        int r = JOptionPane.showConfirmDialog(this, "Bạn thật sự muốn thoát?", "Thông báo", JOptionPane.YES_NO_OPTION);
        if (r == JOptionPane.YES_OPTION)
            System.exit(0);
    }
    
    void them()
    {
        if (txtMa.getText().isEmpty())
        {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập mã!");
            txtMa.requestFocus();
        }
        else
        {
            try {
                Connection con = DriverManager.getConnection(url);
                String sql = "insert into HOCSINH values(?, ?, ?, ?, ?, ?)";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, txtMa.getText());
                ps.setString(2, txtTen.getText());
                ps.setString(3, txtaDiaChi.getText());
                ps.setString(4, txtTenChaMe.getText());
                ps.setString(5, txtDienThoai.getText());
                ps.setString(6, (String)cbxTieuChuan.getSelectedItem());
                boolean r = ps.execute();
                if (r == false)
                {
                    JOptionPane.showMessageDialog(this, "Thêm thành công!");
                    model.setRowCount(0);
                    tblHS.setModel(model);
                    bang();
                }
                else
                    JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            } 
            catch (Exception ex) 
            {
                System.out.println(ex);
                JOptionPane.showMessageDialog(this, "Lỗi!");
            }
        }
    }
    
    void chinhSua()
    {
        macDinh(true);
        txtMa.setEnabled(false);
        cbxPhi.setEnabled(false);
        txtTen.requestFocus();
    }
    
    void capNhat()
    {
        try {
            Connection con = DriverManager.getConnection(url);
            String sqlHS = "update HOCSINH set TENHOCSINH = ?, DIACHI = ?, TENCHAME = ?, DIENTHOAI = ?, MATIEUCHUAN = ? where MAHOCSINH = ?";
            PreparedStatement psHS = con.prepareStatement(sqlHS);
            psHS.setString(1, txtTen.getText());
            psHS.setString(2, txtaDiaChi.getText());
            psHS.setString(3, txtTenChaMe.getText());
            psHS.setString(4, txtDienThoai.getText());
            psHS.setString(5, (String)cbxTieuChuan.getSelectedItem());
            psHS.setString(6, txtMa.getText());
            int rHS = psHS.executeUpdate();
            if (rHS > 0)
            {
                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
                model.setRowCount(0);
                tblHS.setModel(model);
                bang();
            }
            else
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại!");
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    void xoa()
    {
        try 
        {
            Connection con = DriverManager.getConnection(url);
            String sql = "delete from HOCSINH where MAHOCSINH = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, txtMa.getText());
            int r = ps.executeUpdate();
            if (r > 0)
            {
                JOptionPane.showMessageDialog(this, "Xóa thành công!");
                model.setRowCount(0);
                tblHS.setModel(model);
                bang();
            }
            else
                JOptionPane.showMessageDialog(this, "Chọn học sinh cần xóa.");
        } 
        catch (Exception ex) 
        {
            System.out.println(ex);
        }
    }
    
    int i = -1;
    void code()
    {
        String ma = (String)tblHS.getValueAt(i, 0);
        String ten = (String)tblHS.getValueAt(i, 1);
        String dc = (String)tblHS.getValueAt(i, 2);
        String tencm = (String)tblHS.getValueAt(i, 3);
        String dt = (String)tblHS.getValueAt(i, 4);
        String tc = (String)tblHS.getValueAt(i, 5);
        int phi = Integer.parseInt((String)tblHS.getValueAt(i, 6));
        txtMa.setText(ma);
        txtTen.setText(ten);
        txtaDiaChi.setText(dc);
        txtTenChaMe.setText(tencm);
        txtDienThoai.setText(dt);
        cbxTieuChuan.setSelectedItem(tc);
        cbxPhi.setSelectedItem((int)phi);
    }
    
    void tien()
    {
        macDinh(false);
        i++;
        code();
        if (i == tblHS.getRowCount() - 1)
        {
            JOptionPane.showMessageDialog(this, "Đang ở cuối danh sách");
            btnTien.setEnabled(false);
            btnLui.setEnabled(true);
        }
    }
    
    void lui()
    {
        macDinh(false);
        i--;
        if (i == -1)
        {
            JOptionPane.showMessageDialog(this, "Đang ở đầu danh sách");
            btnLui.setEnabled(false);
            btnTien.setEnabled(true);
        }
        else
            code();
    }
    
    public Bai2() {
        initComponents();
        setLocationRelativeTo(this);
        bang();
        macDinh(false);
        combobox();
        tblHS.getColumnModel().getColumn(6).setMinWidth(0);
        tblHS.getColumnModel().getColumn(6).setMaxWidth(0);
        tblHS.getColumnModel().getColumn(0).setMinWidth(0);
        tblHS.getColumnModel().getColumn(0).setMaxWidth(0);
        btnLui.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField2 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHS = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtaDiaChi = new javax.swing.JTextArea();
        txtTenChaMe = new javax.swing.JTextField();
        txtDienThoai = new javax.swing.JTextField();
        cbxTieuChuan = new javax.swing.JComboBox();
        cbxPhi = new javax.swing.JComboBox();
        btnMoi = new javax.swing.JButton();
        btnTien = new javax.swing.JButton();
        btnThem = new javax.swing.JButton();
        btnLui = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnChinhSua = new javax.swing.JButton();
        btnCapNhat = new javax.swing.JButton();
        btnThoat = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txtTen = new javax.swing.JTextField();

        jTextField2.setText("jTextField2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblHS.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        tblHS.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã", "Tên", "Địa chỉ", "Tên cha mẹ", "điện thoại", "Tiêu chuẩn", "Phí"
            }
        ));
        tblHS.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHSMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblHS);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Mã học sinh:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("Địa chỉ:");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel3.setText("Tên cha/mẹ:");

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel4.setText("Điện thoại:");

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel5.setText("Tiêu chuẩn");

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel6.setText("Phí:");

        txtMa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        txtaDiaChi.setColumns(20);
        txtaDiaChi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtaDiaChi.setRows(5);
        jScrollPane2.setViewportView(txtaDiaChi);

        txtTenChaMe.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        txtDienThoai.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        txtDienThoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDienThoaiActionPerformed(evt);
            }
        });

        cbxTieuChuan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        cbxPhi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        btnMoi.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnMoi.setText("Mới");
        btnMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnMoiMouseClicked(evt);
            }
        });

        btnTien.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTien.setText("Tiến");
        btnTien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTienMouseClicked(evt);
            }
        });

        btnThem.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnLui.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnLui.setText("Lùi");
        btnLui.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLuiMouseClicked(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setText("Xóa");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btnChinhSua.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnChinhSua.setText("Chỉnh sửa");
        btnChinhSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChinhSuaMouseClicked(evt);
            }
        });

        btnCapNhat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCapNhat.setText("Cập nhật");
        btnCapNhat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCapNhatMouseClicked(evt);
            }
        });

        btnThoat.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnThoat.setText("Thoát");
        btnThoat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThoatMouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel7.setText("Tên:");

        txtTen.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxTieuChuan, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtDienThoai)
                            .addComponent(cbxPhi, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(btnTien, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(btnMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(btnLui, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnChinhSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnThoat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCapNhat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addComponent(txtTenChaMe)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMa)
                            .addComponent(txtTen))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(15, 15, 15)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtTenChaMe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbxTieuChuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbxPhi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMoi)
                            .addComponent(btnThem)
                            .addComponent(btnChinhSua)
                            .addComponent(btnCapNhat))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnLui)
                            .addComponent(btnTien)
                            .addComponent(btnXoa)
                            .addComponent(btnThoat))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDienThoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDienThoaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDienThoaiActionPerformed

    private void btnMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMoiMouseClicked
        moi();
    }//GEN-LAST:event_btnMoiMouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        them();
    }//GEN-LAST:event_btnThemMouseClicked

    private void btnChinhSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChinhSuaMouseClicked
        chinhSua();
    }//GEN-LAST:event_btnChinhSuaMouseClicked

    private void btnCapNhatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCapNhatMouseClicked
        capNhat();
    }//GEN-LAST:event_btnCapNhatMouseClicked

    private void btnTienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTienMouseClicked
        tien();
    }//GEN-LAST:event_btnTienMouseClicked

    private void btnLuiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLuiMouseClicked
        lui();
    }//GEN-LAST:event_btnLuiMouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        xoa();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnThoatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThoatMouseClicked
        thoat();
    }//GEN-LAST:event_btnThoatMouseClicked

    private void tblHSMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHSMouseClicked
        hienThi();
    }//GEN-LAST:event_tblHSMouseClicked

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
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JButton btnLui;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnTien;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox cbxPhi;
    private javax.swing.JComboBox cbxTieuChuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTable tblHS;
    private javax.swing.JTextField txtDienThoai;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtTen;
    private javax.swing.JTextField txtTenChaMe;
    private javax.swing.JTextArea txtaDiaChi;
    // End of variables declaration//GEN-END:variables

}
