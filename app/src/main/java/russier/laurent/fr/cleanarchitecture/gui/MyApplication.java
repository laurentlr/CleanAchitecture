package russier.laurent.fr.cleanarchitecture.gui;

import android.app.Application;

import com.orm.SugarContext;

import russier.laurent.fr.cleanarchitecture.data.DaggerNetComponent;
import russier.laurent.fr.cleanarchitecture.data.NetComponent;
import russier.laurent.fr.cleanarchitecture.data.NetModule;

public class MyApplication extends Application {

    private NetComponent netComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(this);
        netComponent = DaggerNetComponent.builder()
                .netModule(new NetModule("http://jsonplaceholder.typicode.com/"))
                .build();
    }

    public NetComponent getNetComponent() {
        return netComponent;
    }
}
