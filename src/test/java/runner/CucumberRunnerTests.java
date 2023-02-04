package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(tags = "", features = "src/test/java/features/ServiceSpecification.feature", glue = "steps",
        plugin = {})

public class CucumberRunnerTests {

}
