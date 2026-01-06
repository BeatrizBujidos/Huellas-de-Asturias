-- ============================================
-- Script: 00_database_setup.sql
-- Descripción: Creación de la base de datos
-- Fecha: $(date)
-- ============================================

-- Eliminar base de datos si existe (para desarrollo)
DROP DATABASE IF EXISTS arte_asturiano;

-- Crear base de datos
CREATE DATABASE arte_asturiano 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE arte_asturiano;

-- Mensaje de confirmación
SELECT 'Base de datos "arte_asturiano" creada exitosamente.' AS Mensaje;