package creditos.com.creditohugo.Controllers;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import creditos.com.creditohugo.Database.DataBaseEngine;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.Objects.Pago;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class NewCreditController {

    private NewCreditIterface mListener;
    private static final double IVA = 16;

    public interface NewCreditIterface {
        void saveComplete(boolean isSave);

    }

    public NewCreditController(NewCreditIterface listener) {
        this.mListener = listener;
    }

    public void createCotizacion(Cotizacion aCotizacion) {
        double interesMes, ivaInteresMes, capitalMes;
        double totalInteres = 0, totalIva = 0, totalCapital = 0;
        double amount = aCotizacion.getAmount();
        double noPayments = aCotizacion.getNoPayments();
        int paymentsDivisor = 12;
        if (aCotizacion.getTypePayment() == 2) {
            paymentsDivisor = 24;
        }
        if (aCotizacion.getTypePayment() == 3) {
            paymentsDivisor = 52;
        }
        double factor = (aCotizacion.getInterest() / 100) / paymentsDivisor * (1 + (IVA / 100));
        double pMensual = amount * ((factor) / (1 - Math.pow(((1 + factor)), -paymentsDivisor)));
        double pTotal = pMensual * noPayments;
        aCotizacion.setTotal(pTotal);

        Date dateNow = new Date();

        for (int i = 0; i < aCotizacion.getNoPayments(); i++) {
            Pago pago = new Pago();
            interesMes = (amount / noPayments) *
                    (aCotizacion.getInterest() / 100) * (noPayments / paymentsDivisor);

            ivaInteresMes = interesMes * (IVA / 100);
            capitalMes = pMensual - ivaInteresMes - interesMes;
            amount = amount - capitalMes;

            totalInteres = totalInteres + interesMes;
            totalCapital = totalCapital + capitalMes;
            totalIva = totalIva + ivaInteresMes;

            pago.setpCapital(capitalMes);
            pago.setpInteres(interesMes);
            pago.setpIva(ivaInteresMes);
            pago.setpTotal(pMensual);
            pago.setSaldo(amount);
            pago.setIdCotizacion(aCotizacion.getIdCotizacion());

            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dateNow = calculateDatePayment(aCotizacion, dateNow);
            Log.d("test", df.format(dateNow));

            noPayments = noPayments--;

            DataBaseEngine.getInstance().insertPago(pago);

        }
        aCotizacion.setInterestTotal(totalInteres);
        aCotizacion.setCapitalTotal(totalCapital);
        aCotizacion.setIvaTotal(totalIva);


        mListener.saveComplete(DataBaseEngine.getInstance().insertCotizacion(aCotizacion));
    }

    private Date calculateDatePayment(Cotizacion aCotizacion, Date old) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(old);
        if (aCotizacion.getTypePayment() == 1) {
            cal.add(Calendar.MONTH, 1);
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (aCotizacion.getTypePayment() == 2) {
            if (old.getDay() < 15) {
                cal.set(Calendar.DAY_OF_MONTH, 15);
            } else {
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
        } else if (aCotizacion.getTypePayment() == 3) {
            cal.add(Calendar.DAY_OF_YEAR, 7);
        }
        Date date = cal.getTime();

        return date;

    }
}