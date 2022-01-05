DELETE FROM Sushi_Order;
DELETE FROM Sushi_Ingredient;
DELETE FROM Sushi;
DELETE FROM Sushi_Order_Sushi;

DELETE FROM Ingredient;
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (1, 'salmon', 'FISH');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (2, 'tuna', 'FISH');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (3, 'shrimp', 'FISH');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (4, 'butterfish', 'FISH');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (5, 'avocado', 'VEGGIE');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (6, 'cucumber', 'VEGGIE');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (7, 'radish', 'VEGGIE');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (8, 'wasabi', 'ADDITIONS');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (9, 'philadelphia cheese', 'ADDITIONS');
INSERT INTO Ingredient (id, name, ingredient_type) VALUES (10, 'soy sauce', 'ADDITIONS');