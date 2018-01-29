package creditos.com.creditohugo.Objects;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.sql.Date;

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
    private double pTotal;
    @DatabaseField
    private double saldo;
    @DatabaseField
    private double adelanto;
    @DatabaseField
    private boolean status;

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public double getCapitalTotal() {
        return capitalTotal;
    }

    public void setCapitalTotal(double capitalTotal) {
        this.capitalTotal = capitalTotal;
    }

    public double getpInteres() {
        return pInteres;
    }

    public void setpInteres(double pInteres) {
        this.pInteres = pInteres;
    }

    public double getpCapital() {
        return pCapital;
    }

    public void setpCapital(double pCapital) {
        this.pCapital = pCapital;
    }

    public double getpTotal() {
        return pTotal;
    }

    public void setpTotal(double pTotal) {
        this.pTotal = pTotal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(double adelanto) {
        this.adelanto = adelanto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
