package cucumber.Options;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions (	features="src/test/java/features"
					,glue={"stepDefinations"}
					,tags="@excel"
					,plugin = "json:target/jsonReports/cucumber-report.json"
					,publish = false
					)
public class TestRunner {
	
	
}
