# MOTIVATION
High performance and efficiency requires well documentation and clearly explained commits. Below we will explain "How should we commit" and what are the procedures we should obey in steps.

-  Always use your own branches (Never commit to master)
-  Atomicity is key for good commit --> Each commit should include small functionality.
-  All commits should be related(link) to an already open issue(s).
-  Each commit should have a clear and short description.

# How good commit looks like!

 1. Create an issue, add description and labels, assign the assignees.
 2. Always work on your **branch**. (git checkout **BRANCHNAME**)
 3. Add changes to your **branch**. (git add new_file.js)
 4. Add commit message to changes. (git commit -m "TEMPLATE MESSAGE WILL BE EXPLAINED")
 5. Push it! (git push)

### Commit Message Template
git commit -m " **ISSUE_LINK** **COMMIT_TYPE** **DESCRIPTION**"

**ISSUE_LINK** --> Related issue number e.g. **#3** (All issues have a number like Database update #70)

**COMMIT_TYPE** --> **Fix:** || **Docs:** || **Feat:** || **Refactor:** || **Test:**

**Fix:** --> Fixing a bug or another type of problem

**Docs:** --> Documentation or commenting

**Feat:** --> New feature, functionality, is added

**Refactor:** --> Renaming variables and code re-arranging to improve the maintainability and readability(Functionality can change but behavior should be the same)

**Test:** --> Testing 

Example good commits:

 1. git commit -m "#23 #12 Feat: Send mail functionality is added."
 2. git commit -m "#12 Docs: Added documentation for select_last_x_commits."
 3. git commit -m "#3 Test: Test cases for bring_the_action is added."

For this file to be added --> git commit -m "#72 Docs: Commit template is added"

