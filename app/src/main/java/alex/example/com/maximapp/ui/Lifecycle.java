package alex.example.com.maximapp.ui;

public interface Lifecycle<V> {
    void bind(V view);

    void unbind();
}
