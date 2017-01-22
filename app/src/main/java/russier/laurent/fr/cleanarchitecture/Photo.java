package russier.laurent.fr.cleanarchitecture;

class Photo {
    private int id;
    private int albumId;
    private String thumbnailUrl;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    int getAlbumId() {
        return albumId;
    }

    void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    String getThumbnailUrl() {
        return thumbnailUrl;
    }

    void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
