CREATE TABLE IF NOT EXISTS Ingredient (id identity, name VARCHAR(35) NOT NULL, ingredient_type VARCHAR(10) NOT NULL);
CREATE TABLE IF NOT EXISTS Sushi (id identity, name VARCHAR(50) NOT NULL, createdAr TIMESTAMP NOT NULL);
CREATE TABLE IF NOT EXISTS Sushi_Ingredient (sushi BIGINT NOT NULL, ingredient VARCHAR(4) NOT NULL);
ALTER TABLE Sushi_Ingredient ADD FOREIGN KEY (sushi) REFERENCES Sushi(id);
ALTER TABLE Sushi_Ingredient ADD FOREIGN KEY (ingredient) REFERENCES Ingredient(id);

--CREATE TABLE IF NOT EXISTS Sushi_Order (
--    id identity,
--    name VARCHAR(50) NOT NULL,
--    street VARCHAR(50) NOT NULL,
--    streetNr VARCHAR(50) NOT NULL,
--    city VARCHAR(50) NOT NULL,
--    zip VARCHAR(10) NOT NULL,
--    ccNumber VARCHAR(16) NOT NULL,
--    ccExpiration VARCHAR(3) NOT NULL,
--    ccCvv TIMESTAMP NOT NULL);

    CREATE TABLE IF NOT EXISTS `Sushi_Order` (
    `id`         identity,
    name VARCHAR(50) NOT NULL,
    street VARCHAR(50) NOT NULL,
    streetNr VARCHAR(50) NOT NULL,
    city VARCHAR(50) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    ccNumber VARCHAR(16) NOT NULL,
    ccExpiration VARCHAR(3) NOT NULL,
    ccCvv TIMESTAMP NOT NULL);

CREATE TABLE IF NOT EXISTS Sushi_Order_Sushi (sushi_order BIGINT NOT NULL, sushi BIGINT NOT NULL);
ALTER TABLE Sushi_Order_Sushi ADD FOREIGN KEY (sushi_order) REFERENCES Sushi_Order(id);
ALTER TABLE Sushi_Order_Sushi ADD FOREIGN KEY (sushi) REFERENCES Sushi(id);