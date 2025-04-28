(ns sk.handlers.reportes.maximo.controller
  (:require
   [sk.handlers.reportes.maximo.view :refer [maximo-view]]
   [sk.handlers.reportes.maximo.model :refer [get-maximo]]
   [sk.layout :refer [application]]
   [sk.models.util :refer [get-session-id]])
  (:import [java.time LocalDate]
           [java.time.format DateTimeFormatter]
           [java.util Locale]))

(defn maximo
  [_]
  (let [date (LocalDate/now)
        formatter (DateTimeFormatter/ofPattern "MMMM" (Locale. "es" "MX"))
        title (str "Producto mas Vendido en " (.format formatter date))
        ok (get-session-id)
        js nil
        rows (get-maximo)
        content (maximo-view title rows)]
    (application title ok js content)))
