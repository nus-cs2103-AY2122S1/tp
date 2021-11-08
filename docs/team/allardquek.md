---
layout: page
title: Allard Quek's Project Portfolio Page
---

[ProgrammerError](https://github.com/AY2122S1-CS2103-F09-3/tp) is a desktop application which helps CS2100 tutors manage
their students’ lab results in a simple and efficient manner, allowing them to spend less time on administrative
processes and more time teaching students.

The following is a summary of my contributions to the
project. ([View Reposense](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/#breakdown=true&search=allardquek))

#### Features

1. **Added the ability to upload student data**
    - What it does: Allows the TA to upload a CSV file containing students' student ID, class ID, name and email
    - Justification: So that the TA can automate the process of adding students to their classes without having to
      manually type the add command
    - Highlights: This feature involved using different third-party libraries and evaluating their suitability 
      (based on their size, ease-of-use, functionality offered). Challenges included handling the multiple
      possibilities for invalid user input with intuitive error messages and good design.
    - Credits: [opencsv](http://opencsv.sourceforge.net/) (third-party library), Stackoverflow
2. **Added the ability to download student data**
    - What it does: Allows the TA to download the current student data into a CSV format
    - Justification: So that the TA can upload the students' lab results onto Luminus gradebook or share them with other
      TAs.
    - Highlights: This feature was challenging as it required integrating a third-party library into the project for the
      first time and ensuring the feature works across different devices. To improve the user experience, I also created
      a popup message to be which required significant exploration with CSS properties and display sizes.
    - Credits: [org.json](https://mvnrepository.com/artifact/org.json/json) (third-party library), Stackoverflow
3. **Added the ability to view a dashboard of student and lab data**
    - What it does: Shows the total number of students, labs, classes, and the number of labs left to marked for each class
    - Justification: So that the TA can track their progress in marking their students' labs conveniently, especially
      when they have many students
    - Highlights: This feature required using a TreeMap by to store and retrieve students' lab data efficiently so that the dashboard can be 
      updated dynamically with minimal delay without using a database. 
      Additionally, this feature required learning more about javafx components 
      and CSS properties to make the dashboard interface more user-friendly.

#### Other Contributions

1. **Project management:**
    1. Managed releases 1.3-1.3b (2 releases) and issue tracker on GitHub
    2. Set up the GitHub team org, repo, issue labels, Gradle and Notion
    3. Recorded and edited demo videos for v1.3-v1.4 (2 videos)
2. **Enhancements to existing features:**
    - Refactored code and improve code quality for existing features:
      e.g. [#65](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/65)
      , [#79](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/79/files)
      , [#115](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/115)
      , [#117](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/117)
      , [#128](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/128)
      , [#152](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/152)
      , [#155](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/155)
      , [#176](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/176)
      , [#211](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/211)
      , [#232](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/232)
    - Improve testing: e.g. [#100](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/100)
      , [#103](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/103)
      , [#106](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/106/files)
      , [#108](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/108/files)
      , [#130](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/130/files)
    - Various bug fixes for other features: e.g. [#74](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/74)
      , [#200](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/200)
      , [#268](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/268)
      , [#359](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/359/files)
      , [#360](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/360/files)
      , [#389](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/389)
3. **Documentation:**
    - User Guide: Added documentation for table of contents, command syntax information, `upload`, `download`
      , `dashboard`
    - Developer Guide: Added implementation details for the `download` command, diagrams to explain the Ui component structure,
      and effort section detailing the team's challenges and achievements
4. **Review and Mentoring Contributions:**
    - PRs reviewed (with non-trivial review comments): [#68](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/68)
      , [#113](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/113)
      , [#160](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/160)
      , [#161](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/161)
      , [#211](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/211#issuecomment-950448914)
      , [#375](https://github.com/AY2122S1-CS2103-F09-3/tp/pull/375)
    - Contributed to forum discussions: [#142](https://github.com/nus-cs2103-AY2122S1/forum/issues/142)
      , [#297](https://github.com/nus-cs2103-AY2122S1/forum/issues/297) [#322](https://github.com/nus-cs2103-AY2122S1/forum/issues/322)
      , [#335](https://github.com/nus-cs2103-AY2122S1/forum/issues/335)
      , [#350](https://github.com/nus-cs2103-AY2122S1/forum/issues/350#issuecomment-954135759)
      , [#352](https://github.com/nus-cs2103-AY2122S1/forum/issues/352)
    - Reported bugs and suggestions for other teams in the class: [#10](https://github.com/AllardQuek/ped/issues/10)
      , [#11](https://github.com/AllardQuek/ped/issues/11), [#14](https://github.com/AllardQuek/ped/issues/14)
      , [#16](https://github.com/AllardQuek/ped/issues/16), [#18](https://github.com/AllardQuek/ped/issues/18)
      , [#19](https://github.com/AllardQuek/ped/issues/19), [#255](https://github.com/AY2122S1-CS2103-W14-1/tp/pull/255)


<div style="page-break-after: always;"></div>

## <a name="user-guide-contributions"></a>Sample User Guide Contribution

### <a name="upload-data"></a>3.3 Upload Data: `upload` or F4 on keyboard

Uploads student data via a CSV file with the following header: `studentId,classId,name,email`. Note that the data
should contain **only** the student ID, class ID, name and email field respectively and spaces directly before or after
commas should be avoided.

Here is an [example CSV file](https://github.com/AY2122S1-CS2103-F09-3/tp/blob/master/sample_upload/validDataForUpload.csv) on GitHub.
You may download files from GitHub following the instructions [here](https://stackoverflow.com/questions/4604663/download-single-files-from-github).

<div markdown="block" class="alert alert-warning">:exclamation: **Note:**
the CSV should *not* contain students' lab results since this functionality is only meant for the TA to automate the adding of students to PE.

- If there are already existing students, the upload will **overwrite** the existing data rather than
  append to it. This is because in a typical use case, the TA would not want to have the existing data kept if they
  would like to use student data from their own CSV file.
- Furthermore, simply uploading the CSV obtained via the `download` command will result in the file being rejected
</div>

In summary:
1. Select a valid CSV file (with header: `studentId,classId,name,email`) from the file chooser.
2. Note that this command is **not** meant for uploading lab results. Rather, it is only for automating the adding of
   students to PE.

<div style="page-break-after: always;"></div>

## <a name="dev-guide-contributions"></a>Sample Developer Guide Contributions

### <a name="ui-component"></a> **UI Component**

The **API** of this component is specified
in [`Ui.java`](https://github.com/AY2122S1-CS2103-F09-3/tp/blob/master/src/main/java/seedu/programmer/ui/Ui.java)

At a high level, the `MainWindow` component interacts with 3 other main components: `Logic`, `PopupManager` and `FileManager` (Figure 4.2.1).
Note that the components under `MainWindow` have been omitted for simplicity and will be shown in greater detail in the next diagram.

<p align="center">
    <img src="../images/ui/UiClassDiagramOverview.png" width="700" />
</p>
<div style="text-align: center">
    <em>Figure 4.2.1: Overview of Ui components</em>
</div>
<br>
1. Firstly, `MainWindow` interacts with the `Logic` component to determine which data to display to the user.
2. Secondly, `MainWindow` conducts file operations on the Ui through a `FileManager`.
   For instance, the `FileManager` handles situations where the user is required to select files or directories.
3. Thirdly, to manage the display of popup windows to the user, `MainWindow` interacts with a `PopupManager` which handles
   the configuration, creation and showing of popups on the Ui.

In addition, there are two additional windows that the UI can display: `HelpWindow` and `DashboardWindow`. They inherit
from the abstract class `PopupWindow`, which captures the commonalities between classes that represent popup information
to be displayed to the user.

Now taking a closer look at the `MainWindow` component, it consists of a number of parts e.g.`CommandBox`, `ResultDisplay`, `StudentListPanel`
, `StatusBarFooter` etc. (Figure 4.2.2). These components, including the `MainWindow`, inherit from the abstract `UiPart` class which captures
the commonalities between classes that represent parts of the visible GUI. The following is a summary of the parts of the `MainWindow`.

<p align="center">
    <img src="../images/ui/UiClassDiagramMainComponents.png" width="600" />
</p>
<div style="text-align: center">
    <em>Figure 4.2.2: MainWindow Ui components</em>
</div>
<br>
Note that the `UI` component uses the JavaFx UI framework.

- The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder.
  For example, the layout of
  the [`MainWindow`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/java/seedu/address/ui/MainWindow.java)
  is specified
  in [`MainWindow.fxml`](https://github.com/se-edu/addressbook-level3/tree/master/src/main/resources/view/MainWindow.fxml)

- The styling of the UI components are defined in the `src/main/resources/view/css` folder.

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.


### <a name="download-command"></a> **Download Command**

#### Implementation

The implementation details of this feature can be found mainly in `MainWindow` as most of the necessary operations are
related to the UI. In addition, the following classes are utilized:

- `DownloadCommand`: for generating the `DownloadCommandResult`
- `DownloadCommandResult`: for displaying the feedback to the CS2100 TA
- `MainWindow.fxml`: for the addition of a 'Download' button on the MainWindow
- `Popup.css`: for the customisation of styles for pop-up messages

In the `Logic` components, the `download` command works in a similar fashion to the `show` command, except that it does
not require its own parser.


This sequence diagram in Figure 5.5.1 shows how the `download` command works at a lower level:

<p align="center">
    <img src="../images/commands/DownloadCommand/DownloadSequenceDiagram.png" width="600"/>
</p>
<div style="text-align: center">
    <em>Figure 5.5.1: Sequence diagram illustrating the execution of download command</em>
</div>
<br>

The following activity diagram in Figure 5.5.2 summarizes what happens when a CS2100 TA executes the download command:

<p align="center">
    <img src="../images/commands/DownloadCommand/DownloadActivityDiagram.png" width="600"/>
</p>
<div style="text-align: center">
    <em>Figure 5.5.2: Activity diagram for executing download command</em>
</div>
<br>

#### Design Considerations

One of the main considerations was to deal with reading and writing files only when necessary. This meant checking if
there is any data to begin with. Only if there exists any data will the CS2100 TA be prompted to select a folder
destination.

Additionally, a pop-up message was chosen to be displayed for two reasons. First, it provides the user a clear visual
indicator of the result of their command, as compared to the typical textual output they would see. Second, we would
only know if the data was successfully downloaded after the textual response is shown to the user. Using a pop-up
message right at the end of this operation means we can customize the message depending on whether the download was a
success.

#### Alternatives

1. One alternative could be to not use a third-party package (`org.json`), and instead manually parse the json file and
   write the corresponding values to a CSV file which ProgrammerError would create. We chose not to go down this route
   as it is much more tedious with little reward in terms of code management and code quality.


2. Another alternative with respect to the CS2100 TA experience could be to disallow the user from selecting a folder to save
   their data to. Instead, a default location could be chosen so as to save the CS2100 TA some time in getting their data
   downloaded quickly. However, since we wanted to make ProgrammerError more flexible and adaptable to different users, we opted to include the
   functionality of allowing the CS2100 TA to select a folder destination.
