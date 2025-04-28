(ns sk.handlers.niveles.model
(:require [sk.models.crud :refer [Query db]]
[clojure.string :as st]))

(def get-niveles-sql
(str
"
SELECT *
FROM niveles
"
))

(defn get-niveles
[]
(Query db get-niveles-sql))

