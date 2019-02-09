****Spark Networks Edit Profile test****

###### Structure of the projects
**database-migration**: <br/>
This is a module to apply database change. <br/>
The database is a h2 file located in profile-backend/db folder <br/>
To setup and install the database invoke maven command flyway:clean and flyway:migrate <br/>
It is important to install the database and all it's preloaded data before running the backend module

**profile-frontend**: <br/>
This is the angular frontend application

**profile-backend**: <br/>
This is the springboot backend api. <br/>
Includes unit tests with 96% coverage

**kubernetes-config**: <br/>
This contains all kubernetes config yaml files to deploy this project on my personal kubernetes cluster

