---
layout: page
title: Erwin Quek's Project Portfolio Page
---
### Project: ProgrammerError

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) is a desktop application which helps CS2100 tutors manage
their students’ lab results in a simple and efficient manner, allowing them to spend less time on administrative
processes and more time teaching students.

The following is a summary of my contributions to the
project. ([View Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17))


### Features  

1. **New Feature**: Automatic sorting of the student list. 
    * What it does: it helps to ensure that the student list is always sorted whenever an add or command is executed. It first sorts the students basd on their class ID then by name. 
    * Justification: This feature improves the product significantly as it helps to simulate a real life nominal roll where the students will always be sorted by class and name, allowing the user to have an organised set of data to work with. 
    * Highlights: This enhancement provides the user with a more convenient and accessible experience in finding his / her students. 
2. **Improved Feature**: Added Student ID, Class ID and Grade 
    * What it does: it provides important information to identify the student by. 
    * Justification: This feature improves the product by providing vital important to identify the student by. 
    * Highlights: This feature will be used in other commands such as add, edit and filter. This feature was tedious as it required an overhual of the existing AB3 features and test cases. This feature also overlaps with the sample data.  
3. **Improved Feature**: Added a new set of Sample Data. 
    * What it does: it helps to populate ProgrammerError with some students. 
    * Justification: This feature improves testability of product where it allows first time user to explore and learn the various features provided by the product. 
4. **New Feature**: Implemented a feature to help generate random lab scores.
   * What it does: it helps to populate the students in the sample data with randomised lab scores. 
   * Justification This feature improves testability of the product, providing, randomised lab scores to the user. 
   * Highlight: This feature requires getting the total lab score of a particular lab as an upper bound for the randomisation to work, so that it does not generate an invalid score.
5. **Improved Feature**: Implement unique email. 
    * What it does: it ensures that only unique email inputs are valid when executing add or edit student commands. 
    * Justification: simulating a real world constraint, there will never be two students with the same email address. It also help to ensure the filter command results is consistent and accurate.
    * Highlight: Initially I thought that this will be a straightforward implementation. However, after implementing this feature, I discovered a few bugs which I was explain below. 
6. **Improved Feature**: Implement error messages for duplicate email and student id. 
    * What it does: it helps inform the user what is the error with his input command, duplicate student id or duplicate email. This will help provide the user with a better understanding of the error. 
    * Justification: previously, we only implemented a duplicate student error which is vague and does not provide the user with enough feedback to correct his command input. 
    * Highlight: After enforcing this feature and together with feature 5, there was a bug where it the user is forced to input unique email and student id. But users were now unable to edit the existing student id or email. Hence, it required some troubleshooting.
7. **Minor Fix**: Helped to refactor and update the entire project file to match the our product name. 

