(ns sk.handlers.reportes.hoy.controller
  (:require [sk.layout :refer [application]]
            [sk.models.util :refer [get-session-id]]
            [sk.handlers.reportes.hoy.model :refer [get-hoy]]
            [sk.handlers.reportes.hoy.view :refer [hoy-view]]))

(defn hoy [_]
  (let [title "Hoy"
        ok (get-session-id)
        js nil
        rows (get-hoy)
        content (hoy-view title rows)]
    (application title ok js content)))
