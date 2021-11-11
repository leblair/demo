CREATE TABLE usser (
    userid uuid NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    username varchar(24) NOT NULL UNIQUE,
    password varchar(255) NOT NULL,
    role varchar(10),
    enabled boolean DEFAULT true
  );


--se hace uun hash del password y luego un salt, luego se passea,
--en la base de datos se hace $ caracter el salt ABC $ y el password hasheado
--de esta manera nos aseguramos de que aunque tengan el mismo password, en la base de datos no sean iguales
--
  -- afegim un usuari de prova
  CREATE EXTENSION IF NOT EXISTS pgcrypto;
  INSERT INTO usser (username, password) VALUES ('user', crypt('pass', gen_salt('bf')));