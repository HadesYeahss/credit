package creditos.com.creditohugo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import creditos.com.creditohugo.Objects.Cotizacion;
import creditos.com.creditohugo.Objects.Pago;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class DataBaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "credihugo.db";
    private static final int DATABASE_VERSION = 1;

    // the DAO object we use to access the SimpleData table
    private Dao<Cotizacion, Integer> cotizacionDao = null;
    private RuntimeExceptionDao<Cotizacion, Integer>  cotizacionRuntimeDao = null;

    // the DAO object we use to access the SimpleData table
    private Dao<Pago, Integer> pagoDao = null;
    private RuntimeExceptionDao<Pago, Integer>  pagoRuntimeDao = null;

    public DataBaseHelper(Context aContext) {
        super(aContext, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase, ConnectionSource connectionSource) {
        try {
            Log.i(DataBaseHelper.class.getName(), "create BD...");
            TableUtils.createTable(connectionSource, Cotizacion.class);
            TableUtils.createTable(connectionSource, Pago.class);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Can't create databases:", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int i, int i1) {
        try {
            Log.i(DataBaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Cotizacion.class, true);
            TableUtils.dropTable(connectionSource, Pago.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            Log.e(DataBaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Obtiene el RuntimeExceptionDao de Parametro.
     *
     * @return parametroRuntimeDao dao.
     */
    public RuntimeExceptionDao<Cotizacion, Integer> getCotizacionDao() {
        if (cotizacionRuntimeDao == null) {
            cotizacionRuntimeDao = getRuntimeExceptionDao(Cotizacion.class);
        }
        return cotizacionRuntimeDao;
    }

    /**
     * Obtiene el RuntimeExceptionDao de Parametro.
     *
     * @return parametroRuntimeDao dao.
     */
    public RuntimeExceptionDao<Pago, Integer> getPagoDao() {
        if (pagoRuntimeDao == null) {
            pagoRuntimeDao = getRuntimeExceptionDao(Pago.class);
        }
        return pagoRuntimeDao;
    }
}
