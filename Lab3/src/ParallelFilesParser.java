import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ParallelFilesParser implements ParallelParser<File>, Parser<List<File>> {
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void parse(List<File> resources) {
        try {
            for (File file : resources) {
                executorService.submit(getRunnableParser(file));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
