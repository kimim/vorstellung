(ns vorstellung.echarts.board
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]
   [re-echarts.core :refer [ECharts]]
   [vorstellung.common.utils :as utils]))

(defn distribution []
  [:> ECharts
      {:style {:width "800px" :height "600px"}
       :theme "dark"
       :option
       {:title {:text "Energy Consumption"}
        :dataset {:dimention [:Week :Value]
                  :source [{:Week "Coal" :Value 820} {:Week "Petro" :Value 932} {:Week "Gas" :Value 901}
                           {:Week "New Energy" :Value 934}]}
        :xAxis {:type "category"}
        :yAxis {:type "value"}
        :series [{:type "line"
                  :smooth true}]}}])

(defn capacity []
  [:> ECharts
      {:style {:width "800px" :height "600px"}
       :theme "dark"
       :option
       {:title {:text "Energy Generation"}
        :dataset {:dimention [:Week :Value]
                  :source [{:Week "Mon" :Value 820} {:Week "Tue" :Value 932} {:Week "Wed" :Value 901}
                           {:Week "Thu" :Value 934} {:Week "Fri" :Value 1220} {:Week "Sat" :Value 820}
                           {:Week "Sun" :Value 990}]}
        :xAxis {:type "category"}
        :yAxis {:type "value"}
        :series [{:type "line"
                  :smooth true}]}}])

(defn page []
  [:> m/Container {:max-width "lg" :disableGutters true}
   [:> m/Grid {:container true :spacing 3}
    [:> m/Grid {:item true :xs 4 :sm 4 :lg 4}
     [capacity]]
    [:> m/Grid {:item true :xs 4 :sm 4 :lg 4}
     [capacity]]
    [:> m/Grid {:item true :xs 4 :sm 4 :lg 4}
     [capacity]]]])

(defn tool []
  [:div])
