package eu.isdc.aircheckapp.module;

import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Provides common singletons access
 * Created by Mihai.Traistaru on 22-Apr-16.
 */
@Module
public class CommonModule {

    private Context context;

    public CommonModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    Bus provideBus() {
        return new Bus(ThreadEnforcer.ANY);
    }
}
