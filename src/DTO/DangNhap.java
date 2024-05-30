package DTO;

public class DangNhap {
	private String taiKhoanEmail;
	private String matKhau;
	
	public DangNhap() {
		taiKhoanEmail  = "";
		matKhau = "";
		
	}
	
	public DangNhap(String taiKhoan, String matKhau) {
		this.taiKhoanEmail = taiKhoan;
		this.matKhau = matKhau;
	}
	
	public String getTaiKhoan() {
		return taiKhoanEmail;
	}
	
	public String getMatKhau() {
		return matKhau;
	}
	
	public void  setTaiKhoan(String tk) {
		taiKhoanEmail = tk;
	}
	
	public void setMatKhau(String mk) {
		matKhau = mk;
	}
	
	public String toString () {
		return "Tai khoan: " + taiKhoanEmail + "co mat khau la: " + matKhau;
	}
}
