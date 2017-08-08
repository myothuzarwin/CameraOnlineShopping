/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntityCollection;

import java.sql.Date;

/**
 *
 * @author myothuzar
 */
public class OrderHeaderInfo {

    private Integer orderid;
    private String customerid;
    private Date orderdate;
    private Integer no_of_product;
    private Integer SubTotal;
    private Double Discount_Percentage;
    private Double Discount_Amount;
    private Double Total_Amount;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public String getCustomerid() {
        return customerid;
    }

    public void setCustomerid(String customerid) {
        this.customerid = customerid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public Integer getNo_of_product() {
        return no_of_product;
    }

    public void setNo_of_product(Integer no_of_product) {
        this.no_of_product = no_of_product;
    }

    public Integer getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(Integer SubTotal) {
        this.SubTotal = SubTotal;
    }

    public Double getDiscount_Percentage() {
        return Discount_Percentage;
    }

    public void setDiscount_Percentage(Double Discount_Percentage) {
        this.Discount_Percentage = Discount_Percentage;
    }

    public Double getDiscount_Amount() {
        return Discount_Amount;
    }

    public void setDiscount_Amount(Double Discount_Amount) {
        this.Discount_Amount = Discount_Amount;
    }

    public Double getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(Double Total_Amount) {
        this.Total_Amount = Total_Amount;
    }

}
