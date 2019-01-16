Mettez votre logique à l'épreuve :
- Prérequis :
. Java 1.8
. Intellij Idea

Compiler le programme (avec intellij):
1 - File / Projet Structure
2 - Artifacts
3 - Séléctionner le + ensuite JAR / (from modules with dependencies)
4 - Choisir la Main class (main) puis OK
5 - Appliquer puis OK
6 - Build / Build artifacts puis séléctionner "build" dans action

Lancer le programme :
1 - Ouvrir cmd (invite de commande)
2 - Copier le chemin ou se trouve le .jar (jeu logique/out/artifacts/...)
3 - ecrire dans le cmd : "cd " suivie du chemin ou se trouve le .jar
4 - entrer la commande : "java -jar le_nom_du_jar.jar" et lancer