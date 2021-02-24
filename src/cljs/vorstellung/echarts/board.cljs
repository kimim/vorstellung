(ns vorstellung.echarts.board
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]
   ["@material-ui/data-grid" :as dg]
   [vorstellung.common.utils :as utils]))

(defn page []
  [:> m/Container {:max-width "lg" :disableGutters true}
   [:> m/Grid {:container true :spacing 3}
    [:> m/Grid {:item true :xs 12 :sm 12 :lg 12}]]])

(defn tool []
  [:div])
