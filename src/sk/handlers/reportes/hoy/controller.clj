(ns sk.handlers.reportes.hoy.controller
  (:require
   [sk.handlers.reportes.hoy.model :refer [get-hoy]]
   [sk.handlers.reportes.hoy.view :refer [hoy-view]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn hoy
  [_]
  (let [title "Ventas Hoy"
        ok (get-session-id)
        js nil
        rows (get-hoy)
        content (hoy-view title rows)]
    (application title ok js content)))