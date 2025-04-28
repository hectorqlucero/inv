(ns sk.handlers.reportes.niveles.controller
  (:require [sk.layout :refer [application]]
            [sk.models.util :refer [get-session-id]]
            [sk.handlers.reportes.niveles.model :refer [get-niveles]]
            [sk.handlers.reportes.niveles.view :refer [niveles-view]]))

(defn niveles [_]
  (let [title "Niveles"
        ok (get-session-id)
        js nil
        rows (get-niveles)
        content (niveles-view title rows)]
    (application title ok js content)))
