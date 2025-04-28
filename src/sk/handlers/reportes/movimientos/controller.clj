(ns sk.handlers.reportes.movimientos.controller
  (:require [sk.layout :refer [application]]
            [sk.models.util :refer [get-session-id]]
            [sk.handlers.reportes.movimientos.model :refer [get-movimientos]]
            [sk.handlers.reportes.movimientos.view :refer [movimientos-view]]))

(defn movimientos [_]
  (let [title "Movimientos"
        ok (get-session-id)
        js nil
        rows (get-movimientos)
        content (movimientos-view title rows)]
    (application title ok js content)))
