@FunctionalInterface
public interface Parser<E> {
    void parse(E resource);
}
