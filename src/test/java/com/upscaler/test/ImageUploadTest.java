package com.upscaler.test;

import com.aventstack.extentreports.Status;
import com.upscaler.utils.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class ImageUploadTest extends BaseTest {

    @Test
    public void testImageUpload() {
        test = extent.createTest("testImageUpscaling", "Test the image upscaling process");

        ImageUpscalerPage imageUpscalerPage = new ImageUpscalerPage(driver, wait);
        imageUpscalerPage.navigateToImageUpscaler();
        test.log(Status.INFO, "Navigated to Spyne.ai Image Upscaler page");
        imageUpscalerPage.uploadImage(System.getProperty("user.dir")+File.separator+"testFiles"+ File.separator+"sampleJpeg.jpeg");
        test.log(Status.INFO, "Image uploaded");
        imageUpscalerPage.waitForLoader();
        test.log(Status.INFO, "Image upscaling process started");
        Assert.assertTrue(imageUpscalerPage.isProcessButtonDisplayed(), "Process button not displayed after upscaling");
        test.log(Status.PASS, "Image Process successful, testImageUpload passed");
    }
}
