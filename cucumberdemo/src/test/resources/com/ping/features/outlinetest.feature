Feature: outline feature
  Everybody wants to know when it's Friday

  Scenario Outline: Sunday isn't Friday yet
    Given today is <today>
    When I ask whether it's Friday yet
    Then I should be told <answer>
    Examples:
      | today      | answer              |
      |"friday"    | "friday actual"     |
      |"not friday"| "not friday actual" |