(ns vorstellung.icons.icons
  (:require
   ["@material-ui/core" :as m]
   ;; https://material-ui.com/components/material-icons/
   ["@material-ui/icons" :as icons]))

(defn page []
  (let [icons (goog.object/getValues icons)
        count (goog.object/getCount icons)]
    [:> m/Grid {:container true :spacing 3}
     (for [i (range 90)]
       (let [icon (aget icons (rand-int count))]
         ;; Each child in a list should have a unique "key" prop.
         ;; https://reactjs.org/docs/lists-and-keys.html#keys
         ^{:key i}
         [:> m/Grid {:item true :xs 2}
          [:> m/Paper
           [:div
            [:> icon]
            [:p (aget icon "displayName")]]]]))]))
