package russier.laurent.fr.cleanarchitecture.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;

@Module
public class NetModule {

    private String mBaseUrl;

    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Api provideApi(OkHttpClient client) {
        return new Api(mBaseUrl, client);
    }

    @Provides
    @Singleton
    PhotoService providePhotoService(Api api) {
        return api.getRetrofit().create(PhotoService.class);
    }

    @Provides
    @Singleton
    PhotoMapper providePhotoMapper() {
        return new PhotoMapper();
    }

    @Provides
    @Singleton
    PhotoRepository providePhotoRepository(PhotoService photoService, PhotoMapper mapper) {
        return new PhotoRepositoryImpl(photoService, mapper);
    }

}
