(ns reftest.core
  (:require [reagent.core :as reagent]
            [goog.object :as gobj]))

(enable-console-print!)

(println "This text is printed from src/reftest/core.cljs. Go ahead and edit it and see reloading in action.")

(defonce app-state (reagent/atom {:text "Hello world!"}))

(defn hello-component
  []
  (let [dom-node (atom nil)]
    (reagent/create-class
     {:component-did-mount (fn [x] (js/console.log @dom-node))
      :component-will-unmount (fn [] (reset! dom-node nil))
      :reagent-render (fn [] [:h2 {:ref (fn [y] (reset! dom-node y))} "hello"])})))

(defn hello-world
  []
  [:div
   [:h1 (:text @app-state)]
   [hello-component]])

(reagent/render-component [hello-world]
                          (. js/document (getElementById "app")))

(defn on-js-reload [])
