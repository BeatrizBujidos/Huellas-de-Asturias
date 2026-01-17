-- ============================================
-- Script: 02_constraints.sql
-- Descripción: Relaciones y restricciones entre tablas
-- ============================================

-- Usar la base de datos
USE arte_asturiano;

-- Relación OBRA -> ARTISTA
ALTER TABLE obras
ADD CONSTRAINT fk_obra_artista 
FOREIGN KEY (id_artista) 
REFERENCES artistas(id) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Relación OBRA -> MUSEO
ALTER TABLE obras
ADD CONSTRAINT fk_obra_museo 
FOREIGN KEY (id_museo) 
REFERENCES museos(id) 
ON DELETE SET NULL 
ON UPDATE CASCADE;

-- Relación OBRA -> EPOCA
ALTER TABLE obras
ADD CONSTRAINT fk_obra_epoca 
FOREIGN KEY (id_epoca) 
REFERENCES epocas(id) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Relación MONUMENTO -> EPOCA
ALTER TABLE monumentos
ADD CONSTRAINT fk_monumento_epoca 
FOREIGN KEY (id_epoca) 
REFERENCES epocas(id) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Restricción para fechas de artista (fecha_muerte debe ser >= fecha_nacimiento si ambas existen)
DELIMITER //
CREATE TRIGGER before_artista_insert
BEFORE INSERT ON artistas
FOR EACH ROW
BEGIN
    IF NEW.fecha_muerte IS NOT NULL AND NEW.fecha_nacimiento IS NOT NULL THEN
        IF NEW.fecha_muerte < NEW.fecha_nacimiento THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de muerte no puede ser anterior a la fecha de nacimiento';
        END IF;
    END IF;
END//

CREATE TRIGGER before_artista_update
BEFORE UPDATE ON artistas
FOR EACH ROW
BEGIN
    IF NEW.fecha_muerte IS NOT NULL AND NEW.fecha_nacimiento IS NOT NULL THEN
        IF NEW.fecha_muerte < NEW.fecha_nacimiento THEN
            SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'La fecha de muerte no puede ser anterior a la fecha de nacimiento';
        END IF;
    END IF;
END//
DELIMITER ;

-- Mensaje de confirmación
SELECT 'Restricciones y relaciones aplicadas exitosamente.' AS Mensaje;