package me.szp.plugin.tool;

import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.roots.ModuleRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.JavaSourceRootType;

import java.util.List;

/**
 * 模块工具类
 *
 * @author szp
 * @version 1.0.0
 * @since 2020/09/01 17:15
 */
public final class ModuleUtils {
    /**
     * 禁用构造方法
     */
    private ModuleUtils() {
        throw new UnsupportedOperationException();
    }

    /**
     * 获取module路径
     *
     * @param module 模块
     * @return 路径
     */
    public static VirtualFile getModuleDir(@NotNull Module module) {
        String modulePath = ModuleUtil.getModuleDirPath(module);
        // 统一路径分割符号
        modulePath = modulePath.replace("\\", "/");
        // 尝试消除不正确的路径
        if (modulePath.contains(".idea/modules/")) {
            modulePath = modulePath.replace(".idea/modules/","");
        }
        if (modulePath.contains("/.idea")) {
            modulePath = modulePath.replace("/.idea","");
        }
        return VirtualFileManager.getInstance().findFileByUrl(String.format("file://%s", modulePath));
    }

    /**
     * 获取模块的源代码文件夹，不存在
     *
     * @param module 模块对象
     * @return 文件夹路径
     */
    public static VirtualFile getSourcePath(@NotNull Module module) {
        List<VirtualFile> virtualFileList = ModuleRootManager.getInstance(module).getSourceRoots(JavaSourceRootType.SOURCE);
        if (CollectionUtil.isEmpty(virtualFileList)) {
            VirtualFile modulePath = getModuleDir(module);
            // 尝试智能识别原代码路径(通过上面的方式，IDEA不能百分百拿到原代码路径)
            VirtualFile srcDir = VfsUtil.findRelativeFile(modulePath, "src", "main", "java");
            if (srcDir != null && srcDir.isDirectory()) {
                return srcDir;
            }
            return modulePath;
        }
        return virtualFileList.get(0);
    }

    /**
     * 判断模块是否存在源代码文件夹
     *
     * @param module 模块对象
     * @return 是否存在
     */
    public static boolean existsSourcePath(Module module) {
        return !CollectionUtil.isEmpty(ModuleRootManager.getInstance(module).getSourceRoots(JavaSourceRootType.SOURCE));
    }
}
