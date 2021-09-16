(ns vorstellung.header
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]
   ;; require only submodules, reduce 7M in dev, 4M in prod
   ;; https://clojure.atlassian.net/browse/CLJS-2376 about :default
   ["@material-ui/icons/ExitToApp" :default ExitToApp]
   ["@material-ui/icons/ContactSupportOutlined" :default ContactSupportOutlined]
   ["@material-ui/icons/Menu" :default Menu]
   ["@material-ui/icons/ChevronRight" :default ChevronRight]
   ["@material-ui/icons/ChevronLeft" :default ChevronLeft]
   ["@material-ui/icons/ToysOutlined" :default ToysOutlined]
   ["@material-ui/icons/ViewComfy" :default ViewComfy]
   ["@material-ui/icons/InsertChartOutlined" :default InsertChartOutlined]
   ["@material-ui/icons/GridOn" :default GridOn]
   ["@material-ui/icons/BackupOutlined" :default BackupOutlined]
   ["@material-ui/icons/Today" :default Today]
   ["@material-ui/icons/Fullscreen" :default Fullscreen]
   ["@material-ui/icons/FullscreenExit" :default FullscreenExit]
   ["@material-ui/icons/ExpandLess" :default ExpandLess]
   ["@material-ui/icons/ExpandMore" :default ExpandMore]
   ["@material-ui/icons/Build" :default Build]
   ["@material-ui/icons/Brightness4" :default Brightness4]
   ["@material-ui/icons/Brightness5" :default Brightness5]
   [vorstellung.common.menu :as menu]
   [vorstellung.app.menu :as menu-app]
   [vorstellung.echarts.menu :as menu-echarts]))

(defn menu-list []
  [:<>
   ;;[menu-app/menu]
   [menu-echarts/menu]
   [menu/item "/#/about" ContactSupportOutlined "About"]])

(defn navbar [toolbar]
  (r/with-let [open (r/atom false)]
    (if (or (nil? @(rf/subscribe [:common/navbar-visible?]))
            @(rf/subscribe [:common/navbar-visible?]))
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
         [:> m/Toolbar {:style {:flexGrow 1}}
          [:> m/Typography {:variant "h6"}
           "Die Welt ist Meine Vorstellung"]
          [toolbar]]
         ;; button the switch dark and light theme
         (if-let [theme (rf/subscribe [:common/theme])]
           (let [new-theme (if (= "light" @theme) "night" "light")]
             [:> m/IconButton {:style {:color "white"}
                               :on-click #(rf/dispatch [:common/set-theme new-theme])}
              (if (= "light" @theme)
                [:> Brightness4]
                [:> Brightness5])]))
         ;; button to switch fullscreen
         [:> m/IconButton {:style {:color "white"}
                           :on-click #(rf/dispatch [:common/set-navbar-visible? false])}
          [:> Fullscreen]]
         [:> m/Link  {:href "/logout"}
          [:> m/IconButton {:style {:color "white"}}
           [:> ExitToApp]]]]]
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
         [menu-list]]]]
      [:div
       [:> m/Fab {:size "small"
                  :style {;; same location as maximize button
                          :position "absolute" :right "75px" :top "13px"
                          :z-index "100" ;; avoid covered by page contents
                          }
                  :on-click #(rf/dispatch [:common/set-navbar-visible? true])}
        [:> FullscreenExit]]])))

(defn page []
  (if-let [page @(rf/subscribe [:common/page])]
    [:div {:style {:display "flex"}}
     [navbar (:tool page)]
     [:main (if (or (nil? @(rf/subscribe [:common/navbar-visible?]))
                    @(rf/subscribe [:common/navbar-visible?]))
              {:style {:flex-grow 1 :padding "88px 24px 24px 24px"}}
              {:style {:flex-grow 1 :padding "0px 0px 0px 0px"}})
      [(:page page)]]]))

(defn page-no-header []
  (if-let [page @(rf/subscribe [:common/page])]
    [:div {:style {:display "flex"}}
     [(:page page)]]))
