package creditos.com.creditohugo.Objects;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */
public class Cotizacion implements Serializable {

    @DatabaseField(generatedId = true)
    private int idCotizacion;
    @DatabaseField
    private String name;
    @DatabaseField
    private double amount;
    @DatabaseField
    private double interest;
    @DatabaseField
    private double noPayments;
    @DatabaseField
    private int typePayment;
    @DatabaseField
    private Date datePayment;
    @DatabaseField
    private double total;
    @DatabaseField
    private double capitalTotal;
    @DatabaseField
    private double interestTotal;
    @DatabaseField
    private double ivaTotal;


    public Cotizacion() {
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int aIdCotizacion) {
        this.idCotizacion = aIdCotizacion;
    }

    public String getName() {
        return name;
    }

    public void setName(String aName) {
        this.name = aName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double aMount) {
        this.amount = aMount;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double aInterest) {
        this.interest = aInterest;
    }

    public double getNoPayments() {
        return noPayments;
    }

    public void setNoPayments(double aNoPayments) {
        this.noPayments = aNoPayments;
    }

    public int getTypePayment() {
        return typePayment;
    }

    public void setTypePayment(int aTypePayment) {
        this.typePayment = aTypePayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date aDatePayment) {
        this.datePayment = aDatePayment;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double aTotal) {
        this.total = aTotal;
    }

    public double getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(double aCapitalTotal) {
        this.capitalTotal = aCapitalTotal;
    }

    public double getInterestTotal() {
        return interestTotal;
    }

    public void setInterestTotal(double aInterestTotal) {
        this.interestTotal = aInterestTotal;
    }

    public double getIvaTotal() {
        return ivaTotal;
    }

    public void setIvaTotal(double aIvaTotal) {
        this.ivaTotal = aIvaTotal;
    }
}
