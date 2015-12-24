(ns test.core (:import (org.jsoup Jsoup)
            (org.jsoup.nodes Document Element)
            (org.jsoup.select Elements)
            (java.io.FileReader)
            ) (:use test.ui))

(defn -main
  "I don't do a whole lot."
  [& args]
  (launchDisplay)
)
