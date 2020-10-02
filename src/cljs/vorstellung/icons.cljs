(ns vorstellung.icons
  (:require ["@material-ui/icons" :as ico]))

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> ico/Apps]
    [:> ico/Mail]
    [:> ico/Cake]
    [:> ico/BugReport]]])
