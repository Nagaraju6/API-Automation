Feature: protean API Automation

  Scenario Outline: Verify the Search Request coming from BAP to BG with and without header
    Given Add payload for "<scenario>" "<search type>"
    When user calls SearchAPI with POST http request
    Then the API call is success with <status code>
    And status in response body is "<status>"

    Examples: 
      | scenario                           | search type   | status code | status |
      | SearchRequestWithHeader            | Retail_Search |         200 | ACK    |
      | SearchRequestWithoutheader         | Retail_Search |         401 | NACK   |
      | SearchRequestForIncorrectSignature | Retail_Search |         401 | NACK   |
      | SearchRequestForIncorrectHeader    | Retail_Search |         401 | ACK    |
      
