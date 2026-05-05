-- ============================================
-- Script: 04_add_english_fields.sql
-- Descripción: Añade campos en inglés para
--              biografías de artistas y
--              descripciones de obras
-- ============================================

USE arte_asturiano;

-- Añadir biografia_en a la tabla artistas
ALTER TABLE artistas
    ADD COLUMN biografia_en TEXT AFTER biografia;

-- Añadir descripcion_en a la tabla obras
ALTER TABLE obras
    ADD COLUMN descripcion_en TEXT AFTER descripcion;

SELECT 'Columnas de traducción añadidas correctamente.' AS Mensaje;
