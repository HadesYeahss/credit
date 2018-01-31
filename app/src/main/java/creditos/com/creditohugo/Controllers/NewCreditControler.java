package creditos.com.creditohugo.Controllers;

import android.util.Log;

import creditos.com.creditohugo.Database.DataBaseEngine;
import creditos.com.creditohugo.Objects.Cotizacion;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class NewCreditControler {

    private NewCreditIterface mListener;

    public interface NewCreditIterface {
        void saveComplete(boolean isSave);

    }

    public NewCreditControler(NewCreditIterface listener) {
        this.mListener = listener;
    }

    public void createCotizacion(Cotizacion aCotizacion) {

        double interesMes = 0;
        double ivaInteresMes = 0;
        double capitalMes = 0;

        double iva = 12;
        double amount = aCotizacion.getAmount();
        double noPayments = aCotizacion.getNoPayments();
        int paymentsDivisor = 12;
        if (aCotizacion.getTypePayment() == 2) {
            paymentsDivisor = 24;
        }
        if (aCotizacion.getTypePayment() == 3) {
            paymentsDivisor = 52;
        }
        double factor = (aCotizacion.getInterest() / 100) / paymentsDivisor * (1 + (iva / 100));
        double pMensual = amount * ((factor) / (1 - Math.pow(((1 + factor)), -aCotizacion.getNoPayments())));

        for (int i = 0; i < aCotizacion.getNoPayments(); i++) {
            interesMes = (amount / noPayments) *
                    (aCotizacion.getInterest() / 100) * (noPayments / paymentsDivisor);
            ivaInteresMes = interesMes * (iva/100);
            capitalMes = pMensual - ivaInteresMes - interesMes;
            Log.d("CAPITAL",String.valueOf(capitalMes));
            Log.d("IVA",String.valueOf(ivaInteresMes));
            Log.d("INTERES",String.valueOf(interesMes));
            Log.d("-----------------------","-------------");

            amount = amount-capitalMes;
            noPayments = noPayments--;
        }


        mListener.saveComplete(DataBaseEngine.getInstance().insertCotizacion(aCotizacion));
    }
}