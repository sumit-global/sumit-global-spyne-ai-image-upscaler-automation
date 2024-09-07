package com.upscaler.test;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class ImageUpscalerPage {
    WebDriver driver;
    WebDriverWait wait;

    // PageFactory locators
    @FindBy(xpath = "//*[text()='Upload an image']")
    WebElement uploadButton;

    @FindBy(css = "[alt='spyne logo']")
    WebElement logo;

    @FindBy(xpath = "//*[contains(@class,'dropdown-tab')]")
    List<WebElement> menuItems;

    @FindBy(xpath = "//*[text()='Get a Demo']")
    WebElement getDemoButton;

    @FindBy(xpath = "//*[contains(@src,'loader.gif')]")
    WebElement loader;

    @FindBy(xpath = "//*[text()='Process']")
    WebElement buttonProcess;

    @FindBy(xpath = "(//*[contains(text(),'No image to upload')]/following-sibling::div[1]//img)[1]")
    WebElement firstStaticImage;

    @FindBy(css = "[class*=spinner]")
    WebElement processor;

    @FindBy(xpath = "(//*[contains(text(),'Download')])[1]")
    WebElement downloadButton;

    // Constructor initializes elements using PageFactory
    public ImageUpscalerPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public void navigateToImageUpscaler() {
        driver.get("https://www.spyne.ai/image-upscaler");
    }

    public void uploadImage(String imagePath) {
        wait.until(ExpectedConditions.visibilityOf(firstStaticImage)).click();
    }

    public boolean isLogoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
    }

    public boolean isGetDemoDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(getDemoButton)).isDisplayed();
    }

    public boolean isMenuItemsDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfAllElements(menuItems)).size() == 7;
    }

    public void waitForLoader() {
        wait.until(ExpectedConditions.visibilityOf(loader));
    }

    public boolean isProcessButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(buttonProcess)).isDisplayed();
    }

    public void waitForProcessor() {
        new WebDriverWait(driver, Duration.ofSeconds(70)).until(ExpectedConditions.invisibilityOf(processor));
    }

    public boolean isDownloadButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOf(downloadButton)).isDisplayed();
    }

}

