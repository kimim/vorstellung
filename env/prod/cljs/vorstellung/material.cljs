(ns vorstellung.material
  (:require
   [vorstellung.material.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
