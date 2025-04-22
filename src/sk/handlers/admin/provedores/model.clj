(ns sk.handlers.admin.provedores.model
  (:require
   [sk.models.crud :refer [db Query]]))

(def get-provedores-sql
  (str
   "
SELECT *
FROM provedores
"))

(defn get-provedores
  []
  (Query db get-provedores-sql))

(def get-provedores-id-sql
  (str
   "
SELECT *
FROM provedores
WHERE id = ?
"))

(defn get-provedores-id
  [id]
  (first (Query db [get-provedores-id-sql id])))

