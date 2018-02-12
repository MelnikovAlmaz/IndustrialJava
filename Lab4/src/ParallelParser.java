import java.io.File;

@FunctionalInterface
public interface ParallelParser{
    Runnable getRunnableParser(File resource);
}
