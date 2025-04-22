(ns sk.handlers.admin.productos.model
  (:require
   [sk.models.crud :refer [db Query]]))

(def get-productos-sql
  (str
   "
SELECT *,
    CONCAT('$', FORMAT(precio,2)) as precio_formatted
FROM productos
"))

(defn get-productos
  []
  (Query db get-productos-sql))

(def get-productos-id-sql
  (str
   "
SELECT *
FROM productos
WHERE id = ?
"))

(defn get-productos-id
  [id]
  (first (Query db [get-productos-id-sql id])))

