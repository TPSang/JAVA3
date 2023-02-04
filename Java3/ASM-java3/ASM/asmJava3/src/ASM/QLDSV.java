package ASM;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class QLDSV extends javax.swing.JFrame {

    ArrayList<DiemSV> list = new ArrayList<>();
    DefaultTableModel model;
    private Connection conn;
    int index = -1;
    String url ="jdbc:sqlserver://localhost:1433;databaseName=ASM;user=sa;password=123456";
    public QLDSV() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        model = (DefaultTableModel) tblQLDSV.getModel();
        loadData();
        fillToTable();
    }

    public void Timkiem(){
    int i;
    String ma = txtTimkiem.getText();
    for(i=0;i<list.size();i++){
        DiemSV diemsv = list.get(i);
        if(diemsv.masv.equals(ma)){
        JOptionPane.showMessageDialog(this,"Đã tìm được!","",JOptionPane.INFORMATION_MESSAGE);
        index=i;
        DisplayDiemSV();
        break;
        }
    }
    if(i== list.size()){
    JOptionPane.showMessageDialog(this,"Không tìm được!","",JOptionPane.INFORMATION_MESSAGE);
    txtTimkiem.requestFocus();
    }
    }

 
    public  void updateDiemTB(){try {
    
    Integer ta=Integer.parseInt(txtEnglish.getText());
    Integer th=Integer.parseInt(txtIt.getText());
    Integer gdtc=Integer.parseInt(txtGdtc.getText());
    float tb= (ta+th+gdtc)/3;
    lblDiemTB.setText(tb+"");  
        } catch (Exception e) {
            DiemSV diemSV= list.get(index);
            float tb= (float) ((diemSV.anh+diemSV.tin+diemSV.gdtc)/3);
            lblDiemTB.setText(tb+"");
        }
     }
    
    public void loadData(){
        list.clear();
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(url);
        
            stmt = conn.createStatement();
            String sql = "select ID,Grade.MASV as MASV,Hoten,Tienganh,TinHoc,GDTC from GRADE,Students where Grade.MASV = Students.MASV";
            try (ResultSet rs = stmt.executeQuery(sql)) {
                while(rs.next()){
                    int ID = rs.getInt("ID");
                    String MaSV=rs.getString("MASV");
                    String Hoten=rs.getString("Hoten");
                    int Tienganh=rs.getInt("Tienganh");
                    int TinHoc=rs.getInt("TinHoc");
                    int GDTC=rs.getInt("GDTC");
                    DiemSV diemsv= new DiemSV(ID,MaSV,Hoten,Tienganh,TinHoc,GDTC);
                    list.add(diemsv);
                }
                index = 0;
                DisplayDiemSV();
                updateDiemTB();
            }
            stmt.close();
            conn.close();
          } catch (SQLException ex) {
    }
        finally{
         if(stmt != null){try {
             stmt.close();
             } catch (SQLException ex) {
             }
        if(conn != null){try {
             conn.close();
             } catch (SQLException ex) {
             }
}   
        }
    }}
    private void DisplayDiemSV(){
        try {
    DiemSV diemSV= list.get(index);
    txtMaSV.setText(diemSV.masv+"");
    lblName.setText(diemSV.name+"");
    txtEnglish.setText(diemSV.anh+"");
    txtIt.setText(diemSV.tin+"");
    txtGdtc.setText(diemSV.gdtc+"");
    updateDiemTB();
    int s=index+1;
    lblRecord.setText("Record "+s+" of "+(list.size()));
    tblQLDSV.setRowSelectionInterval(index,index);
    } catch (Exception e) {
        }
    }
    void fillToTable(){
        model.setRowCount(0);
        list.stream().map((nv) -> new Object[]{nv.getId(), nv.getName(), nv.getAnh(), nv.getTin(), nv.getGdtc()}).forEachOrdered((row) -> {
            model.addRow(row);
        });
    }
     boolean check(){
        Boolean check = true;
        
        if (txtMaSV.getText().equals("") ||txtEnglish.getText().equals("") ||txtIt.getText().equals("") ||txtGdtc.getText().equals("")) {            
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống!");
            check = false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMasv().equals(txtMaSV.getText())) {            
                JOptionPane.showMessageDialog(this, "Mã sv đã tồn tại!");
                check = false;
                break;
            }
        }
        return check;
     }

    public void delete() {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "DELETE FROM GRADE WHERE MASV = ?";
        try {
            conn= DriverManager.getConnection(url);
            ps= conn.prepareStatement(sql); 
            ps.setString(1, txtMaSV.getText());
            ps.execute();
            JOptionPane.showMessageDialog(this,"Xóa thành công!");
            loadData();
            index=0;
            DisplayDiemSV();
        } catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this,"Lỗi");
        }finally{
         if(ps != null){try {
             ps.close();
             } catch (SQLException ex) {
             }
        if(conn != null){try {
             conn.close();
             } catch (SQLException ex) {
             }
}   
        }
    }
     
    }

    public void update() {
         String sql = "UPDATE GRADE SET TIENGANH = ?, TINHOC = ?, GDTC = ? WHERE MASV = ? ";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            ps.setInt(1, Integer.parseInt(txtEnglish.getText()));
            ps.setInt(2,Integer.parseInt(txtIt.getText()));
            ps.setInt(3,Integer.parseInt(txtGdtc.getText()));
            ps.setString(4,txtMaSV.getText());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this,"Update thành công");
        } catch (SQLException e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this,"Lỗi.....");
        }finally{
         if(ps != null){try {
             ps.close();
             } catch (SQLException ex) {
             }
        if(conn != null){try {
             conn.close();
             } catch (SQLException ex) {
             }
        }   
        }
    } 
    }
    public void save(){
        if (check()) {
        Connection conn = null;
        PreparedStatement ps = null;
        String sql = "INSERT INTO GRADE(MASV, TIENGANH, TINHOC, GDTC) VALUES ( ?, ?, ?, ?)";
     DiemSV sv = new DiemSV();
        sv.name = lblName.getText();
        sv.anh = Double.parseDouble(txtEnglish.getText());
        sv.tin = Double.parseDouble(txtIt.getText());
        sv.gdtc = Double.parseDouble(txtGdtc.getText());
        list.add(sv);
        try {
            conn = DriverManager.getConnection(url);
            ps=conn.prepareStatement(sql);
            ps.setString(1,txtMaSV.getText());
            ps.setInt(2, Integer.parseInt(txtEnglish.getText()));
            ps.setInt(3,Integer.parseInt(txtIt.getText()));
            ps.setInt(4,Integer.parseInt(txtGdtc.getText()));
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Lưu thành công!");
        }  catch (Exception e) {
          e.printStackTrace();
          JOptionPane.showMessageDialog(this,"Lỗi");
        }finally{
         if(ps != null){try {
             ps.close();
             } catch (SQLException ex) {
             }
        if(conn != null){try {
             conn.close();
             } catch (SQLException ex) {
             }
}   
        }
    }}}
    public void reset(){
        txtMaSV.setText(null);
        txtEnglish.setText(null);
        txtIt.setText(null);
        txtGdtc.setText(null);
        lblDiemTB.setText(null);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnTimkiem = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtTimkiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        TECHNOLOGY = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaSV = new javax.swing.JTextField();
        txtEnglish = new javax.swing.JTextField();
        txtIt = new javax.swing.JTextField();
        txtGdtc = new javax.swing.JTextField();
        aaaa = new javax.swing.JLabel();
        lblDiemTB = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        btnNew = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblQLDSV = new javax.swing.JTable();
        lblRecord = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, org.jdesktop.beansbinding.ELProperty.create("Quản Lý Điểm Sinh Viên"), this, org.jdesktop.beansbinding.BeanProperty.create("title"));
        bindingGroup.addBinding(binding);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FIND", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        btnTimkiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/search.png"))); // NOI18N
        btnTimkiem.setText("SEARCH");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("ID:");

        txtTimkiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTimkiem)
                .addGap(101, 101, 101))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(txtTimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 204));
        jLabel1.setText("Quản Lý Điểm Sinh Viên");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("FULL NAME");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("ID");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("ENGLISH");

        TECHNOLOGY.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        TECHNOLOGY.setText("TECHNOLOGY");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("EXERCISE");

        txtMaSV.setEditable(false);

        aaaa.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        aaaa.setForeground(new java.awt.Color(204, 0, 204));
        aaaa.setText("Điểm trung bình:");

        lblDiemTB.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(TECHNOLOGY)
                    .addComponent(jLabel8))
                .addGap(32, 32, 32)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaSV)
                    .addComponent(txtEnglish)
                    .addComponent(txtIt)
                    .addComponent(txtGdtc, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(aaaa))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(lblDiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel4)
                    .addComponent(lblName, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtMaSV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aaaa, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEnglish, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TECHNOLOGY)
                    .addComponent(txtIt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDiemTB, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtGdtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/add.png"))); // NOI18N
        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/delete.png"))); // NOI18N
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/edit.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/skip_backward.png"))); // NOI18N
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/rewind.png"))); // NOI18N
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/fast_forward.png"))); // NOI18N
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/skip_forward.png"))); // NOI18N
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        tblQLDSV.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "FULLNAME", "ENGLISH", "TECHNOLOGY", "EXERCISE"
            }
        ));
        tblQLDSV.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblQLDSVMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblQLDSV);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 153));
        jLabel7.setText("3 sinh viên có điểm cao nhất");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(btnSave, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                            .addComponent(btnNew, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                            .addComponent(btnDelete, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)
                                            .addComponent(btnUpdate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(28, 28, 28)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblRecord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnSave, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(lblRecord, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        reset();
        
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        save();
        
       
    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        delete();
        update();
        DisplayDiemSV();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        update();
        
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void tblQLDSVMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblQLDSVMouseClicked
        
    }//GEN-LAST:event_tblQLDSVMouseClicked

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFirstActionPerformed
        index = tblQLDSV.getSelectedRow();
        index=0;
        DisplayDiemSV();
        updateDiemTB();
    }//GEN-LAST:event_btnFirstActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        index = tblQLDSV.getSelectedRow();
        index--;
        if(index<0){
        JOptionPane.showMessageDialog(null,"Bạn đang ở đầu danh sách");
        index=0;
        return;
        }
        DisplayDiemSV();
        updateDiemTB();
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        index = tblQLDSV.getSelectedRow();
        index++;
        if (index>=list.size()) {JOptionPane.showMessageDialog(null,"Bạn đang ở cuối danh sách");
            index=list.size()-1;
            return;
        }
        DisplayDiemSV();
        updateDiemTB();
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        index = tblQLDSV.getSelectedRow();
        index=list.size()-1;
        DisplayDiemSV();
        updateDiemTB();
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        Timkiem();
    }//GEN-LAST:event_btnTimkiemActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(QLDSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(QLDSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(QLDSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(QLDSV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
           
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new QLDSV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TECHNOLOGY;
    private javax.swing.JLabel aaaa;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDiemTB;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRecord;
    private javax.swing.JTable tblQLDSV;
    private javax.swing.JTextField txtEnglish;
    private javax.swing.JTextField txtGdtc;
    private javax.swing.JTextField txtIt;
    private javax.swing.JTextField txtMaSV;
    private javax.swing.JTextField txtTimkiem;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
