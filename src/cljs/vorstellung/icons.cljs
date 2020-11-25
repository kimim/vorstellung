(ns vorstellung.icons
  (:require ["@material-ui/core" :as m]
            ["@material-ui/icons" :as icons]))

(defn page []
  (let [icons (goog.object/getValues icons)
        count (goog.object/getCount icons)]
    [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
     [:div
      [:> m/Grid {:container true :spacing 3}
       (for [i (range 90)]
         (let [icon (aget icons (rand-int count))]
           [:> m/Grid {:item true :xs 2}
            [:> m/Paper
             [:div
              [:> icon]
              [:p (aget icon "displayName")]]]]))]]]))
