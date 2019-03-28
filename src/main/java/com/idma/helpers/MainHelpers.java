package com.idma.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class MainHelpers {

    public static final String MAIN_URL = "https://dev2.revetinc.com/";


    private static String platform;
    public static String browser;

    public static final String chromeBr = "chrome";
    public static final String ffBr = "firefox";
    public static List<String> browsers = new ArrayList<String>() {{
        add(chromeBr);
        add(ffBr);
    }};
    public static final String DRIVER_LOCATION = "src/main/resources/";
    public static final String DEFAULT_BROWSER = ffBr;//chromeBr;
    public static final String FF_SYS_PROPERTY = "webdriver.gecko.driver";
    public static final String GC_SYS_PROPERTY = "webdriver.chrome.driver";
    private static final String DRIVER_WIN_EXT = ".exe";
    private static final String CHROME_DIRVER = "chromedriver";
    private static final String FF_DRIVER = "geckodriver";


    public static void InitialSetUp()  {

        //Get Platform
        platform = System.getProperty("os.name");
        //Get Browser
        browser = System.getProperty("browser");
        if (browser == null || !(browsers.contains(browser.toLowerCase()))) {
            browser = DEFAULT_BROWSER;
        } else {
            browser = browser.toLowerCase();
        }
//        configProperties = loadProperties(fileName);
        setCorrectDriverInfo(browser);
    }

    public static void setCorrectDriverInfo(String browser) {
        String brPath = DRIVER_LOCATION;
        if (browser.equals(chromeBr)) {
            brPath += CHROME_DIRVER;
            if (platform.contains("Windows")) {
                brPath += DRIVER_WIN_EXT;
            }
            System.setProperty(GC_SYS_PROPERTY, brPath);
        } else if (browser.equals(ffBr)) {
            brPath += FF_DRIVER;
            if (platform.contains("Windows")) {
                brPath += DRIVER_WIN_EXT;
            }
            System.setProperty(FF_SYS_PROPERTY, brPath);
        }
    }


    public static String getURL() {
        return MAIN_URL;
    }
}
