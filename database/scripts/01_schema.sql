-- ============================================
-- Script: 01_schema.sql
-- Descripción: Creación de tablas
-- ============================================

-- Usar la base de datos
USE arte_asturiano;

-- Tabla: epocas
CREATE TABLE IF NOT EXISTS epocas (
    id_epoca INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(168) NOT NULL,
    descripcion TEXT,
    fecha_inicio INT,
    fecha_fin INT,
    caracteristicas TEXT,
    fecha_creacion_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT = 'Tabla de épocas históricas';

-- Tabla: artistas
CREATE TABLE IF NOT EXISTS artistas (
    id_artista INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellidos VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE,
    fecha_muerte DATE,
    lugar_nacimiento VARCHAR(200),
    biografia TEXT,
    estilo VARCHAR(200),
    imagen VARCHAR(580),
    fecha_creacion_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT = 'Tabla de artistas';

-- Tabla: museos
CREATE TABLE IF NOT EXISTS museos (
    id_museo INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    direccion VARCHAR(580),
    ciudad VARCHAR(200),
    latitud DECIMAL(18,8),
    longitud DECIMAL(11,8),
    horario TEXT,
    web VARCHAR(255),
    imagen VARCHAR(580),
    fecha_creacion_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT = 'Tabla de museos';

-- Tabla: obras
CREATE TABLE IF NOT EXISTS obras (
    id_obra INT AUTO_INCREMENT PRIMARY KEY,
    id_artista INT NOT NULL,
    id_museo INT NULL,
    id_epoca INT NOT NULL,
    titulo VARCHAR(255) NOT NULL,
    fecha_creacion INT,
    tecnica VARCHAR(168),
    descripcion TEXT,
    dimensiones VARCHAR(200),
    fecha_creacion_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT = 'Tabla de obras';

-- Tabla: monumentos
CREATE TABLE IF NOT EXISTS monumentos (
    id_monumento INT AUTO_INCREMENT PRIMARY KEY,
    id_epoca INT NOT NULL,
    nombre VARCHAR(255) NOT NULL,
    fecha_construccion INT,
    descripcion TEXT,
    latitud DECIMAL(18,8),
    longitud DECIMAL(11,8),
    fecha_creacion_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT = 'Tabla de monumentos';

-- Tabla: Imagenes
CREATE TABLE IF NOT EXISTS imagenes (
    id_imagen INT AUTO_INCREMENT PRIMARY KEY,
    url VARCHAR(500) NOT NULL,
    tipo_entidad ENUM ('OBRA', 'MONUMENTO') NOT NULL,
    id_entidad INT NOT NULL,
    es_principal BOOLEAN DEFAULT TRUE,
    orden INT DEFAULT 1, 
    fecha_creacion_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci
COMMENT = 'Tabla de imágenes';

-- Índices para mejorar el rendimiento
CREATE INDEX idx_obra_artista ON obras(id_artista);
CREATE INDEX idx_obra_museo ON obras(id_museo);
CREATE INDEX idx_obra_epoca ON obras(id_epoca);
CREATE INDEX idx_monumento_epoca ON monumentos(id_epoca);
CREATE INDEX idx_artista_nombre ON artistas(nombre);
CREATE INDEX idx_museo_ciudad ON museos(ciudad);
CREATE INDEX idx_epoca_nombre ON epocas(nombre);
CREATE INDEX idx_imagen_entidad on imagenes(tipo_entidad, id_entidad)

-- Índices adicionales para búsquedas frecuentes
CREATE INDEX idx_artista_estilo ON artistas(estilo);
CREATE INDEX idx_monumento_tipo ON monumentos(tipo);
CREATE INDEX idx_obra_titulo ON obras(titulo);

-- Mensaje de confirmación
SELECT 'Tablas creadas exitosamente.' AS Mensaje;