DELETE FROM MarketplaceProducts;

INSERT INTO menu_item (name, price_in_pence, allergens, description, is_vegetarian, is_vegan)
VALUES ('Egg and Bacon', 100, 'egg', '', FALSE, FALSE);

INSERT INTO menu_item (name, price_in_pence, allergens, description, is_vegetarian, is_vegan)
VALUES ('Egg Sausage and Bacon', 200, 'egg', '', FALSE, FALSE);

INSERT INTO menu_item (name, price_in_pence, allergens, description, is_vegetarian, is_vegan)
VALUES ('Egg and Spam', 200, 'egg', '', FALSE, FALSE);

INSERT INTO menu_item (name, price_in_pence, allergens, description, is_vegetarian, is_vegan)
VALUES ('Egg, Bacon and Spam', 250, 'egg', '', FALSE, FALSE);