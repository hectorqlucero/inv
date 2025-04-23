(ns sk.handlers.icambios.model
  (:require
   [sk.models.crud :refer [db Query]]))

(def get-icambios-sql
  (str
   "
SELECT icambios.*,
    provedores.nombre as provedor,
    productos.nombre as producto,
    (icambios.cantidad_nueva - icambios.cantidad_anterior) as movimiento
FROM icambios
    JOIN inventario on inventario.id = icambios.inventario_id
    JOIN provedores on provedores.id = inventario.provedor_id
    JOIN productos on productos.id = inventario.producto_id
"))

(defn get-icambios
  []
  (Query db get-icambios-sql))

(def get-icambios-id-sql
  (str
   "
SELECT *
FROM icambios
WHERE id = ?
"))

(defn get-icambios-id
  [id]
  (first (Query db [get-icambios-id-sql id])))

(comment
  (get-icambios))