package creditos.com.creditohugo.Database;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import creditos.com.creditohugo.ConfigApplication;
import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.Objects.Pago;

/**
 * DataBaseEngine.
 */
public enum DataBaseEngine {
    INSTANCE;

    private static final String TAG = "DatabaseEngine";

    private static DataBaseHelper mHelper = null;

    /**
     * Get an instance of the dataBase Engine.
     *
     * @return singletonDB;
     */
    public static DataBaseEngine getInstance() {
        return INSTANCE;
    }

    /**
     * inserCotizacion
     * Insert a list of parameters.
     *
     * @param aCotizacion the list with parameters.
     * @return true or false.
     */
    public boolean insertCotizacion(Cotizacion aCotizacion) {
        Log.d(TAG, "Inserta cotizacion");
        RuntimeExceptionDao<Cotizacion, Integer> cotizacionDao =
                getDatabaseHelper().getCotizacionDao();
        //First delete all the previous parametros
        cotizacionDao.create(aCotizacion);
        return true;
    }

    /**
     * updateCotizacion
     * Update a list of parameters.
     *
     * @param aCotizacion the list with parameters.
     * @return true or false.
     */
    public boolean updateCotizacion(Cotizacion aCotizacion) {
        Log.d(TAG, "Update cotizacion");
        RuntimeExceptionDao<Cotizacion, Integer> cotizacionDao =
                getDatabaseHelper().getCotizacionDao();
        //First delete all the previous parametros
        cotizacionDao.update(aCotizacion);
        return true;
    }

    public List<Cotizacion> getCotizaciones() {
        Log.d(TAG, "consulta contizaciones");
        RuntimeExceptionDao<Cotizacion, Integer> cotizacionDao =
                getDatabaseHelper().getCotizacionDao();
        //First delete all the previous parametros
        return cotizacionDao.queryForAll();
    }
    public Cotizacion getCotizacionForId(Cotizacion aCotizacion) {
        Log.d(TAG, "consulta contizacion");
        RuntimeExceptionDao<Cotizacion, Integer> cotizacionDao =
                getDatabaseHelper().getCotizacionDao();
        //First delete all the previous parametros
        return cotizacionDao.queryForId(aCotizacion.getmId());
    }

    /**
     * inserCotizacion
     * Insert a list of parameters.
     *
     * @param aPago the list with parameters.
     * @return true or false.
     */
    public boolean insertPago(Pago aPago) {
        Log.d(TAG, "Inserta pago");
        RuntimeExceptionDao<Pago, Integer> pagoDao =
                getDatabaseHelper().getPagoDao();
        //First delete all the previous parametros
        pagoDao.create(aPago);
        return true;
    }
    /**
     * updatePago
     * Update a list of parameters.
     *
     * @param aPago the list with parameters.
     * @return true or false.
     */
    public boolean updatePago(Pago aPago) {
        Log.d(TAG, "Update pago");
        RuntimeExceptionDao<Pago, Integer> pagoDao =
                getDatabaseHelper().getPagoDao();
        //First delete all the previous parametros
        pagoDao.update(aPago);
        return true;
    }

    /**
     * Obtiene el helper para acceder a la base de datos.
     *
     * @return DatabaseHelper
     */
    private DataBaseHelper getDatabaseHelper() {
        if (mHelper == null) {
            mHelper = OpenHelperManager.getHelper(ConfigApplication.mAppContext, DataBaseHelper.class);
        }
        return mHelper;
    }

    /**
     * Libera la Base de Datos.
     */
    public void free() {
        releaseDatabaseHelper();
    }

    /**
     * Libera el helper para acceder a la base de datos.
     */
    private void releaseDatabaseHelper() {
        if (mHelper != null) {
            OpenHelperManager.releaseHelper();
            mHelper = null;
        }
    }


}
