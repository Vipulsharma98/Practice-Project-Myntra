package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

        public class MyntraTest {
            public static void main(String[] args) {
                System.setProperty("webdriver.edge.driver", "C:/Users/vipulsharma/Downloads/edgedriver_win64/msedgedriver.exe");
                WebDriver driver = new EdgeDriver();
                driver.manage().window().maximize();

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

                try {
                    // Step 1: Open Myntra
                    driver.get("https://www.myntra.com/");

                    // Wait for the Kids menu to be visible
                    WebElement kidsMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@data-group='kids']")));

                    // Step 2: Hover over 'Kids' section
                    Actions actions = new Actions(driver);
                    actions.moveToElement(kidsMenu).perform();

                    // Wait for 'Boys Clothing' to be clickable after hover
                    WebElement boysClothing = wait.until(
                            ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Boys Clothing']"))
                    );

                    // Step 3: Click 'Boys Clothing'
                    boysClothing.click();

                    // Wait for the radio buttons (e.g., size filters) to be present
                    List<WebElement> radioButtons = wait.until(
                            ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='radio']"))
                    );

                    boolean preselectedFound = false;
                    for (WebElement radio : radioButtons) {
                        if (radio.isSelected()) {
                            preselectedFound = true;
                            System.out.println("Preselected radio button found: " + radio.getAttribute("value"));
                        }
                    }

                    if (!preselectedFound) {
                        System.out.println("No preselected radio buttons found.");
                    }

                    // Optional: Wait before closing to see result (replace with wait for a specific element if needed)
                    Thread.sleep(3000);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    driver.quit();
                }
            }
        }
