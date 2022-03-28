@RestAssuredCase
Feature: test feature test
#  Scenario: test assured aw
 #   When I send a get request with url "http://localhost:8080/usertwo/getUser?id=11111"
   # Then the response status should be "200"

  Background:
  Given set base url and set header
  """
    {"accept-encoding": "gzip,deflate","accept-language":"zh-CN"}
    """
#    Scenario Outline:
#      When I send a get request with url "http://localhost:8080/usertwo/getUser?id=11111"
#      Then the JSON response "<jsonPath>" equals "<value>"
#      Examples:
#        | jsonPath | value |
#        |code      |200    |

  Scenario: test post request
    When I send a POST request to "/save" and request json:
    """
    {
    "bookid": "5433",
    "bookname": "Effective Java",
    "author": "Joshua Bloch",
    "price": "670"
}
    """
    Then state code will be "200"
    And the JSON response "bookid" equals "5433"
  Scenario: test put with param
    When I get book with "1" and "test"
    Then response code should be 200 and header should be:
    """
{"Content-Type":"application/json","Transfer-Encoding":"chunked","Keep-Alive":"timeout=60","Connection":"keep-alive"}
    """
