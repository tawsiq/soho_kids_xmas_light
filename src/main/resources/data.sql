#DELETE FROM SponsorInfo;
#INSERT INTO SponsorInfo (company_name, contact_person, email)
#VALUES ('RashidInc','Tuhin','Tuhin@gmail.com');

/*Have commented this out as was testing SQL before so using this as dummy test, but is no longer needed*/

/*DELETE FROM ContactInfo;
INSERT INTO ContactInfo (`name`, email, `subject`, message) VALUES ('Ozair', 'ozair@gmail.com', 'I like milk', 'Just wanted to get in contact with you to say that I really like milk... and speed typing.')*/


DELETE FROM MarketplaceProducts;

INSERT INTO MarketplaceProducts (product_id, filename, filepath, product_name, price)
VALUES (1, 'Black-Tshirt-Arm-Logo.png', 'C:\\Projects\\Soho_kids_project_softeng\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\images\\marketplace-productlist\\Products\\Black-Tshirt-Arm-Logo.png', 'Black T-shirt with Arm Logo', 30.00);

INSERT INTO MarketplaceProducts (product_id, filename, filepath, product_name, price)
VALUES (2, 'Black-T-Shirt.png', 'C:\\Projects\\Soho_kids_project_softeng\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\images\\marketplace-productlist\\Products\\Black-T-Shirt.png', 'Black T-Shirt', 28.00);

INSERT INTO MarketplaceProducts (product_id, filename, filepath, product_name, price)
VALUES (3, 'White-T-Shirt.png', 'C:\\Projects\\Soho_kids_project_softeng\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\images\\marketplace-productlist\\Products\\White-T-Shirt.png', 'White T-Shirt', 28.00);

INSERT INTO MarketplaceProducts (product_id, filename, filepath, product_name, price)
VALUES (4, 'Gingerbread-Greeting-Card.jpg', 'C:\\Projects\\Soho_kids_project_softeng\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\images\\marketplace-productlist\\Products\\Gingerbread-Greeting-Card.jpg', 'Gingerbread Greeting Card', 5.00);

INSERT INTO MarketplaceProducts (product_id, filename, filepath, product_name, price)
VALUES (5, 'Wrapping-Paper.jpg', 'C:\\Projects\\Soho_kids_project_softeng\\team-7-soho-kids-christmas-lights\\src\\main\\resources\\static\\images\\marketplace-productlist\\Products\\Wrapping-Paper.jpg', 'Wrapping Paper 2022', 10.00);
--Delete product ID

