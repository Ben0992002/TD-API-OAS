-- Nettoyage des données existantes
TRUNCATE TABLE dish_ingredient, dish, ingredient RESTART IDENTITY;

-- Insertion d'ingrédients de base
INSERT INTO ingredient (name, category, price) VALUES
                                                   ('Farine', 'Féculent', 200.0),
                                                   ('Oeuf', 'Protéine', 500.0),
                                                   ('Sucre', 'Assaisonnement', 100.0),
                                                   ('Poulet', 'Viande', 2500.0),
                                                   ('Chocolat', 'Dessert', 1500.0);

-- Insertion de Plats
-- Note : Les prix sont variés pour tester les filtres priceUnder/priceOver
INSERT INTO dish (name, category, unit_price) VALUES
                                                  ('Salade César', 'entrée', 1200.0),
                                                  ('Soupe à l''oignon', 'entrée', 800.0),
                                                  ('Poulet Bicyclette', 'résistance', 3500.0),
                                                  ('Steak Frites', 'résistance', 4500.0),
                                                  ('Mousse au Chocolat', 'dessert', 1500.0),
                                                  ('Crème Brûlée', 'dessert', 2000.0);

-- Liaison pour le TD5
INSERT INTO dish_ingredient (dish_id, ingredient_id) VALUES
                                                         (3, 4), -- Poulet Bicyclette contient du Poulet
                                                         (5, 5); -- Mousse contient du Chocolat