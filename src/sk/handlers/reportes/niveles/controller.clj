(ns sk.handlers.reportes.niveles.controller
  (:require
   [sk.handlers.reportes.niveles.view :refer [niveles-view]]
   [sk.handlers.reportes.niveles.model :refer [get-niveles]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn niveles
  [_]
  (let [title "Niveles de Inventario"
        ok (get-session-id)
        js nil
        rows (get-niveles)
        content (niveles-view title rows)]
    (application title ok js content)))