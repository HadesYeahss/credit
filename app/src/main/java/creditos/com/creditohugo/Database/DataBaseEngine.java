package creditos.com.creditohugo.Database;

import android.util.Log;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import creditos.com.creditohugo.ConfigApplication;
import creditos.com.creditohugo.Objects.Cotizacion;

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
    public static DataBaseEngine getInstance() {return INSTANCE;}

    /**
     * inserCotizacion
     * Insert a list of parameters.
     *
     * @param aCotizacione the list with parameters.
     * @return true or false.
     */
    public boolean insertCotizacion(Cotizacion aCotizacione) {
        Log.d(TAG,"Inserta");
        RuntimeExceptionDao<Cotizacion, Integer> cotizacionDao =
                getDatabaseHelper().getCotizacionDao();
        //First delete all the previous parametros
        cotizacionDao.createOrUpdate(aCotizacione);
        return true;
    }
    public List<Cotizacion> getCotizaciones() {
        Log.d(TAG,"Inserta");
        RuntimeExceptionDao<Cotizacion, Integer> cotizacionDao =
                getDatabaseHelper().getCotizacionDao();
        //First delete all the previous parametros
        return cotizacionDao.queryForAll();
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
