package creditos.com.creditohugo.Controllers;

import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        double amount = aCotizacion.getmAmount();
        double noPayments = aCotizacion.getmNoPayments();
        int paymentsDivisor = 12;
        if (aCotizacion.getmTypePayment() == 2) {
            paymentsDivisor = 24;
        }
        if (aCotizacion.getmTypePayment() == 3) {
            paymentsDivisor = 52;
        }
        double factor = (aCotizacion.getmInterest() / 100) / paymentsDivisor * (1 + (IVA / 100));
        double pMensual = amount * ((factor) / (1 - Math.pow(((1 + factor)), -paymentsDivisor)));
        double pTotal = pMensual * noPayments;
        aCotizacion.setmTotal(pTotal);

        Date dateInitial = aCotizacion.getmDatePayment();

        DataBaseEngine.getInstance().insertCotizacion(aCotizacion);
        for (int i = 0; i < aCotizacion.getmNoPayments(); i++) {

            interesMes = (amount / noPayments) *
                    (aCotizacion.getmInterest() / 100) * (noPayments / paymentsDivisor);

            ivaInteresMes = interesMes * (IVA / 100);
            capitalMes = pMensual - ivaInteresMes - interesMes;
            amount = amount - capitalMes;

            totalInteres = totalInteres + interesMes;
            totalCapital = totalCapital + capitalMes;
            totalIva = totalIva + ivaInteresMes;


            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            dateInitial = calculateDatePayment(aCotizacion, dateInitial, i);
            Log.d("test", df.format(dateInitial));

            noPayments = noPayments--;

            Pago pago = new Pago(aCotizacion, interesMes, capitalMes,ivaInteresMes, pMensual,
                    amount, false,dateInitial);

            DataBaseEngine.getInstance().insertPago(pago);

        }
        aCotizacion.setmInterestTotal(totalInteres);
        aCotizacion.setmCapitalTotal(totalCapital);
        aCotizacion.setmIvaTotal(totalIva);
        DataBaseEngine.getInstance().updateCotizacion(aCotizacion);

        Cotizacion cotizacion = DataBaseEngine.getInstance().getCotizacionForId(aCotizacion);

        if (cotizacion.getmTypePayment() == 2) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(cotizacion.getmDatePayment());
            boolean isQuince = false;
            if (cotizacion.getmDatePayment().getDate() < 15) {
                cal.set(Calendar.DAY_OF_MONTH, 15);
                isQuince = true;
            } else {
                cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            }
            Date past = cal.getTime();
            ArrayList<Pago> pagos = new ArrayList<Pago>(cotizacion.getmPagos());
            for (int i = 0; i < pagos.size(); i++) {

                if (i == 0) {
                    pagos.get(i).setmDiaPago(past);
                    DataBaseEngine.getInstance().updatePago(pagos.get(i));
                    past = cal.getTime();
                    isQuince = !isQuince;
                    continue;
                }

                if (isQuince) {
                    cal.add(Calendar.MONTH, 1);
                    cal.set(Calendar.DAY_OF_MONTH, 15);
                } else {
                    cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                }
                isQuince = !isQuince;
                pagos.get(i).setmDiaPago(past);
                DataBaseEngine.getInstance().updatePago(pagos.get(i));
                past = cal.getTime();
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                Log.d("PAGO", df.format(past));
            }
        }

        mListener.saveComplete(true);
    }

    private Date calculateDatePayment(Cotizacion aCotizacion, Date old, int i) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(old);
        if (aCotizacion.getmTypePayment() == 1) {
            if (i != 0) {
                cal.add(Calendar.MONTH, 1);
            }
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        } else if (aCotizacion.getmTypePayment() == 3) {
            cal.add(Calendar.DAY_OF_YEAR, 7);
        }
        Date date = cal.getTime();

        return date;

    }
}