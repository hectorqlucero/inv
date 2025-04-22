(ns sk.handlers.admin.inventario.model
  (:require
   [sk.models.crud :refer [db Query]]))

(def get-inventario-sql
  (str
   "
SELECT inventario.*,
   productos.nombre as producto_id_formatted,
   provedores.nombre as provedor_id_formatted
FROM inventario
   JOIN productos on productos.id = inventario.producto_id
   JOIN provedores on provedores.id = inventario.provedor_id
"))

(defn get-inventario
  []
  (Query db get-inventario-sql))

(def get-inventario-id-sql
  (str
   "
SELECT *
FROM inventario
WHERE id = ?
"))

(defn productos-options
  []
  (Query db ["select id as value, nombre as label from productos order by nombre"]))

(defn provedores-options
  []
  (Query db ["select id as value, nombre as label from provedores order by nombre"]))

(defn get-inventario-id
  [id]
  (first (Query db [get-inventario-id-sql id])))

(comment
  (get-inventario)
  (provedores-options)
  (productos-options))
