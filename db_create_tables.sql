CREATE TABLE categories
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    name VARCHAR(50) NOT NULL,
    parent_category_id INT
);
CREATE TABLE category_properties
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    category_id INT NOT NULL,
    property_id INT NOT NULL,
    CONSTRAINT Category_properties_Categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT Category_properties_Properties_id_fk FOREIGN KEY (property_id) REFERENCES properties (id)
);
CREATE TABLE contact_details
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    phone VARCHAR(15) NOT NULL,
    email VARCHAR(50),
    address VARCHAR(100),
    postcode VARCHAR(8),
    city VARCHAR(50),
    country_code VARCHAR(2),
    name VARCHAR(50) NOT NULL,
    CONSTRAINT Contact_details_Countries_code_fk FOREIGN KEY (country_code) REFERENCES countries (code)
);
CREATE UNIQUE INDEX contact_details_email_uindex ON contact_details (email);
CREATE UNIQUE INDEX contact_details_phone_uindex ON contact_details (phone);
CREATE TABLE countries
(
    code VARCHAR(2) PRIMARY KEY NOT NULL,
    name VARCHAR(100) NOT NULL
);
CREATE UNIQUE INDEX Countries_code_uindex ON countries (code);
CREATE TABLE customer_groups
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    name VARCHAR(50) NOT NULL,
    discount INT DEFAULT 0 NOT NULL
);
CREATE TABLE customers
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    contact_detail_id INT NOT NULL,
    customer_group_id INT NOT NULL,
    CONSTRAINT Customers_Contact_details_id_fk FOREIGN KEY (contact_detail_id) REFERENCES contact_details (id),
    CONSTRAINT Customers_Customer_groups_id_fk FOREIGN KEY (customer_group_id) REFERENCES customer_groups (id)
);
CREATE TABLE invoices
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    payment_date DATETIME2,
    amount DECIMAL(8,2) NOT NULL
);
CREATE TABLE order_items
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    order_id INT NOT NULL,
    variant_id INT NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT Order_items_Orders_id_fk FOREIGN KEY (order_id) REFERENCES orders (id)
);
CREATE TABLE orders
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    order_date DATETIME2 NOT NULL,
    amount DECIMAL(8,2),
    delivery_status INT DEFAULT 0 NOT NULL,
    invoice_id INT,
    customer_id INT NOT NULL,
    delivery_date DATETIME2 NOT NULL,
    CONSTRAINT Orders_Invoices_id_fk FOREIGN KEY (invoice_id) REFERENCES invoices (id),
    CONSTRAINT Orders_Customers_id_fk FOREIGN KEY (customer_id) REFERENCES customers (id)
);
CREATE TABLE product_categories
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    product_id INT NOT NULL,
    category_id INT NOT NULL,
    is_primary BIT NOT NULL,
    CONSTRAINT Product_categories_Products_id_fk FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT Product_categories_Categories_id_fk FOREIGN KEY (category_id) REFERENCES categories (id)
);
CREATE TABLE products
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    name VARCHAR(50) NOT NULL,
    cost_price DECIMAL(6,2) DEFAULT 0 NOT NULL,
    sales_price DECIMAL(6,2) DEFAULT 0 NOT NULL,
    rent_price DECIMAL(6,2) DEFAULT 0 NOT NULL,
    country_code VARCHAR(2),
    min_stock INT DEFAULT 5 NOT NULL,
    description VARCHAR(2000),
    supplier_id INT NOT NULL,
    CONSTRAINT Products_Countries_code_fk FOREIGN KEY (country_code) REFERENCES countries (code),
    CONSTRAINT Products_Suppliers_id_fk FOREIGN KEY (supplier_id) REFERENCES suppliers (id)
);
CREATE TABLE properties
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    name VARCHAR(50) NOT NULL
);
CREATE TABLE property_values
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    property_id INT NOT NULL,
    value VARCHAR(100),
    CONSTRAINT Property_values_Properties_id_fk FOREIGN KEY (property_id) REFERENCES properties (id)
);
CREATE TABLE suppliers
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    contact_detail_id INT NOT NULL,
    co_reg_no INT,
    CONSTRAINT Suppliers_Contact_details_id_fk FOREIGN KEY (contact_detail_id) REFERENCES contact_details (id)
);
CREATE TABLE variant_property_values
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    property_value_id INT NOT NULL,
    variant_id INT NOT NULL,
    CONSTRAINT Variant_property_values_Property_values_id_fk FOREIGN KEY (property_value_id) REFERENCES property_values (id),
    CONSTRAINT Variant_property_values_Variants_id_fk FOREIGN KEY (variant_id) REFERENCES variants (id)
);
CREATE TABLE variants
(
    id INT PRIMARY KEY NOT NULL IDENTITY,
    product_id INT NOT NULL,
    quantity INT NOT NULL,
    CONSTRAINT Variants_Products_id_fk FOREIGN KEY (product_id) REFERENCES products (id)
);