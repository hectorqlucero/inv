(ns sk.routes.proutes
  (:require
   [sk.handlers.reportes.tipo.controller :as tipo-reporte]
   [sk.handlers.reportes.inexistencia.controller :as inexistencia-reporte]
   [sk.handlers.reportes.maximo.controller :as maximo-reporte]
   [sk.handlers.reportes.ventas.controller :as ventas-reporte]
   [sk.handlers.reportes.hoy.controller :as hoy-reporte]
   [sk.handlers.reportes.valor.controller :as valor-reporte]
   [sk.handlers.reportes.reordenar.controller :as reordenar-reporte]
   [sk.handlers.reportes.movimientos.controller :as movimientos-reporte]
   [sk.handlers.reportes.niveles.controller :as niveles-reporte]
   [sk.handlers.admin.movimientos.controller :as movimientos-controller]
   [sk.handlers.icambios.controller :as icambios-dashboard]
   [sk.handlers.admin.inventario.controller :as inventario-controller]
   [sk.handlers.admin.productos.controller :as productos-controller]
   [sk.handlers.admin.provedores.controller :as provedores-controller]
   [compojure.core :refer [defroutes GET POST]]
   [sk.handlers.admin.users.controller :as users-controller]
   [sk.handlers.users.controller :as users-dashboard]))

(defroutes proutes
  (GET "/reportes/tipo" params [] (tipo-reporte/tipo params))
  (GET "/reportes/inexistencia" params [] (inexistencia-reporte/inexistencia params))
  (GET "/reportes/maximo" params [] (maximo-reporte/maximo params))
  (GET "/reportes/ventas" params [] (ventas-reporte/ventas params))
  (GET "/reportes/hoy" params [] (hoy-reporte/hoy params))
  (GET "/reportes/valor" params [] (valor-reporte/valor params))
  (GET "/reportes/reordenar" params [] (reordenar-reporte/reordenar params))
  (GET "/reportes/movimientos" params [] (movimientos-reporte/movimientos params))
  (GET "/reportes/niveles" params [] (niveles-reporte/niveles params))
  (GET "/admin/movimientos" params [] (movimientos-controller/movimientos params))
  (GET "/admin/movimientos/edit/:id" [id] (movimientos-controller/movimientos-edit id))
  (POST "/admin/movimientos/save" params [] (movimientos-controller/movimientos-save params))
  (GET "/admin/movimientos/add" params [] (movimientos-controller/movimientos-add params))
  (GET "/admin/movimientos/delete/:id" [id] (movimientos-controller/movimientos-delete id))
  (GET "/inventario/maximo/:id" [id] (movimientos-controller/get-maximo id))
  (GET "/icambios" params [] (icambios-dashboard/icambios params))
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