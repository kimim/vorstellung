(ns vorstellung.login
  (:require [vorstellung.login.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
