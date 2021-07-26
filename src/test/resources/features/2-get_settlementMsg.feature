Feature: retrieve existing settlement Msg by trade id
  Scenario: with valid trade id it returns existing settlement msg
    When user request for existing settlement msg with existing trade id
    Then the fetch 'IS SUCCESSFUL'
