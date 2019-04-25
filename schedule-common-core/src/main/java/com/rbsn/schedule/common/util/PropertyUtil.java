package com.rbsn.schedule.common.util;


import org.slf4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * 属性文件工具类.
 */
public class PropertyUtil {

    /**
     * 日志类
     */
    private static Logger logger;

    /**
     * 属性类
     */
    public static Properties properties = new Properties();

    /**
     * 加载属性文件.
     */
    public static void loadProp(String fileName) {
        InputStream is = null;
        Reader reader = null;

        // 首先在classpath中找，如果找不到，则在工作目录下找
        is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (is == null) {
            // 没找到，则在使用绝对路径，找到jar所在的根目录
            // String rootPath = System.getProperty("user.dir");
            String rootPath = System.getProperty("logDirPath");
            logger.info("rootPath:{}", rootPath);

            // 截取一下文件名：BirthdayReminder/生日名单.xls ->
            if (StringUtil.subBySplit(fileName, "/") != null) {
                fileName = StringUtil.subBySplit(fileName, "/");
            } else if (StringUtil.subBySplit(fileName, "\\") != null) {
                fileName = StringUtil.subBySplit(fileName, "\\");
            }

            try {
                is = new FileInputStream(rootPath + File.separator + fileName);
                reader = new InputStreamReader(is, "UTF-8");
                logger.info("在classpath下没找到，在用户工作目录下找{},是否找到：{}", fileName, is == null ? "否" : "是");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.info("获取输入流异常:{}", e);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                logger.info("不支持的编码异常:{}", e);
            }
        } else {
            try {
                reader = new InputStreamReader(is, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                logger.info("不支持的编码异常:{}", e);
            }
        }

        try {
            properties.load(reader);

        } catch (IOException e) {
            e.printStackTrace();
            logger.info("加载属性文件异常：{}", fileName);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("输入流关闭异常:{}", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    logger.info("字符流关闭异常:{}", e);
                }
            }
        }
    }

