'for h2 database



DROP TABLE IF EXISTS user;
CREATE TABLE user(id NUMBER AUTO_INCREMENT PRIMARY KEY,name VARCHAR(15) NOT NULL,password VARCHAR(100) NOT NULL);
INSERT INTO user(name,password ) VALUES ('user1', '$2a$10$7bvyCx6OGn7rQnwgVnvbDOWagw5s3T.oslj5VYvyJWQcPvnBZ8uFa' ), ('user2', '$2a$10$E4FZjdsa8ohDmh6v6wwLlOd5dPbpPHhGCR7RrMdTXCHg2s2ZGcD4e' ),('user3','$2a$10$6ULHPNijESBKLBgjogwWt.rnu4By3nWrnSThnWqwMunQkzuVCds3m');




'for postgress 

CREATE TABLE accounts (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	created_on TIMESTAMP NOT NULL,
        last_login TIMESTAMP 
);

The following statement creates the  roles table that consists of two columns: role_id and role_name:

CREATE TABLE roles(
   role_id serial PRIMARY KEY,
   role_name VARCHAR (255) UNIQUE NOT NULL
);


The following statement creates the account_roles table that has three columns: user_id, role_id and grant_date.

CREATE TABLE account_roles (
  user_id INT NOT NULL,
  role_id INT NOT NULL,
  grant_date TIMESTAMP,
  PRIMARY KEY (user_id, role_id),
  FOREIGN KEY (role_id)
      REFERENCES roles (role_id),
  FOREIGN KEY (user_id)
      REFERENCES accounts (user_id)
);




