(ns vorstellung.echarts.menu
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]
   ;; require only submodules, reduce 7M in dev, 4M in prod
   ;; https://clojure.atlassian.net/browse/CLJS-2376 about :default
   ["@material-ui/icons/Build" :default Build]
   ["@material-ui/icons/ExpandLess" :default ExpandLess]
   ["@material-ui/icons/ExpandMore" :default ExpandMore]
   ["@material-ui/icons/ToysOutlined" :default ToysOutlined]
   ["@material-ui/icons/ViewComfy" :default ViewComfy]
   ["@material-ui/icons/GridOn" :default GridOn]
   ["@material-ui/icons/BackupOutlined" :default BackupOutlined]
   ["@material-ui/icons/Today" :default Today]
   [vorstellung.common.menu :as menu]))

(defn menu []
  (r/with-let [collapse? (r/atom false)]
    (let [view-name (rf/subscribe [:common/page-id])
          open? (or @collapse?
                     (= "vorstellung.app.core" (namespace @view-name)))]
      [:<>
       [:> m/ListItem {:button true :selected (if open? true false)
                       :on-click #(swap! collapse? not)}
        [:> m/ListItemIcon [:> Build]]
        [:> m/Typography {:variant "inherit" :noWrap true :style {:flexGrow 1}} "Menu"]
        (if open? [:> ExpandLess] [:> ExpandMore])]
       [:> m/Collapse {:in open?}
        [menu/item "/echarts/" ViewComfy       "Echarts"]]])))
