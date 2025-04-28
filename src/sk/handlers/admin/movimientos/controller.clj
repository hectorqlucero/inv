(ns sk.handlers.admin.movimientos.controller
  (:require
   [sk.handlers.admin.movimientos.model :refer [actualizar-inventario
                                                get-inventario-por-producto
                                                get-movimientos
                                                get-movimientos-id]]
   [sk.handlers.admin.movimientos.view :refer [movimientos-add-view
                                               movimientos-edit-view
                                               movimientos-modal-script
                                               movimientos-view]]
   [sk.layout :refer [application error-404]]
   [sk.models.crud :refer [build-form-delete build-form-save]]
   [sk.models.util :refer [get-session-id user-level]]))

(defn get-maximo
  [producto-id]
  (let [maximo (get-inventario-por-producto (Integer. producto-id))]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (str maximo)}))

(defn movimientos [_]
  (let [title "Movimientos"
        ok (get-session-id)
        js nil
        rows (get-movimientos)
        content (movimientos-view title rows)]
    (if
     (or
      (= (user-level) "A")
      (= (user-level) "S"))
      (application title ok js content)
      (application title ok nil "Only <strong>los administrators </strong> can access this option!!!"))))

(defn movimientos-edit
  [id]
  (let [title "Modificar movimientos"
        ok (get-session-id)
        js (movimientos-modal-script)
        row (get-movimientos-id  id)
        rows (get-movimientos)
        content (movimientos-edit-view title row rows)]
    (application title ok js content)))

(defn movimientos-save
  [{params :params}]
  (let [table "movimientos"
        producto-id (Integer. (:producto_id params))
        tipo-movimiento (:tipo_movimiento params)
        cantidad (Integer. (:cantidad params))
        result (actualizar-inventario producto-id tipo-movimiento cantidad)
        result (build-form-save params table)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/movimientos")
      (error-404 "No se pudo procesar el record!" "/admin/movimientos"))))

(defn movimientos-add
  [_]
  (let [title "Crear nuevo movimientos"
        ok (get-session-id)
        js (movimientos-modal-script)
        row nil
        rows (get-movimientos)
        content (movimientos-add-view title row rows)]
    (application title ok js content)))

(defn movimientos-delete
  [id]
  (let [table "movimientos"
        result (build-form-delete table id)]
    (if (= result true)
      (error-404 "Record se processo correctamente!" "/admin/movimientos")
      (error-404 "No se pudo procesar el record!" "/admin/movimientos"))))

(comment
  (get-maximo "1")
  (actualizar-inventario "1" "compra" "1"))
