package russier.laurent.fr.cleanarchitecture;

class JsonPhoto {
    private int id;
    private int albumId;
    private String title;
    private String url;
    private String thumbnailUrl;

    int getAlbumId() {
        return albumId;
    }

    void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    String getUrl() {
        return url;
    }

    String getThumbnailUrl() {
        return thumbnailUrl;
    }

    void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
