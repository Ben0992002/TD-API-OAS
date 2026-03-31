-- Suppression de la base si elle existe (pour repartir à neuf)
DROP DATABASE IF EXISTS restaurant;
DROP USER IF EXISTS restaurant_user;

-- 1. Création de l'utilisateur
CREATE USER restaurant_user WITH PASSWORD 'my_password';

-- 2. Création de la base de données
CREATE DATABASE restaurant OWNER restaurant_user;

-- 3. Attribution des privilèges
GRANT ALL PRIVILEGES ON DATABASE restaurant TO restaurant_user;