package russier.laurent.fr.cleanarchitecture.gui.presenter;

public interface PresenterFactory<T extends Presenter> {
    T create();
}