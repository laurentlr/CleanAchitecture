package russier.laurent.fr.cleanarchitecture.data;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import russier.laurent.fr.cleanarchitecture.domain.Photo;

public class PhotoMapper {

    @Inject
    public PhotoMapper() {
        //defautl constructor for injection
    }

    List<Photo> transform(@Nullable List<JsonPhoto> jsonPhotos) {
        List<Photo> photos = new ArrayList<>();
        if (jsonPhotos != null && !jsonPhotos.isEmpty())
            for (JsonPhoto jsonPhoto : jsonPhotos) {
                Photo photo = new Photo();
                photo.setId(jsonPhoto.getId());
                photo.setAlbumId(jsonPhoto.getAlbumId());
                photo.setThumbnailUrl(jsonPhoto.getThumbnailUrl());
                photo.setTitle(jsonPhoto.getTitle());
                photos.add(photo);
            }
        return photos;
    }

    List<Photo> transformFormDataBase(@NonNull List<DataBasePhoto> dataBasePhotos) {
        List<Photo> photos = new ArrayList<>();
        for (DataBasePhoto dataBasePhoto : dataBasePhotos) {
            Photo photo = new Photo();
            photo.setTitle(dataBasePhoto.getTitle());
            photo.setThumbnailUrl(dataBasePhoto.getThumbnailUrl());
            photo.setId(dataBasePhoto.getPhotoId());
            photo.setAlbumId(dataBasePhoto.getAlbumId());
            photos.add(photo);
        }
        return photos;
    }

    DataBasePhoto transformToDataBasePhoto(@NonNull Photo photo) {
        return new DataBasePhoto(
                photo.getId(),
                photo.getAlbumId(),
                photo.getTitle(),
                photo.getThumbnailUrl()
        );
    }
}
