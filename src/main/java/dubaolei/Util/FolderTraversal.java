package dubaolei.Util;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;


/**
 * @author dubaolei
 * @version 1.0.0
 * @ClassName FolderTraversal.java
 * @Description 文件工具类
 * @createTime 2022年12月08日 10:19:00
 */
public class FolderTraversal {
    public static void main(String[] args) throws IOException {
        // 1. 需求：打印文件夹内所有文件名称（含子级）
         Files.walk(Paths.get("src")).forEach(System.out::println);
        // 2. 注意事项（1） try-with-resources (2) 遍历结果包含文件夹
        try (Stream<Path> pathStream = Files.walk(Paths.get("src"))) {
            // 过滤掉文件夹
            pathStream.filter(Files::isRegularFile).forEach(System.out::println);
        }
        // 3. 需求：在文件夹的所有Java文件中寻找单词right
        Files.walkFileTree(Paths.get("src"), new FileVisitor<Path>() {
            @Override
            // 访问文件夹之前
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            // 访问文件时
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(".java")) {
                    String content = Files.readString(file);
                    if (content.contains("right")) {
                        System.out.println(file);
                    }
                }
                return FileVisitResult.CONTINUE;
            }

            @Override
            // 访问文件失败
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }

            @Override
            // 访问文件夹之后
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
