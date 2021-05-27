package PageObjects;


import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import utility.WaitCommand;


public class WestwingnowPage {
	WaitCommand wc;
    WebDriver driver;
   
	public WestwingnowPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="onetrust-accept-btn-handler")
	private WebElement PopUp;
	
	public void PopUp(){
		PopUp.click();
	}
	
	@FindBy(xpath="//div[@data-testid='reg__mode']/div[3]/button")
	private WebElement LogInHere;
	
	public void LogInHere(){
		LogInHere.click();
	}
	
	@FindBy(name="email")
	private WebElement EmailAddress;
	
	public void EmailAddress(String strEmailAddress){
		EmailAddress.sendKeys(strEmailAddress);
	}
	
	@FindBy(name="password")
	private WebElement Password;
	
	public void Password(String strPassword){
		Password.sendKeys(strPassword);
	}
	
	@FindBy(xpath="//button[@data-testid='login_reg_submit_btn']")
	private WebElement LoginButton;
	
	public void LoginButton(){
		LoginButton.click();
	}
	
	@FindBy(xpath="//input[@data-testid='search-input']")
	private WebElement Searchbar;
	
	public void Searchbar(){
		Searchbar.click();
	}
	
	public void SearchData(String searchdata){
		Searchbar.sendKeys(searchdata);
	}
	
	@FindBy(xpath="//div[@data-testid='search-wrapper']/div[2]/div[@role='contentinfo']/div/div[1]/nav/div")
	private List<WebElement> SearchProductList;
	
	
	public void SearchProductList(String value) throws InterruptedException{
		List<WebElement> ProductList = SearchProductList;
		int countProductList = ProductList.size();
		System.out.println("count of Product List : " + countProductList);
		for(int i=0;i<countProductList;i++)
		{System.out.println(ProductList.get(i).getText());
			wc=new WaitCommand(driver);
			wc.pause(2000);
			if(ProductList.get(i).getText().contains(value)){
				wc.pause(2000);
				ProductList.get(i).click();
				break;
			}
		}
		}	
	
	
	@FindBy(xpath="//div[@data-testid='cross-category-navigation']/a")
	private List<WebElement> VerifyProductPage;
	
	public List<WebElement> VerifyProductLinks(){
		return VerifyProductPage;
	}
	
	public void VerifyProductPage(List<WebElement> locator) throws InterruptedException{
		List<WebElement> ProductList = locator;
		int countProductList = ProductList.size();
		System.out.println("count of Product List : " + countProductList);
		for(int i=0;i<countProductList;i++)
		{System.out.println(ProductList.get(i).getText());
			}
		}
	
	@FindBy(xpath="(//div[@data-testid='generic-product']/a//div[2]/div[2]/div[1])[1]")
	private WebElement WishListIcon;
	
	public void WishListIcon(){
		WishListIcon.click();
	}
	
	@FindBy(xpath="//div/header/div[8]/div[3]")
	private WebElement WishListButton;
	
	public void WishListButton(){
		WishListButton.click();
	}

	@FindBy(xpath="//ul[@class='listProducts']/li[1]/button[1]")
	private WebElement DeleteProduct;
	
	public void DeleteProduct(){
		DeleteProduct.click();
	}

	
	
}
