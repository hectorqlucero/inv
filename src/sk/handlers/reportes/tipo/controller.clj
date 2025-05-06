(ns sk.handlers.reportes.tipo.controller
  (:require
   [sk.handlers.reportes.tipo.model :refer [get-tipo]]
   [sk.handlers.reportes.tipo.view :refer [tipo-view]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn tipo [_]
  (let [title "Movimientos por Tipo"
        ok (get-session-id)
        js nil
        rows (get-tipo)
        content (tipo-view title rows)]
    (application title ok js content)))

