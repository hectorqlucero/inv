(ns sk.handlers.reportes.reordenar.controller
  (:require [sk.layout :refer [application]]
            [sk.models.util :refer [get-session-id]]
            [sk.handlers.reportes.reordenar.model :refer [get-reordenar]]
            [sk.handlers.reportes.reordenar.view :refer [reordenar-view]]))

(defn reordenar [_]
  (let [title "Reordenar"
        ok (get-session-id)
        js nil
        rows (get-reordenar)
        content (reordenar-view title rows)]
    (application title ok js content)))
