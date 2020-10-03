(ns vorstellung.grid
  (:require [reagent.core :as r]
            ["@material-ui/core" :as m]))

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> m/Grid {:container true :spacing 3}
     (for [i (range 1 5)]
       [:> m/Grid {:item true :xs 3}
        [:> m/Paper (str i "/" 4)]])
     (for [i (range 1 4)]
       [:> m/Grid {:item true :xs 4}
        [:> m/Paper (str i "/" 3)]])
     (for [i (range 1 3)]
       [:> m/Grid {:item true :xs 6}
        [:> m/Paper (str i "/" 2)]])
     [:> m/Grid {:item true :xs 12}
      [:> m/Paper 1]]]]])
