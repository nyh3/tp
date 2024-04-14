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

#### What it does:

Allows users to view expenditures filtered by the desired month and year values inputted by the user.
After listing out every expenditure that fit the requirements, a final amount (displayed up to 2 decimal
places) will be shown for clearer understanding of expenditure breakdown.

**2. Refactored View by Month and View by Year Functions**

[#117](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/117)

#### What it does:

Changed the inputs calling the function from using dashes `-` to using `/`, which fits more smoothly
with the rest of the codes. In addition, users are now required to have a `e/` in front of the function 
calls to distinguish said functions with the other features such as GPA and Timetalbe calculations.

**3. J-unit Tests for Adding Expenditures**

[#100](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/100)

#### What it does:

Created test cases to test for invalid inputs pertaining to illegal characters, inputs that are not
compliant with program's expected inputs, as well as edges cases pertaining to string splitting.

**4. Timetable Listing**

[#101](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/101)

#### What it does:

Allow users to see the stored timetable in ascending time format, separated by the days of the week.

**5. Handling Edge Cases**

[#182](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/182)
[#191](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/191)

#### What it does:

Added exception handling for extremely long user inputs. Where users will be prompted with an error
message. As well as an exception which handles user inputs where classes last overnight. Now classes
are only allowed to start after midnight and end before 11pm.

**6. J-unit Tests for Timetable Class**

[#202](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/202)
[#221](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/221)

#### What it does:

Created test cases to test for invalid inputs pertaining to the usage of negative or non-integer inputs;
test cases pertaining to invalid class durations, start hours and day of the week; as well as test cases
pertaining to normal operations such as addition, deletion and conflicting class inputs. In addition, test
cases pertaining to extremely long inputs are also tested to ensure robustness of the program.

**7. Made TimetableList Class More Robust**

[#222](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/222)
[#221](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/221)

#### What it does:

Restricts user inputs to certain number of characters, and ensures that the user inputs are within the 
expected range. In addition, the class now has a more robust error handling system, which will prompt 
users with specific error messages when they input specific invalid inputs.

**8. Made ExpenditureList Class More Robust**

[#217](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/217)

#### What it does:

Added restrictions on the number of characters a user can enter for each part of the expenditure input.
J-unit tests that emphasizes on lengthy inputs are also created to stress test the program and ensure
no undefined behavior occurs.

**9. JavaDoc Enhancement**

[#269](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/269)
[#268](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/268)

#### What it does:

Added JavaDoc comments to classes and methods to provide a more detailed explanation of the methods
used. This will help future developers understand the code better and make it easier for them to maintain
and improve the program.

### Contributions to User Guide

- Standardised description formats for User Guide.
  [#183](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/183)
- Reformatted and updated all components of the User Guide to adhere to cohesiveness.
  [#196](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/196)
- Highlighted information that users need to pay particular attention to when using our program.
  [#198](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/198)
- Creation of clearer descriptions for timetable-related functions available in our program.
  [#187](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/187)
- Creation of more understandable and specific description of expenditure-related functions.
  [#184](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/184)
- Included the command summaries of all available commands in our program.
  [#181](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/181)

### Contributions to Developer Guide

Wrote user stories and created sequence diagram for the CantVasMain class and PrcoessCommand class
jointly with other teammates.
[#238](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/238)
[#236](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/236)
[#234](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/234)
[232](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/232)


### Contributions to team-based tasks

- Assigned user stories corresponding to different features to individual group members.
- Creation and testing of artefact JAR files before each milestone (v1.0, v2.0 and v2.1).
- Enabled assertions for checking valid string lengths inputted into the addExpenditure class.
  [#42](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/42)
- Assigned improvement tasks of different features to group members.
- Refactored codes or other members to adhere to more OOP approaches.
- Active debugging for source-tree related problems that are encountered by other teammates.
- Inspected and reviewed the code of other teammates to ensure that the code is up to standard.
- Helped to resolve merge conflicts that occurred during the merging of different branches.
- Addressed and resolved issues raised during PED. Such as 
  [#149](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/195/commits).