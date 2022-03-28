@Selenium
Feature: set selenium for test.
  for practice
  Scenario: open a browser1
    Given open a browser with a url
    Then input "test" by xpath value "//input[@type='text']"
    And input "test" by xpath value "//input[@type='password']"
    And touch "//button//span[text()='登录']"