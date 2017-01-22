package russier.laurent.fr.cleanarchitecture.gui;

import dagger.Component;
import russier.laurent.fr.cleanarchitecture.data.NetComponent;

@PresenterScope
@Component(dependencies = NetComponent.class, modules = PhotoModule.class)
interface PhotoComponent {

    void inject(PhotosActivity photosActivity);
}
