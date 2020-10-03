(ns vorstellung.header
  (:require [reagent.core :as r]
            ["@material-ui/core" :as m]
            ["@material-ui/icons" :as ico
             :refer [Menu ChevronRight ChevronLeft Mail]]))

(defn menu-item [link icon text]
  [:> m/ListItem {:button true :component "a" :href link}
   [:> m/ListItemIcon [:> icon]]
   [:> m/ListItemText {:primary text}]])

(defn navbar []
  (r/with-let [open (r/atom false)]
    [:div
     [:> m/AppBar {:position "fixed"
                   :style {:width (if @open "calc(100% - 240px)" "100%")
                           :z-index 2000}}
      [:> m/Toolbar {:classes {:root "appBar"}}
       [:> m/IconButton {:color "inherit"
                         :style {:margin-left "-20px"
                                 :margin-right "2px" :display (if @open "none" "inherit")}
                         :on-click #(swap! open not)}
        [:> Menu]]
       [:> m/Typography {:variant "h6" :style {:flexGrow 1}}
        "Die Welt ist Meine Vorstellung"]
       [:> m/Button {:color "inherit"} "Login"]]]
     [:> m/Drawer {:variant "permanent"
                   :classes {:paper (if @open "drawer-open" "drawer-close")}
                   :style {:width (if @open "240px" "55px")
                           :overflow-x "hidden"}}
      [:div {:style {:align-items "center" :min-height 64
                     :display "flex" :justifyContent "flex-end"}}
       [:> m/IconButton {:style {:height 48 :width 48}
                         :on-click #(swap! open not)}
        (if @open [:> ChevronLeft]
            [:> ChevronRight])]]
      [:> m/Divider]
      [:> m/List {:style {:width (if @open "240px" "55px")}}
       #_[:> m/ListItem {:button true :component "a" :href "#/icons"}
        [:> m/ListItemIcon [:> ico/Apps]]
        [:> m/ListItemText {:primary "Icons"}]]
       [menu-item "#/icons" ico/Apps "Icons"]
       [menu-item "#/grid" ico/BorderAll "Grid Layout"]
       [menu-item "#/about" ico/Chat "About"]
       ]]]))
