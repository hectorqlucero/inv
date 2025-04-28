(ns sk.handlers.reportes.valor.controller
  (:require [sk.layout :refer [application]]
            [sk.models.util :refer [get-session-id]]
            [sk.handlers.reportes.valor.model :refer [get-valor]]
            [sk.handlers.reportes.valor.view :refer [valor-view]]))

(defn valor [_]
  (let [title "Valor"
        ok (get-session-id)
        js nil
        rows (get-valor)
        content (valor-view title rows)]
    (application title ok js content)))
