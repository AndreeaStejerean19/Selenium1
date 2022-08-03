import org.apache.commons.exec.launcher.CommandLauncherImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class Selenium {

    static WebDriver driver;

    public void setup(){
        System.setProperty("webdriver.chrome.driver","Driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    public void navigateToHomepage() {
        driver.get("http://qa2magento.dev.evozon.com/");
    }

    private void homepage() {
            String title=driver.getTitle();
            System.out.println(title);

            String url=driver.getCurrentUrl();
            System.out.println(url);

            WebElement element=driver.findElement(By.className("logo"));
            element.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            driver.navigate().to("http://qa1magento.dev.evozon.com/women.html");

            driver.navigate().back();

            driver.navigate().forward();

            driver.navigate().refresh();

            driver.quit();
        }

        public void account() {

        WebElement element=driver.findElement(By.cssSelector("#header > div > div.skip-links > div > a"));
        element.click();

        driver.quit();
    }
    public void languages(){

        WebElement element=driver.findElement(By.cssSelector("#select-language"));
        element.click();

        Select oSelect = new Select(driver.findElement(By.cssSelector("#select-language")));
        List<WebElement> elementCount = oSelect.getOptions();
        System.out.println(elementCount.size());

        Select select=new Select(element);
        select.selectByVisibleText("French");

        driver.quit();

    }

    public void search() throws InterruptedException {

        WebElement element = driver.findElement(By.cssSelector("#search"));
        element.sendKeys("Acest text va fi sters");
        Thread.sleep(3000);
        element.clear();

        element.sendKeys("woman");
        Thread.sleep(3000);
        element.submit();
        Thread.sleep(3000);

        driver.quit();
    }

    public void newProductsList(){
        List<WebElement> elementCount = driver.findElements(By.cssSelector(".item.last"));
        System.out.println(elementCount.size());

        WebElement element = driver.findElement(By.className("product-name"));
        for(WebElement element1 : elementCount)
        { String linkText=element1.getText().split("\n")[0]; //ia doar numele pana la enter
        System.out.println(linkText);}

        driver.quit();
    }

    public void navigateToPage(String pageName){
        //sa identifici lista
        List<WebElement> pageList = driver.findElements(By.cssSelector("div#header-nav ol.nav-primary > li")); //elem de tip ol care are clasa primary
        //parcurgi  lista WebElement e tipul elem(ca int, string), page=i, pageList e=n
        for(WebElement page:pageList) {
            //pune conditia si verifica conditia
            //System.out.println(page.getText());
            if(page.getText().equals(pageName)) {
                page.click();
                break;
            }
        }
        //driver.quit();
    }


    //ACELASI FOR CA SI SUS
//        for(int i=0; i<pageList.size(); i++)
//        {
//            if(pageList.get(i).getText().equals(pageName)) {
//                pageList.get(i).click();
//                break;
//            }
//        }

    public void addProductToCartWithoutQuit() throws InterruptedException {
        //get(1)
        //lista cu categ, cu submeniu, cu prod
        //identif list de culori, size
        navigateToPage("WOMEN"); //lista cu categ

        WebElement topBlousesButton = driver.findElement(By.cssSelector("body > div > div > div.main-container.col1-layout > div > div.col-main > ul > li:nth-child(2) > a > img"));
        topBlousesButton.click();

        WebElement selectproduct = driver.findElement(By.cssSelector("body > div > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(2) > a"));
        selectproduct.click();

        WebElement selectColorOfProduct = driver.findElement(By.cssSelector("#swatch21 > span.swatch-label > img"));
        selectColorOfProduct.click();

        WebElement selectSizeOfProduct = driver.findElement(By.cssSelector("#swatch81 > span.swatch-label"));
        selectSizeOfProduct.click();

        WebElement addQuantity = driver.findElement(By.id("qty"));
        addQuantity.sendKeys("5");
        Thread.sleep(3000);

        WebElement addProductToCart = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button > span > span"));
        addProductToCart.click();

    }

    public void addProductToCart() throws InterruptedException {
        //get(1)
        //lista cu categ, cu submeniu, cu prod
        //identif list de culori, size
        navigateToPage("WOMEN"); //lista cu categ

        WebElement topBlousesButton = driver.findElement(By.cssSelector("body > div > div > div.main-container.col1-layout > div > div.col-main > ul > li:nth-child(2) > a > img"));
        topBlousesButton.click();

        WebElement selectproduct = driver.findElement(By.cssSelector("body > div > div > div.main-container.col3-layout > div > div.col-wrapper > div.col-main > div.category-products > ul > li:nth-child(2) > a"));
        selectproduct.click();

        WebElement selectColorOfProduct = driver.findElement(By.cssSelector("#swatch21 > span.swatch-label > img"));
        selectColorOfProduct.click();

        WebElement selectSizeOfProduct = driver.findElement(By.cssSelector("#swatch81 > span.swatch-label"));
        selectSizeOfProduct.click();

        WebElement addQuantity = driver.findElement(By.id("qty"));
        addQuantity.sendKeys("5");
        Thread.sleep(3000);

        WebElement addProductToCart = driver.findElement(By.cssSelector("#product_addtocart_form > div.product-shop > div.product-options-bottom > div.add-to-cart > div.add-to-cart-buttons > button > span > span"));
        addProductToCart.click();


    }

    public  void removeProductFromCart() throws InterruptedException {
        addProductToCartWithoutQuit();
        driver.navigate().back();

    }


    public static void main(String[] args) throws InterruptedException {
        Selenium selenium = new Selenium();
        selenium.setup();
        selenium.navigateToHomepage();
        //selenium.navigateToPage("ACCESSORIES");
        //homepage();
        //account();
        //languages();
        //search();
        //newProductsList();
        //selenium.addProductToCart();
        selenium.removeProductFromCart();
    }
}
