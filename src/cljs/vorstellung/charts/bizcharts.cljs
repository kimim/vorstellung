(ns vorstellung.charts.bizcharts
  (:require
   [reagent.core :as r]
   ["@material-ui/core" :as m]
   ;; https://github.com/alibaba/BizCharts
   [bizcharts :as bc]))

(def data
  [{"city" "Tokyo", "temperature" 7, "month" "Jan"}
   {"city" "London", "temperature" 3.9, "month" "Jan"}
   {"city" "Tokyo", "temperature" 13, "month" "Feb"}
   {"city" "London", "temperature" 4.2, "month" "Feb"}
   {"city" "Tokyo", "temperature" 16.5, "month" "Mar"}
   {"city" "London", "temperature" 5.7, "month" "Mar"}
   {"city" "Tokyo", "temperature" 14.5, "month" "Apr"}
   {"city" "London", "temperature" 8.5, "month" "Apr"}
   {"city" "Tokyo", "temperature" 10, "month" "May"}
   {"city" "London", "temperature" 11.9, "month" "May"}
   {"city" "Tokyo", "temperature" 7.5, "month" "Jun"}
   {"city" "London", "temperature" 15.2, "month" "Jun"}
   {"city" "Tokyo", "temperature" 9.2, "month" "Jul"}
   {"city" "London", "temperature" 17, "month" "Jul"}
   {"city" "Tokyo", "temperature" 14.5, "month" "Aug"}
   {"city" "London", "temperature" 16.6, "month" "Aug"}
   {"city" "Tokyo", "temperature" 9.3, "month" "Sep"}
   {"city" "London", "temperature" 14.2, "month" "Sep"}
   {"city" "Tokyo", "temperature" 8.3, "month" "Oct"}
   {"city" "London", "temperature" 10.3, "month" "Oct"}
   {"city" "Tokyo", "temperature" 8.9, "month" "Nov"}
   {"city" "London", "temperature" 5.6, "month" "Nov"}
   {"city" "Tokyo", "temperature" 5.6, "month" "Dec"}
   {"city" "London", "temperature" 9.8, "month" "Dec"}])

(defn city []
  [:> bc/Chart {:data (clj->js data) :autoFit true :height 300}
   [:> bc/LineAdvance {:shape "smooth"
                       :point true
                       :area true
                       :position "month*temperature"
                       :color "city"}]])

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> m/Grid {:container true :spacing 3}
     [:> m/Grid {:item true :xs 12 :sm 6 :lg 3}
      [:> m/Paper
       [city]]]]]])
