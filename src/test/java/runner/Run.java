package runner;

import environment.Setup;
import org.testng.annotations.Test;
import pages.Workflow;

public class Run extends Setup {
    @Test
    public void runFlow() throws InterruptedException {
        driver.get("https://www.webstaurantstore.com/");
        Workflow workflow = new Workflow(driver);
        workflow.steps();
    }
}
