package russier.laurent.fr.cleanarchitecture.gui.presenter;

public interface Presenter<T> {
    void onDestroy();

    void onViewAttached(T view);

    void onViewDetached();
}
