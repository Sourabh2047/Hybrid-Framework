package doxzilla_pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class Doxzilla_SearchPage {
    @FindBy(xpath = "//div[@class='rail-title']/h2")
    public WebElement noSearchText;
    @FindBy(xpath = "(//div[@class='card-img img-fluid'])[9]")
    public WebElement click_FirstEpisodeContent;
    //************************************************************ Detail Page *************************************************************
    @FindBy(xpath = "//div[@class='basic-detail']/h2")
    public WebElement contentTitle;
    @FindBy(xpath = "(//span[@class='episodeHeading'])[2]")
    public WebElement episodeTitle;
    @FindBy(xpath = "//span[@class='addtolist']/i")
    public WebElement addMovieToMyList;
    @FindBy(xpath = "//div[@class='start-over-icon']//img")
    public WebElement playTrailer;
    @FindBy(xpath = "//div[@class='watch-now-icon']//img")
    public WebElement playMovie;
    @FindBy(xpath = "(//div[@class='watch-now-icon'])[3]")
    public WebElement playEpisode;
    @FindBy(xpath = "//span[@class='addtolist']")
    public WebElement addEpisodeToMyList;
    WebDriverWait wait;
    WebDriver driver;
    //************************************************************ Search Header *************************************************************
    @FindBy(css = "input#search-input-control")
    private WebElement SearchTextBox;
    @FindBy(css = "//i[@class='material-icons searchicon']")
    private WebElement SearchButton;
    //************************************************************ Search Result *************************************************************
    @FindBy(xpath = "//h2[text()='Popular Searches']")
    private WebElement popularSearch;
    @FindBy(xpath = "(//button[@class='more-btn pull-right'])[1]")
    private WebElement documentarySeeAll;
    @FindBy(xpath = "(//button[@class='more-btn pull-right'])[2]")
    private WebElement episodeHeaderSeeAll;
    @FindBy(xpath = "(//div[@class='card-img img-fluid'])[1]")
    private WebElement click_FirstContent;

    //*****************************************************************************************************************************************
    public Doxzilla_SearchPage(WebDriver driver) {
        super();
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        PageFactory.initElements(driver, this);
    }

    public WebElement searchBox() {
        wait.until(ExpectedConditions.elementToBeClickable(SearchTextBox)).click();
        return SearchTextBox;
    }

    public String getSearchResultText() {
        return noSearchText.getText();
    }

    public void popularSearch() throws InterruptedException {
        Actions ac = new Actions(driver);
        WebElement ele = wait.until(ExpectedConditions.visibilityOf(SearchTextBox));
        if (driver.getCurrentUrl().contains("th")) {
            ac.click(ele);
            Thread.sleep(2000);
            ac.sendKeys(Keys.RETURN);
            ac.build().perform();
            String text = driver.findElement(By.xpath("//h2[text()='ค้นหาเรื่องยอดนิยม']")).getText();
            System.out.println(text);
            //Assert.assertEquals(text, "(ค้นหาเรื่องยอดนิยม");
        } else {
            ac.click(ele);
            Thread.sleep(2000);
            ac.sendKeys(Keys.RETURN);
            ac.build().perform();
            WebElement text = wait.until(ExpectedConditions.visibilityOf(popularSearch));
            Assert.assertTrue(popularSearch.isDisplayed());
            System.out.println(text);
            //Assert.assertEquals(text, "(Popular Searches");
        }
    }

    public String openFirstRail_FirstContent() throws InterruptedException {
        Thread.sleep(3000);
        Actions ac = new Actions(driver);
        ac.moveToElement(click_FirstContent).click();
        ac.build().perform();
        return wait.until(ExpectedConditions.elementToBeClickable(contentTitle)).getText();
    }

    public String openSecondRail_FirstContent() throws InterruptedException {
        Thread.sleep(3000);
        Actions ac = new Actions(driver);
        ac.moveToElement(click_FirstEpisodeContent).click();
        ac.build().perform();
        return wait.until(ExpectedConditions.elementToBeClickable(episodeTitle)).getText();
    }

    public void addToMyList() throws InterruptedException {
        Actions ac = new Actions(driver);
        ac.moveToElement(addMovieToMyList).click().build().perform();
        Thread.sleep(3000);
    }
}