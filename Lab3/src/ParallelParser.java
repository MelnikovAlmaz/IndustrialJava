public interface ParallelParser<E>{
    Runnable getRunnableParser(E resource) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
