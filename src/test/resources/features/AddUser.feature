Feature: Librarian add users

  @wip
  Scenario Outline:add users with all valid info
    Given the librarian is on the users page
    When  user  click add user
    And user enter full Name "<fullName>"
    And user enter password "<password>"
    And user enter user Group "<userGroup>"
    And user enter start Date "<startDate>"
    And user enter email "<email>"
    And user enter status "<status>"
    And user enter end date "<endDate>"
    And user enter address "<address>"
    Then  user should be displayed by "<fullName>"
    * user should verify fullName with database "<fullName>"

    Examples:
      | fullName | password | userGroup | startDate  | email                | status | endDate    | address     |
      | oguzhan    | pass123  | Librarian | 2021-02-21 | o123@gmail.com | ACTIVE | 2021-03-21 | Connecticut |

