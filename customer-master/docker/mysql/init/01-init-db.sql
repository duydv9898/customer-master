-- Customer Master Database Initialization Script
-- This script will be executed automatically when the MySQL container starts for the first time

-- Set character set and collation
SET NAMES utf8mb4;
SET CHARACTER SET utf8mb4;

-- Use the database
USE customer_master_db;

-- Grant privileges to appuser
GRANT ALL PRIVILEGES ON customer_master_db.* TO 'appuser'@'%';
FLUSH PRIVILEGES;

-- Create tables will be handled by Hibernate/JPA (ddl-auto: update)
-- But we can create some initial reference data here

-- ============================================
-- REFERENCE DATA INITIALIZATION
-- ============================================

-- Gender reference data
INSERT IGNORE INTO gender (gender_id, gender_code, gender_name, gender_name_local, iso_5218_code, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'M', 'Male', 'Nam', 1, 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'F', 'Female', 'Nữ', 2, 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'O', 'Other', 'Khác', 9, 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Country reference data (Vietnam)
INSERT IGNORE INTO country (country_id, country_code, country_name, country_name_local, iso3_code, numeric_code, dialing_code, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'VN', 'Vietnam', 'Việt Nam', 'VNM', '704', '+84', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'US', 'United States', 'Hoa Kỳ', 'USA', '840', '+1', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'GB', 'United Kingdom', 'Anh', 'GBR', '826', '+44', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Marital Status reference data
INSERT IGNORE INTO marital_status (marital_status_id, marital_status_code, marital_status_name, marital_status_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'SINGLE', 'Single', 'Độc thân', 'Unmarried', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'MARRIED', 'Married', 'Đã kết hôn', 'Legally married', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'DIVORCED', 'Divorced', 'Ly hôn', 'Legally divorced', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'WIDOWED', 'Widowed', 'Góa', 'Spouse deceased', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Client Type reference data
INSERT IGNORE INTO client_type (client_type_id, client_type_code, client_type_name, client_type_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'INDIVIDUAL', 'Individual', 'Cá nhân', 'Individual customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'CORPORATE', 'Corporate', 'Doanh nghiệp', 'Corporate customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'SME', 'Small Medium Enterprise', 'Doanh nghiệp vừa và nhỏ', 'SME customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Category reference data
INSERT IGNORE INTO category (category_id, category_code, category_name, category_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'RETAIL', 'Retail', 'Bán lẻ', 'Retail banking customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'PREMIUM', 'Premium', 'Cao cấp', 'Premium banking customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'VIP', 'VIP', 'VIP', 'VIP banking customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Language reference data
INSERT IGNORE INTO language (language_id, language_code, language_name, language_local, iso_639_2_code, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'en', 'English', 'Tiếng Anh', 'eng', 'English language', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'vi', 'Vietnamese', 'Tiếng Việt', 'vie', 'Vietnamese language', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Contact Channel reference data
INSERT IGNORE INTO contact_channel (contact_channel_id, contact_channel_code, contact_channel_name, contact_channel_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'EMAIL', 'Email', 'Email', 'Email communication', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'SMS', 'SMS', 'Tin nhắn SMS', 'SMS communication', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'PHONE', 'Phone', 'Điện thoại', 'Phone call', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'APP', 'Mobile App', 'Ứng dụng di động', 'Mobile app notification', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Customer Segment reference data
INSERT IGNORE INTO customer_segment (customer_segment_id, segment_code, segment_name, segment_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'MASS', 'Mass Market', 'Thị trường đại chúng', 'Mass market segment', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'AFFLUENT', 'Affluent', 'Khá giả', 'Affluent segment', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'WEALTH', 'Wealth', 'Giàu có', 'Wealth management segment', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Industry reference data
INSERT IGNORE INTO industry (industry_id, industry_code, industry_name, industry_local, parent_code, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'IT', 'Information Technology', 'Công nghệ thông tin', NULL, 'IT industry', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'FIN', 'Finance', 'Tài chính', NULL, 'Financial services', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'MFG', 'Manufacturing', 'Sản xuất', NULL, 'Manufacturing industry', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'RET', 'Retail', 'Bán lẻ', NULL, 'Retail industry', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'EDU', 'Education', 'Giáo dục', NULL, 'Education sector', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Occupation reference data
INSERT IGNORE INTO occupation (occupation_id, occupation_code, occupation_name, occupation_local, industry_code, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'DEV', 'Software Developer', 'Lập trình viên', 'IT', 'Software development', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'ACC', 'Accountant', 'Kế toán', 'FIN', 'Accounting professional', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'ENG', 'Engineer', 'Kỹ sư', 'MFG', 'Engineering professional', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'TCH', 'Teacher', 'Giáo viên', 'EDU', 'Teaching professional', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'MGR', 'Manager', 'Quản lý', NULL, 'Management position', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Residency Status reference data
INSERT IGNORE INTO residency_status (residency_status_id, residency_status_code, residency_status_name, residency_status_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'CITIZEN', 'Citizen', 'Công dân', 'National citizen', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'RESIDENT', 'Permanent Resident', 'Thường trú', 'Permanent resident', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'TEMP', 'Temporary Resident', 'Tạm trú', 'Temporary resident', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'FOREIGN', 'Foreigner', 'Người nước ngoài', 'Foreign national', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Relationship Type reference data
INSERT IGNORE INTO relationship_type (relationship_type_id, relationship_type_code, relationship_type_name, relationship_type_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'SPOUSE', 'Spouse', 'Vợ/Chồng', 'Married partner', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'PARENT', 'Parent', 'Cha/Mẹ', 'Parent relationship', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'CHILD', 'Child', 'Con', 'Child relationship', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'SIBLING', 'Sibling', 'Anh/Chị/Em', 'Sibling relationship', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'GUARDIAN', 'Guardian', 'Người giám hộ', 'Legal guardian', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Product Group reference data
INSERT IGNORE INTO product_group (product_group_id, product_group_code, product_group_name, product_group_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'CASA', 'Current and Savings Account', 'Tài khoản thanh toán và tiết kiệm', 'CASA products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'LOAN', 'Loans', 'Cho vay', 'Loan products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'CARD', 'Cards', 'Thẻ', 'Card products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'INV', 'Investment', 'Đầu tư', 'Investment products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- ============================================
-- SYSTEM PARAMETERS
-- ============================================

INSERT IGNORE INTO system_parameter (param_group, param_type, param_value, description, sort_order, status, created_by, created_at, updated_by, updated_at) VALUES
('CUSTOMER', 'MIN_AGE', '18', 'Minimum age for customer registration', 1, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('CUSTOMER', 'MAX_AGE', '120', 'Maximum age for customer registration', 2, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('CUSTOMER', 'ID_EXPIRY_WARNING_DAYS', '30', 'Days before ID expiry to show warning', 3, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('KYC', 'RETRY_COUNT', '3', 'Maximum KYC retry attempts', 1, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('SECURITY', 'SESSION_TIMEOUT', '1800', 'Session timeout in seconds', 1, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW());

-- ============================================
-- CREATE INDEXES FOR PERFORMANCE
-- ============================================

-- Customer indexes
-- CREATE INDEX IF NOT EXISTS idx_customer_email ON customer(email);
-- CREATE INDEX IF NOT EXISTS idx_customer_phone ON customer(primary_phone);
-- CREATE INDEX IF NOT EXISTS idx_customer_status ON customer(cif_status);
-- CREATE INDEX IF NOT EXISTS idx_customer_type ON customer(client_type);
-- CREATE INDEX IF NOT EXISTS idx_customer_segment ON customer(customer_segment_code);
-- CREATE INDEX IF NOT EXISTS idx_customer_created ON customer(created_at);
--
-- -- Address indexes
-- CREATE INDEX IF NOT EXISTS idx_address_customer ON address(customer_id);
-- CREATE INDEX IF NOT EXISTS idx_address_province ON address(province_code);
-- CREATE INDEX IF NOT EXISTS idx_address_district ON address(district_code);
--
-- -- Identification indexes
-- CREATE INDEX IF NOT EXISTS idx_identification_customer ON identification(customer_id);
-- CREATE INDEX IF NOT EXISTS idx_identification_number ON identification(identification_number);
-- CREATE INDEX IF NOT EXISTS idx_identification_kyc_status ON identification(kyc_status);
--
-- -- Event audit indexes
-- CREATE INDEX IF NOT EXISTS idx_event_entity ON event(entity_name, entity_id);
-- CREATE INDEX IF NOT EXISTS idx_event_time ON event(event_time);
-- CREATE INDEX IF NOT EXISTS idx_event_correlation ON event(correlation_id);

-- ============================================
-- COMPLETION MESSAGE
-- ============================================

SELECT 'Customer Master Database initialized successfully!' AS message;


-- Create tables will be handled by Hibernate/JPA (ddl-auto: update)
-- But we can create some initial reference data here

-- ============================================
-- REFERENCE DATA INITIALIZATION
-- ============================================

-- Gender reference data
INSERT IGNORE INTO gender (gender_id, gender_code, gender_name, gender_name_local, iso_5218_code, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'M', 'Male', 'Nam', 1, 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'F', 'Female', 'Nữ', 2, 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'O', 'Other', 'Khác', 9, 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Country reference data (Vietnam)
INSERT IGNORE INTO country (country_id, country_code, country_name, country_name_local, iso3_code, numeric_code, dialing_code, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'VN', 'Vietnam', 'Việt Nam', 'VNM', '704', '+84', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'US', 'United States', 'Hoa Kỳ', 'USA', '840', '+1', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'GB', 'United Kingdom', 'Anh', 'GBR', '826', '+44', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Marital Status reference data
INSERT IGNORE INTO marital_status (marital_status_id, marital_status_code, marital_status_name, marital_status_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'SINGLE', 'Single', 'Độc thân', 'Unmarried', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'MARRIED', 'Married', 'Đã kết hôn', 'Legally married', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'DIVORCED', 'Divorced', 'Ly hôn', 'Legally divorced', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'WIDOWED', 'Widowed', 'Góa', 'Spouse deceased', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Client Type reference data
INSERT IGNORE INTO client_type (client_type_id, client_type_code, client_type_name, client_type_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'INDIVIDUAL', 'Individual', 'Cá nhân', 'Individual customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'CORPORATE', 'Corporate', 'Doanh nghiệp', 'Corporate customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'SME', 'Small Medium Enterprise', 'Doanh nghiệp vừa và nhỏ', 'SME customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Category reference data
INSERT IGNORE INTO category (category_id, category_code, category_name, category_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'RETAIL', 'Retail', 'Bán lẻ', 'Retail banking customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'PREMIUM', 'Premium', 'Cao cấp', 'Premium banking customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'VIP', 'VIP', 'VIP', 'VIP banking customer', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Language reference data
INSERT IGNORE INTO language (language_id, language_code, language_name, language_local, iso_639_2_code, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'en', 'English', 'Tiếng Anh', 'eng', 'English language', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'vi', 'Vietnamese', 'Tiếng Việt', 'vie', 'Vietnamese language', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Contact Channel reference data
INSERT IGNORE INTO contact_channel (contact_channel_id, contact_channel_code, contact_channel_name, contact_channel_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'EMAIL', 'Email', 'Email', 'Email communication', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'SMS', 'SMS', 'Tin nhắn SMS', 'SMS communication', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'PHONE', 'Phone', 'Điện thoại', 'Phone call', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'APP', 'Mobile App', 'Ứng dụng di động', 'Mobile app notification', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Customer Segment reference data
INSERT IGNORE INTO customer_segment (customer_segment_id, segment_code, segment_name, segment_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'MASS', 'Mass Market', 'Thị trường đại chúng', 'Mass market segment', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'AFFLUENT', 'Affluent', 'Khá giả', 'Affluent segment', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'WEALTH', 'Wealth', 'Giàu có', 'Wealth management segment', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Industry reference data
INSERT IGNORE INTO industry (industry_id, industry_code, industry_name, industry_local, parent_code, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'IT', 'Information Technology', 'Công nghệ thông tin', NULL, 'IT industry', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'FIN', 'Finance', 'Tài chính', NULL, 'Financial services', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'MFG', 'Manufacturing', 'Sản xuất', NULL, 'Manufacturing industry', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'RET', 'Retail', 'Bán lẻ', NULL, 'Retail industry', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'EDU', 'Education', 'Giáo dục', NULL, 'Education sector', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Occupation reference data
INSERT IGNORE INTO occupation (occupation_id, occupation_code, occupation_name, occupation_local, industry_code, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'DEV', 'Software Developer', 'Lập trình viên', 'IT', 'Software development', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'ACC', 'Accountant', 'Kế toán', 'FIN', 'Accounting professional', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'ENG', 'Engineer', 'Kỹ sư', 'MFG', 'Engineering professional', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'TCH', 'Teacher', 'Giáo viên', 'EDU', 'Teaching professional', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'MGR', 'Manager', 'Quản lý', NULL, 'Management position', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Residency Status reference data
INSERT IGNORE INTO residency_status (residency_status_id, residency_status_code, residency_status_name, residency_status_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'CITIZEN', 'Citizen', 'Công dân', 'National citizen', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'RESIDENT', 'Permanent Resident', 'Thường trú', 'Permanent resident', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'TEMP', 'Temporary Resident', 'Tạm trú', 'Temporary resident', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'FOREIGN', 'Foreigner', 'Người nước ngoài', 'Foreign national', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Relationship Type reference data
INSERT IGNORE INTO relationship_type (relationship_type_id, relationship_type_code, relationship_type_name, relationship_type_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'SPOUSE', 'Spouse', 'Vợ/Chồng', 'Married partner', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'PARENT', 'Parent', 'Cha/Mẹ', 'Parent relationship', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'CHILD', 'Child', 'Con', 'Child relationship', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'SIBLING', 'Sibling', 'Anh/Chị/Em', 'Sibling relationship', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'GUARDIAN', 'Guardian', 'Người giám hộ', 'Legal guardian', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- Product Group reference data
INSERT IGNORE INTO product_group (product_group_id, product_group_code, product_group_name, product_group_local, description, record_status, created_at, created_by, updated_at, updated_by) VALUES
(UUID(), 'CASA', 'Current and Savings Account', 'Tài khoản thanh toán và tiết kiệm', 'CASA products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'LOAN', 'Loans', 'Cho vay', 'Loan products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'CARD', 'Cards', 'Thẻ', 'Card products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM'),
(UUID(), 'INV', 'Investment', 'Đầu tư', 'Investment products', 'ACTIVE', NOW(), 'SYSTEM', NOW(), 'SYSTEM');

-- ============================================
-- SYSTEM PARAMETERS
-- ============================================

INSERT IGNORE INTO system_parameter (param_group, param_type, param_value, description, sort_order, status, created_by, created_at, updated_by, updated_at) VALUES
('CUSTOMER', 'MIN_AGE', '18', 'Minimum age for customer registration', 1, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('CUSTOMER', 'MAX_AGE', '120', 'Maximum age for customer registration', 2, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('CUSTOMER', 'ID_EXPIRY_WARNING_DAYS', '30', 'Days before ID expiry to show warning', 3, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('KYC', 'RETRY_COUNT', '3', 'Maximum KYC retry attempts', 1, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW()),
('SECURITY', 'SESSION_TIMEOUT', '1800', 'Session timeout in seconds', 1, 1, 'SYSTEM', NOW(), 'SYSTEM', NOW());

-- ============================================
-- CREATE INDEXES FOR PERFORMANCE
-- ============================================

-- Customer indexes
-- CREATE INDEX IF NOT EXISTS idx_customer_email ON customer(email);
-- CREATE INDEX IF NOT EXISTS idx_customer_phone ON customer(primary_phone);
-- CREATE INDEX IF NOT EXISTS idx_customer_status ON customer(cif_status);
-- CREATE INDEX IF NOT EXISTS idx_customer_type ON customer(client_type);
-- CREATE INDEX IF NOT EXISTS idx_customer_segment ON customer(customer_segment_code);
-- CREATE INDEX IF NOT EXISTS idx_customer_created ON customer(created_at);
--
-- -- Address indexes
-- CREATE INDEX IF NOT EXISTS idx_address_customer ON address(customer_id);
-- CREATE INDEX IF NOT EXISTS idx_address_province ON address(province_code);
-- CREATE INDEX IF NOT EXISTS idx_address_district ON address(district_code);
--
-- -- Identification indexes
-- CREATE INDEX IF NOT EXISTS idx_identification_customer ON identification(customer_id);
-- CREATE INDEX IF NOT EXISTS idx_identification_number ON identification(identification_number);
-- CREATE INDEX IF NOT EXISTS idx_identification_kyc_status ON identification(kyc_status);
--
-- -- Event audit indexes
-- CREATE INDEX IF NOT EXISTS idx_event_entity ON event(entity_name, entity_id);
-- CREATE INDEX IF NOT EXISTS idx_event_time ON event(event_time);
-- CREATE INDEX IF NOT EXISTS idx_event_correlation ON event(correlation_id);

-- ============================================
-- COMPLETION MESSAGE
-- ============================================

SELECT 'Customer Master Database initialized successfully!' AS message;