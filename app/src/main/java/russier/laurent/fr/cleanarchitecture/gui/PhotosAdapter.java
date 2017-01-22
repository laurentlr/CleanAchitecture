package russier.laurent.fr.cleanarchitecture.gui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import russier.laurent.fr.cleanarchitecture.R;
import russier.laurent.fr.cleanarchitecture.domain.Photo;

class PhotosAdapter extends RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder> {

    private List<Photo> photos;

    @Override
    public PhotosAdapter.PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.photo_view_holder, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PhotoViewHolder holder, int position) {
        holder.displayPhoto(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.size() : 0;
    }

    void update(List<Photo> photos) {
        this.photos = photos;
        notifyDataSetChanged();
    }

    class PhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title) TextView titleView;
        @BindView(R.id.image) ImageView imageView;

        PhotoViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void displayPhoto(Photo photo) {
            titleView.setText(photo.getTitle());
            Glide.with(imageView.getContext())
                    .load(photo.getThumbnailUrl())
                    .fitCenter()
                    .override(200, 200)
                    .into(imageView);
        }
    }
}
