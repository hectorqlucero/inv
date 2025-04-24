(ns sk.handlers.reportes.ventas.controller
  (:require
   [sk.handlers.reportes.ventas.view :refer [ventas-view]]
   [sk.handlers.reportes.ventas.model :refer [get-ventas]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]])
  (:import [java.time LocalDate]
           [java.time.format DateTimeFormatter]
           [java.util Locale]))

(defn ventas
  [_]
  (let [date (LocalDate/now)
        formatter (DateTimeFormatter/ofPattern "MMMM" (Locale. "es" "MX"))
        title (str "Ventas de " (.format formatter date))
        ok (get-session-id)
        js nil
        rows (get-ventas)
        content (ventas-view title rows)]
    (application title ok js content)))