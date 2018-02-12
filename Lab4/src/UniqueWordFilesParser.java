import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueWordFilesParser extends ParallelFilesParser {

    public static void main(String[] args) throws IOException {
        UniqueWordFilesParser resourceParser = new UniqueWordFilesParser();
        List<File> resources = new ArrayList<>(Arrays.asList(new File("file1"), new File("file2")));
        resourceParser.parseByParcer(resources, (File file) -> new UniqueWordParser(file));
    }
}
