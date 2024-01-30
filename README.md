# FINAL_PROJECT
## This is final Project "Syllabus Tag Mapping"
Project Details: Here is two different Msa which are talking internally with each other to get funcanalities for valid mapping between the Syllabus and Tags

# Description
## First MSA
Syallabus Msa: This MSA defines the syllabus with its Attributes details.

## Second MSA
The Second MSA internally Consist of two MSAs one is Tag MSA and Another is Syllabus-Tag MSA.

Tag MSA: This MSA defines the tag details with tag attributes.

SyllabusTag: This is basically the MSA in Which we performed the Mapping of Syllabus With Tag by their ID by validating their presence.

## Third MSA
Th third MSA is used as server for hosting Both the MSA, syllabus and Tag.

## Environment to Execute
### Requirements

1. Redis server to be installed on your System.
2. Active internet to Build and Download the projects Prperly.
### Steps
1. First run the third MSA  which is having the eureka Server which is respounsible for hosting a server.
2. Run any one MSA  and make sure that its registry is showing on the eureka server.
3. Now run the last Msa and check the registry on the Eureka.
4. Now u can test the functionalities on the server by either Calling Swagger or by hitting the Controller EndPoints.

