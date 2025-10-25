# Material Management CRUD API - Technical Assessment

## Overview
Build a complete CRUD API for the Material Management module using Spring Boot. This assessment evaluates your ability to work with normalized database schemas and implement production-ready REST APIs.

## Setup using Docker
### Postgres database
`docker run --name postgres-db -e POSTGRES_PASSWORD=admin123 -p 5432:5432 -d postgres:17.6`

Execute the ddl from `src/main/resources/schema.sql` and insert queries from `src/main/resources/starter-data.sql`

### Spring Boot startup
Add VM arguments `-Duser.timezone=UTC` 


#### Very Optional (Bonus Points):
- Pagination and sorting for GET ALL endpoint
- Search/filter functionality
- Integration tests
- API documentation (Swagger/OpenAPI)
- Optimistic locking for concurrent updates

## Deliverables
1.  Complete Spring Boot application source code
2.  README with setup instructions
3.  Postman collection or equivalent for API testing
4.  Brief documentation of design decisions

## Sample Data for Testing

Below are sample JSON payloads for creating the necessary entities. Remember that `createdBy` and `updatedBy` are handled by the auditing mechanism, so you don't need to include them in your requests. The `externalId` is also auto-generated.

**Note:** The `AuditingConfig` currently uses a hardcoded UUID (`a1b2c3d4-e5f6-7890-1234-567890abcdef`) for `createdBy` and `updatedBy`. In a real application, this would come from the authenticated user's context.

### 1. Create Unit of Measurement (UOM)
**POST /api/uoms** (You would need to implement UOM CRUD endpoints similarly to Material, but for testing Material, you can manually insert into the database or extend the API.)

Example UOM IDs (for reference in Material creation):
*   `"uomId": "11111111-1111-1111-1111-111111111111"` (e.g., for 'Kilogram')
*   `"uomId": "22222222-2222-2222-2222-222222222222"` (e.g., for 'Liter')

### 2. Create Material Type
**POST /api/material-types** (Similar to UOM, you'd need to implement these endpoints or manually insert.)

Example Material Type ID:
*   `"materialTypeId": "33333333-3333-3333-3333-333333333333"` (e.g., for 'Raw Material')

### 3. Create Material Manufacturer
**POST /api/material-manufacturers** (Similar to UOM, you'd need to implement these endpoints or manually insert.)

Example Material Manufacturer ID:
*   `"materialManufacturerId": "44444444-4444-4444-4444-444444444444"` (e.g., for 'Acme Corp')

### 4. Create Material Vendor
**POST /api/material-vendors** (Similar to UOM, you'd need to implement these endpoints or manually insert.)

Example Material Vendor ID:
*   `"materialVendorId": "55555555-5555-5555-5555-555555555555"` (e.g., for 'Global Suppliers')

### 5. Create Material
**POST /api/materials**

```json
{
  "materialName": "Steel Bar 10mm",
  "materialRatePerPack": 150.75,
  "materialPackSize": 10.0,
  "materialUomId": "11111111-1111-1111-1111-111111111111", 
  "materialTypeId": "33333333-3333-3333-3333-333333333333",
  "materialManufacturerId": "44444444-4444-4444-4444-444444444444",
  "materialVendorId": "55555555-5555-5555-5555-555555555555",
  "materialDesc": "High-grade steel bar for construction"
}
```

### 6. Update Material
**PUT /api/materials/{id}** (Replace `{id}` with an actual Material ID from a created material)

```json
{
  "materialName": "Steel Bar 12mm",
  "materialRatePerPack": 180.50,
  "materialPackSize": 12.0,
  "materialDesc": "Updated: High-grade steel bar for heavy construction"
}
```

### 7. Get All Active Materials
**GET /api/materials**

### 8. Get Material by ID
**GET /api/materials/{id}** (Replace `{id}` with an actual Material ID)

### 9. Soft Delete Material
**DELETE /api/materials/{id}** (Replace `{id}` with an actual Material ID)
