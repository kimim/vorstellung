(ns vorstellung.common.menu
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@mui/material" :as m]))

(defn item [link icon text]
  [:> m/ListItem {:button true :component "a" :href link}
   [:> m/ListItemIcon [:> icon]]
   [:> m/Typography {:variant "inherit" :noWrap true} text]])
