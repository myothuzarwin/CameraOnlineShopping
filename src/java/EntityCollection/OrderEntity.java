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
public class OrderEntity {

    private Integer orderid;
    private Integer customerid;
    private Date orderdate;
    private String receivername;
    private String receiveraddress;
    private String receiverpostalcode;
    private String receiverphno;
    private String receiveremail;
    private String delivery;
    private String payment;
    private Integer SubTotal;
    private Double DiscPer;
    private Double DiscAmt;
    private Double TotAmount;

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Date getOrderdate() {
        return orderdate;
    }

    public void setOrderdate(Date orderdate) {
        this.orderdate = orderdate;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress;
    }

    public String getReceiverpostalcode() {
        return receiverpostalcode;
    }

    public void setReceiverpostalcode(String receiverpostalcode) {
        this.receiverpostalcode = receiverpostalcode;
    }

    public String getReceiverphno() {
        return receiverphno;
    }

    public void setReceiverphno(String receiverphno) {
        this.receiverphno = receiverphno;
    }

    public String getReceiveremail() {
        return receiveremail;
    }

    public void setReceiveremail(String receiveremail) {
        this.receiveremail = receiveremail;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public Integer getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(Integer SubTotal) {
        this.SubTotal = SubTotal;
    }

    public Double getDiscPer() {
        return DiscPer;
    }

    public void setDiscPer(Double DiscPer) {
        this.DiscPer = DiscPer;
    }

    public Double getDiscAmt() {
        return DiscAmt;
    }

    public void setDiscAmt(Double DiscAmt) {
        this.DiscAmt = DiscAmt;
    }

    public Double getTotAmount() {
        return TotAmount;
    }

    public void setTotAmount(Double TotAmount) {
        this.TotAmount = TotAmount;
    }

}
