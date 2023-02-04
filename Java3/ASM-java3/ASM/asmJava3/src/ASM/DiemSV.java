package ASM;



import java.io.Serializable;

public class DiemSV implements Serializable {
    public int id;
    public String masv, name;
    public double anh, tin, gdtc;

    public DiemSV(int id, String masv, String name, double anh, double tin, double gdtc) {
        this.id = id;
        this.masv = masv;
        this.name = name;
        this.anh = anh;
        this.tin = tin;
        this.gdtc = gdtc;
    }
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DiemSV() {
    }

    public String getMasv() {
        return masv;
    }

    public void setMasv(String masv) {
        this.masv = masv;
    }

    public double getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAnh() {
        return anh;
    }

    public void setAnh(double anh) {
        this.anh = anh;
    }

    public double getTin() {
        return tin;
    }

    public void setTin(double tin) {
        this.tin = tin;
    }

    public double getGdtc() {
        return gdtc;
    }

    public void setGdtc(double gdtc) {
        this.gdtc = gdtc;
    }



}
