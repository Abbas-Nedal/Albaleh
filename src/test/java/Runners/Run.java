package Runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "use-case", plugin = "html:outputHtml/report.html",monochrome = true,snippets = CucumberOptions.SnippetType.CAMELCASE,
glue = "Runners")
public class Run {



}
