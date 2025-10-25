-- mdm_schema.material definition

CREATE DATABASE mdm;

CREATE SCHEMA IF NOT EXISTS sequences;
CREATE SCHEMA IF NOT EXISTS mdm_schema;

-- Drop table

-- DROP TABLE mdm_schema.material;

CREATE SEQUENCE sequences.material_externalid_seq
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE mdm_schema.material (
	material_id uuid DEFAULT gen_random_uuid() NOT NULL,
	external_id varchar(50) DEFAULT (((('MAT-'::text || EXTRACT(year FROM CURRENT_TIMESTAMP)::text) || '-'::text) || lpad(nextval('sequences.material_externalid_seq'::regclass)::text, 4, '0'::text))) NOT NULL,
	material_name varchar(100) NOT NULL,
	material_rate_per_pack numeric(18, 3) NOT NULL,
	material_pack_size numeric(18, 3) NOT NULL,
	material_uom_id uuid NOT NULL,
	material_type_id uuid NULL,
	material_manufacturer_id uuid NULL,
	material_vendor_id uuid NULL,
	material_desc varchar(255) NULL,
	is_active bool DEFAULT true NOT NULL,
	created_by uuid NOT NULL,
	created_on timestamp DEFAULT now() NOT NULL,
	updated_by uuid NULL,
	updated_on timestamp DEFAULT now() NOT NULL,
	CONSTRAINT material_external_id_key UNIQUE (external_id),
	CONSTRAINT material_pkey PRIMARY KEY (material_id)
);
CREATE UNIQUE INDEX uq_material_name_active ON mdm_schema.material USING btree (lower((material_name)::text)) WHERE (is_active = true);


-- mdm_schema.material_manufacturer definition

-- Drop table

-- DROP TABLE mdm_schema.material_manufacturer;

CREATE SEQUENCE sequences.material_manufacturer_externalid_seq
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE mdm_schema.material_manufacturer (
	material_manufacturer_id uuid DEFAULT gen_random_uuid() NOT NULL,
	external_id varchar(50) DEFAULT (((('MMF-'::text || EXTRACT(year FROM CURRENT_TIMESTAMP)::text) || '-'::text) || lpad(nextval('sequences.material_manufacturer_externalid_seq'::regclass)::text, 4, '0'::text))) NOT NULL,
	material_manufacturer_name varchar(100) NOT NULL,
	material_manufacturer_contact_person varchar(100) NULL,
	material_manufacturer_contact_number varchar(15) NULL,
	material_manufacturer_email varchar(100) NULL,
	material_manufacturer_gst varchar(15) NULL,
	material_manufacturer_desc varchar(255) NULL,
	is_active bool DEFAULT true NOT NULL,
	created_by uuid NOT NULL,
	created_on timestamp DEFAULT now() NOT NULL,
	updated_by uuid NULL,
	updated_on timestamp DEFAULT now() NOT NULL,
	CONSTRAINT material_manufacturer_external_id_key UNIQUE (external_id),
	CONSTRAINT material_manufacturer_pkey PRIMARY KEY (material_manufacturer_id)
);
CREATE UNIQUE INDEX uq_material_manufacturer_name_active ON mdm_schema.material_manufacturer USING btree (lower((material_manufacturer_name)::text)) WHERE (is_active = true);


-- mdm_schema.material_type definition

-- Drop table

-- DROP TABLE mdm_schema.material_type;

CREATE SEQUENCE sequences.material_type_externalid_seq
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE mdm_schema.material_type (
	material_type_id uuid DEFAULT gen_random_uuid() NOT NULL,
	external_id varchar(50) DEFAULT (((('MTY-'::text || EXTRACT(year FROM CURRENT_TIMESTAMP)::text) || '-'::text) || lpad(nextval('sequences.material_type_externalid_seq'::regclass)::text, 4, '0'::text))) NOT NULL,
	material_type_name varchar(100) NOT NULL,
	material_type_desc varchar(255) NULL,
	is_active bool DEFAULT true NOT NULL,
	created_by uuid NOT NULL,
	created_on timestamp DEFAULT now() NOT NULL,
	updated_by uuid NULL,
	updated_on timestamp DEFAULT now() NOT NULL,
	CONSTRAINT material_type_external_id_key UNIQUE (external_id),
	CONSTRAINT material_type_pkey PRIMARY KEY (material_type_id)
);
CREATE UNIQUE INDEX uq_material_type_name_active ON mdm_schema.material_type USING btree (lower((material_type_name)::text)) WHERE (is_active = true);


-- mdm_schema.material_vendor definition

-- Drop table

-- DROP TABLE mdm_schema.material_vendor;

CREATE SEQUENCE sequences.material_vendor_externalid_seq
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE mdm_schema.material_vendor (
	material_vendor_id uuid DEFAULT gen_random_uuid() NOT NULL,
	external_id varchar(50) DEFAULT (((('MVN-'::text || EXTRACT(year FROM CURRENT_TIMESTAMP)::text) || '-'::text) || lpad(nextval('sequences.material_vendor_externalid_seq'::regclass)::text, 4, '0'::text))) NOT NULL,
	material_vendor_name varchar(100) NOT NULL,
	material_vendor_contact_person varchar(100) NULL,
	material_vendor_contact_number varchar(15) NULL,
	material_vendor_email varchar(100) NULL,
	material_vendor_gst varchar(15) NULL,
	material_vendor_desc varchar(255) NULL,
	is_active bool DEFAULT true NOT NULL,
	created_by uuid NOT NULL,
	created_on timestamp DEFAULT now() NOT NULL,
	updated_by uuid NULL,
	updated_on timestamp DEFAULT now() NOT NULL,
	CONSTRAINT material_vendor_external_id_key UNIQUE (external_id),
	CONSTRAINT material_vendor_pkey PRIMARY KEY (material_vendor_id)
);
CREATE UNIQUE INDEX uq_material_vendor_name_active ON mdm_schema.material_vendor USING btree (lower((material_vendor_name)::text)) WHERE (is_active = true);


