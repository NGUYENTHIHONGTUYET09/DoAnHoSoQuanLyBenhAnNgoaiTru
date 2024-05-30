package DTO;
public class Tinh {
    private int id;
    private String tenTinh;

    // Constructor
    public Tinh(int id, String tenTinh) {
        this.id = id;
        this.tenTinh = tenTinh;
    }
    
    public Tinh(){}

    // Getter for id
    public int getId() {
        return id;
    }

    // Getter for tenTinh
    public String getTenTinh() {
        return tenTinh;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTenTinh(String tenTinh) {
        this.tenTinh = tenTinh;
    }
    
  
}
