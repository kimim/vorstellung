(ns vorstellung.echarts.menu
  (:require
   ;; require only submodules, reduce 7M in dev, 4M in prod
   ;; https://clojure.atlassian.net/browse/CLJS-2376 about :default
   ["@mui/icons-material/ViewComfy" :default ViewComfy]
   [vorstellung.common.menu :as menu]))

(defn menu []
  [menu/item "/echarts/" ViewComfy       "Charts"])
