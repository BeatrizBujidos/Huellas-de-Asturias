-- ============================================
-- Script: 02_constraints.sql
-- Descripción: Relaciones y restricciones entre tablas
-- ============================================

-- Usar la base de datos
USE arte_asturiano;

-- Relación OBRA -> ARTISTA
ALTER TABLE obra
ADD CONSTRAINT fk_obra_artista 
FOREIGN KEY (id_artista) 
REFERENCES artista(id_artista) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Relación OBRA -> MUSEO
ALTER TABLE obra
ADD CONSTRAINT fk_obra_museo 
FOREIGN KEY (id_museo) 
REFERENCES museo(id_museo) 
ON DELETE SET NULL 
ON UPDATE CASCADE;

-- Relación OBRA -> EPOCA
ALTER TABLE obra
ADD CONSTRAINT fk_obra_epoca 
FOREIGN KEY (id_epoca) 
REFERENCES epoca(id_epoca) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Relación MONUMENTO -> EPOCA
ALTER TABLE monumento
ADD CONSTRAINT fk_monumento_epoca 
FOREIGN KEY (id_epoca) 
REFERENCES epoca(id_epoca) 
ON DELETE CASCADE 
ON UPDATE CASCADE;

-- Relación EVENTO -> MUSEO (opcional, para exposiciones)
ALTER TABLE evento
ADD CONSTRAINT fk_evento_museo 
FOREIGN KEY (id_museo) 
REFERENCES museo(id_museo) 
ON DELETE SET NULL 
ON UPDATE CASCADE;

-- Restricción para fechas de artista (fecha_muerte debe ser >= fecha_nacimiento si ambas existen)
DELIMITER //
CREATE TRIGGER before_artista_insert
BEFORE INSERT ON artista
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
BEFORE UPDATE ON artista
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

-- Restricción para fechas de eventos
DELIMITER //
CREATE TRIGGER before_evento_insert
BEFORE INSERT ON evento
FOR EACH ROW
BEGIN
    IF NEW.fecha_fin < NEW.fecha_inicio THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de fin no puede ser anterior a la fecha de inicio';
    END IF;
END//

CREATE TRIGGER before_evento_update
BEFORE UPDATE ON evento
FOR EACH ROW
BEGIN
    IF NEW.fecha_fin < NEW.fecha_inicio THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'La fecha de fin no puede ser anterior a la fecha de inicio';
    END IF;
END//
DELIMITER ;

-- Mensaje de confirmación
SELECT 'Restricciones y relaciones aplicadas exitosamente.' AS Mensaje;