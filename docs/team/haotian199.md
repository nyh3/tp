# Liu Haotian - Project Portfolio Page

## Overview
CantVas is a comprehensive tool tailored for academic and financial management.
With features like expense tracking, GPA calculation, timetable management,
and daily motivation, CantVas streamlines student life for enhanced organization
and productivity.

## Summary of Contributions

### Code Contributed
[Link to code](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2024-02-23&tabOpen=true&tabType=authorship&tabAuthor=Haotian199&tabRepo=AY2324S2-CS2113-W13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

### Features implemented
**1. Enabled users to add and delete classes from timetable **

[#75](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/75)

#### What it does:

This is a feature for managing a timetable or class schedule in a Java application. 
It allows the user to add and delete classes from a timetable, with each class having attributes such as the day of the 
week, time, duration, and location. 
The added classes can also be stored and accessed later even after the user closes the program.

**2. Handles exceptions when input has invalid format**

[#36](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/36)

#### What it does:

The code uses a custom exception InvalidInputFormatException to handle specific cases where the input format does 
not meet the expected criteria. This exception is thrown when the input string for the expenditure does not 
contain the required keywords ("d/", "amt/", "date/") in the correct order or format.

**3. J-unit Tests for Processing command**

#### What it does:

Created JUnit tests created include test cases for TimetableParser, GPAMain, Module, ModuleList, YlogX, YX, YXsq.

JUnit tests for GC-related classes:
- Checked that the panel instances (YlogX, YX, YXsq) are not null after they have been instantiated.
- Verified that the panels have a set preferred size of 400x400 pixels.
- Ensured that drawing operation does not throw any exceptions, which is crucial for validating that the painting 
logic (including drawing graphs, axes, labels, etc.) does not encounter runtime errors or illegal states by simulating
the painting process.
  [#257](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/257)

JUnit tests for GPA-related classes:
- Ensured that the Module and ModuleList classes manage modules correctly, accurately reporting their 
state through counts and total credits.
  [#276](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/276)

JUnit tests for TimetableParser
- Ensured that TimetableParser handles erroneous inputs such as incorrect or missing commands by providing the correct 
feedback and also ensures that system still behaves as expected
  [#277](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/277)


### Contributions to User Guide

- Created the product introduction for our application CantVas 
  [#105](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/105)
- Added Q&A and user stories for the TimeTable feature.
  [#105](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/105)
  [#63](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/63/files)
  [#258](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/258)


### Contributions to Developer Guide

- Added Class Diagram and its explanation for GPA-related classes(GPACommand, GPAMain, Module, 
ModuleList, ProcessListCommand)
  [#275](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/275)
- Added Class Diagram and its explanation for GC-related classes(ProcessGCCommand, YlogX, YX, YXsq)
  [#282](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/282)
- Added Sequence Diagram for GC-related classes(ProcessGCCommand, YlogX, YX, YXsq) and explained how GC works.
  [#283](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/283)
  [#282](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/282)

### Contributions to team-based tasks

- Completed tasks that were assigned to me quickly.
- Creation and testing of artefact JAR files before each milestone (v1.0, v2.0 and v2.1).
- Corrected names of methods and classes to make them more comprehensible.
  [#54](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/54)
- Actively participated in our weekly team meetings.
- Helped with documentation of the codes so as to make the codes more readable and easier to understand
  [#262](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/262)
  [#263](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/263)
  [#266](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/266)
  [#279](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/279)
  [#281](https://github.com/AY2324S2-CS2113-W13-3/tp/pull/281)


 
