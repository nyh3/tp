# Developer Guide

## Acknowledgements

{list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well}

## Design & implementation

The sequence diagrams for our main class, CantVasMain and its interactions with
other classes.
![img.png](images/img.png)
![img_1.png](images/img_1.png)

## Product scope
### Target user profile
NUS Students.

### Value proposition

Users can use this app to keep track of their expenditures and therefore they can better manage their finances.
Students can use this app to calculate their GPA grade

## User Stories

| Version | As a ...                                | I want to ...                                                        | So that I can ...                                                                 |
|---------|-----------------------------------------|----------------------------------------------------------------------|-----------------------------------------------------------------------------------|
| v1.0    | international student on a budget       | use CantVas to add my purchases                                      | meticulously record my expenses                                                   |
| v1.0    | analytical student                      | view a breakdown of his expenditures                                 | focus on financial responsibility                                                 |
| v1.0    | budget-conscious student                | view my expenses filtered by specific months and years               | track my progress towards financial goals and identify any trends over time       |
| v1.0    | long-term planner                       | see an overview of my expenditures grouped by year                   | evaluate my financial health and plan for future expenses accordingly             |
| v1.0    | student who wants to start fresh        | ability to delete all of my recorded expenditures                    | reset my financial tracking system and begin anew                                 |
| v1.0    | careless user                           | delete wrong items in the expenditure tracker                        | add back the correct item in the expenditure tracker                              |
| v1.0    | user who needs assistance               | be able to access help information within the application            | understand how to use its features effectively                                    |
| v1.0    | user finished with my tasks             | be able to exit the application easily                               | close it and move on to other activities                                          |
| v1.0    | Proactive business major                | calculate my course grades on CantVas step by step                   | see which mod I have to SU                                                        |
| v1.0    | user who values user assistance         | access help information and exit the GPA calculator easily           | get assistance if needed and navigate the application seamlessly                  |
| v2.0    | organized student                       | add my expenses by type                                              | understand where my money is going and make informed financial decisions          |
| v2.0    | strategic spender                       | categorize and view my expenditures by type                          | identify areas where I can potentially cut costs or adjust my budget.             |
| v2.0    | user seeking daily inspiration          | see motivational quotes and tips CantVas provides at startup         | stay motivated throughout her academic journey                                    |
| v2.0    | student trying to organize my schedule  | input my classes into the timetable tracker                          | keep track of the classes I have                                                  |
| v2.0    | student who needs to adjust my schedule | remove specific classes from my timetable                            | ensure the timetable accurately reflects my current commitments                   |
| v2.0    | student planning for the upcoming week  | see all my classes scheduled for the week in one place               | plan my study and extracurricular activities efficiently                          |
| v2.0    | student organizing my daily tasks       | check my classes for a specific day                                  | focus on the tasks and commitments for that day without feeling overwhelmed       |
| v2.0    | meticulous user                         | add modules to the list by inputting their details                   | maintain a comprehensive record of all the modules I've taken for GPA calculation |
| v2.0    | detail-oriented student                 | edit the module list, including adding, deleting, or viewing modules | ensure the accuracy of my GPA calculation                                         |

## Non-Functional Requirements

1. Should be portable and working on any mainstream OS as long as it has Java 11 or above installed.
2. Should be able to hold up to 1000 or more expenditure without any change in performance of CantVas.
3. Should be able to handle exceptions and edge cases for smooth operation.
4. A user with an average typing speed in regular English text (not code or system admin commands) should find that 
they can complete most tasks more quickly using text commands rather than relying on the mouse.
5. Comprehensive unit tests should be implemented within the application to guarantee accurate functionality, 
streamline maintenance, and facilitate future enhancements.

## Environment Setup
Java JDK 8 or above
An IDE that supports Java (e.g., IntelliJ IDEA, Eclipse)

## Glossary

* *glossary item* - Definition

## Instructions for manual testing

{Give instructions on how to do a manual product testing e.g., how to load sample data to be used for testing}

# Motivational messages
The Motivational module prints a randomly generated motivational quote for the user whenever the program starts 

# Expenditure
The expenditure module is comprehensive allows users to key in their expenses to enable them to store all their 

## Overview
The expenditure module allows users to key in their expenses to enable them to store all their 
expenses.
This guide will walk developers through how to use the expenditure function to track their expenses

