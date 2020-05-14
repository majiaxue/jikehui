package com.example.fix;

import android.content.Context;

import java.io.File;
import java.util.HashSet;

import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

public class FixDexUtils {

    // classes2.dex  classes3.dex
    private static HashSet<File> loadedDex = new HashSet<>();

    static {
        // 修复代码之前，先清理集合
        loadedDex.clear();
    }

    // 加载热修复的dex文件
    public static void loadFixedDex(Context context) {
        File fileDir = context.getDir(Constants.DEX_DIR, Context.MODE_PRIVATE);
        //循环私有目录下的所有文件
        File[] listFiles = fileDir.listFiles();
        for(File file : listFiles) {
            if(file.getName().endsWith(Constants.DEX_SUFFIX) && !"classes.dex".equals(file.getName())) {
                //找到修复包的文件   添加到集合
                loadedDex.add(file);

            }
        }

        // 创建类加载器
        createDexClassLoader(context, fileDir);
    }

    private static void createDexClassLoader(Context context, File fileDir) {
        //创建解压目录
        String optDir = fileDir.getAbsolutePath() + File.separator + "opt_dex";
        // 创建目录
        File fopt = new File(optDir);
        if(!fopt.exists()) {
            fopt.mkdirs();
        }

        for(File dex : loadedDex) {
            // 创建自有的加载器
            DexClassLoader classLoader = new DexClassLoader(dex.getAbsolutePath(),optDir,null, context.getClassLoader());
            // 每循环一次  修复一次
            fix(classLoader,context);
        }
    }

    private static void fix(DexClassLoader classLoader, Context context) {
        // 获取系统的PathClassLoader
        PathClassLoader pathLoader = (PathClassLoader)context.getClassLoader();
        try{
            // 获取自有的dexElements数组
            Object myElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(classLoader));
            // 获取系统的dexElements数组
            Object systemElements = ReflectUtils.getDexElements(ReflectUtils.getPathList(pathLoader));
            // 合并并且生成新的dexElements数组
            Object dexElements = ArrayUtils.combineArray(myElements,systemElements);
            // 获取系统的pathList
            Object systemPathList = ReflectUtils.getPathList(pathLoader);
            // 通过反射技术，将新的dexElements数组赋值给系统的pathList
            ReflectUtils.setField(systemPathList, systemPathList.getClass(), dexElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
