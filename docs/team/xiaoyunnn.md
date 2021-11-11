---
layout: page
title: Wu Xiao Yun's Project Portfolio Page
---

### Project: TuitionAddressBook (TAB)

Tuition Address Book (TAB) is an all-in-one desktop application for 1-to-1 private home tutors that helps to keep track of their students and their respective lesson information Everything on TAB can be done without ever leaving the keyboard! and empower tutors to provide the best quality home tuition service. TAB helps users manage students’ contact details faster than a typical mouse/GUI driven app.

Given below are my contributions to the project.

* **New Feature**: Added a tag command that allows users to view all existing tags in TAB.
  * What it does: This feature allows users to view a list of all existing tags created for the students together with the number of students labelled with each tag.
  * Justification: This feature improves the user experience as users can add different tags to the students for better organization.
  * Highlights: This feature complements the Find feature. Filtering by tags require users to input the exact tag name(s). 
    If users are unsure of the exact existing tag names, they can first view the tag list then filter students by the tag names they are interested in. 
    There are multiple ways of implementing this enhancement. The current design is chosen due to the balance between ease of implementation, efficiency as well as space allocation.

* **New Feature**: Added a reminder command.
  * What it does: This feature allows users to view a list of lessons that ends within the next 48 hours.
  * Justification: This feature gives user a heads-up on the upcoming lessons so that they have time to prepare the lesson materials needed.
  * Highlights: This feature complements the Calendar feature. As users can only view the student name, subject and type of the lessons in the calendar views,
    having the reminder command allows users to see the homework as well as outstanding fees for the lessons, if any.
  
* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=f13-3&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=Xiaoyunnn&tabRepo=AY2122S1-CS2103T-F13-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&zFR=false)

