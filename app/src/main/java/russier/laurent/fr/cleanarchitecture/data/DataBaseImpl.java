package russier.laurent.fr.cleanarchitecture.data;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;

class DataBaseImpl implements DataBase {

    private final Context context;

    DataBaseImpl(Context context) {
        this.context = context;
    }

    @Override
    public void save(DataBasePhoto dataBasePhoto) {
        dataBasePhoto.save();
    }

    @Override
    public void savePhoto(int photoId) {
        List<DataBasePhoto> dataBasePhotos = DataBasePhoto.find(DataBasePhoto.class, "photoId = ?", String.valueOf(photoId));
        if (dataBasePhotos != null && dataBasePhotos.size() == 1) {
            DataBasePhoto dataBasePhoto = dataBasePhotos.get(0);
            try {
                DataBasePhoto savedPhoto = DataBasePhoto.findById(DataBasePhoto.class, dataBasePhoto.getId());
                Bitmap bitmap = Glide
                        .with(context)
                        .load(savedPhoto.getThumbnailUrl())
                        .asBitmap()
                        .into(100, 100)
                        .get();
                String newImagePath = saveToInternalStorage(bitmap, String.valueOf(savedPhoto.getPhotoId()));
                //TODO Should save local photo in a different attribute
                savedPhoto.setThumbnailUrl(newImagePath);
                savedPhoto.save();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public long getPhotoCount() {
        return DataBasePhoto.count(DataBasePhoto.class);
    }

    /**
     * TwODO This listAll is way to slow
     *
     * @return photos saved in dataBase
     */
    @Override
    public List<DataBasePhoto> getPhotos() {
        return DataBasePhoto.listAll(DataBasePhoto.class);
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String imageName) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath = new File(directory, imageName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return mypath.getAbsolutePath();
    }
}
