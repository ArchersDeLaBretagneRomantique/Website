package bzh.abr.album.photo.model;

import bzh.abr.album.model.Album;

public class PhotoBuilder {

    private Photo photo;

    private PhotoBuilder() {
        photo = new Photo();
    }

    public static PhotoBuilder newBuilder() {
        return new PhotoBuilder();
    }

    public PhotoBuilder setFile(String file) {
        photo.setFile(file);
        return this;
    }

    public PhotoBuilder setAlbum(Album album) {
        photo.setAlbum(album);
        return this;
    }

    public Photo build() {
        return photo;
    }

}
