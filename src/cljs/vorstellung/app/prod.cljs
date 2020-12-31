(ns vorstellung.app.prod
  (:require
   [vorstellung.app.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
