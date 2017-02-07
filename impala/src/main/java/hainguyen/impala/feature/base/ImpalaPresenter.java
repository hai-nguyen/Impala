package hainguyen.impala.feature.base;

public interface ImpalaPresenter<T> {
    void setView(T view);

    void destroyView();
}
