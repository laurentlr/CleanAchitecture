package russier.laurent.fr.cleanarchitecture.data;

import java.util.List;

class DataBaseImpl implements DataBase {
    @Override
    public void save(DataBasePhoto dataBasePhoto) {
        dataBasePhoto.save();
    }

    @Override
    public long getPhotoCount() {
        return DataBasePhoto.count(DataBasePhoto.class);
    }

    /**
     * This listAll is way to slow
     *
     * @return photos saved in dataBase
     */
    @Override
    public List<DataBasePhoto> getPhotos() {
        return DataBasePhoto.listAll(DataBasePhoto.class);
    }
}
