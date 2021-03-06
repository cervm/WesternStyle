INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(011) 02222484','aliquet.odio@imperdiet.co.uk','P.O. Box 341, 724 Adipiscing Ave','4582','New Westminster','PL','Charles Wilcox');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(0851) 33299266','semper.pretium@nisimagna.net','P.O. Box 658, 1120 Metus. Rd.','Y1P 8J2','Vergnies','DK','Thomas Klein');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(0881) 13350800','blandit.mattis.Cras@blanditatnisi.com','P.O. Box 626, 5430 Luctus. Rd.','OI2U 9IX','Swan Hill','Côte D''DK','Karyn Decker');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(039833) 845401','ad.litora.torquent@Nullamnisl.com','640-5297 Justo Road','41803','Stratford-upon-Avon','DK','Ava Bartlett');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(0702) 17732320','aliquam.eros.turpis@pedesagittisaugue.ca','Ap #874-2965 Lacus. Road','55746','Kirkintilloch','DK','TaShya Kent');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(00494) 0714254','sapien.gravida@augueSedmolestie.org','688-3155 Luctus, Road','11125','Mezzana','DK','Keegan Morrow');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(032669) 715266','Maecenas.mi@egetlacusMauris.ca','Ap #494-4723 Hendrerit Av.','6469KK','Enkhuizen','SE','Lee Osborne');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(0346) 73642618','nibh.Donec@Nullafacilisi.org','P.O. Box 268, 1843 Fames St.','53-343','Opgrimbie','RU','Daniel Hicks');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(03933) 8097413','eu.tellus@tellusid.ca','Ap #775-1823 Aenean Avenue','51573','Harlingen','CZ','Chava Nixon');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(07797) 7104265','aliquet.odio.Etiam@Aliquam.ca','P.O. Box 781, 3610 Aenean Rd.','56813','Lalbahadur Nagar','US','Howard Gibbs');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(035145) 465617','massa.rutrum.magna@Phasellus.edu','P.O. Box 646, 7882 Ornare, Av.','C2C 1H9','Sint-Pieters-Woluwe','DK','Molly Jenkins');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(0642) 88661755','luctus.vulputate@velit.org','Ap #322-2240 Auctor Rd.','6855','Wanzele','PL','Zeus Vaughan');
INSERT INTO contact_details([phone],[email],[address],[postcode],[city],[country_code],[name]) VALUES('(09576) 3433477','nascetur@dignissimtemporarcu.ca','P.O. Box 478, 3744 Mi St.','73272','Ficulle','SK','Nelle Wiggins');

INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (26, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (27, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (28, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (29, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (30, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (31, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (32, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (33, 1);
INSERT INTO [customers] ([contact_detail_id],[customer_group_id]) VALUES (34, 1);

INSERT INTO [suppliers] ([contact_detail_id],[co_reg_no]) VALUES (35, 16234);
INSERT INTO [suppliers] ([contact_detail_id],[co_reg_no]) VALUES (36, 23454);
INSERT INTO [suppliers] ([contact_detail_id],[co_reg_no]) VALUES (37, 56234);
INSERT INTO [suppliers] ([contact_detail_id],[co_reg_no]) VALUES (38, 98734);

INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('placerat,',124,114,58,5,'magnis dis parturient montes, nascetur ridiculus mus. Donec dignissim', 'PL', 1);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('Mauris',190,118,63,8,'orci sem eget massa. Suspendisse eleifend.', 'DK', 2);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('tristique',99,138,36,5,'luctus et ultrices posuere cubilia Curae; Donec tincidunt. Donec vitae erat vel', 'US', 3);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('posuere',72,122,43,8,'metus eu erat semper rutrum. Fusce dolor quam,', 'US', 4);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('Nullam',69,146,54,5,'elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar', 'DK', 1);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('ipsum.',99,123,20,5,'auctor, velit eget laoreet posuere, enim nisl elementum purus, accumsan interdum', 'US', 2);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('dolor.',143,148,51,5,'et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada', 'DK', 3);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('Suspendisse',136,137,52,4,'Vivamus molestie dapibus ligula. Aliquam', 'DK', 4);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('ad',191,108,63,5,'at, nisi. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur', 'PL', 1);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('nascetur',196,140,23,6,'at auctor ullamcorper, nisl arcu iaculis enim,', 'PL', 2);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('mi,',137,130,38,4,'pede, ultrices a, auctor non, feugiat nec, diam. Duis', 'DK', 3);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('in,',50,102,48,8,'justo. Proin non massa non ante bibendum', 'AN', 4);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('quam',195,108,80,8,'ligula. Nullam enim. Sed nulla ante, iaculis nec, eleifend non, dapibus', 'DE', 1);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('purus.',162,133,65,10,'tortor at risus. Nunc ac sem ut dolor dapibus gravida. Aliquam tincidunt,', 'DE', 2);
INSERT INTO products([name],[cost_price],[sales_price],[rent_price],[min_stock],[description], [country_code], [supplier_id]) VALUES('convallis',168,140,47,8,'pretium et, rutrum non, hendrerit id, ante. Nunc mauris sapien, cursus in, hendrerit consectetuer, cursus', 'CZ', 3);

INSERT INTO variants([quantity], [product_id]) VALUES(29, 2);
INSERT INTO variants([quantity], [product_id]) VALUES(17, 3);
INSERT INTO variants([quantity], [product_id]) VALUES(16, 4);
INSERT INTO variants([quantity], [product_id]) VALUES(22, 5);
INSERT INTO variants([quantity], [product_id]) VALUES(26, 16);
INSERT INTO variants([quantity], [product_id]) VALUES(22, 6);
INSERT INTO variants([quantity], [product_id]) VALUES(27, 7);
INSERT INTO variants([quantity], [product_id]) VALUES(30, 8);
INSERT INTO variants([quantity], [product_id]) VALUES(27, 9);
INSERT INTO variants([quantity], [product_id]) VALUES(27, 10);
INSERT INTO variants([quantity], [product_id]) VALUES(19, 11);
INSERT INTO variants([quantity], [product_id]) VALUES(22, 12);
INSERT INTO variants([quantity], [product_id]) VALUES(21, 13);
INSERT INTO variants([quantity], [product_id]) VALUES(30, 14);
INSERT INTO variants([quantity], [product_id]) VALUES(21, 15);

INSERT INTO categories([name]) VALUES ('Clothes');
INSERT INTO categories([name]) VALUES ('Misc');
INSERT INTO categories([name]) VALUES ('Electronics');
INSERT INTO categories([name]) VALUES ('Tools');
INSERT INTO categories([name]) VALUES ('Food');
INSERT INTO categories([name], [parent_category_id]) VALUES ('Hats', 1);
INSERT INTO categories([name], [parent_category_id]) VALUES ('Tshirts', 1);
INSERT INTO categories([name], [parent_category_id]) VALUES ('Heavy duty', 4);
INSERT INTO categories([name], [parent_category_id]) VALUES ('Meat', 5);
INSERT INTO categories([name], [parent_category_id]) VALUES ('Vegan a.k.a for pussies', 5);

INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (2, 6, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (2, 2, 0);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (3, 2, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (4, 1, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (5, 3, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (6, 4, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (7, 1, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (8, 5, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (9, 2, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (10, 3, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (11, 4, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (11, 1, 0);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (12, 5, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (13, 2, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (14, 1, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (15, 4, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (16, 1, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (20, 2, 1);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (20, 5, 0);
INSERT INTO [product_categories] ([product_id], [category_id], [is_primary]) VALUES (22, 6, 1);
