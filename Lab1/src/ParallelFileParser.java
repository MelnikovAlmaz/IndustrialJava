import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class ParallelFileParser implements ParallelParser<String>, Parser<File>{
    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void parse(File resource) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(resource));
            String line = reader.readLine();
            while (line != null) {
                executorService.submit(getRunnableParser(line));
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}
