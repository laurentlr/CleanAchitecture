package russier.laurent.fr.cleanarchitecture.data;

import javax.inject.Singleton;

import dagger.Component;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;

@Singleton
@Component(modules = {NetModule.class})
public interface NetComponent {

    PhotoRepository photoRepository();
}
