package Lab5;

class HocSinh {
    private String MASV = "";
    private String HOTEN = "";
    private String EMAIL = "";
    private String SDT = "";
    private String DIACHI = "";
    private boolean GIOITINH = false;

    public HocSinh() 
    {
        
    }

    public HocSinh(String MASV, String HOTEN, String EMAIL, String SDT, boolean GIOITINH, String DIACHI) 
    {
        this.MASV = MASV;
        this.HOTEN = HOTEN;
        this.EMAIL = EMAIL;
        this.SDT = SDT;
        this.GIOITINH = GIOITINH;
        this.DIACHI = DIACHI;
    }

    // get set mã sinh viên
    public void setMASV(String MASV) 
    {
        this.MASV = MASV;
    }

    public String getMASV() 
    {
        return MASV;
    }
        
    // set set họ tên
    public void setHOTEN(String HOTEN) 
    {
        this.HOTEN = HOTEN;
    }

    public String getHOTEN() 
    {
        return HOTEN;
    }
    
    // get set email
    public void setEMAIL(String EMAIL) 
    {
        this.EMAIL = EMAIL;
    }

    public String getEMAIL() 
    {
        return EMAIL;
    }
    
    // get set số điện thoại
    public void setSDT(String SDT) 
    {
        this.SDT = SDT;
    }

    public String getSDT() 
    {
        return SDT;
    }
    
    // set set địa chỉ
    public void setDIACHI(String DIACHI) 
    {
        this.DIACHI = DIACHI;
    }
    
    public String getDIACHI() 
    {
        return DIACHI;
    }

    // get set giới tính
    public void setGIOITINH(boolean GIOITINH) 
    {
        this.GIOITINH = GIOITINH;
    }

    public boolean getGIOITINH() 
    {
        return GIOITINH;
    }
}
