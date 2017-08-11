package cn.zry.modules.common;

public class ConfigurationHolder {

    /** 文件内容加载到Properties中后，将被关闭释放 */
    private PropertiesLoader p;

    private static class SingletonHolder {
        private static ConfigurationHolder uniqueInstance;
        private static String APP_PROPERTIES_FILE_NAME = "application.properties";
        private static String APP_PROPERTIES_FILE_NAME_DEV = "application.dev.properties";

        static {
            uniqueInstance = new ConfigurationHolder();
            init();
        }

        static void init() {
            String activeProfile = System.getProperty("spring.profiles.active");
            System.out.println("-------------------"+activeProfile+"----------------------------------");
            if ("development".equalsIgnoreCase(activeProfile)) {
                uniqueInstance.p = new PropertiesLoader(APP_PROPERTIES_FILE_NAME, APP_PROPERTIES_FILE_NAME_DEV);
            } else {
                uniqueInstance.p = new PropertiesLoader(APP_PROPERTIES_FILE_NAME);
            }
        }
    }

    public synchronized static void reload() {
        SingletonHolder.init();
    }

    public static PropertiesLoader getInstance() {
        return SingletonHolder.uniqueInstance.p;
    }

}
