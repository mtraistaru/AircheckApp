package eu.isdc.aircheckapp;

import javax.inject.Singleton;

import dagger.Component;
import eu.isdc.aircheckapp.activity.ConditionsActivity;
import eu.isdc.aircheckapp.activity.TravelActivity;
import eu.isdc.aircheckapp.activity.WelcomeActivity;
import eu.isdc.aircheckapp.activity.SymptomsActivity;
import eu.isdc.aircheckapp.module.CommonModule;
import eu.isdc.aircheckapp.module.WebserviceModule;
import eu.isdc.aircheckapp.api.AircheckService;

/**
 * Add here classes that can be injected to be served by dagger with common modules.
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
@Singleton
@Component(modules = {CommonModule.class, WebserviceModule.class})
public interface AircheckComponent {

    void inject(WelcomeActivity welcomeActivity);

    void inject(AircheckService aircheckService);

    void inject(SymptomsActivity symptomsActivity);

    void inject(ConditionsActivity conditionsActivity);

    void inject(TravelActivity travelActivity);
}
