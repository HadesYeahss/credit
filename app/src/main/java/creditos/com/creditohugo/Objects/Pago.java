package creditos.com.creditohugo.Objects;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */
public class Pago implements Serializable {

    @DatabaseField(generatedId = true)
    private int idPago;
    @DatabaseField
    private double capitalTotal;
    @DatabaseField
    private double pInteres;
    @DatabaseField
    private double pCapital;
    @DatabaseField
    private double pIva;
    @DatabaseField
    private double pTotal;
    @DatabaseField
    private double saldo;
    @DatabaseField
    private double adelanto;
    @DatabaseField
    private boolean status;
    @DatabaseField
    private Date diaPago;
    @DatabaseField
    private int idCotizacion;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int aIdPago) {
        this.idPago = aIdPago;
    }

    public double getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(double aCapitalTotal) {
        this.capitalTotal = aCapitalTotal;
    }

    public double getpInteres() {
        return pInteres;
    }

    public void setpInteres(double aPInteres) {
        this.pInteres = aPInteres;
    }

    public double getpCapital() {
        return pCapital;
    }

    public void setpCapital(double aPCapital) {
        this.pCapital = aPCapital;
    }

    public double getpTotal() {
        return pTotal;
    }

    public void setpTotal(double aPTotal) {
        this.pTotal = aPTotal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double aSaldo) {
        this.saldo = aSaldo;
    }

    public double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(double aAdelanto) {
        this.adelanto = aAdelanto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean aStatus) {
        this.status = aStatus;
    }

    public double getpIva() {
        return pIva;
    }

    public void setpIva(double apIva) {
        this.pIva = apIva;
    }

    public Date getDiaPago() {
        return diaPago;
    }

    public void setDiaPago(Date aDiaPago) {
        this.diaPago = aDiaPago;
    }

    public int getIdCotizacion() {
        return idCotizacion;
    }

    public void setIdCotizacion(int aIdCotizacion) {
        this.idCotizacion = aIdCotizacion;
    }
}
