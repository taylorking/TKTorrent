(ns tktorrent.core (:import
           (java.io FileReader File)
            (java.net InetAddress)
            (java.lang Thread)
            (javax.swing JFrame JPanel JProgressBar)
            (com.turn.ttorrent.client Client SharedTorrent)
            (com.turn.ttorrent.tracker Tracker)
            (com.turn.ttorrent.common Torrent)
    )
    (:use clojure.string)
)

(defn -main
  "program entry point"
  [& args]
  ;(let [client (Client.
    ;            (InetAddress/getLocalHost)
    ;            (.fromFile SharedTorrent
    ;              (File. args)
    ;            )
    ;)])
  ;; Verify that the user has passed a path to a torrent file
  (if-not (zero? (count args)) (do
      (def filePath (first args))
      (def path (split filePath #"/")) 
      (def directory (join "/" (butlast path)))
      (let [client (Client. (InetAddress/getLocalHost)
                            (SharedTorrent/fromFile
                              (File. filePath)
                              (File. directory)
                            )
                    )
          ]
          (.download client)
          (def torrent (.getTorrent client))
          (println "initializing torrent")
          (while (not (.isInitialized torrent)) (
            ;just hang out waiting for the torrent to start                                       
          ))
          (println "torrent successfully initialized.")

          (let [frame (JFrame.)]
            (.setSize frame 300 200)
            (let [panel (JPanel.)] 
              (.add (.getContentPane frame) panel)
              (let [progressBar (JProgressBar.)] 
                (.add panel progressBar)
                (.setMaximum progressBar (Math/pow 10 9))
                (.setMinimum progressBar 0) 
                (.setVisible frame true)
                (while (not= (.toString (.getState client)) "DONE") (do
                           (def status (.toString (.getState client)))
                           (def complete (* (.getCompletion torrent) (Math/pow 10 6)))
                           (.setValue progressBar complete)
                           (println (join [status " " (join [complete "%"])]))     
                           (Thread/sleep 1000)
                    ))          
              )
            )
          )
      )
    )
    (do
      (println "Please pass a filepath to the program")
        1
    )
  )
)
