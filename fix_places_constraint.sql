-- Fix the places table type constraint to match Java enum values
-- Run this in your PostgreSQL database

-- Drop the existing incorrect constraint
ALTER TABLE places DROP CONSTRAINT IF EXISTS places_type_check;

-- Add the correct constraint matching PlaceType enum
ALTER TABLE places ADD CONSTRAINT places_type_check 
CHECK (type IN ('RESTAURANT', 'GYM', 'HOTEL', 'SPORTS', 'CAFE'));

-- Verify the constraint was added
SELECT conname, pg_get_constraintdef(oid) 
FROM pg_constraint 
WHERE conrelid = 'places'::regclass AND conname = 'places_type_check';
