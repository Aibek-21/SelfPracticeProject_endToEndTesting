Feature:User add books

  Scenario Outline: user add books
    Given user on the home page
    When user clicks books button
    And user clicks add book button
    And user inputs book name "<book name>"
    And user input ISBN "<ISBN>"
    And user inputs year "<year>"
    And user inputs author "<author>"
    And user chooses book category "<book category>"
    And user inputs description "<description>"
    And user clicks save changes
    Then user should see the new book name "<name>'
    Examples:
      |book name|ISBN|year|author|book category|description|
      |Harry Potter|12345|1999|J.K.Rowling|Fable|wizards |