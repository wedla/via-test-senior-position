package br.com.via.test.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:reports/test-report-ui.html"}, 
	features = "src/test/resources/features/ui",
	glue = { "br.com.via.stepsDefinitions.ui"})
public class RunCucumberUITest {
}