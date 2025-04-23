(ns sk.handlers.icambios.controller
  (:require
   [sk.handlers.icambios.model :refer [get-icambios]]
   [sk.handlers.icambios.view :refer [icambios-view]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]]))

(defn icambios [_]
  (let [title "Icambios"
        ok (get-session-id)
        js nil
        rows (get-icambios)
        content (icambios-view title rows)]
    (application title ok js content)))

