(ns sk.handlers.reportes.movimientos.controller
  (:require
   [sk.handlers.reportes.movimientos.view :refer [movimientos-view]]
   [sk.handlers.reportes.movimientos.model :refer [get-movimientos]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn movimientos
  [_]
  (let [title "Movimientos de Inventario"
        ok (get-session-id)
        js nil
        rows (get-movimientos)
        content (movimientos-view title rows)]
    (application title ok js content)))