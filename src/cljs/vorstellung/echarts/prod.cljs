(ns vorstellung.echarts.prod
  (:require
   [vorstellung.echarts.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
