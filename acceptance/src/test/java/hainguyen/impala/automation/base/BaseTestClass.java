package hainguyen.impala.automation.base;

import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import hainguyen.impala.automation.BuildConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;


public class BaseTestClass {

    protected static AppiumDriver driver;
    final static String APPIUM_URL = "http://0.0.0.0:4723/wd/hub";

    @BeforeClass public static void init() throws MalformedURLException {

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "5.1.1");
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android device");
        cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, "4000");
        cap.setCapability(MobileCapabilityType.APP, getAppPath(BuildConfig.APP_MODULE_PATH));
        cap.setCapability(MobileCapabilityType.FULL_RESET, false);
        cap.setCapability(MobileCapabilityType.NO_RESET, true);
        driver = new AndroidDriver(new URL(APPIUM_URL), cap);
    }

    @AfterClass public static void decompose() throws Exception {
        if (driver != null) driver.quit();
    }

    @Before public void setup() throws Exception {
        driver.launchApp();
    }

    @After public void tearDown() {
        driver.closeApp();
    }

    private static String getAppPath(String appModuleName) {
        String currentDir = new File("").getAbsolutePath();
        String baseDirPath = currentDir.substring(0, currentDir.lastIndexOf(File.separator))
            + File.separator
            + appModuleName
            + File.separator
            + "build";
        String apkDirectoryPath = baseDirPath + File.separator +
            "outputs" + File.separator + "apk";
        File apkDirectory = new File(apkDirectoryPath);
        String apkFilePath = StringUtils.EMPTY;
        if (apkDirectory.exists() && apkDirectory.isDirectory()) {
            for (File file : apkDirectory.listFiles()) {
                if (file.getName().indexOf("automation-debug") > -1
                    && file.getName().indexOf("unaligned") < 0) {
                    apkFilePath = file.getAbsolutePath();
                    break;
                }
            }
        }

        if (StringUtils.isEmpty(apkFilePath)) {
            throw new IllegalArgumentException("APK file not found for automation test");
        }

        return apkFilePath;
    }
}
