public interface ParallelParser<E>{
    Runnable getRunnableParser(E resource);
}
