package creditos.com.creditohugo.Controllers;

import java.util.ArrayList;

import creditos.com.creditohugo.Database.DataBaseEngine;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.Objects.Pago;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class NewCreditControler {

    private NewCreditIterface mListener;
    private static final double IVA= 16;

    public interface NewCreditIterface {
        void saveComplete(boolean isSave);

    }

    public NewCreditControler(NewCreditIterface listener) {
        this.mListener = listener;
    }

    public void createCotizacion(Cotizacion aCotizacion) {
        ArrayList<Pago> pagos = new ArrayList<Pago>();
        double interesMes;
        double ivaInteresMes;
        double capitalMes;
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

        for (int i = 0; i < aCotizacion.getNoPayments(); i++) {
            Pago pago = new Pago();
            interesMes = (amount / noPayments) *
                    (aCotizacion.getInterest() / 100) * (noPayments / paymentsDivisor);

            ivaInteresMes = interesMes * (IVA / 100);
            capitalMes = pMensual - ivaInteresMes - interesMes;
            amount = amount - capitalMes;
            pago.setpCapital(capitalMes);
            pago.setpInteres(interesMes);
            pago.setpIva(ivaInteresMes);
            pago.setpTotal(pMensual);
            pago.setSaldo(amount);
            pago.setIdCotizacion(aCotizacion.getIdCotizacion());
            pagos.add(pago);
            noPayments = noPayments--;
            DataBaseEngine.getInstance().insertPago(pago);

        }


        mListener.saveComplete(DataBaseEngine.getInstance().insertCotizacion(aCotizacion));
    }
}