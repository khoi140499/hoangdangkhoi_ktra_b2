package com.example.hoangdangkhoi_ktra2_bai2;

public class QuyenGop {
    private int stt;
    private String hoten;
    private String thanhpho;
    private String ngay;
    private long sotien;

    public QuyenGop(String hoten, String thanhpho, String ngay, long sotien) {
        this.stt = stt;
        this.hoten = hoten;
        this.thanhpho = thanhpho;
        this.ngay = ngay;
        this.sotien = sotien;
    }

    public QuyenGop(int stt, String hoten, String thanhpho, String ngay, long sotien) {
        this.stt = stt;
        this.hoten = hoten;
        this.thanhpho = thanhpho;
        this.ngay = ngay;
        this.sotien = sotien;
    }

    public int getStt() {
        return stt;
    }

    public String getHoten() {
        return hoten;
    }

    public String getThanhpho() {
        return thanhpho;
    }

    public String getNgay() {
        return ngay;
    }

    public long getSotien() {
        return sotien;
    }

    @Override
    public String toString() {
        return stt+"noi"+hoten+"noi"+thanhpho+"noi"+ngay+"noi"+sotien;
    }
}
