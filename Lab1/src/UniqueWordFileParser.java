import java.io.File;
import java.io.IOException;

public class UniqueWordFileParser extends ParallelFileParser {
    @Override
    public Runnable getRunnableParser(String resource) {
        return new UniqueWordParser(resource);
    }

    public static void main(String[] args) throws IOException {
        String fileName = "input.txt";
        File file = new File(fileName);
        UniqueWordFileParser resourceParser = new UniqueWordFileParser();
        resourceParser.parse(file);
    }
}