### List of Operations:
#### Deleting an expenditure:
Deleting an expenditure in numerical form, by referencing its index in the array.
Format: e/ del/ INDEX
Example: e/ del/ 3
#### Adding an expenditure:
Adding an expenditure in numerical form.
Format: add n/DOLLARS_AND_CENTS d/DD.MM.YYYY
Example: add n/3.22 d/31.01.2024
#### Viewing expenditures by month:
Viewing an expenditure in numerical form, filtered by month.
Format: view -m MM.YYYY
Example: view -m 01.2023
#### View expenditure by year:
Viewing an expenditure in numerical form, filtered by year.
Format: view -y YEAR
Example: view -y 2023
#### Viewing expenditures:
Viewing an expenditure in numerical form, in sequence of when they are added.
Format: list
Example: list



# GPA Function
The GPA Calculator Module is a comprehensive component designed to facilitate the calculation 
of a user's updated GPA based on their current academic standing and projected module grades. 
This guide will walk developers through the architecture, functionality, and core components of the module.

------------------------------------------------------------------------------------------

## Overview
The module is divided into two primary classes:

------------------------------------------------------------------------------------------

## Class: Expenditure
This class is responsible for processing expenditure related commands and storing all expenses input by the user

### ExpenditureList Key Method
#### addExpenditure(String expenditure, Boolean userAdded)
Takes in the string input by the user and splits it into the respective parts. "userAdded" checks if the method is called by
the user or called when reading from a storage file.

### deleteExpenditure(int index)
Takes in an index and deletes the respective expenditure from the expenditure list.

### GPACommand: 
Handles user interactions, input collection, and directs the flow of the GPA calculation process.
### GPAMain: 
Contains the logic for calculating the updated GPA.

## Class: GPACommand
This class is responsible for interacting with the user, collecting inputs, 
and managing the flow of the GPA calculation process. It operates within a loop, 
allowing the user to perform multiple calculations or exit at any point.

### GPACommand Key Methods
#### processGPACommand(): 
Orchestrates the overall process, including collecting user inputs and displaying the updated GPA.

### Workflow
#### Start/Exit Prompt: 
Prompt the user to start the calculation or exit.
#### GPA and MCs Input: 
Collect the user's current GPA and the number of Modular Credits (MCs) taken.
#### Module Details Input: 
For each module the user wants to add, collect the modular credit and the expected grade.
#### GPA Calculation: 
Calls GPAMain.calculateNewGPA to compute the updated GPA based on inputs.
#### Display Updated GPA: 
Displays the calculated updated GPA to the user.


## Class: GPAMain
Contains the core logic for calculating the new GPA given the user's current GPA, 
total accumulated credits, and details of modules being added.

### Key Methods
#### calculateNewGPA()
input: double currentGPA, int totalAccumulatedCredits, int numOfModules, int[] moduleCredits, String[] moduleGrades
Calculates and returns the updated GPA.
#### calculatePointsForGrade(String grade): 
Translates a letter grade into its corresponding GPA points.

### GPA Calculation Logic
#### Total Points Calculation: 
Calculates the total points achieved so far by multiplying the current GPA by the total accumulated credits.
#### Add Points for New Modules: 
For each new module, add to the total points based on the grade and credits.
#### Calculate Updated GPA: 
Divide the total points by the new total credits (accumulated + new modules) to get the updated GPA.

------------------------------------------------------------------------------------------
## Sub-Classes

## Class: ProcessCommand
This class identifies the userinput type and passes the command into the relevant classes to exceute the command

## Class: InvalidInputFormatException

### InvalidInputFormatException()
Throws an error message when the user enters an input that has the wrong format and the user will be informed about their wrong format

### Usages 
This exception is thrown in addExpenditure()

## Class: Storage
Handles reading from and writing to the expenditure file.

### Key Methods
#### createNewFile(): 
Creates a new expenditure file if it doesn't exist.
#### readExpenditureFile(): 
Reads expenditure data from the file and returns an ExpenditureList object containing the data.
#### processLine(String line): 
Processes a line read from the file and extracts expenditure information.
#### writeToFile(ExpenditureList expenses): 
Writes expenditure data from an ExpenditureList object to the file.

## Development Notes
Input Validation: Ensure that GPA scores and credit numbers are within valid ranges. This module expects a GPA between 0 and 5, and non-negative numbers for credits.
Error Handling: Properly handle invalid inputs, such as non-numeric values for credits or unsupported grade values.
Assertions: Use assertions to catch unexpected values during development. Ensure they are adequately handled or logged.

## Future Enhancements
GUI Integration: Consider developing a graphical user interface for easier input and interaction.

Persistent Data: Implement functionality to save and retrieve historical GPA calculations.

Expanded Grade Scale: Allow for customization of the grade to GPA points mapping to accommodate different institutions' grading scales.
