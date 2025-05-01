(ns sk.handlers.reportes.inexistencia.controller
  (:require
   [sk.handlers.reportes.inexistencia.model :refer [get-inexistencia]]
   [sk.handlers.reportes.inexistencia.view :refer [inexistencia-view]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn inexistencia [_]
  (let [title "Inexistencia"
        ok (get-session-id)
        js nil
        rows (get-inexistencia)
        content (inexistencia-view title rows)]
    (application title ok js content)))

