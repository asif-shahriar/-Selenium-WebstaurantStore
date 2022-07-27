package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class Workflow {
    WebDriver driver;
    WebDriverWait wait;

    public Workflow(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "searchval")
    WebElement searchBox;
    @FindBy(css = "button[type='submit']")
    WebElement submitSearch;
    @FindBy(css = "a[data-testid='itemDescription']")
    List<WebElement> assertAll;
    @FindBy(css = "a[aria-label='Page 9']")
    WebElement lastPage;
    @FindBy(css = "input[data-action='addToCart']")
    WebElement lastProduct;
    @FindBy(css = "button[aria-label='Submit Feedback']")
    WebElement addCart;
    @FindBy(xpath = "//a[contains(text(),'View Cart')]")
    WebElement viewCart;
    @FindBy(className = "emptyCartButton")
    WebElement emptyCart;
    @FindBy(className = "subtotal")
    WebElement total;
    @FindBy(className = "bg-origin-box-border")
    List<WebElement> accept;
    @FindBy(className = "header-1")
    WebElement assertEmpty;

    public void steps() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        // Step 1 and 2
        searchBox.sendKeys("stainless work table");
        submitSearch.click();
        Thread.sleep(2000);
        // Step 3
        for (int i = 0; i < assertAll.size(); i++) {
            String s = assertAll.get(i).getText();
            Assert.assertTrue(s.contains("Table"));
            System.out.println("Assertion successful!");
        }
        // Step 4
        lastPage.click();
        Thread.sleep(1500);
        lastProduct.click();
        wait.until(ExpectedConditions.elementToBeClickable(addCart)).click();
        // Step 5
        wait.until(ExpectedConditions.elementToBeClickable(viewCart)).click();
        String t = total.getText();
        Assert.assertTrue(t.contains("264.49"));
        wait.until(ExpectedConditions.elementToBeClickable(emptyCart)).click();
        accept.get(9).click();
        String e = assertEmpty.getText();
        Assert.assertTrue(e.contains("Your cart is empty"));
    }
}
