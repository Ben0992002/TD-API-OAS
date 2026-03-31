-- Nettoyage des tables existantes
DROP TABLE IF EXISTS stock_movement;
DROP TABLE IF EXISTS dish_ingredient;
DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS ingredient;

-- 1. Table Ingrédient (TD5)
CREATE TABLE ingredient (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            category VARCHAR(50),
                            price DOUBLE PRECISION NOT NULL
);

-- 2. Table Plat (Mis à jour pour Évaluation K3)
CREATE TABLE dish (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(100) UNIQUE NOT NULL,
                      category VARCHAR(50) NOT NULL,
                      unit_price DOUBLE PRECISION NOT NULL
);

-- 3. Table de liaison Plat <-> Ingrédient (TD5)
-- Cette table permet de savoir quels ingrédients composent un plat
CREATE TABLE dish_ingredient (
                                 id SERIAL PRIMARY KEY,
                                 dish_id INT REFERENCES dish(id) ON DELETE CASCADE,
                                 ingredient_id INT REFERENCES ingredient(id) ON DELETE CASCADE,
                                 required_quantity DOUBLE PRECISION -- Quantité nécessaire (ex: 0.5 kg)
);

-- 4. Table Mouvement de Stock (Pour la partie stock du TD5)
CREATE TABLE stock_movement (
                                id SERIAL PRIMARY KEY,
                                ingredient_id INT REFERENCES ingredient(id) ON DELETE CASCADE,
                                datetime TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
                                unit VARCHAR(10), -- KG, L, PCS
                                value DOUBLE PRECISION,
                                type VARCHAR(10) -- ENTREE ou SORTIE
);