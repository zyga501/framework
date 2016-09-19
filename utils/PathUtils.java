package framework.utils;

public class PathUtils {
    public static String getProjectPath() {
        return PathUtils.class.getResource("/").getPath().substring(1).replaceAll("%20", " ");
    }

    public static String getTopPackagePath() {
        String projectPath = getProjectPath();
        String topPackageName = PathUtils.class.getPackage().getName();
        topPackageName = topPackageName.substring(0, topPackageName.indexOf('.'));
        return projectPath.concat("/").concat(topPackageName);
    }
}
