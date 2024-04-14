# Shao Sizhe - Project Portfolio Page

## Overview
CantVas is a comprehensive tool tailored for academic and financial management.
With features like expense tracking, GPA calculation, timetable management,
and daily motivation, CantVas streamlines student life for enhanced organization
and productivity.

## Summary of Contributions

### Code Contributed
[Link to code](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=heart&breakdown=true)

### Features implemented
**1. Functionality to View Filtered Expenditures**
[#22](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/22)
[#117](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/117)

#### What it does:

Allows users to view expenditures filtered by the desired month and year values inputted by the user.
Refactored function inputs from using `-` to `/` for consistency. Added `e/` prefix to function calls for
distinction from GPA and Timetable calculations.

**2. J-unit Tests for ExpenditureList and TimetableList Class**
[#100](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/100)
[#202](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/202)
[#221](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/221)

#### What it does:

Developed tests for invalid inputs, non-compliant inputs, and edge cases related to string splitting.

**3. Timetable Listing**
[#101](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/101)

#### What it does:

Allow users to see the stored timetable in ascending time format, separated by the days of the week.

**4. Handling Edge Cases in TimetableList Class**
[#182](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/182)
[#191](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/191)

#### What it does:

Implemented exception handling for lengthy inputs and overnight classes. 
Classes are now restricted from midnight to 11 pm.

**5. Made TimetableList and ExpenditureList Class More Robust**
[#222](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/222)
[#221](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/221)
[#217](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/217)

#### What it does:

Imposes character limit on user inputs, validates input range, and provides specific 
error messages for invalid inputs.

**6. JavaDoc Enhancement**
[#269](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/269)
[#268](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/268)

#### What it does:

Enhanced JavaDoc comments for better code understanding and maintainability.

### Contributions to User Guide

- Reformatted and updated all components of the User Guide to adhere to cohesiveness.
  [#196](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/196)
  [#183](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/183)
- Highlighted information that users need to pay particular attention to when using our program.
  [#198](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/198)
  [#187](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/187)
- Creation of a more understandable and specific description of expenditure-related functions.
  [#184](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/184)
- Included the command summaries of all available commands in our program.
  [#181](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/181)

### Contributions to Developer Guide

Wrote user stories and created sequence diagrams for the CantVasMain class and PrcoessCommand class
jointly with other teammates.
[#238](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/238)
[#236](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/236)
[#234](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/234)
[#232](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/232)


### Contributions to team-based tasks

- Assigned user stories corresponding to different features to individual group members.
- Creation and testing of artefact JAR files before each milestone (v1.0, v2.0 and v2.1).
- Enabled assertions for checking valid string lengths inputted into the addExpenditure class.
  [#42](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/42)
- Assigned improvement tasks of different features to group members.
- Refactored codes or other members to adhere to more OOP approaches.
- Active debugging for source-tree-related problems that are encountered by other teammates.
- Addressed and resolved issues raised during PED. Helped other members address issues as well. Such as 
  [#195](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/195/commits).
