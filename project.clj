
(defproject cljs-calendar "0.0.2"
  :description "A simple drop in calendar for clojurescript applications"
  :source-path "src"
  :url "https://github.com/peterjewicz/cljs-calendar"
  :license {:name "Eclipse Public License"
  :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
    [org.clojure/clojurescript "1.10.238"]
    [org.clojure/core.async  "0.4.474"]
    [reagent "0.7.0"]]
  :plugins [[lein-cljsbuild "1.1.7"]]
  :cljsbuild
  {
  :builds [{
    :id "dev"
    :source-paths ["src"]
    :compiler {:main cljs-calendar.core
      :npm-deps {:moment "*"}
      :install-deps true
      :output-to "public/cljs-calendar.js"
      :output-dir ".cljsbuild"
      :optimizations :none
      :source-map true
  }}
]})
