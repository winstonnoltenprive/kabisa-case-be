### Starting the Backend

1. **Ensure Docker is Installed:**  
   Make sure you have Docker installed on your machine.

2. **Spin Up PostgreSQL:**  
   Run the following command to start the PostgreSQL container in detached mode:
   ```bash
   docker compose up -d
   ```
   This command will launch your database container. The included `init.sql` script will automatically create the necessary tables and relationships.

3. **Start the Spring Boot Application:**  
   Once the database is up and running, start your Spring Boot application.