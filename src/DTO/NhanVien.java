package DTO;

import java.sql.Date;

public class NhanVien{
	private int id;
	private String MANV;
	private String HOTEN;
	private Date NGAYSINH;
	private String DIACHI;
	private String GIOITINH;
	private Date NGAYVL;
	private String VAITRO;
        private String TRANGTHAI;

    public String getTRANGTHAI() {
        return TRANGTHAI;
    }

    public void setTRANGTHAI(String TRANGTHAI) {
        this.TRANGTHAI = TRANGTHAI;
    }
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMANV() {
		return MANV;
	}
	public void setMANV(String mANV) {
		MANV = mANV;
	}
	public String getHOTEN() {
		return HOTEN;
	}
	public void setHOTEN(String hOTEN) {
		HOTEN = hOTEN;
	}
	public Date getNGAYSINH() {
		return NGAYSINH;
	}
	public void setNGAYSINH(Date nGAYSINH) {
		NGAYSINH = nGAYSINH;
	}
	public String getDIACHI() {
		return DIACHI;
	}
	public void setDIACHI(String dIACHI) {
		DIACHI = dIACHI;
	}
	public String getGIOITINH() {
		return GIOITINH;
	}
	public void setGIOITINH(String gIOITINH) {
		GIOITINH = gIOITINH;
	}
	public Date getNGAYVL() {
		return NGAYVL;
	}
	public void setNGAYVL(Date nGAYVL) {
		NGAYVL = nGAYVL;
	}
	public String getVAITRO() {
		return VAITRO;
	}
	public void setVAITRO(String vAITRO) {
		VAITRO = vAITRO;
	}
//	public NhanVien(int id, String mANV, String hOTEN, Date nGAYSINH, String dIACHI, String gIOITINH, Date nGAYVL,
//			String vAITRO, String TRANGTHAI) {
//		super();
//		this.id = id;
//		MANV = mANV;
//		HOTEN = hOTEN;
//		NGAYSINH = nGAYSINH;
//		DIACHI = dIACHI;
//		GIOITINH = gIOITINH;
//		NGAYVL = nGAYVL;
//		VAITRO = vAITRO;
//                this.TRANGTHAI = TRANGTHAI;
//	}
        public NhanVien(int id, String MANV, String HOTEN, Date NGAYSINH, String DIACHI, String GIOITINH, Date NGAYVL, String VAITRO, String TRANGTHAI) {
        this.id = id;
        this.MANV = MANV;
        this.HOTEN = HOTEN;
        this.NGAYSINH = NGAYSINH;
        this.DIACHI = DIACHI;
        this.GIOITINH = GIOITINH;
        this.NGAYVL = NGAYVL;
        this.VAITRO = VAITRO;
        this.TRANGTHAI = TRANGTHAI;
    }
        
        public NhanVien(String mANV, String hOTEN, Date nGAYSINH, String dIACHI, String gIOITINH, Date nGAYVL,
			String vAITRO,String TRANGTHAI) {
		MANV = mANV;
		HOTEN = hOTEN;
		NGAYSINH = nGAYSINH;
		DIACHI = dIACHI;
		GIOITINH = gIOITINH;
		NGAYVL = nGAYVL;
		VAITRO = vAITRO;
                this.TRANGTHAI = TRANGTHAI;
	}
        
        public NhanVien(String hOTEN, Date nGAYSINH, String dIACHI, String gIOITINH, Date nGAYVL,
			String vAITRO,String TRANGTHAI) {
		HOTEN = hOTEN;
		NGAYSINH = nGAYSINH;
		DIACHI = dIACHI;
		GIOITINH = gIOITINH;
		NGAYVL = nGAYVL;
		VAITRO = vAITRO;
                this.TRANGTHAI = TRANGTHAI;
	}
        
	public NhanVien() {
		super();
	}

    @Override
    public String toString() {
        return "NhanVien{" + "id=" + id + ", MANV=" + MANV + ", HOTEN=" + HOTEN + ", NGAYSINH=" + NGAYSINH + ", DIACHI=" + DIACHI + ", GIOITINH=" + GIOITINH + ", NGAYVL=" + NGAYVL + ", VAITRO=" + VAITRO + ", TRANGTHAI=" + TRANGTHAI + '}';
    }
	
	
}