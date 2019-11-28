import org.apache.bcel.Repository;
import org.apache.bcel.classfile.ClassParser;
import org.apache.bcel.classfile.JavaClass;
import org.apache.bcel.util.ClassPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BcelAnalyzer {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        {
            // Java class by name
            JavaClass java = Repository.lookupClass("com.example.HelloJava");
            System.out.println(java.getSourceFileName());
        }

        {
            // Kotlin class by name
            JavaClass kt = Repository.lookupClass("com.example.HelloKotlin");
            System.out.println(kt.getSourceFileName());
        }

        {
            // Java class by file name
            String fileName = "build/classes/java/main/com/example/HelloJava.class";
            try (InputStream inputStream = Files.newInputStream(Paths.get(fileName))) {
                final ClassParser parser = new ClassParser(inputStream, fileName);
                JavaClass javaClass = parser.parse();
                System.out.println(javaClass.getSourceFileName());
            }
        }
    }
}
