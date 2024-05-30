/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Report.dtos;

/**
 *
 * @author 15520
 */
public class ReportDTO {
    private String name;
    private int value;
    private String year;
    private double money;

    public ReportDTO(String year, double money) {
        this.year = year;
        this.money = money;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
    
    

    public ReportDTO(String provinceName, int quantity) {
        this.name = provinceName;
        this.value = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String provinceName) {
        this.name = provinceName;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int quantity) {
        this.value = quantity;
    }

    @Override
    public String toString() {
        return "ReportDTO{" + "name=" + name + ", value=" + value + ", year=" + year + ", money=" + money + '}';
    }
    
    
}
