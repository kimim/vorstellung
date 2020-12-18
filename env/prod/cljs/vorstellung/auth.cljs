(ns vorstellung.auth
  (:require
   [vorstellung.auth.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
