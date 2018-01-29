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
    private int noPayments;
    @DatabaseField
    private boolean typePayment;
    @DatabaseField
    private Date datePayment;

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

    public int getNoPayments() {
        return noPayments;
    }

    public void setNoPayments(int aNoPayments) {
        this.noPayments = aNoPayments;
    }

    public boolean isTypePayment() {
        return typePayment;
    }

    public void setTypePayment(boolean aTypePayment) {
        this.typePayment = aTypePayment;
    }

    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date aDatePayment) {
        this.datePayment = aDatePayment;
    }
}
