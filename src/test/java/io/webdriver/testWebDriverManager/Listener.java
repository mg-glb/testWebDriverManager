package io.webdriver.testWebDriverManager;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

public class Listener extends TestListenerAdapter {

  private WebDriver webDriver = null;

  @Override
  public void onTestSuccess(ITestResult result) {
    webDriver = ((TestDriver) result.getInstance()).getDriver();
    File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
    String nameScreenshot = result.getName();
    String fileName;
    try {
      fileName = getScreenshotFileName(nameScreenshot);
      FileUtils.copyFile(screenshot, new File(getScreenshotFilePath() + fileName));
      Reporter.setCurrentTestResult(result);
      Reporter.log(("<a href=\"screenshots\\" + fileName + "\" >"));
      Reporter.log(("<img src=\"screenshots\\" + fileName + "\" width=\"200\" height=\"200\" />"));
      Reporter.log(("</a></br>" + nameScreenshot));
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } 
  }

  private String getScreenshotFileName(String nameTest) throws IOException {
    DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy_hh.mm.ss");
    Date date = new Date();
    return dateFormat.format(date) + "_" + nameTest + ".png";
  }

  private String getScreenshotFilePath() throws IOException {
    File directory = new File(".");
    String newFileNamePath = directory.getCanonicalPath() + "/test-output/screenshots/";
    return newFileNamePath;
  }
}
