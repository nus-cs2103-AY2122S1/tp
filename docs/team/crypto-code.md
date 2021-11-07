---
layout: page
title: Atin Sakkeer's Project Portfolio Page
---

## Project: CohortConnect

CohortConnect is an advanced desktop address book which facilitates networking among Computer Science (CS) students. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 15 kLoC.

Given below are my contributions to the project.

### New Features

* **Show Command**:
    * Pull requests [\#40](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/40) and [\#65](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/65)
    * What it does: Allows user to view details of a user using the Show command by the index, name, github username or telegram id.
    * Justification: This feature allows for a clean UI and displaying details of the chosen Student only.
    * Highlights: The implementation too was challenging as it required interconnecting the ModelManager with the PersonListPanel.


* **GitHubUtil Class**
    * Pull requests [\#98](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/98)
    * What it does: Contains the methods necessary for web scraping GitHub for data.
    * Highlights: This class helps with gathering data on the contacts including Profile Picture, Repository count and Language contributions.


* **Find A Buddy Feature**
    * Pull request [\#137](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/137)
    * What it does: Allows user to get the top 5 matches based on GitHub similarities.
    * Justification: This feature improves the product significantly because the user will be able to find teammates with similarities.
    * Highlights: This enhancement is central to the app's use. It required an in-depth analysis of similarity metrics. The implementation too was challenging as it required applying Euclidean Distance, Manhattan Distance and Cosine Similarity.
    * Credits: The distance metrics were researched [here](https://towardsdatascience.com/17-types-of-similarity-and-dissimilarity-measures-used-in-data-science-3eb914d2681)
    

* **LangColorUtil**
    * Pull request [\#248](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/248)
    * What it does: Helps display common languages to improve testability of the Find A Buddy Feature
    * Highlights: This was challenging since there were lots of languages and manually adding each would be time-consuming. To overcome this, I wrote a Python program to write the Java code to add to each color to the Hashmap, hence automating the process.
    * Credits: The color data was obtained from this [source](https://github.com/ozh/github-colors/blob/master/colors.json)


### UI Enhancements

* **Addition of Student Detail Pane** 
    * Pull request [\#40](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/40)
    * Enhanced the UI to make it cleaner and easier to use.
    * Display student details on clicking on a particular student.
    
* **Addition of Tabs**
    * Pull request [\#91](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/91)
    * Enhanced the UI/UX by creating tabs for the different features
    * The tabs are `Contacts`, `Favourites`, `Events` and `Find A Buddy`
    

### Test Cases Written

Wrote test cases fore the following classes
* Ai class (Pull requests [\#250](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/250) )
* LangColorUtil class (Pull requests [\#250](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/250) )
* Show command (Pull requests [\#253](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/253) )
* SampleDataUtil (Pull requests [\#254](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/254))
* FindABuddyPredicate (Pull requests [\#254](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/254))
* Favourite Command (Pull request [\#255](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/255))
* UnFavourite Command (Pull request [\#255](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/255))

### Documentation

* **User Guide:**   
    * Added the documentation for the features `show` and `FindABuddy` (Pull request  [\#265](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/265))
  
* **Developer Guide:**
    * Added implementation details of the `show` command. (Pull request [\#110](https://github.com/AY2122S1-CS2103T-T10-1/tp/pull/110))


**Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s1.github.io/tp-dashboard/?search=T10&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=docs~functional-code~test-code~other&since=2021-09-17&tabOpen=true&tabType=authorship&tabAuthor=crypto-code&tabRepo=AY2122S1-CS2103T-T10-1%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false)