-- mdm_schema.unit_of_measurement definition

-- Drop table

-- DROP TABLE mdm_schema.unit_of_measurement;

CREATE SEQUENCE sequences.unit_of_measurement_externalid_seq
    START 1
    INCREMENT 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE TABLE mdm_schema.unit_of_measurement (
	uom_id uuid DEFAULT gen_random_uuid() NOT NULL,
	external_id varchar(50) DEFAULT (((('UOM-'::text || EXTRACT(year FROM CURRENT_TIMESTAMP)::text) || '-'::text) || lpad(nextval('sequences.unit_of_measurement_externalid_seq'::regclass)::text, 4, '0'::text))) NOT NULL,
	uom_name varchar(100) NOT NULL,
	uom_symbol varchar(20) NOT NULL,
	uom_desc varchar(255) NULL,
	is_active bool DEFAULT true NOT NULL,
	created_by uuid NOT NULL,
	created_on timestamp DEFAULT now() NOT NULL,
	updated_by uuid NULL,
	updated_on timestamp DEFAULT now() NOT NULL,
	CONSTRAINT unit_of_measurement_external_id_key UNIQUE (external_id),
	CONSTRAINT uom_pkey PRIMARY KEY (uom_id)
);
CREATE UNIQUE INDEX uq_uom_name_active ON mdm_schema.unit_of_measurement USING btree (lower((uom_name)::text)) WHERE (is_active = true);
CREATE UNIQUE INDEX uq_uom_symbol_active ON mdm_schema.unit_of_measurement USING btree (lower((uom_symbol)::text)) WHERE (is_active = true);



-- referred core_schema.
-- Ensure the schema exists
CREATE SCHEMA IF NOT EXISTS core_schema;

-- Employee table
CREATE TABLE core_schema.employee (
    employee_id uuid DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    email varchar(150) UNIQUE,
    is_active boolean DEFAULT true NOT NULL,
    created_on timestamp DEFAULT now() NOT NULL,
    created_by uuid NULL,  -- could reference another employee if needed
    updated_on timestamp DEFAULT now(),
    updated_by uuid NULL
);

-- Optional: unique index on email for active employees
CREATE UNIQUE INDEX uq_employee_email_active
    ON core_schema.employee (lower(email))
    WHERE (is_active = true);

-- Enable pgcrypto if not already
CREATE EXTENSION IF NOT EXISTS pgcrypto;

-- referred core_schema end.

-- mdm_schema.material foreign keys

ALTER TABLE mdm_schema.material ADD CONSTRAINT fk_material_createdby_employee FOREIGN KEY (created_by) REFERENCES core_schema.employee(employee_id);
ALTER TABLE mdm_schema.material ADD CONSTRAINT fk_material_materialmanufacturer FOREIGN KEY (material_manufacturer_id) REFERENCES mdm_schema.material_manufacturer(material_manufacturer_id);
ALTER TABLE mdm_schema.material ADD CONSTRAINT fk_material_materialtype FOREIGN KEY (material_type_id) REFERENCES mdm_schema.material_type(material_type_id);
ALTER TABLE mdm_schema.material ADD CONSTRAINT fk_material_materialvendor FOREIGN KEY (material_vendor_id) REFERENCES mdm_schema.material_vendor(material_vendor_id);
ALTER TABLE mdm_schema.material ADD CONSTRAINT fk_material_uom FOREIGN KEY (material_uom_id) REFERENCES mdm_schema.unit_of_measurement(uom_id);
ALTER TABLE mdm_schema.material ADD CONSTRAINT fk_material_updatedby_employee FOREIGN KEY (updated_by) REFERENCES core_schema.employee(employee_id);


-- mdm_schema.material_manufacturer foreign keys

ALTER TABLE mdm_schema.material_manufacturer ADD CONSTRAINT fk_materialmanufacturer_createdby_employee FOREIGN KEY (created_by) REFERENCES core_schema.employee(employee_id);
ALTER TABLE mdm_schema.material_manufacturer ADD CONSTRAINT fk_materialmanufacturer_updatedby_employee FOREIGN KEY (updated_by) REFERENCES core_schema.employee(employee_id);


-- mdm_schema.material_type foreign keys

ALTER TABLE mdm_schema.material_type ADD CONSTRAINT fk_materialtype_createdby_employee FOREIGN KEY (created_by) REFERENCES core_schema.employee(employee_id);
ALTER TABLE mdm_schema.material_type ADD CONSTRAINT fk_materialtype_updatedby_employee FOREIGN KEY (updated_by) REFERENCES core_schema.employee(employee_id);


-- mdm_schema.material_vendor foreign keys

ALTER TABLE mdm_schema.material_vendor ADD CONSTRAINT fk_materialvendor_createdby_employee FOREIGN KEY (created_by) REFERENCES core_schema.employee(employee_id);
ALTER TABLE mdm_schema.material_vendor ADD CONSTRAINT fk_materialvendor_updatedby_employee FOREIGN KEY (updated_by) REFERENCES core_schema.employee(employee_id);


-- mdm_schema.unit_of_measurement foreign keys

ALTER TABLE mdm_schema.unit_of_measurement ADD CONSTRAINT fk_uom_createdby_employee FOREIGN KEY (created_by) REFERENCES core_schema.employee(employee_id);
ALTER TABLE mdm_schema.unit_of_measurement ADD CONSTRAINT fk_uom_updatedby_employee FOREIGN KEY (updated_by) REFERENCES core_schema.employee(employee_id);
