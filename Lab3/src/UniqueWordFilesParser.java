import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UniqueWordFilesParser extends ParallelFilesParser {
    @Override
    public Runnable getRunnableParser(File resource) {
        try {
            Loader loader = new Loader();
            Object[] args=new Object[]{resource};
            UniqueWordParser parser = (UniqueWordParser) loader.findClass("UniqueWordParser").getDeclaredConstructor(File.class).newInstance(resource);
            return parser;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) throws IOException {
        UniqueWordFilesParser resourceParser = new UniqueWordFilesParser();
        List<File> resources = new ArrayList<>(Arrays.asList(new File("file1"), new File("file2")));
        resourceParser.parse(resources);
    }
}
