Feature: test assured api
  Scenario:
    Given there is basic info
      |baseURL|https://api.douban.com/|
    When I get the book with asin "9787108059444"
    Then state code will be "200"
    And response includes the following data
      |id|27029497|