    /**
     * 加载属性文件.
     */
    public static void loadPropWithoutLog(String fileName) {
        InputStream is = null;
        Reader reader = null;

        // 首先在classpath中找，如果找不到，则在工作目录下找
        is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
        SysoutUtil.printInfo("在classpath下找{0},是否找到：{1}", fileName, is == null ? "否" : "是");
        if (is == null) {
            // 没找到，则在使用绝对路径，找到jar所在的根目录
            // String rootPath = System.getProperty("user.dir");
            String rootPath = System.getProperty("logDirPath");
            SysoutUtil.printInfo("rootPath:{0}", rootPath);

            // 截取一下文件名：BirthdayReminder/生日名单.xls ->
            if (StringUtil.subBySplit(fileName, "/") != null) {
                fileName = StringUtil.subBySplit(fileName, "/");
            } else if (StringUtil.subBySplit(fileName, "\\") != null) {
                fileName = StringUtil.subBySplit(fileName, "\\");
            }

            try {
                is = new FileInputStream(rootPath + File.separator + fileName);
                reader = new InputStreamReader(is, "UTF-8");
                SysoutUtil.printInfo("在classpath下没找到，在用户工作目录下找{0},是否找到：{1}", fileName, is == null ? "否" : "是");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                SysoutUtil.printInfo("获取输入流异常:{0}", e);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                SysoutUtil.printInfo("不支持的编码异常:{0}", e);
            }
        } else {
            try {
                reader = new InputStreamReader(is, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                SysoutUtil.printInfo("不支持的编码异常:{0}", e);
            }
        }

        try {
            properties.load(reader);

            SysoutUtil.printInfo("加载属性文件成功：{0}", fileName);
        } catch (IOException e) {
            e.printStackTrace();
            SysoutUtil.printInfo("加载属性文件异常：{0}", fileName);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    SysoutUtil.printInfo("输入流关闭异常:{0}", e);
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    SysoutUtil.printInfo("字符流关闭异常:{0}", e);
                }
            }
        }
    }

    /**
     * 根据文件名获取输入流.
     */
    public static InputStream getInputStremByFileName(String fileName) {
        InputStream is = null;
        // 首先在classpath中找，如果找不到，则在工作目录下找
        is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
        logger.info("在classpath下找{},是否找到：{}", fileName, is == null ? "否" : "是");

        if (is == null) {
            // 没找到，则在使用绝对路径，找到jar所在的根目录
            // String rootPath = System.getProperty("user.dir");
            String rootPath = System.getProperty("logDirPath");
            logger.info("rootPath:{}", rootPath);

            // 截取一下文件名：BirthdayReminder/生日名单.xls ->
            if (StringUtil.subBySplit(fileName, "/") != null) {
                fileName = StringUtil.subBySplit(fileName, "/");
            } else if (StringUtil.subBySplit(fileName, "\\") != null) {
                fileName = StringUtil.subBySplit(fileName, "\\");
            }

            try {
                is = new FileInputStream(rootPath + File.separator + fileName);
                logger.info("在classpath下没找到，在用户工作目录下找{},是否找到：{}", fileName, is == null ? "否" : "是");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.info("获取输入流异常");
            }
        }
        return is;
    }

    /**
     * 根据文件名获取输入流.
     */
    public static Reader getReaderByFileName(String fileName) {
        InputStream is = null;
        Reader reader = null;
        // 首先在classpath中找，如果找不到，则在工作目录下找
        is = PropertyUtil.class.getClassLoader().getResourceAsStream(fileName);
        logger.info("在classpath下找{},是否找到：{}", fileName, is == null ? "否" : "是");

        if (is == null) {
            // 没找到，则在使用绝对路径，找到jar所在的根目录
            // String rootPath = System.getProperty("user.dir");
            String rootPath = System.getProperty("logDirPath");
            logger.info("rootPath:{}", rootPath);

            // 截取一下文件名：BirthdayReminder/生日名单.xls ->
            if (StringUtil.subBySplit(fileName, "/") != null) {
                fileName = StringUtil.subBySplit(fileName, "/");
            } else if (StringUtil.subBySplit(fileName, "\\") != null) {
                fileName = StringUtil.subBySplit(fileName, "\\");
            }

            try {
                is = new FileInputStream(rootPath + File.separator + fileName);
                reader = new InputStreamReader(is, "UTF-8");
                logger.info("在classpath下没找到，在用户工作目录下找{},是否找到：{}", fileName, is == null ? "否" : "是");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                logger.info("获取输入流异常");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return reader;
    }

    /**
     * 通过key取对应的值.
     */
    public static String getValueByKey(String key) {
        String value = "";
        if(key.equals("int unsigned")){
            key="int";
        }
        if (key != null && key.length() > 0) {
            try {
                // 首先在系统属性中获取，如果没有，则在配置中获取
                if (!StringUtil.isEmpty(System.getProperty(key))) {
                    value = System.getProperty(key);
                    // logger.info("在系统属性中获取{}的value为：{}", key, value);
                } else {
                    value = properties.getProperty(key);
                    // logger.info("在配置文件中获取{}的value为：{}", key, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 通过key取对应的值.
     */
    public static String getValueByKeyWithoutLog(String key) {
        String value = "";
        if (key != null && key.length() > 0) {
            try {
                // 首先在系统属性中获取，如果没有，则在配置中获取
                if (!StringUtil.isEmpty(System.getProperty(key))) {
                    value = System.getProperty(key);
                    SysoutUtil.printInfo("在系统属性中获取{0}的value为：{1}", key, value);
                } else {
                    value = properties.getProperty(key);
                    SysoutUtil.printInfo("在配置文件中获取{0}的value为：{1}", key, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 设置一个属性.
     */
    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }

    /**
     * 获取配置路径.
     * 这个方法可以获取jar文件所在文件夹的路径，不包含lib包。
     */
    public static String getConfigurationPath() {
        String path = PropertyUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        if (!path.endsWith("/")) {
            path = path.substring(0, path.lastIndexOf("/") + 1);
            SysoutUtil.printInfo("jar文件所在文件夹的路径:{0}", path);
        } else {
            SysoutUtil.printInfo("classpath路径:{0}", path);
        }

        // String libDir = "/" + "lib" + "/";
        // if (path.endsWith(libDir)) {
        // path = path.replaceAll(libDir, "/");
        // }
        return path;
    }

    public static void setLogger(Logger logger) {
        PropertyUtil.logger = logger;
    }

}
