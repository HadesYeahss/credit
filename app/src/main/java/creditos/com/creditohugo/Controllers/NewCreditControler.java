package creditos.com.creditohugo.Controllers;

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

    public NewCreditControler() {
    }

    public NewCreditControler(NewCreditIterface listener) {
        this.mListener = listener;
    }

    public void createCotizacion(Cotizacion aCotizacion) {

        /*double interesMes = (aCotizacion.getAmount()/aCotizacion.getNoPayments())*
                (aCotizacion.getInterest()/100)*(aCotizacion.getNoPayments()/12);*/

        mListener.saveComplete(DataBaseEngine.getInstance().insertCotizacion(aCotizacion));
    }
}