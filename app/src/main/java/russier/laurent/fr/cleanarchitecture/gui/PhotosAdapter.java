package russier.laurent.fr.cleanarchitecture.gui;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import russier.laurent.fr.cleanarchitecture.domain.Photo;

class PhotosAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Photo> photos;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }

    void update(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }
}
