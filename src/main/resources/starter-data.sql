INSERT INTO core_schema.employee (employee_id, employee_name)
VALUES ('a1b2c3d4-e5f6-7890-1234-567890abcdef', 'System User');

INSERT INTO mdm_schema.unit_of_measurement (uom_id, external_id, uom_name, uom_symbol, uom_desc, is_active, created_by, created_on, updated_on)
VALUES ('11111111-1111-1111-1111-111111111111', 'UOM-2025-0001', 'Kilogram', 'kg', 'Standard unit of mass', TRUE, 'a1b2c3d4-e5f6-7890-1234-567890abcdef', NOW(), NOW());
INSERT INTO mdm_schema.unit_of_measurement (uom_id, external_id, uom_name, uom_symbol, uom_desc, is_active, created_by, created_on, updated_on)
VALUES ('22222222-2222-2222-2222-222222222222', 'UOM-2025-0002', 'Liter', 'L', 'Standard unit of volume', TRUE, 'a1b2c3d4-e5f6-7890-1234-567890abcdef', NOW(), NOW());

INSERT INTO mdm_schema.material_type (material_type_id, external_id, material_type_name, material_type_desc, is_active, created_by, created_on, updated_on)
VALUES ('33333333-3333-3333-3333-333333333333', 'MTY-2025-0001', 'Raw Material', 'Materials in their unprocessed state', TRUE, 'a1b2c3d4-e5f6-7890-1234-567890abcdef', NOW(), NOW());

INSERT INTO mdm_schema.material_manufacturer (material_manufacturer_id, external_id, material_manufacturer_name, material_manufacturer_contact_person, material_manufacturer_contact_number, material_manufacturer_email, material_manufacturer_gst, material_manufacturer_desc, is_active, created_by, created_on, updated_on)
VALUES ('44444444-4444-4444-4444-444444444444', 'MMF-2025-0001', 'Acme Corp', 'John Doe', '1234567890', 'john.doe@acmecorp.com', 'GSTIN12345', 'Leading manufacturer of industrial goods', TRUE, 'a1b2c3d4-e5f6-7890-1234-567890abcdef', NOW(), NOW());

INSERT INTO mdm_schema.material_vendor (material_vendor_id, external_id, material_vendor_name, material_vendor_contact_person, material_vendor_contact_number, material_vendor_email, material_vendor_gst, material_vendor_desc, is_active, created_by, created_on, updated_on)
VALUES ('55555555-5555-5555-5555-555555555555', 'MVN-2025-0001', 'Global Suppliers', 'Jane Smith', '0987654321', 'jane.smith@globalsuppliers.com', 'GSTIN54321', 'Reliable supplier of various materials', TRUE, 'a1b2c3d4-e5f6-7890-1234-567890abcdef', NOW(), NOW());