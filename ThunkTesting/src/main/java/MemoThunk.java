public abstract class MemoThunk<T> implements Thunk<T> {
    private boolean evaluated = false;
    private T value;

    @Override
    public T get() {
        if (!evaluated) {
            value = compute();
            evaluated = true;
        }
        return value;
    }

    protected abstract T compute();
}