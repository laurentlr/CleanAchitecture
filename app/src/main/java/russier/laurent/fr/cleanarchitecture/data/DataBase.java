package russier.laurent.fr.cleanarchitecture.data;

import java.util.List;

public interface DataBase {
    void save(DataBasePhoto dataBasePhoto);

    long getPhotoCount();

    List<DataBasePhoto> getPhotos();
}
