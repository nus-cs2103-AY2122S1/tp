---
layout: page
title: Use Cases
---

This page contains the use cases in our project. The sections below have been segmented to major sections where each
use case is classified under to allow for easier navigation.
<br><br>
For all use cases below, the **System** is the `Trace2Gather` and the **Actor** is the `user`, unless specified otherwise.


* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Guest

### Use case: Add a guest
**MSS**

1.  User requests to add a guest and inputs the necessary parameters.
2.  Trace2Gather adds the guest with the input details.
3.  Trace2Gather displays a message to the user indicating that the guest has been added.

    Use case ends.

**Extensions**

* 1a. There are missing parameters or input is not in the correct format.
    * 1a1. Trace2Gather displays a message indicating to the user the required parameters.
    * 1a2. User inputs the parameters again until all required parameters are input.

        Use case resumes at step 2.

<br><br>

### Use case: Edit a guest
**MSS**
1.  User requests to list guests.
2.  Trace2Gather shows a list of guests.
3.  User requests to edit a guest at a specific index in the list.
4.  Trace2Gather edits the guest.

    Use case ends.

**Extensions**
* 2a. The list is empty.

    Use case ends.

* 2b. The given index is invalid.
    * 2b1. Trace2Gather displays a message indicating to the user the required format.
    * 2b2. User edits the index to the correct index.

        Use case resumes at step 3.

* 2c. The input command is not in the correct format.
    * 2c1. Trace2Gather displays a message indicating to the user the required format.
    * 2c2. User edits the input to the correct format.

        Use case resumes at step 3.

<br><br>

### Use case: Retrieve all guests
**MSS**

1.  User requests to retrieve all the entries of guests.
2.  User is able to view all the entries of guests.

    Use case ends.

### Use case: Search for guest(s) by name
**MSS**

1.  User requests to find a specific guest using the name of the guest.
2.  User is able to view all guests with the same name.

    Use case ends.

**Extensions**
* 1a. The list containing guests with the same name is empty.

    Use case ends.

<br><br>


--------------------------------------------------------------------------------------------------------------------

## Room

### Use case: Add room(s)
**MSS**

1.  User requests to add a room or multiple rooms along with their tags to indicate properties about the room.
2.  Trace2Gather adds the room(s) with the tagged properties details.
3.  Trace2Gather displays a message to the user indicating that the room(s) has been added.

    Use case ends.

**Extensions**

* 1a. There are missing parameters or input is not in the correct format.
    * 1a1. Trace2Gather displays a message indicating to the user the required parameters.
    * 1a2. User inputs the parameters again until all required parameters are input.

        Use case resumes at step 2.

<br><br>

### Use case: Search for room(s)
**MSS**

1.  User requests to find a specific room / some rooms using the room number.
2.  User is able to view all rooms as specified.

    Use case ends.

**Extensions**
* 1a. The list containing room(s) with desired room number(s) is empty.

  Use case ends.

<br><br>

### Use case: Retrieve all occupied rooms
**MSS**

1.  User requests to retrieve all occupied rooms.
2.  User is able to view all the entries of occupied rooms.

    Use case ends.

**Extensions**
* 1a. The list containing all occupied rooms is empty.

  Use case ends.

<br><br>

### Use case: Retrieve all vacant rooms
**MSS**

1.  User requests to retrieve all vacant rooms.
2.  User is able to view all vacant rooms.

    Use case ends.

**Extensions**
* 1a. The list containing all vacant rooms is empty.

  Use case ends.

<br><br>

### Use case: Retrieve all rooms
**MSS**

1.  User requests to retrieve all the entries of rooms.
2.  User is able to view all the entries of rooms.

    Use case ends.

<br><br>

### Use case: Check-in guest(s) to a room
**MSS**

1.  User requests check-in guest(s) into a room using guest list index.
2.  Trace2Gather adds the guest(s) into the corresponding room.
3.  Trace2Gather displays a message to the user indicating that the room(s) has been added.
4.  Occupancy status of specified room is updated, and the guest(s) are shown in the room.

    Use case ends.

**Extensions**

* 1a. Guest index is not valid
    * 1a1. Trace2Gather displays a message indicating to the user that the command is invalid.
    * 1a2. User changes the index to be correct.

        Use case resumes at step 2.

* 1b. Guest(s) is already in another room.
    * 1a1. Trace2Gather displays a message indicating to the user the guests that already have a room.

        Use case ends.

<br><br>

### Use case: Check-out room
**MSS**

1.  User requests to check-out guest(s) current in a room.
2.  Room and its guests are added into History list.
3.  Trace2Gather removes the guest(s) from room.
4.  Trace2Gather displays a message to the user indicating that the room(s) has been checked-out.
5.  Occupancy status of specified room is updated.

    Use case ends.

**Extensions**

* 1a. Room is empty.
    * 1a1. Trace2Gather displays a message indicating to the user that the room is vacant.
      Use case ends.

<br><br>

--------------------------------------------------------------------------------------------------------------------

## History

### Use case: Retrieve all history records
**MSS**

1.  User requests to retrieve all history record entries.
2.  User is able to view all history record entries.

    Use case ends.

<br><br>

### Use case: Search for specific history record
**MSS**

1.  User requests to find a specific history record using either room number or guest name.
2.  User is able to view all history records as specified.

    Use case ends.

**Extensions**
* 1a. The list containing history records with desired history record(s) is empty.

  Use case ends.

<br><br>

--------------------------------------------------------------------------------------------------------------------

