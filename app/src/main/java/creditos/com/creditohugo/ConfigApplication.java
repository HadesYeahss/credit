package creditos.com.creditohugo;

import android.app.Application;
import android.util.Log;

/**
 * Created by rigoberto.torres on 26/01/2018.
 */

public class ConfigApplication extends Application {
    public static ConfigApplication mAppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppContext = this;
    }
    public static ConfigApplication getAppContext() {
        return mAppContext;
    }
}
