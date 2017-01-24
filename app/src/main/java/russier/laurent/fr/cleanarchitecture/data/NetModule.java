package russier.laurent.fr.cleanarchitecture.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import russier.laurent.fr.cleanarchitecture.domain.PhotoRepository;

@Module
public class NetModule {

    private final String mBaseUrl;
    private final Context applicationContext;

    public NetModule(String baseUrl, Context applicationContext) {
        this.mBaseUrl = baseUrl;
        this.applicationContext = applicationContext;
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
    DataBase provideDataBase() {
        return new DataBaseImpl(applicationContext);
    }

    @Provides
    @Singleton
    PhotoRepository providePhotoRepository(PhotoService photoService, PhotoMapper mapper, DataBase dataBase) {
        return new PhotoRepositoryImpl(photoService, mapper, dataBase);
    }

}
