; # описание типов комментов
; pre-release
;; pre-pre-release
;;; super-base-primitive-example

; # регэкспы
;;; (#(re-pattern %) "dsflkjhsfs")

; # достаем список файлов
;; (def files (.listFiles (File. ".")))
;;; (.toString (get files 3))
;; (pmap #(get files %) '(1 2))
; (pmap  #(.toString (get files %)) (take (count files) (iterate inc 0)))

; # получаем количетво доп аргументов 
;; (defn xz [a & args]
;;   (println (sort (flatten (conj a args))))
;;   (if (not (= args nil)) (count (vec args))))


;# получаем список вида ({путь1:пройден},{путь2:nil}, ...)
;; (defn walk-files-from-path [path]
;;   (loop
;;       [files (get-files-by-path path)
;;        res []
;;   nil)))


(ns clojure-course-task02.core
  (:gen-class)
  (:import java.io.File))

;# получаем все файлы в указанном пути
(defn get-files-by-path [path]
  (pmap #(.getName %) (seq (.listFiles (File. path)))))

;# проверка ненулевых
(defn not-nil? [val]
  (if (nil? val) false
      true))

(defn get-files-tree-recur [start-path]
  (loop [pre-res (vec (get-files-by-path start-path))
        res []]
    (if (= (filter not-nil? (flatten pre-res)) res)
      res
      (recur (conj res (flatten pre-res)) (pmap get-files-tree-recur res)))))
    

(defn find-files [file-name path]
  "TODO: Implement searching for a file using his name as a regexp."
  (let [
       files (.listFiles (File. path))
  ])
  (get-files-by-path path))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path))))))

(-main "a" ".")