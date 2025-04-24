(ns sk.routes.proutes
  (:require
   [sk.handlers.reportes.hoy.controller :as hoy-dashboard]
   [sk.handlers.reportes.maximo.controller :as maximo-dashboard]
   [sk.handlers.reportes.ventas.controller :as ventas-dashboard]
   [sk.handlers.reportes.valor.controller :as valor-dashboard]
   [sk.handlers.reportes.reordenar.controller :as reordenar-dashboard]
   [sk.handlers.reportes.movimientos.controller :as movimientos-dashboard]
   [sk.handlers.reportes.niveles.controller :as niveles-dashboard]
   [sk.handlers.icambios.controller :as icambios-dashboard]
   [sk.handlers.admin.movimientos.controller :as movimientos-controller]
   [sk.handlers.admin.inventario.controller :as inventario-controller]
   [sk.handlers.admin.productos.controller :as productos-controller]
   [sk.handlers.admin.provedores.controller :as provedores-controller]
   [compojure.core :refer [defroutes GET POST]]
   [sk.handlers.admin.users.controller :as users-controller]
   [sk.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/reportes/hoy" params [] (hoy-dashboard/hoy params))
  (GET "/reportes/maximo" params [] (maximo-dashboard/maximo params))
  (GET "/reportes/ventas" params [] (ventas-dashboard/ventas params))
  (GET "/reportes/valor" params [] (valor-dashboard/valor params))
  (GET "/reportes/reordenar" params [] (reordenar-dashboard/reordenar params))
  (GET "/reportes/movimientos" params [] (movimientos-dashboard/movimientos params))
  (GET "/reportes/niveles" params [] (niveles-dashboard/niveles params))
  (GET "/icambios" params [] (icambios-dashboard/icambios params))
  (GET "/admin/movimientos" params [] (movimientos-controller/movimientos params))
  (GET "/admin/movimientos/edit/:id" [id] (movimientos-controller/movimientos-edit id))
  (POST "/admin/movimientos/save" params [] (movimientos-controller/movimientos-save params))
  (GET "/admin/movimientos/add" params [] (movimientos-controller/movimientos-add params))
  (GET "/admin/movimientos/delete/:id" [id] (movimientos-controller/movimientos-delete id))
  (GET "/inventario/maximo/:id" [id] (movimientos-controller/get-maximo id))
  (GET "/admin/inventario" params [] (inventario-controller/inventario params))
  (GET "/admin/inventario/edit/:id" [id] (inventario-controller/inventario-edit id))
  (POST "/admin/inventario/save" params [] (inventario-controller/inventario-save params))
  (GET "/admin/inventario/add" params [] (inventario-controller/inventario-add params))
  (GET "/admin/inventario/delete/:id" [id] (inventario-controller/inventario-delete id))
  (GET "/admin/productos" params [] (productos-controller/productos params))
  (GET "/admin/productos/edit/:id" [id] (productos-controller/productos-edit id))
  (POST "/admin/productos/save" params [] (productos-controller/productos-save params))
  (GET "/admin/productos/add" params [] (productos-controller/productos-add params))
  (GET "/admin/productos/delete/:id" [id] (productos-controller/productos-delete id))
  (GET "/admin/provedores" params [] (provedores-controller/provedores params))
  (GET "/admin/provedores/edit/:id" [id] (provedores-controller/provedores-edit id))
  (POST "/admin/provedores/save" params [] (provedores-controller/provedores-save params))
  (GET "/admin/provedores/add" params [] (provedores-controller/provedores-add params))
  (GET "/admin/provedores/delete/:id" [id] (provedores-controller/provedores-delete id))
  (GET "/admin/users" params [] (users-controller/users params))
  (GET "/admin/users/edit/:id" [id] (users-controller/users-edit id))
  (POST "/admin/users/save" params [] (users-controller/users-save params))
  (GET "/admin/users/add" params [] (users-controller/users-add params))
  (GET "/admin/users/delete/:id" [id] (users-controller/users-delete id))
  (GET "/users" params [] (users-dashboard/users params)))