* **Enhancements to existing features**:
    * Added quick tips in result display section whenever users launch the app. (Pull requests [\#39](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/39))
    * Added additional fields regarding academic details (i.e. school, academic stream and academic level) for students. (Pull requests [\#38](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/38), [\#42](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/42))
    * Improved the Help feature by adding a command summary table with each command's description, format and example. (Pull request [\#99](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/99))
    * Updated the GUI color scheme and effects to improve user-friendliness. (Pull requests [\#173](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/173), [\#227](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/227), [\#318](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/318))


* **Documentation**:
    * User Guide:
        * Added documentation for the features `tag` and `remind`. (Pull requests [\#163](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/163))
        * Updated glossary list. (Pull request [\#164](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/164))
        * Added student parameters table for easier reference. (Pull request [\#317](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/317)) 
        * Added annotations for GUI screenshots. (Pull request [\#317](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/317))
    * Developer Guide:
      * Added implementation details and use cases of the `tag` and `remind` features. (Pull requests [\#148](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/148) [\#342](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/342)).
      * Modified some diagrams (e.g. `Model` and `Ui` class diagrams).

* **Community**:
    * PRs reviewed (with non-trivial review comments): [\#67](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/67), [\#73](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/73), [\#107](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/107), [\#123](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/123), [\#134](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/134), [\#183](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/183), [\#223](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/223), [\#276](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/276), [\#278](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/278), [\#279](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/279), [\#280](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/280), [\#307](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/307), [\#311](https://github.com/AY2122S1-CS2103T-F13-3/tp/pull/311).

<div style="page-break-after: always;"></div>

#### Contribution to User Guide
Given below is an excerpt from TAB's [User Guide](https://ay2122s1-cs2103t-f13-3.github.io/tp/UserGuide.html) detailing instructions on how to use the new `remind` feature I added.
Due to page limits, some content is not included.

##### Viewing upcoming lessons: `remind`
Displays a list of upcoming lessons that ends within the next 48 hours.
- Lesson cards in the reminder list do not display the date range and the cancelled dates, if any.

Format: `remind`

Example: Suppose the date today is 1 Nov 2021 and current time is 1500h,
- lessons with the following dates and time are considered upcoming:
    - 1 Nov 2021 with end time at or after 1500h,
    - 2 Nov 2021 with any valid time range,
    - 3 Nov 2021 with start time before or at 1500h.
- lessons with the following dates and time are not considered upcoming:
    - dates before 1 Nov 2021 (has passed),
    - 1 Nov 2021 with end time before 1500h (has passed),
    - 3 Nov 2021 with start time after 1500h (beyond 48 hours).

<div markdown="block" class="alert alert-info">
**:information_source: Note:**<br>
  <ul>
    <li>Reminder does not refresh the list of upcoming lessons automatically.</li>
    <li>Type <code>remind</code>, click <kbd>Reminder</kbd> on the menu bar or press <kbd>F5</kbd> (see <a href="https://ay2122s1-cs2103t-f13-3.github.io/tp/UserGuide.html#menu-bar-shortcuts">Menu Bar Shortcuts</a> for more available shortcuts) to refresh the list of upcoming lessons.</li>
    <li>Reminder also updates when valid <code>ladd</code>, <code>ledit</code>, <code>ldelete</code> commands are executed. 
      <ul>
        <li>See <a href="https://ay2122s1-cs2103t-f13-3.github.io/tp/UserGuide.html#managing-lessons">Managing Lessons</a> for more details regarding these commands.</li>
      </ul>
    </li>
  </ul>
</div>

<div style="page-break-after: always;"></div>

#### Contribution to Developer Guide
Given below is an excerpt from TAB's [Developer Guide](https://ay2122s1-cs2103t-f13-3.github.io/tp/DeveloperGuide.html) detailing the implementation of the new `tag` and `remind` features I added.
Due to page limits, some content is not included.

##### Implementation - Reminder Feature
The reminder feature allows users to view a list of upcoming lessons that ends in the next 48 hours.

Viewing a list of upcoming lessons is facilitated by `CalendarEntryList`.
- `CalendarEntryList` holds all lesson entries as well as a separate list of lessons that are considered upcoming.
- Whenever data modifications are made to lessons, `CalendarEntryList` will update the list of calendar entries accordingly.
- At the same time, `CalendarEntryList#isUpcoming(Entry<Lesson>)` checks if the lesson modified ends within the next 48 hours and `CalendarEntryList` will make changes accordingly to the list of upcoming lessons.
    - e.g. if the user edits an upcoming lesson such that the date and time are no longer considered upcoming, the lesson will be removed from the reminder list.
    
<div markdown="span" class="alert alert-info">:information_source: **Note:** 
Reminder does not refresh the list of upcoming lessons automatically if no data modifications were made to lessons. Users need to enter `remind`, click <kbd>Reminder</kbd> on the menu bar or press <kbd>F5</kbd> to update the list of upcoming lessons. </div>

Given below is an example usage scenario and how viewing reminder is executed:
- **Step 1:** The user enter the command `remind` to view the list of upcoming lessons. `Logic` calls `AddressBookParser` to parse this command string, creating a `RemindCommand`.
- **Step 3:** `Logic` executes the `RemindCommand`. During execution, `RemindCommand#execute()` instantiates a `CommandResult` with the `DisplayType` of `REMINDER` as a signal for `MainWindow` to open the `ReminderWindow` or focus on it if it was already opened.
- **Step 4:** `MainWindow` then handles this command by calling `MainWindow#showReminder()`. `Logic#updateUpcomingLessons()` is then called to display the updated list of upcoming lessons to the user.

###### Design considerations
**Aspect: Date and time range of lessons to be considered as upcoming**

* **Alternative 1 (current implementation):** Lessons are considered upcoming if they end within the next 48 hours.
    * Pros: Easy to implement and detect misbehavior.
    * Cons: Low flexibility towards users customization. Users are unable to determine the time range that they want to consider lessons as "upcoming".
* **Alternative 2:** Allow users to define the time range within which lessons are considered "upcoming".
    * Pros: Greater flexibility in planning lesson materials.
    * Cons: Higher chance of causing the app to misbehave.

As Alternative 2 requires more testing and hence more time to minimize bugs, we decided to put off alternative 2 for future considerations given the limited amount of time we have.

##### Implementation - Viewing Tags
Viewing tag is facilitated by `UniqueTagList`.

- `UniqueTagList` stores a list of alphabetically sorted unique unmodifiable tags with case-insensitive tag names.
- `UniqueTagList` holds a private field `tagCounter` that maps `Tag` to `Integer`, where `Integer` is the number of persons labelled under each tag.
- `Tag` objects in `UniqueTagList` may not have the same reference as the `Person` object's `Tag`, i.e. each `Person` has a set of `Tag` objects on its own.

These operations are called when a person is added, edited, or deleted with `AddCommand`, `EditCommand` and `DeleteCommand` respectively.

Given below is an example usage scenario and how viewing tag is executed:
- **Step 1:** The user launches the application. The `Model` is initialized with the saved data (or sample data if there were no saved data). Tags from each person is loaded into `UniqueTagList` and `tagCounter` will store the corresponding number of students labelled with the tags.
- **Step 2:** The user enter the command `tag` to view all tags. `Logic` calls `AddressBookParser` to parse this command string, creating a `TagCommand`.
- **Step 3:** `Logic` executes the `TagCommand`. During execution, `TagCommand#execute()` instantiates a `CommandResult` with the `DisplayType` of `TAGS` as a signal for `MainWindow` to switch the center panel to show the tag list.
- **Step 4:** `MainWindow` then handles this command by calling `CenterPanel#displayTagListPanel()` to display the tag list to the user.

##### Manual Testing - Viewing reminder

1. Add a lesson to a student. <br>
   Prerequisites: This lesson does not clash with existing lessons. If there were clashes, delete the clashing lesson(s).
2. Enter `remind` to open the reminder window.

<div markdown="span" class="alert alert-info">:information_source: <b>Note:</b> Please vary the date and time accordingly (adding or subtracting by an equivalent amount of day(s) and time), 
depending on the date and time when you are testing.</div>

Suppose the date today is 1 Nov 2021 and current time is 1500 hours,
* Test case: `ladd 1 date/1 nov 2021 time/1500-1501 rates/40 subject/math` followed by `remind`. <br>
  Expected: This lesson will appear in the reminder window. <br>
  Suppose the time now is 1502 hours, enter `remind`. <br>
  Expected: This lesson no longer appears in the reminder window.

* Test case: `ladd 1 date/2 nov 2021 time/1600-1700 rates/40 subject/math` followed by `remind`.<br>
  Expected: This lesson appears in the reminder window.

* Test case: `ladd 1 date/3 nov 2021 time/1600-1700 rates/40 subject/math` followed by `remind`.<br>
  Expected: This lesson does not end within the next 48 hours and hence does not appear in the reminder window.

* Test case: `ladd 1 date/31 oct 2021 time/1600-1700 rates/40 subject/math` followed by `remind`.<br>
  Expected: This lesson has passed and hence does not appear in the reminder window.

Incorrect `remind` command to try:

1. `remind x` where x is any character.

Expected: Error details will be shown in the status message.
