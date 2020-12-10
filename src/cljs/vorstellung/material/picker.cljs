(ns vorstellung.material.picker
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]))

(defn native-picker []
  [:<>
   [:> m/Typography {:variant "h5" :color "primary"} "Let's pick a time"]
   [:div {:style {:padding "24px 24px 24px 24px"}}
    [:> m/Grid {:container true :spacing 1}
     [:> m/Grid {:item true :xs 4 :sm 4 :lg 4}
      [:> m/Paper
       [:form {:style {:padding "5px 5px 5px 5px"}}
        [:> m/TextField
         {:id "date"
          :label "Holiday"
          :type "date"
          :default-value "2012-12-12"
          :InputLabelProps {:shrink true}}]]]]
     [:> m/Grid {:item true :xs 4 :sm 4 :lg 4}
      [:> m/Paper
       [:form {:style {:padding "5px 5px 5px 5px"}}
        [:> m/TextField
         {:id "datetime-local"
          :label "Appointment"
          :type "datetime-local"
          :default-value "2020-12-12T12:12"
          :InputLabelProps {:shrink true}}]]]]
     [:> m/Grid {:item true :xs 4 :sm 4 :lg 4}
      [:> m/Paper
       [:form {:style {:padding "5px 5px 5px 5px"}}
        [:> m/TextField
         {:id "time"
          :label "Alarm"
          :type "time"
          :default-value "07:00"
          ;; +5*n min is valid
          :inputProps {:step 300}
          ;; shrink the label to up
          :InputLabelProps {:shrink true}}]]]]]]])

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> m/Grid {:container true :spacing 3}
     [:> m/Grid {:item true :xs 6 :sm 3 :lg 3}
      [:> m/Paper
       [native-picker]]]]]])
