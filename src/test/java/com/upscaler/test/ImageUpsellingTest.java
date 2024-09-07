package com.upscaler.test;

import com.aventstack.extentreports.Status;
import com.upscaler.utils.BaseTest;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ImageUpsellingTest extends BaseTest {
    @Test
    public void testImageUpscaling() {
        test = extent.createTest("testImageUpscaling", "Test the image upscaling process");

        ImageUpscalerPage imageUpscalerPage = new ImageUpscalerPage(driver, wait);
        imageUpscalerPage.navigateToImageUpscaler();
        test.log(Status.INFO, "Navigated to Spyne.ai Image Upscaler page");
        imageUpscalerPage.uploadImage(System.getProperty("user.dir")+ File.separator+"testFiles"+ File.separator+"sampleJpeg.jpeg");
        test.log(Status.INFO, "Image uploaded");
        imageUpscalerPage.waitForLoader();
        test.log(Status.INFO, "Image upscaling process started");
        imageUpscalerPage.wait.until(ExpectedConditions.visibilityOf(imageUpscalerPage.buttonProcess)).click();
        imageUpscalerPage.waitForProcessor();
        Assert.assertTrue(imageUpscalerPage.isDownloadButtonDisplayed(), "Download button not displayed after upscaling");
        test.log(Status.PASS, "Image upscaling successful, testImageUpscaling passed");
    }
}
