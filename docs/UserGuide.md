---
layout: page
title: User Guide
---
### 5. Viewing a student: `view`

Views all the related information of a student stored on ProgrammerError with his/her student ID.

Format: `**view -sid <STUDENT_ID>**`

Examples:

- `**view -sid A1234567X`** Views all related information of the student with student ID A1234567X in ProgrammerError.

### 6. Updating a student's grade : `update`

Updates the grade of an existing student's in the PE.

Format: `update -sid <STUDENT_ID> -grade <GRADE_SCORE>`

- Updates the student with `<STUDENT_ID> with the grade <GRADE_SCORE>`
- Existing values will be updated to the input values.

Examples:

- `update -sid A1234567X -grade 100`  Updates the grade of the student with student ID A1234567X to be 100.
- `update -sid A2345678X -grade 97.5`  Updates the grade of the student with student ID A2345678X to be 97.5.