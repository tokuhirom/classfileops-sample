import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class AsmAnalyzer {
    public static void main(String[] args) throws IOException {
        // Read source file name by class name
        {
            String className = "com.example.HelloKotlin";
            String sourceFileName = getSourceFileNameByAsm(new ClassReader(className));
            System.out.println(sourceFileName);
        }

        // Read source file name by file name
        {
            String fileName = "build/classes/java/main/com/example/HelloJava.class";
            try (InputStream inputStream = Files.newInputStream(Paths.get(fileName))) {
                String sourceFileName = getSourceFileNameByAsm(new ClassReader(inputStream));
                System.out.println(sourceFileName);
            }
        }
    }

    private static String getSourceFileNameByAsm(ClassReader classReader) throws IOException {
        final String[] retval = {null};
        classReader.accept(new ClassVisitor(Opcodes.ASM7) {
            @Override
            public void visitSource(String source, String debug) {
//                System.out.println("SOURCE='" + source + "', DEBUG='" + debug + "'");
                retval[0] = source;
            }
        }, ClassReader.SKIP_CODE | ClassReader.SKIP_FRAMES);
        return retval[0];
    }
}
