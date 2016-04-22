package eu.isdc.aircheckapp;

import android.app.Application;
import android.content.Context;

import eu.isdc.aircheckapp.module.CommonModule;
import eu.isdc.aircheckapp.module.WebserviceModule;

/**
 * Common app behavior. Initialises dependency injection.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
public class AircheckApp extends Application {

    AircheckComponent aircheckComponent;

    public static AircheckApp getApp(Context context) {
        return (AircheckApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        aircheckComponent = DaggerAircheckComponent
                .builder()
                .commonModule(new CommonModule(this))
                .webserviceModule(new WebserviceModule())
                .build();
    }

    public AircheckComponent getAircheckComponent() {
        return aircheckComponent;
    }

    public void setAircheckComponent(AircheckComponent aircheckComponent) {
        this.aircheckComponent = aircheckComponent;
    }
}
