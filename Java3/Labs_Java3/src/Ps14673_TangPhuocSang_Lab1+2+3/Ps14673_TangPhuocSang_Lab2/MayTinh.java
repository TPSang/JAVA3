/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Lab2;

import javax.swing.JOptionPane;

/**
 *
 * @author TuanDuc
 */
public class MayTinh extends javax.swing.JFrame {
    String str = "";
    String q = "";
    
    void so1()
    {
        str += "1";
        lblXuat.setText(str);
    }
    
    void so2()
    {
        str += "2";
        lblXuat.setText(str);
    }
    
    void so3()
    {
        str += "3";
        lblXuat.setText(str);
    }
    
    void so4()
    {
        str += "4";
        lblXuat.setText(str);
    }
    
    void so5()
    {
        str += "5";
        lblXuat.setText(str);
    }
     
    void so6()
    {
        str += "6";
        lblXuat.setText(str);
    }
    
    void so7()
    {
        str += "7";
        lblXuat.setText(str);
    }
    
    void so8()
    {
        str += "8";
        lblXuat.setText(str);
    }
    
    void so9()
    {
        str += "9";
        lblXuat.setText(str);
    }
    
    void so0()
    {
        str += "0";
        lblXuat.setText(str);
    }
    
    void xoa()
    {
        lblXuat.setText("0");
        str = "";
    }
    
    String pheptoan = "";
    void cong()
    {
        pheptoan = "+";
        q = str;
        str = "";
        lblXuat.setText(pheptoan);
    }
    
    void tru()
    {
        pheptoan = "-";
        q = str;
        str = "";
        lblXuat.setText(pheptoan);
    }
    
    void nhan()
    {
        pheptoan = "*";
        q = str;
        str = "";
        lblXuat.setText(pheptoan);
    }
    
    void chia()
    {
        pheptoan = "/";
        q = str;
        str = "";
        lblXuat.setText(pheptoan);
    }
    
    void phanTram()
    {
        pheptoan = "%";
        q = str;
        str = "";
        lblXuat.setText(pheptoan);
    }
    
    void bang()
    {
        long a = Long.parseLong(q);
        long b = Long.parseLong(str);
        long kq = 0;
        if (pheptoan.equals("+"))
        {
            kq = a + b;
            lblXuat.setText(String.valueOf(kq));
        }
        else if (pheptoan.equals("-"))
        {
            kq = a - b;
            lblXuat.setText(String.valueOf(kq));
        }
        else if (pheptoan.equals("*"))
        {
            kq = a * b;
            lblXuat.setText(String.valueOf(kq));
        }
        else if (pheptoan.equals("/"))
        {
            if (a != 0)
            {
                kq = a / b;
                lblXuat.setText(String.valueOf(kq));
            }
            else
                JOptionPane.showMessageDialog(this, "a phải lớn hơn 0");
        }
        else if (pheptoan.equals("%"))
        {
            kq = a % b;
            lblXuat.setText(String.valueOf(kq));
        }
    }
    
    void can()
    {
        lblXuat.setText(String.valueOf(Math.sqrt(Double.parseDouble(str))));
    }
    
    void x1()
    {
        double b = Double.parseDouble(str);
        double kq = 0d;
        kq = 1 / b ;
        lblXuat.setText(String.valueOf(kq));
    }
    
