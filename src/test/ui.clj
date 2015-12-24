(ns test.ui (:import (javax.swing JFrame)
                     (java.awt FlowLayout)
                     (java.awt.event ActionListener)
                     (javax.swing JPanel)
                     (javax.swing JButton)))

(def act (proxy [ActionListener] []
  (actionPerformed [event]
    (println (.toString event))
  )
))
(defn launchDisplay [] (
     (let [frame (JFrame.)]
       (let [panel (JPanel.)]
       (.setSize frame 300 200)
       (.add (.getContentPane frame) panel)
       (let [buttonA (JButton.)]
         (.setText buttonA "buttonA")
         (.add panel buttonA)
       )
       (let [buttonB (JButton.)]
         (.setText buttonB "buttonB")
         (.add panel buttonB)
       )
       (let [buttonC (JButton.)]
         (.setText buttonC "buttonC")
         (.addActionListener buttonC act)
         (.add panel buttonC)
       )
       (.setTitle frame "Taylors first clojure app")
       (.setVisible frame true)))
  )
)

