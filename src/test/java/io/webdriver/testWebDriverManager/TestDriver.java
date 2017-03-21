package io.webdriver.testWebDriverManager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.ChromeDriverManager;

public class TestDriver {

  private WebDriver driver;

  @BeforeClass
  public static void setupClass() {
    ChromeDriverManager.getInstance().setup();
  }

  @BeforeMethod
  public void setupTest() {
    driver = new ChromeDriver();
  }

  @AfterMethod
  public void teardown() {
    if (driver != null) {
      driver.close();
      driver.quit();
    }
  }

  @Test
  public void test() {
    driver.navigate().to("https://www.google.com");
    WebDriverWait wait = new WebDriverWait(driver, 10);
    wait.until(//
        (ExpectedCondition<Boolean>) wd //
        -> //
        ((JavascriptExecutor) wd)//
            .executeScript("return document.readyState")//
            .equals("complete")//
    );
  }
}
