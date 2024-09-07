package com.upscaler.test;

import com.aventstack.extentreports.Status;
import com.upscaler.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationAndUIValidationTest extends BaseTest {

    @Test
    public void testNavigation() {
        test = extent.createTest("testNavigation", "Test the navigation to the Image Upscaler page");

        // Pass driver and wait to the page object
        ImageUpscalerPage imageUpscalerPage = new ImageUpscalerPage(driver, wait);
        imageUpscalerPage.navigateToImageUpscaler();
        test.log(Status.INFO, "Navigated to Spyne.ai Image Upscaler page");
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.spyne.ai/image-upscaler", "URL is not correct");
        test.log(Status.PASS, "URL is correct");
        Assert.assertTrue(imageUpscalerPage.isLogoDisplayed());
        test.log(Status.PASS, "Spyne Logo is Visible");
        Assert.assertTrue(imageUpscalerPage.isMenuItemsDisplayed());
        test.log(Status.PASS, "Total 7 Menu Item Displayed");
        Assert.assertTrue(imageUpscalerPage.isGetDemoDisplayed());
        test.log(Status.PASS, "Get Demo Button is Visible");
    }
}

