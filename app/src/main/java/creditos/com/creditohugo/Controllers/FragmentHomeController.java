package creditos.com.creditohugo.Controllers;

import java.util.ArrayList;

import creditos.com.creditohugo.Database.DataBaseEngine;
import creditos.com.creditohugo.Fragments.FragmentHome;
import creditos.com.creditohugo.Objects.Cotizacion;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class FragmentHomeController {

    private MainAcvitivyIterface mListener;

    public interface MainAcvitivyIterface {

    }

    public FragmentHomeController(FragmentHome listener) {
        this.mListener = listener;
    }

    public ArrayList<Cotizacion> loadCotizaciones() {
        ArrayList<Cotizacion> cotizaciones = new ArrayList<>();
        DataBaseEngine.getInstance().getCotizaciones();
        cotizaciones = (ArrayList<Cotizacion>) DataBaseEngine.getInstance().getCotizaciones();
        return cotizaciones;
    }
}