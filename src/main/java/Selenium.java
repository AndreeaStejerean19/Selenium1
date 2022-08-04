import org.apache.commons.exec.launcher.CommandLauncherImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        WebElement element=driver.findElement(By.cssSelector(".account-cart-wrapper > a"));
        element.click();

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
        WebElement element = driver.findElement(By.id("search"));
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
        { String linkText=element1.getText().split("\n")[0];
        System.out.println(linkText);}

        driver.quit();
    }

    public void navigateToPage(String pageName){
        List<WebElement> pageList = driver.findElements(By.cssSelector("div#header-nav ol.nav-primary > li"));
        for(WebElement page:pageList) {
            if(page.getText().equals(pageName)) {
                page.click();
                break;
            }
        }
        //driver.quit();
    }

    public void addProductToCartWithoutQuit() throws InterruptedException {
        navigateToPage("WOMEN");

        WebElement topsBlousesButton = driver.findElement(By.cssSelector(".catblocks li:nth-child(2) > a"));
        topsBlousesButton.click();


        WebElement selectproduct = driver.findElement(By.cssSelector("ul.products-grid > li:nth-child(2) > a"));
        selectproduct.click();

        WebElement selectColorOfProduct = driver.findElement(By.cssSelector("ul#configurable_swatch_color > li:nth-child(2) > a"));
        selectColorOfProduct.click();

        WebElement selectSizeOfProduct = driver.findElement(By.cssSelector("ul#configurable_swatch_size > li:nth-child(3) > a"));
        selectSizeOfProduct.click();

        WebElement addQuantity = driver.findElement(By.id("qty"));
        addQuantity.clear();
        addQuantity.sendKeys("5");

        WebElement addProductToCart = driver.findElement(By.cssSelector(".add-to-cart-buttons "));
        addProductToCart.click();
    }

    public void addProductToCart() throws InterruptedException {
        navigateToPage("WOMEN"); //lista cu categ

        WebElement topBlousesButton = driver.findElement(By.cssSelector("ul.catblocks > li:nth-child(2) >a"));
        topBlousesButton.click();

        WebElement selectProduct = driver.findElement(By.cssSelector("ul.products-grid > li:nth-child(2) > a"));
        selectProduct.click();

        WebElement selectColorOfProduct = driver.findElement(By.cssSelector("ul#configurable_swatch_color > li:nth-child(2) > a"));
        selectColorOfProduct.click();

        WebElement selectSizeOfProduct = driver.findElement(By.cssSelector("ul#configurable_swatch_size > li:nth-child(3) > a"));
        selectColorOfProduct.click();

        WebElement addQuantity = driver.findElement(By.id("qty"));
        addQuantity.clear();
        addQuantity.sendKeys("5");

        WebElement addProductToCart = driver.findElement(By.cssSelector(".add-to-cart-buttons "));
        addProductToCart.click();

        driver.quit();

    }

    public  void removeProductFromCart() throws InterruptedException {
        addProductToCartWithoutQuit();
        navigateToPage("VIP");

        WebElement flapoverProduct = driver.findElement(By.cssSelector(".category-products > .products-grid > li:nth-child(1)"));
        flapoverProduct.click();

        WebElement addQuantity = driver.findElement(By.id("qty"));

        addQuantity.clear();
        addQuantity.sendKeys("7");

        WebElement addProductToCart = driver.findElement(By.cssSelector(".add-to-cart-buttons > button "));
        addProductToCart.click();

        WebElement removeProduct = driver.findElement(By.id("shopping-cart-table")).findElement(By.xpath("./tbody/tr/td[6]/a"));
        removeProduct.click();

    }

//    public void submitReview() {
//        navigateToPage("ACCESSORIES");
//        WebElement dropdown = driver.findElement(By.cssSelector("#nav > ol > li.level0.nav-3.active.parent > a"));
//        for(int i=0 ; i<5 ; i++){
//            dropdown.click();
//            WebDriverWait wait = new WebDriverWait(driver,15);
//            wait.until(ExpectedCondition.visibilityOf(driver.findElement()))
//   }

    public void registerNewUser() {
        account();
        WebElement myAccoutButton =driver.findElement(By.cssSelector("#header-account > div > ul > li:nth-child(5) > a"));
        myAccoutButton.click();

        WebElement fillName = driver.findElement(By.id("firstname"));
        fillName.sendKeys("Pop");

        WebElement fillLastName = driver.findElement(By.id("lastname"));
        fillLastName.sendKeys("Ana");

        WebElement fillEmail= driver.findElement(By.id("email_address"));
        fillEmail.sendKeys("ana.pop@yahoo.com");

        WebElement fillPassword = driver.findElement(By.id("password"));
        fillPassword.sendKeys("anapop2022");

        WebElement fillConfirmPassword = driver.findElement(By.id("confirmation"));
        fillConfirmPassword.sendKeys("anapop2022");

        WebElement registerButton = driver.findElement(By.cssSelector("#form-validate > div.buttons-set > button"));
        registerButton.click();
        driver.quit();

    }

    public static void main(String[] args) throws InterruptedException {
        Selenium selenium = new Selenium();
        selenium.setup();
        selenium.navigateToHomepage();

        //selenium.navigateToPage("ACCESSORIES");
        //selenium.homepage();
        //selenium.account();
        //selenium.languages();
        //selenium.search();
        //selenium.newProductsList();
        //selenium.addProductToCart();
        //selenium.addProductToCartWithoutQuit();
        selenium.removeProductFromCart();
        //selenium.registerNewUser();

    }
}
