package hainguyen.impala.automation;

import org.junit.Assert;
import org.junit.Test;

import hainguyen.impala.automation.base.BaseTestClass;
import hainguyen.impala.automation.base.Util;


public class AppiumSampleTest extends BaseTestClass {
    private Util u = new Util();
    private UIElements I = new UIElements();
    private ValidationValues V = new ValidationValues();
    @Test
    public void UIVerification()
    {
        System.out.println("=========== UIVerification ===========" );
        u.DateTime();
        Assert.assertTrue(u.IsElementPresent(driver, I.buttonTest));
        Assert.assertTrue(u.IsTextPresent(driver, I.buttonTest, V.sClickMe));

        // Click on Click Me button
        u.IsTextPresentAndClick(driver,I.buttonTest,V.sClickMe);

        // Verification : Email and Password display
        Assert.assertTrue(u.IsElementPresent(driver, I.email));
        Assert.assertTrue(u.IsElementPresent(driver, I.password));
        Assert.assertTrue(u.IsElementPresent(driver, I.email_sign_in_button));

    }

    @Test
    public void LoginSuccessful()
    {
        System.out.println("=========== LoginSuccessful ===========" );
        u.DateTime();
        Assert.assertTrue(u.IsElementPresent(driver, I.buttonTest));
        Assert.assertTrue(u.IsTextPresent(driver, I.buttonTest, V.sClickMe));

        // Click on Click Me button
        u.IsTextPresentAndClick(driver,I.buttonTest,V.sClickMe);

        // input email
        driver.findElement(I.email).sendKeys(V.sEmail);

        // input password
        driver.findElement(I.password).sendKeys(V.sPassword);

        // Click on email_sign_in_button
        u.IsTextPresentAndClick(driver,I.email_sign_in_button, V.sSighIn);
        u.waitForEnable(driver,I.textViewWelcome,5);

        // Verification : Login success
        Assert.assertTrue(u.IsTextPresent(driver, I.textViewWelcome , V.textViewWelcome));
        Assert.assertTrue(u.IsTextPresent(driver, I.textViewDateOfBirth , V.textViewDateOfBirth));
        Assert.assertTrue(u.IsTextPresent(driver, I.textViewGender , V.textViewGender));
        Assert.assertTrue(u.IsTextPresent(driver, I.textViewAddress , V.textViewAddress));
        Assert.assertTrue(u.IsTextPresent(driver, I.textViewPhone , V.textViewPhone));
    }

}
