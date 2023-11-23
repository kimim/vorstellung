(ns vorstellung.app.menu
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@mui/material" :as m]
   ;; require only submodules, reduce 7M in dev, 4M in prod
   ;; https://clojure.atlassian.net/browse/CLJS-2376 about :default
   ["@mui/icons-material/Build" :default Build]
   ["@mui/icons-material/ExpandLess" :default ExpandLess]
   ["@mui/icons-material/ExpandMore" :default ExpandMore]
   ["@mui/icons-material/ViewComfy" :default ViewComfy]
   ["@mui/icons-material/GridOn" :default GridOn]
   ["@mui/icons-material/BackupOutlined" :default BackupOutlined]
   ["@mui/icons-material/Today" :default Today]
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
        [menu/item "/app/#/table" ViewComfy       "Table"]
        [menu/item "/app/#/about" GridOn          "Menu Item 1"]
        [menu/item "/app/#/about" BackupOutlined  "Menu Item 2"]
        [menu/item "/app/#/about" Today           "Menu Item 3"]]])))
