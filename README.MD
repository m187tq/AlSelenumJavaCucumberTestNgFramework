To run this project please use the following mvn command:

mvn -Dtest=Runner test


Cucumber Reporting
------------------
After the tests have run, if you run the following mvn command:

mvn cluecumber-report:reporting

Then go to the target/generated-report directory and double click on index.html, then load
that file into a browser you will see the report.


Pipelines
------------
20 pipe line schedulers

Employer Frontend

| Name | Runner Name | Tag name | Feature path |
| End to End Test and Smoke Tests (20 mins)     | Runner | @SmokeTest,@EndToEnd | src/test/resources/features/ |
| BSA Admin frontend and Manage Contract Templates (1 Hour) | Runner | @RegressionNew |
src/test/resources/features/AdminUI/
src/test/resources/features/employer-frontend/ContractTemplates/ManageContractTemplates/ |
| Employer frontend AddUsers, OtherFeatures, ManageAtRisk, ManageAccreditedlogos and Employer Hub Folders (40 Minutes) |
Runner | @RegressionNew | src/test/resources/features/employer-frontend/AddUsers/
src/test/resources/features/employer-frontend/ManageAccreditedlogos/
src/test/resources/features/employer-frontend/ManageAtRisk/ src/test/resources/features/employer-frontend/OtherFeatures/
src/test/resources/features/employer-frontend/employer-hub/ |
| Employer frontend OfferJob Folder - (2 Hours 30 minutes)  | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/OfferJob/ |
| Pre Employment checks (2 Hours 30 minutes) | PreEmpRunner | @RegressionNew |
src/test/resources/features/employer-frontend/Pre-employment-checks/ |
| Referee Actions (1 Hour 40 minutes) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/RefereeActions/ |
| Pre Emp Checks In System References (40 minutes) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/Pre-employment-checks-InsystemRefs/ |
| Employer Issue Contract Journey (20 minutes) | IssueContractRunner | @RegressionNew |
src/test/resources/features/employer-frontend/ContractTemplates/IssueContract/ |
| EsrVacancy folder - This job calls ESR to get Data - (1 Hour) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/EsrVacancy/ |
| AfterPublishingAVacancy folder - Common Features - Saturday Run (70 Minutes) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/AfterPublishingAVacancy/CommonFeatures/ |
| AfterPublishingAVacancy folder - Interview - Saturday Run (9.5 Hours) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/AfterPublishingAVacancy/Interview/ |
| AfterPublishingAVacancy folder - Shortlisting - Saturday Run (3.5 hours) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/AfterPublishingAVacancy/Shortlisting/ |
| Employer frontend CreateAVacancy Folder - Sunday Run - (4 Hours 30 Minutes) | Runner | @RegressionNew |
src/test/resources/features/employer-frontend/CreateAVacancy/ |
| AfterPublishingAVacancy folder - Interview Reserve List | Runner | @InterviewReserveList |
src/test/resources/features/employer-frontend/AfterPublishingAVacancy/Interview/Interview-Reserve-List/ |

Candidate Frontend

| Name | Runner Name | Tag name | Feature path |
| Candidate IAT Prof Reg Journey (20 Minutes) | CandidatePreEmpChecksRunner | @CandidatePreEmpChecks |
src/test/resources/features/candidate-frontend/Professional-Registrations/ |
| Candidate Issue Contract Journey (15 minutes) | CandidateContractRunner | @CandidateContractJourney |
src/test/resources/features/candidate-frontend/Candidate-Contract-Journey/ |
| Candidate frontend Regression - Sunday Run - (8 Hours) | CandidateRunner | @CandidateRegressionNew |
src/test/resources/features/candidate-frontend/ |


