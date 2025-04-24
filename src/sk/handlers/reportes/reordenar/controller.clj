(ns sk.handlers.reportes.reordenar.controller
  (:require
   [sk.handlers.reportes.reordenar.view :refer [reordenar-view]]
   [sk.handlers.reportes.reordenar.model :refer [get-reordenar]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn reordenar
  [_]
  (let [title "Reordenar de Inventario"
        ok (get-session-id)
        js nil
        rows (get-reordenar)
        content (reordenar-view title rows)]
    (application title ok js content)))