CREATE TRIGGER inventario_cambios
AFTER UPDATE ON inventario
FOR EACH ROW
BEGIN
    IF OLD.cantidad <> NEW.cantidad THEN
        INSERT INTO icambios (
            inventario_id,
            cantidad_anterior,
            cantidad_nueva,
            fecha
        )
        VALUES (
            OLD.id,
            OLD.cantidad,
            NEW.cantidad,
            NOW()
        );
    END IF;
END;
