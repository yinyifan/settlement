Feature: Create Settlement Message

  Scenario Outline: WITH ALL REQUIRED FIELDS Create Settlement Message IS SUCCESSFUL

    Given user wants to create an Settlement Message with the following attributes "<tradeId>", "<ssiCode>","<amount>","<currency>","<valueDate>"
    When user hit the POST url "v1/settlement"
    Then the save 'IS SUCCESSFUL'
    Examples:
      | tradeId | ssiCode    | amount    | currency | valueDate |
      | 1234567 | OCBC_DBS_1 | 123456.78 | USD      | 01012021  |


