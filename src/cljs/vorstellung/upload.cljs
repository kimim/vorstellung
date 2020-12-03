(ns vorstellung.upload
  (:require
   [reagent.core :as r]
   [re-frame.core :as rf]
   ["@material-ui/core" :as m]
   [react-dropzone-uploader :default Dropzone]))

(defn uploader []
  [:> Dropzone
   {:getUploadParams #(rf/dispatch [:upload {:filename "A" :file "B"}])
    :onChangeStatus nil
    :maxFiles 1
    :multiple false
    :canCancel false
    :inputContent "Drop a file"
    :styles (clj->js {:dropzoneActive {:borderColor "green"}})
    }])

(defn page []
  [:main {:style {:flexGrow 1 :padding "88px 24px 24px 24px"}}
   [:div
    [:> m/Grid {:container true :spacing 3}
     [:> m/Grid {:item true :xs 12 :sm 6 :lg 3}
      [:> m/Paper
       [uploader]]]]]])
