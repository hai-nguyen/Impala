package hainguyen.impala.automation.base;

import org.openqa.selenium.By;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import hainguyen.impala.automation.UIElements;
import hainguyen.impala.automation.ValidationValues;
import io.appium.java_client.AppiumDriver;


public class Util<Elemement> {

    private UIElements I = new UIElements();
    private ValidationValues V = new ValidationValues();

    public void pauseTestFor(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void DateTime() {

        // Create object of SimpleDateFormat class and decide the format
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        //get current date time with Date()
        Date date = new Date();

        // Now format the date
        String date1 = dateFormat.format(date);

        // Print the Date
        System.out.println(date1);

    }

    public void AllowPermission(AppiumDriver drive) {
        boolean Display = drive.findElement(By.name("Allow")).isDisplayed();
        System.out.println("PASS : " + Display);
        if (Display == true)
            drive.findElement(By.name("Allow")).click();
        else
            System.out.println("Permission : " + Display);

    }

    public boolean IsElementPresent(AppiumDriver drive, By Element) {

        try {
            drive.findElement(Element);
            System.out.println("PASS : Element Display successful " + Element);
            return true;
        } catch (Exception e) {
            System.out.println("FALSE : Element does not display " + Element);
            return false;
        }
    }

    public boolean IsTextPresentAndClick(AppiumDriver drive, By Element, String sText) {

        String sAttribute;
        sAttribute = drive.findElement(Element).getText();
        try {
            if (sAttribute.matches(sText)) {
                System.out.println("PASS : Text Display successful " + sAttribute);
                drive.findElement(Element).click();
                AllowPermission(drive);
                return true;
            } else {
                System.out.println("FALSE : Could not found element : " + sAttribute);
                return false;
            }
        } catch (Exception e) {
            System.out.println("FALSE : Could not found element : " + sAttribute);
            return false;
        }
    }

    public boolean IsTextPresent(AppiumDriver drive, By Element, String sText) {
        String sAttribute;
        sAttribute = drive.findElement(Element).getText();
        String text = sAttribute.replaceAll("\\r|\\n", " ");
        System.out.println(text);
        System.out.println(sText);
        try {
            if (!text.contains(sText)) {
                System.out.println("FALSE : Text does not display " + Element);
                return false;
            } else {
                System.out.println("PASS : Text display " + Element);
                return true;
            }

        } catch (Exception e) {
            System.out.println("PASS : Text display " + Element);
            return true;
        }

    }


    public void waitForEnable(AppiumDriver drive, By Element, int iNumber) {
        int timeout = 0;

        while (timeout <= iNumber && !IsElementPresent(drive, Element)) {
            timeout++;
        }
    }

    public void waitForTextDisplay(AppiumDriver drive, By Element, String sText, int iNumber) {
        int timeout = 0;

        while (timeout <= iNumber && !IsTextPresent(drive, Element, sText)) {
            timeout++;
        }
    }


    public boolean IsElementNotEnable(AppiumDriver drive, By Element) {
        String sAttribute;
        sAttribute = drive.findElement(Element).getAttribute("enabled");
        System.out.println(sAttribute);
        try {
            if (sAttribute.matches("false")) {
                System.out.println("PASS : Button Continue does not enable with invalid number");
                return true;
            } else {
                System.out.println("FALSE : Button Continue enable with invalid number");
                return false;
            }
        } catch (Exception e) {
            System.out.println("FALSE : Button Continue enable with invalid number");
            return false;
        }

    }

    public boolean IsElementEnable(AppiumDriver drive, By Element) {
        String sAttribute;
        sAttribute = drive.findElement(Element).getAttribute("enabled");
        System.out.println(sAttribute);
        try {
            if (sAttribute.matches("true")) {
                System.out.println("PASS : Button Continue enable with valid number");
                return true;
            } else {
                System.out.println("FALSE : Button Continue does not enable with valid number");
                return false;
            }
        } catch (Exception e) {
            System.out.println("FALSE : Button Continue does not enable with valid number");
            return false;
        }

    }

    public boolean IsElementNotDisplay(AppiumDriver drive, By Element) {
        try {
            drive.findElement(Element);
            System.out.println("FAIL : Element Display successful : " + Element);
            return false;
        } catch (Exception e) {
            System.out.println("PASS : Element does not Display successful : " + Element);
            return true;
        }
    }


}
