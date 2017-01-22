package russier.laurent.fr.cleanarchitecture.data;

import java.util.ArrayList;
import java.util.List;

import russier.laurent.fr.cleanarchitecture.domain.Photo;

public class PhotoMapper {
    List<Photo> transform(List<JsonPhoto> jsonPhotos) {
        List<Photo> photos = new ArrayList<>();
        for (JsonPhoto jsonPhoto : jsonPhotos) {
            Photo photo = new Photo();
            photo.setId(jsonPhoto.getId());
            photo.setAlbumId(jsonPhoto.getAlbumId());
            photo.setThumbnailUrl(jsonPhoto.getThumbnailUrl());
            photos.add(photo);
        }
        return photos;
    }
}
