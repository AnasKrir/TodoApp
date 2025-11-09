# 📱 TodoApp – Application Android de gestion de tâches
---
TodoApp est une application mobile Android développée en Java qui permet aux utilisateurs de **gérer facilement leurs tâches personnelles**. Elle adopte l'architecture **MVVM**, utilise **Room** pour la persistance locale, et offre une expérience utilisateur fluide.

<!-- Plateforme & Langage -->
![Android](https://img.shields.io/badge/Platform-Android-brightgreen)
![Java](https://img.shields.io/badge/Language-Java-orange)
![Android%20Studio](https://img.shields.io/badge/IDE-Android%20Studio-0A7E07)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](https://opensource.org/licenses/MIT)


---

## 📑 Sommaire
1. [🚀 Fonctionnalités](#-fonctionnalités)
2. [🛠️ Technologies utilisées](#️-technologies-utilisées)
3. [🧱 Architecture MVVM](#-architecture-mvvm)
4. [📂 Structure du projet](#-structure-du-projet)
5. [✅ Lancer le projet](#-lancer-le-projet)
6. [🔒 Sécurité](#-sécurité)
7. [📌 Améliorations futures](#-améliorations-futures)
8. [📄 Licence](#-licence)

---

## 🚀 Fonctionnalités

- 🔐 Authentification : inscription et connexion d'utilisateurs
- 📝 Gestion des tâches : ajout, modification, suppression
- ✅ Marquage des tâches comme terminées
- 💾 Stockage local avec Room (SQLite)
- 🔁 Mise à jour en temps réel via LiveData
- 📱 Interface fluide et intuitive avec RecyclerView

---

## 🛠️ Technologies utilisées

| Technologie | Rôle |
|-------------|------|
| Java        | Langage de développement principal |
| Android Studio | Environnement de développement |
| Room (SQLite) | Base de données locale |
| LiveData & ViewModel | Réactivité et gestion du cycle de vie |
| MVVM | Architecture du projet |
| ConstraintLayout | UI responsive |
| RecyclerView | Liste des tâches |

---

## 🧱 Architecture MVVM

- **Model** : Entités `Task`, DAO, Room Database
- **ViewModel** : Communication entre UI et Repository
- **View** : Activités et Fragments (XML UI + logique)

----



## 📂 Structure du projet
```
TodoApp/
├── app/
│ ├── java/com/example/todoapp/
│ │ ├── model/
│ │ ├── dao/
│ │ ├── viewmodel/
│ │ └── ui/
│ └── res/layout/
```

---

## ✅ Lancer le projet

### 1. Cloner le projet son fork :
   ```
   git clone https://github.com/TON_USER/TodoApp.git
   ```
### 2. Ouvrir avec Android Studio

### 3. Lancer l'application sur un émulateur ou appareil physique

#### 🔒 Sécurité <br/>
- Mots de passe hashés localement (niveau de sécurité basique)
- Données stockées uniquement localement (aucune API distante)

#### 📌 Améliorations futures <br/>
📡 Synchronisation cloud (Firebase, REST API)<br/>
🔔 Notifications de rappel<br/>
📊 Statistiques des tâches<br/>


---

 ## 📄 Licence

Projet sous licence MIT. <br/>
✅ Libre d’utiliser, modifier, distribuer.

© 2025 — KRIR Anas & EL YOURI Adam

---



