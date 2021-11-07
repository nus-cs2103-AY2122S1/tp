---
layout: page
title: Manual Testing
---

Manual Testing was focused mainly on Testing Features and the Graphical User Interface.


## Table of Contents

* [Feature Testing](#feature-testing)
  * [Student Particulars](#features-related-to-student-particulars)
    * [Add Student](#add-student)
    * [Delete Student](#delete-student)
    * [Tag Student](#tag-student)
    * [Get Personal Detail](#get-personal-detail)
    * [Edit Personal Detail](#edit-personal-detail)
  * [Grades, Studio Attendance, Participation](#features-related-to-grades-attendance-and-participation)
    * [Add Grade](#add-grade)
    * [Edit Attendance](#edit-attendance)
    * [Edit Participation](#edit-participation)
  * [Visualization](#features-related-to-visualization)
    * [View student information](#view-student-information)
    * [Show Grade](#show-grade)
    * [Visualize all Grades](#visualize-all-grades)
    * [Filter Academy Directory](#filter-academy-directory)
    * [Sort Student List](#sort-student-list)
  * [Others](#other-features)
    * [Launch and Shutdown](#launch-and-shutdown)
    * [List all Students](#list-all-students)
    * [Clear Student List](#clear-student-list)
    * [View Commit History](#clear-student-list)
    * [Revert Commit](#clear-student-list)
    * [Undo](#clear-student-list)
    * [Redo](#clear-student-list)
    * [Help](#help)
    * [Saving Data](#saving-data)
* [GUI Testing](#graphical-user-interface-gui-testing)
  * [Visual Design](#visual-design)
  * [User Interface Functionality](#user-interface-functionality)
* [Performance Testing](#performance-testing)
  * [Visualization with large `AcademyDirectory`](#visualization-with-large-academydirectory-_coming-soon_)
  * [Limit test `AcademyDirectory` capacity](#limit-test-academydirectory-capacity-_coming-soon_)
* [Compatibility Testing](#compatibility-testing)
  * [Cross-OS Compatibility](#cross-os-compatibility-_coming-soon_)
* [Portability Testing](#portability-testing)
  * [Transfer of AcademyDirectory](#transfer-of-academydirectory-_coming-soon_)



## Feature Testing

In this section, testing focused on testing the features implemented and their basic functionality. These tests are
primarily to detect potential unintended results that unit testing for the respective commands failed to detect.

***

### Features related to Student Particulars

#### Add Student

1. Adding a student while all students are being shown

   2. Prerequisites: List all students using the `list` command. All students are shown in the list. 
   3. Test cases:
        1. `add n/Charles Ng te/@charles e/e0123434@u.nus.edu p/90390421`<br>
           Expected: One new student will be added to the list. Details of the added student shown in the status message. Result display remains the same. Students can be found in Student Panel List on the left.
        2. `add n/Charles Ng te/@charles e/e0123434@u.nus.edu`<br>
           Expected: One new student will be added to the list. Phone Number is not supplied and will be defaulted to `NA`. Details of the added student shown in the status message. Result display remains the same. Students can be found in Student Panel List on the left.
        3. `add n/Charles Ng te/@charles`<br>
           Expected: Required field `EMAIL` is not supplied. No student is added. Error `Invalid command format!` will be shown alongside with a proper usage of command. Result display remains the same.
        4. `add n/Charles Ng te/@charles e/e0123434@u.nus.edu p/90`<br>
           Expected: `PHONE` field must be number with at least 3 digits or string `NA`. No new student is added. Error will be shown alongside with a proper usage of command. Result display remains the same.
        5. `add n/Charles Ng te/@charles e/e0123434@u.nus.edu` followed by `add n/Charles Ng te/@charles e/e0123434@u.nus.edu`<br>
           Expected: Student with existing name exists. No new student is added. Error `This student already exists ...` will be shown. Result display remains the same. 
   4. Other incorrect delete commands to try:
      1. `add`<br>
         Expected: Similar to cases of missing required field.
      2. `add n/Charles`<br>
         Expected: Similar to cases of missing required field.


2. Adding a student while only one student is being shown, with there being more than one student in the list 
   1. Prerequisites: Filter students by their tag `streams` using the `filter streams` command. A subset of students shown.
   2. Test cases:
       1. `add n/Charles Ng te/@charles e/e0123434@u.nus.edu p/90390421`<br>
          Expected: One new student will be added. Details of the added student shown in the status message. Result display remains the same. Students can be found in Student Panel List on the left. This student will also be added to the actual list of students.
       2. `add n/Charles Ng te/@charles e/e0123434@u.nus.edu` followed by `filter streams` followed by `add n/Charles Ng te/@charles e/e0123434@u.nus.edu`<br>
          Expected: Student with the same found in full list of students. Duplicate detection is done on the full list of students, not on the current list in student list panel. No new student is added. Error `This student already exists ...` will be shown. Result display remains the same.
   3. Other incorrect delete commands to try:
       1. `add`<br>
          Expected: Similar to cases of missing required field.
       2. `add n/Charles`<br>
          Expected: Similar to cases of missing required field.


#### Delete Student

1. Deleting a student while all students are being shown

    1. Prerequisites: List all students using the `list` command. Multiple students in the list.

    2. Test cases:
        1. `delete 1`<br>
           Expected: First student is deleted from the list. Details of the deleted student shown in the status message. Result display remains the same.
        2. `delete 0`<br>
           Expected: No student is deleted. Error details shown in the status message. Result display remains the same.

    3. Other incorrect delete commands to try:
        1. `delete`<br>
        2. `delete -1`<br>
        3. `delete x` (where x is larger than the list size)<br>
           Expected: Similar to previous.


2. Deleting a student while a selected group of students is being shown
    1. Prerequisites: Filter students by their tag `streams` using the `filter streams` command. A subset of students shown.

    2. Test cases:
        1. `delete 1`<br>
           Expected: First student is deleted from the list. Details of the deleted student shown in the status message. Result display remains the same. This student will also be deleted in the main list.
        2. `delete 0`<br>
           Expected: No student is deleted. Error details shown in the status message. Result display remains the same.

    3. Other incorrect delete commands to try:
        1. `delete`<br>
        2. `delete x` (where x is larger than the current list size)<br>
           Expected: Similar to previous.
        

#### Tag Student

1. Tag a student while all students are being shown, and no student is being viewed.
    1. Prerequisites: List all students using `list` command. List must contain at least 1 student.
    2. Test cases: 
       1. `tag 1 t/test` <br>
          Expected: First student's tag(s) is/are now replaced by "test".
       1. `tag 1 t/tag1 t/tag2` <br>
          Expected: First student's tag(s) is/are now replaced by "tag1" and "tag2".
       1. `tag 1 t/` <br>
          Expected: First student's tag(s) is/are now removed.
       1. `tag 1 t/!!!!` <br>
          Expected: First student's tag(s) remain unchanged. Error details shown in the status message. Result display remain the same.
    4. Other incorrect tag commands to try:
        1. `tag t/test` (Missing `INDEX`)
        2. `tag -1 t/test` (`INDEX` out of bound)
        3. `tag 1 t/test t/` (Multiple tag entries cannot contain empty tags)
        4. `tag 1 t/test tag1` (Tags can only contain one word)


2. Tag a student while all students are being shown, with a student being viewed.
    1. Prerequisites: List all students using `list` command. List must contain at least 1 student. View a student using `view 1` command.
    2. The test cases mirror `Test 1`, with he student being viewed in the result display still being viewed in the same
       state after execution of the command regardless if the execution of the command is successful or not.
       To view the changes in `Tag` for the student being viewed in the result display, use `view 1` after the tag command.


#### Get Personal Detail
List of invariance to guide additional test case generation:
- At least one of `p/`, `e/`, or `te/` must be provided
- Name tag `n/` is optional; its use indicates intention to get personal detail of students whose name matches
  the given keywords
- Lack of phone number will not be explicitly shown, unless there are no other information to show.
- Current view of AcademyDirectory does not affect result
- View of AcademyDirectory always resets to the view that will be obtained when running [`ListCommand`](#listcommand)
- Order of tags present to `GetCommand` does not matter. `GetCommand` will prioritise showing phone number, followed
  by email address, followed by telegram handle.
- If duplicates of the same tag is given, the last tag will be used regardless of its validity.

Below are a few test cases which checks for the above. The test cases are by no means exhaustive.

1. Retrieving personal detail of all students while all students have a phone number.
    1. Prerequisites:
        - All students in the list must have a phone number
        - Run each test case twice, the first time by having all students listed using the `list` command,
          and the second time by having the view only show some students e.g. by using `filter` command
          on the tags. Expected outcome is the same in both cases for all test cases
    2. Test cases:
        1. `get p/`<br>
           Expected: Phone number of all students are shown in the result display.
        2. `get e/`<br>
           Expected: Email address of all students are shown in the result display.
        3. `get te/` <br>
           Expected: Telegram handle of all students are shown in the result display.
        4. `get p/ e/` <br>
           Expected: Phone number of all students are shown in the result display, followed by email address
           of all students.
        5. `get e/ p/` <br>
           Expected: Phone number of all students are shown in the result display, followed by email address
           of all students.
        6. `get e/ p/ te/` <br>
           Expected: Phone number of all students are shown in the result display, followed by email address
           and then by telegram handle.


2. Retrieving personal detail of all students. At least one student has no phone number and
   at least one student has a phone number.
    1. Prerequisites:
        - At least one student has no phone number and at least one student has a phone number.
        - Run each test case twice, the first time by having all students listed using the `list` command,
          and the second time by having the view only show some students e.g. by using `filter` command
          on the tags. Expected outcome is the same in both cases for all test cases
    2. Test Cases: Same as point 1 <br>
       Expected: Same as point 1, but only students who have phone numbers will have their phone numbers displayed.
       No change in email address results and/or telegram handle results


3. Retrieving personal detail of all students. No student has a phone number.
    1. Prerequisites:
        - No student has a phone number.
        - Run each test case twice, the first time by having all students listed using the `list` command,
          and the second time by having the view only show some students e.g. by using `filter` command
          on the tags. Expected outcome is the same in both cases for all test cases
    2. Test Cases: Same as point 1 <br>
       Expected: Same as point 1, with the following (minor) changes
        1. Feedback box will always say "Failed to receive one or more personal details. Showing what I can..." for all
           test cases.
        2. For test cases with tags `e/` and/or `te/`, result display will still show email addresses and/or telegram handles
        3. For test cases without the above tags, result display will show "Nothing to show..."


4. Retrieving personal detail of all students. No students are present in AcademyDirectory
    1. Prerequisites: Use `clear` command before beginning testing to make sure no students are present in AcademyDirectory
    2. Test Cases: Same as point 1 <br>
       Expected:
        1. Feedback box will always say "Failed to receive one or more personal details. Showing what I can..." for all
           test cases.
        2. Result display will always show "Nothing to show..."


5. Retrieving personal detail of a student by keyword. At least one student whose name
   matches the given keyword is present in AcademyDirectory. Said student has a phone number.
    1. Prerequisites:
        - At least one student has a name matches `alex`. By match, we mean a case-insensitive keyword match i.e. both "Alex"
          and "aLeX" will match to `alex`. See this matching behavior in [`FilterCommand`](#filtercommand) for more details.
        - Matched students have a phone number.
        - Run each test case twice, the first time by having all students listed using the `list` command,
          and the second time by having the view only show some students e.g. by using `filter` command
          on the tags. Expected outcome is the same in both cases for all test cases
    2. Test cases:
        1. `get p/ n/alex`<br>
           Expected: Phone number of all students whose name matches `alex` are shown in the result display.
        2. `get e/ n/alex`<br>
           Expected: Email address of all students whose name matches `alex` are shown in the result display.
        3. `get te/ n/alex` <br>
           Expected: Telegram handle of all students whose name matches `alex` are shown in the result display.
        4. `get p/ e/ n/alex` <br>
           Expected: Phone number of all students whose name matches `alex` are shown in the result display, followed by email address
           of all students.
        5. `get e/ p/ te/ n/alex` <br>
           Expected: Phone number of all students whose name matches `alex` are shown in the result display, followed by email address
           and then by telegram handle.
        6. `get e/ n/alex p/ te/` <br>
           Expected: Phone number of all students whose name matches `alex` are shown in the result display, followed by email address
           and then by telegram handle.
        7. `get n/alex e/ p/ te/` <br>
           Expected: Phone number of all students whose name matches `alex` are shown in the result display, followed by email address
           and then by telegram handle.


6. Retrieving personal detail of a student by keyword. At least one student whose name matches the given keyword
   is present in AcademyDirectory. Said student/s have no phone numbers.
    1. Prerequisites:
        - At least one student has a name matches `alex`. By match, we mean a case-insensitive keyword match i.e. both "Alex"
          and "aLeX" will match to `alex`. See this matching behavior in [`FilterCommand`](#filtercommand) for more details.
        - Matched students have no phone numbers.
        - Run each test case twice, the first time by having all students listed using the `list` command,
          and the second time by having the view only show some students e.g. by using `filter` command
          on the tags. Expected outcome is the same in both cases for all test cases
    2. Test cases: Same as point 5 <br>
       Expected: Same as point 5, with the following (minor) changes
        1. Feedback box will always say "Failed to receive one or more personal details. Showing what I can..." for all
           test cases.
        2. For test cases with tags `e/` and/or `te/`, result display will still show email addresses and/or telegram handles
        3. For test cases without the above tags, result display will show "Nothing to show..."


7. Retrieving personal detail of a student by keyword. No student whose name matches the given keyword is present in AcademyDirectory.
    1. Prerequisites:
        - No student has a name which matches `alex`. By match, we mean a case-insensitive keyword match i.e. both "Alex"
          and "aLeX" will match to `alex`. See this matching behavior in [`FilterCommand`](#filtercommand) for more details.
        - Run each test case twice, the first time by having all students listed using the `list` command,
          and the second time by having the view only show some students e.g. by using `filter` command
          on the tags. Expected outcome is the same in both cases for all test cases
    2. Test cases: Same as point 5 <br>
       Expected: Same as point 5, with the following (minor) changes
        1. Feedback box will always say "Failed to receive one or more personal details. Showing what I can..." for all
           test cases.
        2. Result display will always show "Nothing to show..."
        

#### Edit Personal Detail

1. Edit a student while all students are being shown

    1. Prerequisites: List all students using the `list` command. All students are shown in the list.

    2. Test cases:
        1. `edit 1 te/@charles`<br>
           Expected: The first student in the list will have their telegram changed to `@charles`. Details of the added student shown in the status message. Result display remains the same. Changes in that student can be viewed immediately in Student Panel List on the left.
        2. `delete 0 te/@charles`<br>
           Expected: No student is edited. Error details shown in the status message. Result display remains the same.
        3. `edit 1 as/@charles`<br>
           Expected: Edit command only works with personal details such as `NAME`(`n/`), `PHONE`(`p/`), `EMAIL`(`e/`) and `TELEGRAM`(`te/`). Assessment (`as/`) can not be edited by this edit command. No student will be edited. Error `Invalid command format!` will be shown alongside with a proper usage of command. Result display remains the same.
        4. `edit 1 p/90`<br>
           Expected: `PHONE` field must be number with at least 3 digits or string `NA`. The first student is edited. Error will be shown alongside with a proper usage of command. Result display remains the same.
        5. `edit 1 n/Charles` followed by `edit 2 n/@charles`<br>
           Expected: Student with existing name exists. First student is successfully edited. The second student is not edited. Error `This student already exists ...` will be shown. Result display remains the same.

    3. Other incorrect delete commands to try:
        1. `edit`<br>
           Expected: No `INDEX` is supplied. Error `invalid command format!` will be shown.
        2. `edit -1`<br>
           Expected: Similar to cases of invalid `INDEX` field.
        3. `edit 1 t/streams`<br>
           Expected: Similar to cases of editing non-personal details.


2. Editing a student while a selected group of students is being shown

    1. Prerequisites: Filter students by their tag `streams` using the `filter streams` command. A subset of students shown.

    2. Test cases:
        1. `edit 1 n/Charles`<br>
           Expected: The first student's name will be added. Details of the edited student shown in the status message. Result display remains the same. Changes in that student can be viewed immediately in Student Panel List on the left. This student information will also be updated to in the full list of students.
        2. `edit 2 n/Charles` <br>
           Expected: Student with the same found in full list of students. Duplicate detection is done on the full list of students, not on the current list in student list panel. No student is edited. Error `This student already exists ...` will be shown. Result display remains the same.

    3. Other incorrect delete commands to try:
        1. `edit`<br>
           Expected: No `INDEX` is supplied. Error `invalid command format!` will be shown.
        2. `edit -2`<br>
           Expected: No `INDEX` is supplied. Error `invalid command format!` will be shown.

***

### Features related to Grades, Attendance and Participation

#### Add Grade
After every positive test case, use the command `view 1` or simply click on the first student panel,
and open the "View Test Score" tab to view the changes in the grade.

1. Record the grade of a single student's assessment, while all students are being shown in the list and no student is being viewed.
    1. Prerequisites: List all students using the `list` command. Multiple students in the list.
    2. Test cases: 
       1. `grade 1 as/ra1 g/15` <br>
          Expected: First student's RA1 score is now updated to 15.
       1. `grade 1 as/readingassessment1 g/15` <br>
          Expected: No student's grade is modified. Error details are shown in the status message indicating invalid assessment. Result display remains the same.
       1. `grade 1 as/ra1 g/101` <br>
          Expected: No student's grade is modified. Error details are shown in the status message indicating invalid grade. Result display remains the same.
    4. Other incorrect grade commands to try:
        1. `grade` (Missing index, assessment and grade)
        2. `grade 1` (Missing assessment and grade)
        3. `grade 1 as/ra1` (Missing grade)
        4. `grade 1 g/15` (Missing assessment)
        5. `grade -1 as/ra1 g/15` (Index out of bound)


2. Record the grade of a single student's assessment, while all students are being shown in the list and a single student is being viewed with no tabs open.
    1. Prerequisites: List all students using `list` command. Multiple students in the list. View a single student using `view 1`.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display still being viewed in the same state with no tabs open after execution of the command regardless if the execution of the command is successful or not.


3. Record the grade of a single student's assessment, while all students are shown in list and a student is being viewed with the "View Test Score" open
    1. Prerequisites: List all students using the list command. Multiple students in the list. View a single student using view 1. Click on "View Participation" in the result display.
    2. The test cases mirror Test 1, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Test Score" tab open.


#### Edit Attendance

1. Marking a single student's attendance while all students are being shown in list and no student is being viewed
    1. Prerequisites: List all students using the `list` command. Multiple students in the list.
    2. Test cases: 
       1. `attendance 1 ses/1 att/1` <br>
          Expected: First student's first Studio session is now marked as having attended
       1. `attendance 1 att/1 ses/1` <br>
          Expected: First student's first Studio session is now marked as having attended
       1. `attendance 1 ses/0 att/1` <br>
          Expected: No student's attendance is modified. Error details are shown in the status message indicating invalid session. Result display remains the same.
       1. `attendance 1 ses/0 att/-1` <br>
          Expected: No student's attendance is modified. Error details are shown in the status message indicating invalid attendance. Result display remains the same.
       1. `attendance 0 ses/1 att/1` <br>
          Expected: No student's attendance is modified. Error details are shown in the status message indicating invalid index. Result display remains the same.
    4. Other incorrect attendance commands to try:
        1. `attendance` (Missing index, session and attendance)
        2. `attendance 1` (Missing session and attendance)
        3. `attendance 1 att/1` (Missing session)
        4. `attendance 1 ses/1` (Missing attendance)
        5. `attendance x ses/y att/z` (Any case where x is an integer greater than the size of the student list, y is an integer out of the range of 0 and 12 inclusive or z is not 0 or 1)


2. Marking a single student's attendance while all students are shown in list and a student is being viewed with no tabs open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display still being viewed in the same state with no tabs open after execution of the command regardless if the execution of the command is successful or not.


3. Marking a single student's attendance while all students are shown in list and a student is being viewed with the "View Participation" open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`. Click on "View Participation" in the result display.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Participation" tab open.


4. Marking a single student's attendance while all students are shown in list and a student is being viewed with the "View Test Score" open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`. Click on "View Test Score" in the result display.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Test Score" tab open.
    

#### Edit Participation

1. Updating a single student's participation score while all students are being shown in list and no student is being viewed
    1. Prerequisites: List all students using the `list` command. Multiple students in the list.
    2. Test cases: 
       1. `participation 1 ses/1 add/1` <br>
          Expected: First student's first Studio session's participation score is 1 greater than it was previously. If it was 500 previously, it would still be 500.
       1. `participation 1 add/1 ses/1` <br>
          Expected: First student's first Studio session's participation score is 1 greater than it was previously. If it was 500 previously, it would still be 500.
       1. `participation 1 ses/0 add/1` <br>
          Expected: No student's participation is modified. Error details are shown in the status message indicating invalid session. Result display remains the same.
       1. `participation 1 ses/1 add/-501` <br>
          Expected: No student's participation is modified. Error details are shown in the status message indicating invalid participation. Result display remains the same.
       1. `participation 0 ses/1 add/1` <br>
          Expected: No student's participation is modified. Error details are shown in the status message indicating invalid index. Result display remains the same.
    4. Other incorrect participation commands to try:
        1. `participation` (Missing index, session and attendance)
        2. `participation 1` (Missing session and attendance)
        3. `participation 1 add/1` (Missing session)
        4. `participation 1 ses/1` (Missing participation)
        5. `participation x ses/y add/z` (Any case where x is an integer greater than the size of the student list, y is an integer out of the range of 0 and 12 inclusive or z is either greater than 500, or smaller than -501)


2. Updating a single student's participation while all students are shown in list and a student is being viewed with no tabs open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display still being viewed in the same state with no tabs open after execution of the command regardless if the execution of the command is successful or not.


3. Updating a single student's participation while all students are shown in list and a student is being viewed with the "View Participation" open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`. Click on "View Participation" in the result display.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Participation" tab open.


4. Updating a single student's participation while all students are shown in list and a student is being viewed with the "View Test Score" open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`. Click on "View Test Score" in the result display.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Test Score" tab open.


***

### Features related to Visualization

#### View student information

1. View all related information of a student
2. Prerequisite: List all student using the `list` command. Multiple students are shown in the list. **List should have exactly 6 students at the time of testing**
3. Test cases: 
   1. `view 1` <br>
      Expected: All related information of the first student is shown on the result display visualizer on the right side. This includes: Student name, current tags, all academic-related information (assessment score, studio participation, studio attendance), and personal contact information (phone, email, telegram) <br>
      Expected: No information is modified, Academy Directory runs as normal <br>
      Expected: Status message is that users are viewing student at position 1 of the list <br>
   2. `view 6` <br>
      Expected: All related information of the last student is shown on the result display visualizer on the right side. This includes: Student name, current tags, all academic-related information (assessment score, studio participation, studio attendance), and personal contact information (phone, email, telegram). No information is modified, Academy Directory runs as normal. Status message is that users are viewing student at position 6 of the list
   3. `view 7` <br>
      Expected: No view is shown on the result display. An error message is shown stating that index number is invalid
   1. `view 0`, `view add`, `view myself in front of the mirror as a failure of society` <br>
      Expected: No view is shown on the result display. An error message is shown stating that index number is invalid (in a sense that it must be a positive integer)

4. View students when list is altered
5. Prerequisite: Using filter to reduce the list view to 1 student only. List should only have one student.
6. Test cases: 
   1. `view 1` <br>
      Expected: All related information are shown about the student. Status message is that users are viewing student at position 1 of the list
   1. `view 2` <br>
      Expected: No view is shown on the result display. An error message is shown stating that index number is invalid. <br>
      Significance: View works for the current index number shown on the student list only.
   

#### Show Grade

1. Shows the collated scores of all the students in Academy Directory along with the average score, while grades for "RA1" not recorded for all students.
    1. Prerequisites:
        1. Clear the Academy Directory using the `clear` command.
        2. Add three students: <br>
           `add n/Alex e/alex@email.com te/@alex` <br>
           `add n/Bob e/bob@email.com te/@bob` <br>
           `add n/Carol e/carol@email.com te/@carol` <br>
    2. Test case: `show ra1` <br>
       Expected: Scores displayed for all students should be "NA", the number of students recorded should be "0", and the
       average should be "NaN".


2. Shows the collated scores of all the students in Academy Directory along with the average score, while grades for "RA1" recorded for some students.
    1. Prerequisites:
        1. Clear the Academy Directory using the `clear` command.
        2. Add three students: <br>
           `add n/Alex e/alex@email.com te/@alex` <br>
           `add n/Bob e/bob@email.com te/@bob` <br>
           `add n/Carol e/carol@email.com te/@carol` <br>
        3. Add grades for all students <br>
           `grade 1 as/ra1 g/15` <br>
           `grade 2 as/ra1 g/16` <br>
    2. Test case: `show ra1` <br>
       Expected: Scores displayed for the students should match the input grades, the number of students recorded should be "2", and the
       average should be "15.50".


3. Shows the collated scores of all the students in Academy Directory along with the average score, while grades for "RA1" recorded for all students.
   1. Prerequisites:
      1. Clear the Academy Directory using the `clear` command.
      2. Add three students: <br>
      `add n/Alex e/alex@email.com te/@alex` <br>
      `add n/Bob e/bob@email.com te/@bob` <br>
      `add n/Carol e/carol@email.com te/@carol` <br>
      3. Add grades for all students <br>
      `grade 1 as/ra1 g/15` <br>
      `grade 2 as/ra1 g/16` <br>
      `grade 3 as/ra1 g/17` <br>
   2. Test case: `show ra1` <br>
      Expected: Scores displayed for the students should match the input grades, the number of students recorded should be "3", and the
      average should be "16".
   

#### Visualize all Grades

1. Visualize class performance when all students grades are entered
    1. Prerequisites: All students grade from all assessments are entered
    2. Test case: `visualize` <br>
       Expected: Box and Whisker plot shown in Result Display. The box includes median, 25%, 75%, max and min grades of all assessments.


2. Visualize class performance when there are no grade.
    1. Prerequisites: No grade from any students or assessments are entered.
    2. Test case: `visualize` <br>
       Expected: Box and Whisker plot shown in Result Display. The plot axis are shown without the data bar.


3. Visualize class performance when some students' grade are not entered.
    1. Prerequisites: There are grades from all assessments, however there are some students' grades that are not entered yet.
    2. Test case: `visualize` <br>
       Expected: Box and Whisker plot shown in Result Display. The box includes median, 25%, 75%, max and min grades of all assessments for **the group of students with grades**.


4. Visualize class performance when some assessments' grades are not entered.
    1. Prerequisites: There are some assessments' grades that are not entered yet.
    2. Test case: `visualize` <br>
       Expected: Box and Whisker plot shown in Result Display. The box includes median, 25%, 75%, max and min grades of assessments with grades. Assessments without grade will not show the data bar.
    

#### Filter Academy Directory

1. Filters the student list while all students are being shown in the list.
    1. Prerequisites:
        1. Create students with the following commands: <br>
           `add n/Student 1 e/test1@email.com te/@test1 t/tag1` <br>
           `add n/Student 2 e/test2@email.com te/@test2 t/tag2`
        2. List all the students using the `list` command. After every test case, use `list` to display all the students.
    2. Test cases: 
       1. `filter student` <br>
          Expected: Student list should be filtered to show Student 1 and Student 2.
       2. `filter student 1` <br>
          Expected: Student list should be filtered to show Student 1 and Student 2.
       3. `filter 1` <br>
          Expected: Student list should be filtered to show Student 1 only.
       4. `filter tag1` <br>
          Expected: Student list should be filtered to show Student 1 only.
       5. `filter Student1` <br>
          Expected: Student list should not show Student 1 and/or Student 2.
       6. `filter tag` <br>
          Expected: Student list should not show Student 1 and/or Student 2.
       7. `filter` <br>
          Expected: Student list should remain unchanged. Error details are shown in the status message indicating invalid command format. Result display remains the same.
       

#### Sort Student List

1. Sorting the student list while all students are shown in and a student is being viewed with no tabs open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list.
    2. Test cases: 
       1. `sort attr/name ord/asc` <br>
          Expected: Student list is now sorted by name in ascending order.
       1. `sort ord/asc attr/name` <br>
          Expected: Student list is now sorted by name in ascending order.
       1. `sort attr/name ord/as` <br>
          Expected: Student list's sorting state remains unchanged. Error details are shown in the status message indicating invalid order. Result display remains the same.
       1. `sort attr/nam ord/asc` <br>
          Expected: Student list's sorting state remains unchanged. Error details are shown in the status message indicating invalid attribute. Result display remains the same.
    4. Other incorrect sort commands to try:
        1. `sort` (Missing attribute and order)
        2. `sort attr/name` (Missing order)
        3. `sort ord/asc`
        4. `sort attr/[ATTRIBUTE] ord/[ORDER]` (Any case where [ATTRIBUTE] is not any of the valid attributes, case-insensitive or [ORDER] is not 'asc' or 'desc', case-insensitive)


2. Sorting the student list while all students are shown in list and a student is being viewed with no tabs open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display still being viewed in the same state with no tabs open after execution of the command regardless if the execution of the command is successful or not.


3. Sorting the student list while all students are shown in list and a student is being viewed with the "View Participation" open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`. Click on "View Participation" in the result display.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Participation" tab open.


4. Sorting the student list while all students are shown in list and a student is being viewed with the "View Test Score" open
    1. Prerequisites: List all students using the `list` command. Multiple students in the list. View a single student using `view 1`. Click on "View Test Score" in the result display.
    2. The test cases mirror `Test 1`, with the student being viewed in the result display in the state with no tabs open after successful execution of the command. If the execution of the command fails, the result display still shows the student with the "View Test Score" tab open.


***

### Other Features

#### Launch and shutdown

1. Initial launch

    1. Download the jar file and copy into an empty folder

    1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

    1. Resize the window to an optimum size. Move the window to a different location. Close the window.

    1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

       

#### List All Students

1. List all students on Academy Directory
    1. Prerequisite: Application database is not empty (meaning that the `clear` command has not been executed, or that there are student entries on the student list)
    2. Test cases: 
       1. `list`
          Expected: All students are shown on the student list panel
       1. `list 3`
          Expected: Error message is shown on the status message display stating that there is an invalid usage detected, no other argument should follow, and that the command is highlighted in red.
       1. `list 02it0hg204`
          Expected: Error message is shown on the status message display, stating that there is an invalid usage detected, no other argument should follow, and that the command is highlighted in red.


#### Clear Student List

1. List all students on Academy Directory
   1. Prerequisite: Application database is not empty (meaning that the `clear` command has not been executed, or that there are student entries on the student list)
   2. Test cases: 
      1. `clear` <br>
         Expected: Student list panel on the left is emptied, with a status message stating that the entries have been cleared.
      1. `clear 3` <br>
         Expected: Error message is shown on the status message display stating that there is an invalid usage detected, no other argument should follow, and that the command is highlighted in red.

#### View Commit History

1. View commit history when history is linear
   1. Prerequisite: Start with an empty commit history. To achieve this, do the following: 
      - Remove version control folder (Default is `data/vc`; refer to `preferences.json` for specific)
      - Start the application
   2. Test cases: 
      1. `history` <br/>
        Expected: Something like the following should appear </br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (HEAD) (MAIN) </br>
         | | 		Initial Commit </br>
         |/ </br>
        The following example is a correct realization of the above template: </br>
         | * 65b8a - Mon, 8 Nov 2021 00:04:37 +0800 (HEAD) (MAIN) </br>
         | | 		Initial Commit</br>
         |/</br>
        The time shown follows the GMT+8 Time zone, and should correspond to the time when the application was started.
      2. Continuing from above, run `clear` followed by `history` </br>
       Expected: Something like the following should appear </br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (HEAD) (MAIN)</br>
         | | 		Academy Directory has been cleared!</br>
         |/</br>
         \* [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (OLD)</br>
         | 		Initial Commit</br>
        Note a few things: 
         - Date and time for initial commit should not change
         - A new commit is created, and its time should correspond to the time when the command `clear` was executed
      3. Continuing from above, run `add n/Alex e/alex@email.com te/@alex` followed by `history` </br>
         Expected: Something like the following should appear </br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (HEAD) (MAIN)</br>
         | | 		New student added: Alex</br>
         |/</br>
         \* [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (OLD)</br>
           | 		Academy Directory has been cleared!</br>
         \* [FIVE CHAR HASH] - DDD, dd Nov YYYY hh:mm:ss +0800</br>
         | 		Initial Commit</br>
         Note a few things:
         - Date and time for initial commit should not change
         - A new commit is created, and its time should correspond to the time when the command `add` was executed
         - The label "(OLD)" shifted to the previous commit i.e. the one that clears the Academy Directory
2. View commit history when history branches
   1. Prerequisite: Start with an empty commit history. To achieve this, do the following:
       - Check that `UndoCommand` works, by following [this](#undo-changes)
       - Remove version control folder (Default is `data/vc`; refer to `preferences.json` for specific)
       - Start the application
   2. Test cases:
      1. Basic Two-way Branching: </br>
         Execute the following commands in sequence: </br>
         `clear` --> `undo` --> `add n/Alex e/alex@email.com te/@alex` --> `history` <br/>
          Expected: Something like the following should appear </br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (HEAD) (MAIN)</br>
         | | 		New student added: Alex </br>
         \* | [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (OLD)</br>
           | | 		Academy Directory has been cleared! </br>
           |/ </br>
         \* [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800</br>
           | 		Initial Commit</br></br>
         Note a few things:
         - Date and time for initial commit should follow the GMT+8 Time zone and should correspond to the time when the application was started.
         - The commit corresponding with `clear` should have its time correspond to the time when the command `clear` was executed.
         - The commit corresponding with `add` should have its time correspond to the time when the command `add` was executed.
         - The label "(OLD)" is on the commit which clears Academy Directory.
         - The label "(HEAD)" and "(MAIN)" is on the latest commit i.e. the commit which adds `Alex`. </br>
      2. Maintain branches when no new branch is created: </br>
        Continuing from above, execute `clear` followed by `history` </br>
        Expected: Something like the following should appear </br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (HEAD) (MAIN)</br>
         | | 		Academy Directory has been cleared!</br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800</br>
         | | 		New student added: Alex</br>
         \* | [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (OLD)</br>
           | | 		Academy Directory has been cleared!</br>
           |/</br>
         \* [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800</br>
           | 		Initial Commit</br>
         Note a few things:
          - Date and time for the last three commits should remain the same
          - The label "(HEAD)" and "(MAIN)" is on the latest commit i.e. the commit which does `clear`
          - The label "(OLD)" is on the commit in the _OLD_ branch i.e. the left branch
      3. New branch created on top of Two-way branch -> make former MAIN branch to OLD and new branch to MAIN </br>
         Continuing from above, execute `undo` --> `undo` --> `add n/Bob e/bob@email.com te/@bob` --> `history` </br>
         Expected: Something like the following should appear </br>
         | * [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (HEAD) (MAIN)</br>
         | | 		New student added: Bob</br>
         \* | [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800 (OLD)</br>
         | | 		Academy Directory has been cleared!</br>
         \* | [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800</br>
         | | 		New student added: Alex</br>
         |/</br>
         \* [FIVE CHAR HASH] - DDD, dd MMM YYYY hh:mm:ss +0800</br>
         | 		Initial Commit</br>
         Note a few things:
          - Date and time for the last three commits should remain the same
          - The commits in the formerly "(MAIN)" branch i.e. the right branch, are now in the "(OLD)" branch 
            i.e. the left branch.
          - The commit formerly labeled as "(MAIN)" is now labeled as "(OLD)"
          - The label "(HEAD)" and "(MAIN)" is on the latest commit i.e. the commit which does `add n/Bob`

The above test cases tests for correctness for the basic behaviors of `HistoryCommand`. They don't have to be 
executed with the exact commands given; using different commands in different order may lead to slightly different
commit message and/or date time shown. However, the general behavior as demonstrated above should remain the same.

#### Help

1. Test general help
    1. Prerequisite: Application is started
    2. Test case: `help` <br>
       Expected: A pop-up window is shown summarizing the format of all commands for users, as well as a link to the web User Guide of Academy Directory
       Expected: All commands used in Academy Directory are shown, and the summary table does not lack any command. To assert this, compare the table to the
       actual User Guide web version and see the matched commands.


2. Test specific help
    1. Prerequisite: Application is started
    2. Test cases: 
       1. `help edit` <br>
          Expected: A pop-up window is shown with a customized help message (based on the User Guide of `edit` command) on how to use `edit`, including significance, format, and example.
       1. `help visualize` <br>
          Expected: A pop-up window is shown with a customized help message (based on the User Guide of `visualize` command) on how to use `visualize`, including significance, format, and example.
       1. `help ad` <br>
          Expected: No pop-up window is shown, and an error message is shown as status message explaining that there exists no instruction for command `ad`.
          Significance of the test case is that specific `help` can only be useful when the command is typed in full rather than in partial - to view help for command `add`, users need to type in `help add` exactly.
       1. `help r230thg4b0p2nnbtpbgetbi03` <br>
          Expected: No pop-up window is shown, and an error message is shown as status message explaining that there exists no instruction for the command.


3. Test functionality of pop-up window
    1. Prerequisite: Help window is already opened before by any mean, and is kept opened for testing
    2. Test cases: 
       1. Focus on the Main Window, do not close Help Window, and type in `help add` or any other equivalent command <br>
          Expected: The help window is refocused with its content change to the new `help` instead.
       1. Click on the `Copy UG Guide` button on the right side, and access the link <br>
          Expected: The User Guide web-version of Academy Directory can be accessed, meaning that the link is indeed copied.


#### Saving data

1. Dealing with missing/corrupted data files

    1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_


2. _{ more test cases to come …​ }_


***

## Graphical User Interface (GUI) Testing

These tests are primarily focused on testing both the appearance and the functionality of the GUI when used by the user.

### Visual Design

1. Test application visual design
    1. Prerequisite: Application is started, the Main Window is opened, users have not entered any command or cleared any data previously, and there are
       some students on the records.
    2. Test cases: 
       1. Assert that Academy Directory has a student list with the various student cards inside <br>
          Expected: Student list and student cards are shown to the users
       1. Assert that Academy Directory has 4 menus in the top left corner of the Main Window <br>
          Expected: There are 4 menus, one with grade logo, one with statistics logo, one with clock logo, and one with question logo
       1. Click on the first menu item with the Grade logo <br>
          Expected: 5 entries will pop up, showing users different options to show grades.
       1. Assert that Academy Directory has an opaque rectangle in the top-right corner of the Main Window <br>
          Expected: The rectangle is shown to the user, and users cannot edit it or remove it.
       1. Assert that Academy Directory has an opaque rectangle in the bottom-right corner of the Main Window <br>
          Expected: The rectangle is shown to the user, and users cannot edit it or remove it.
       1. Assert that Academy Directory has a command box at the bottom of Main Window <br>
          Expected: The command box is shown to users, with a placeholder message `Enter something`, and users can edit the command box.
       1. Type in `this is a test message` <br>
          Expected: The command box shows `this is a test message` - meaning that the command box is able to received user's request
       1. Expand Academy Directory to full screen <br>
          Expected: Background image also expanded, alongside other components of the internal controls (result display, student list, and status message). No other visual misbehavior of the User Interface (image is cropped, whitespace exists, or lacking in any visual design)
       1. Shrink Academy Directory to the smallest possible size <br>
          Expected: Academy Directory is not minimized completely as there is a minimal size for users to still see the data


### User Interface Functionality

2. Test Main Window User Interface functionality
    1. Test cases:
       1. Execute the command `help` <br>
          Expected: Status logger displays a message that informs user a general help message is being shown. Help Window is popped up 
       2. Resize the Help Window by expand it to full screen <br>
       Expected: Help Window is expanded to full screen without compromising the content inside (the message is not being minimized or expanded, users are able to view the message regardless of the size). All commands are inside the help message. To check, scroll the window and see whether commands `participation`, or `revert`, or `visualize` are in the table
       3. Close the help window <br>
          Expected: Help Window is closed successfully, without the Main Window being closed or affected as well
       1. Click on the logger display and try to edit the message <br>
          Expected: Users cannot edit the message or remove the message. Reason is to avoid confusion in usage of the application
       1. Enter `visualize` to the command box, and press enter <br>
          Expected: A box-whisker plot is shown to the users visualizing relative performance on student exam.
       1. Attempt to click on the bottom-right rectangle and remove the data visualization <br>
          Expected: Users cannot remove or manipulated the shown data.
       1. Enter `view 1` to the command box, and press enter <br>
          Expected: A visualized view of the student is shown in the bottom-right corner
       1. Click on the drop-down menu with "View Participation" on the current view <br>
          Expected: The menu is dropped, and users can see the summary of student participation and attendance of CS1101S studio
       1. Click on the drop-down menu with "View Test Score" on the current view <br>
          Expected: The previous menu is closed, the clicked menu is dropped, and users can see the summary of student test score of CS1101S
       1. Enter `list` to the command box, and press enter <br>
          Expected: The `view` visualization of student information does not disappear even when the application is working at the background
       1. Click on the first menu, then click on `Show RA1` entry <br>
          Expected: The equivalent to user input `show RA1` will be executed on Academy Directory
       1. Click on the first student card of the student list <br>
          Expected: A visualized view of the student is shown in the bottom right corner.

***

## Performance testing

### Visualization with large `AcademyDirectory` _[Coming soon]_

* Testing of large number of the `Visualize` function with a large number of students in `AcademyDirectory`
  * Potentially affects runtime and if so, `AcademyDirectory` might require optimization.

### Limit test `AcademyDirectory` capacity _[Coming soon]_

* Testing the `Student` capacity that `AcademyDirectory` can handle
  * Things to look out for: 
    * Potential general loss in performance e.g less responsive buttons, longer startup time, longer time taken to process commands

***

## Compatibility testing

### Cross-OS Compatibility _[Coming soon]_

* Testing of `AcademyDirectory` on all mainstream OS such as `Windows`, `MacOS` and `Linux`.


* Testing of `AcademyDirectory` on more obscure OS such as different `Linux` distributions e.g `Kali Linux`.


***

## Portability testing

### Transfer of `AcademyDirectory` _[Coming soon]_

* Testing transfer of `AcademyDirectory` between identical mainstream OS e.g from `MacOS` to `MacOS`.

* Testing transfer of `AcademyDirectory` between different mainstream OS e.g from `MacOS` to `Windows`.
  * Things to look out for: 
    * Hypothetically, a potential increase in time taken to execute `Visualize` after transfer e.g `Macbook A` to `Macbook B` and back to `Macbook A` without changing any files.


