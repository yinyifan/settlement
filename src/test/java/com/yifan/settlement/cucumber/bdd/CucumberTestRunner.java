package com.yifan.settlement.cucumber.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features",plugin = {"pretty","json:target/cucumber-report.json"})
public class CucumberTestRunner {
}
