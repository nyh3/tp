# User Guide

## Introduction

Welcome to CantVas!

Congratulations on taking the first step towards smarter, more organized 
student life! CantVas is your ultimate companion for managing your academic 
and financial responsibilities with ease. Designed with students in mind, our 
app offers a comprehensive suite of tools to help you stay on top of your 
expenses, organize your timetable and track your grades.

## Quick Start
1. Ensure you have `Java-11` or above installed on your computer.
2. Download the latest version of `CantVas` from [here](https://github.com/AY2324S2-CS2113-W13-3/tp/releases).
3. Copy the jar file to your desired folder.
4. Open a command prompt in the same directory as the jar file.
5. Run `java -jar CantVas.jar` to run the application.

## Features 
CantVas includes the following 
features:
- Expenditure Tracker 
- GPA Calculator
- Timetable Tracker
- Daily Motivational Quotes

### Expenditure Tracker
The expenditure tracker allows you to store, view, and filter the 
expenditures you have entered. Making it a good way to track your 
expenses.

The features include:
- Adding of new expenditures. To do so, key in with the following
  format: `e/ add/ d/ <description> amt/ <cost> date/ <dd.mm.yyyy>`.
- Viewing of saved expenditures without filters. To do so, key in 
  with the following format: `list`.
- Viewing of saved expenditures by month and year. To do so, key in 
  with the following format: `view -m <mm.yyyy>`.
- Viewing of saved expenditures by year. To do so, key in with the
  following format: `view -y <yyyy>`.
- Viewing of saved expenditures by type. To do so, key in with the
  following format: `view -t <type>`.

### GPA Calculator
~~Having LOW GPA? Use GPA calculator
to make you feel better XD~~
> [!TIP]
> You can access this function simply typing `gpa` at 
> main page.
1. Type `GPA` (Regardless of caps) to proceed to start the function.
2. Type Your Current_GPA / Numbers_of_MCs_Taken.
E.g. `4.00/30`
3. Type the numbers of modules taken for this semester.
E.g. `4` $${\color{blue}4}$$
4. Type in the Modular_Credit/Expected grade for each module taken.
E.g. `4/A+` `2/b+`
5. Your GPA will be automatically calculated ! Yeahhh!

### Timetable

### Daily Motivational Quotes
Feeling Down? Dred looking at your packed timetable?
CantVas is here to save your day and uplift your spirits whenever you need it most(when looking at expenses and timetable)

Everytime you start CantVas, we will provide you with a motivational quote to give you energy and
encouragement to get through your day

## FAQ

**Q**: What kind of timetable is the app able to display? Is it for the whole semester, one month or one week?

**A**: CantVas is able to display your timetable from Monday to Friday.


## Command Summary

{Give a 'cheat sheet' of commands here}

* Add todo `todo n/TODO_NAME d/DEADLINE`
