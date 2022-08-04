import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

@RunWith(JUnit4.class)
  public class Homepage {
      private WebDriver driver;

      @BeforeClass
      public static void SetDriverLocation(){
          System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
      }


      @Before
      public void navigateToHomepage(){
          driver = new ChromeDriver();
          driver.get("http://qa2magento.dev.evozon.com/")  ;
      }

      @Test
      public void homepage(){
          String title=driver.getTitle();
          System.out.println(title);

          String url=driver.getCurrentUrl();
          System.out.println(url);

          WebElement element=driver.findElement(By.className("logo"));
          element.click();

          driver.navigate().back();
          driver.navigate().forward();
          driver.navigate().refresh();

          Assert.assertEquals(driver.getTitle(), "Madison Island");
      }
    @Test
    public void account() {
        navigateToHomepage();
        Assert.assertEquals(driver.findElements(By.cssSelector(".account-cart-wrapper > a")).size(), 1);
    }
    @Test
    public void languages() {
        WebElement element=driver.findElement(By.cssSelector("#select-language"));
        element.click();

        Select oSelect = new Select(driver.findElement(By.cssSelector("#select-language")));
        List<WebElement> elementCount = oSelect.getOptions();
        System.out.println(elementCount.size());

        Select selectLanguage=new Select(driver.findElement(By.id("select-language")));
        Assert.assertEquals(1, selectLanguage.getAllSelectedOptions().size());
        //Assert.assertEquals("French", selectLanguage.getFirstSelectedOption().getText());
    }
    @Test
    public void search() {
        WebElement element = driver.findElement(By.id("search"));
        element.sendKeys("Acest text va fi sters");
        element.clear();
        element.sendKeys("woman");
        element.submit();
        Assert.assertEquals(driver.getCurrentUrl(), "http://qa2magento.dev.evozon.com/catalogsearch/result/?q=woman");
    }

    @Test
    public void newProductsList(){
            List<WebElement> elementCount = driver.findElements(By.cssSelector(".item.last"));
            System.out.println(elementCount.size());

            WebElement element = driver.findElement(By.className("product-name"));
            for(WebElement element1 : elementCount)
            { String linkText=element1.getText().split("\n")[0];
                System.out.println(linkText);}
         Assert.assertEquals(5,elementCount.size());

        }

       @Test
       public void navigateToPage(){
           List<WebElement> pageList = driver.findElements(By.cssSelector("div#header-nav ol.nav-primary > li"));
           for(WebElement page:pageList) {
               if(page.getText().equalsIgnoreCase("Sale")) {
                   page.click();
                   break;
               }
           }
           Assert.assertEquals(driver.getTitle(), "Sale");
       }

      @After
      public void quitDriver() {
          driver.quit();
    }



}
