(ns sk.handlers.reportes.valor.controller
  (:require
   [sk.handlers.reportes.valor.view :refer [valor-view]]
   [sk.handlers.reportes.valor.model :refer [get-valor]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn valor
  [_]
  (let [title "Valor del Inventario"
        ok (get-session-id)
        js nil
        rows (get-valor)
        content (valor-view title rows)]
    (application title ok js content)))