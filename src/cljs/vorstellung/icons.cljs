(ns vorstellung.icons
  (:require ["@material-ui/icons" :as icons  :refer [Apps Mail Cake BugReport]]))

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> Apps]
    [:> Mail]
    [:> Cake]
    [:> BugReport]]])
