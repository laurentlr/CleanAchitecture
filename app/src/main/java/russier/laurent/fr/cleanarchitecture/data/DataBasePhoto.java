package russier.laurent.fr.cleanarchitecture.data;

import com.orm.SugarRecord;

/**
 * public for SugarOrm
 */
public class DataBasePhoto extends SugarRecord {

    private int photoId;
    private int albumId;
    private String title;
    private String thumbnailUrl;

    public DataBasePhoto() {
        //empty constructor for SugarORM
    }

    public DataBasePhoto(int photoId, int albumId, String title, String thumbnailUrl) {
        this.photoId = photoId;
        this.albumId = albumId;
        this.title = title;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
