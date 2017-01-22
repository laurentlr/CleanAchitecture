package russier.laurent.fr.cleanarchitecture.gui.dagger;

import dagger.Component;
import russier.laurent.fr.cleanarchitecture.data.NetComponent;
import russier.laurent.fr.cleanarchitecture.gui.presenter.PhotoPresenterFactory;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = PhotoModule.class)
public interface PhotoComponent {

    void inject(PhotoPresenterFactory factory);
}