    public MayTinh() {
        initComponents();
        setLocationRelativeTo(this);
        lblXuat.setText("0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn1 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        btn10 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        btnCongTru = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        btn9 = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnNhan = new javax.swing.JButton();
        btnTru = new javax.swing.JButton();
        btnCong = new javax.swing.JButton();
        btnChia = new javax.swing.JButton();
        btnPhanTram = new javax.swing.JButton();
        btn1X = new javax.swing.JButton();
        btnBang = new javax.swing.JButton();
        btnCang = new javax.swing.JButton();
        lblXuat = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Máy tính");

        btn1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn1.setText("1");
        btn1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn1MouseClicked(evt);
            }
        });

        btn4.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn4.setText("4");
        btn4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn4MouseClicked(evt);
            }
        });

        btn7.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn7.setText("7");
        btn7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn7MouseClicked(evt);
            }
        });

        btn10.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn10.setText("0");
        btn10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn10MouseClicked(evt);
            }
        });

        btn5.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn5.setText("5");
        btn5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn5MouseClicked(evt);
            }
        });

        btn8.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn8.setText("8");
        btn8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn8MouseClicked(evt);
            }
        });

        btnCongTru.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCongTru.setText("+/-");

        btn2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn2.setText("2");
        btn2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn2MouseClicked(evt);
            }
        });

        btn6.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn6.setText("6");
        btn6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn6MouseClicked(evt);
            }
        });

        btn9.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn9.setText("9");
        btn9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn9MouseClicked(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnXoa.setText("C");
        btnXoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXoaMouseClicked(evt);
            }
        });

        btn3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn3.setText("3");
        btn3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn3MouseClicked(evt);
            }
        });

        btnNhan.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnNhan.setText("*");
        btnNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNhanMouseClicked(evt);
            }
        });

        btnTru.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnTru.setText("-");
        btnTru.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnTruMouseClicked(evt);
            }
        });

        btnCong.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCong.setText("+");
        btnCong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCongMouseClicked(evt);
            }
        });

        btnChia.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnChia.setText("/");
        btnChia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnChiaMouseClicked(evt);
            }
        });

        btnPhanTram.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnPhanTram.setText("%");
        btnPhanTram.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPhanTramMouseClicked(evt);
            }
        });

        btn1X.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btn1X.setText("1/x");
        btn1X.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn1XMouseClicked(evt);
            }
        });

        btnBang.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnBang.setText("=");
        btnBang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBangMouseClicked(evt);
            }
        });

        btnCang.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        btnCang.setText("sqrt");
        btnCang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCangMouseClicked(evt);
            }
        });

        lblXuat.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblXuat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblXuat.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel2.setText("CASIO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(lblXuat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn4, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
                                .addComponent(btn7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCongTru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btn3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnXoa, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                                .addComponent(btn9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCong, javax.swing.GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                .addComponent(btnTru, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnChia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnCang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnPhanTram, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btn1X, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPhanTram)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn1X)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBang))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnChia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNhan)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTru)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCong))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnXoa))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnCongTru))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btn10))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1MouseClicked
        so1();
    }//GEN-LAST:event_btn1MouseClicked

    private void btn2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn2MouseClicked
        so2();
    }//GEN-LAST:event_btn2MouseClicked

    private void btn3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn3MouseClicked
        so3();
    }//GEN-LAST:event_btn3MouseClicked

    private void btn4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn4MouseClicked
        so4();
    }//GEN-LAST:event_btn4MouseClicked

    private void btn5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn5MouseClicked
        so5();
    }//GEN-LAST:event_btn5MouseClicked

    private void btn6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn6MouseClicked
        so6();
    }//GEN-LAST:event_btn6MouseClicked

    private void btn7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn7MouseClicked
        so7();
    }//GEN-LAST:event_btn7MouseClicked

    private void btn8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn8MouseClicked
        so8();
    }//GEN-LAST:event_btn8MouseClicked

    private void btn9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn9MouseClicked
        so9();
    }//GEN-LAST:event_btn9MouseClicked

    private void btn10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn10MouseClicked
        so0();
    }//GEN-LAST:event_btn10MouseClicked

    private void btnXoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXoaMouseClicked
        xoa();
    }//GEN-LAST:event_btnXoaMouseClicked

    private void btnCongMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCongMouseClicked
        cong();
    }//GEN-LAST:event_btnCongMouseClicked

    private void btnTruMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnTruMouseClicked
        tru();
    }//GEN-LAST:event_btnTruMouseClicked

    private void btnNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNhanMouseClicked
        nhan();
    }//GEN-LAST:event_btnNhanMouseClicked

    private void btnChiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnChiaMouseClicked
        chia();
    }//GEN-LAST:event_btnChiaMouseClicked

    private void btnBangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBangMouseClicked
        bang();
    }//GEN-LAST:event_btnBangMouseClicked

    private void btnCangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCangMouseClicked
        can();
    }//GEN-LAST:event_btnCangMouseClicked

    private void btnPhanTramMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPhanTramMouseClicked
        phanTram();
    }//GEN-LAST:event_btnPhanTramMouseClicked

    private void btn1XMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn1XMouseClicked
        x1();
    }//GEN-LAST:event_btn1XMouseClicked

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
            java.util.logging.Logger.getLogger(MayTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MayTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MayTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MayTinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MayTinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn10;
    private javax.swing.JButton btn1X;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnBang;
    private javax.swing.JButton btnCang;
    private javax.swing.JButton btnChia;
    private javax.swing.JButton btnCong;
    private javax.swing.JButton btnCongTru;
    private javax.swing.JButton btnNhan;
    private javax.swing.JButton btnPhanTram;
    private javax.swing.JButton btnTru;
    private javax.swing.JButton btnXoa;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblXuat;
    // End of variables declaration//GEN-END:variables
}
