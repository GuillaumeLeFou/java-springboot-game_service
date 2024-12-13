# GAME-SERVICE
## ENDPOINTS
Base URL : /Participation

1. GET /Participation/{id} : Récupérer une participation par ID.

Paramètres :
- id : Identifiant unique de la participation (path variable)

2. GET /Participation : Récupérer toutes les participations
Paramètres :
/
    
3. POST /Participation : Créer une nouvelle participation.

Paramètres :
- DTO ParticipationDTO


Base URL : /Party
1. POST /Party : Créer une nouvelle partie.

Paramètres :
- DTO PartyDTO

2. GET /Party/{id} : Récupérer une partie par ID.

Paramètres :
- id : Identifiant unique de la partie.

3. DELETE /Party/{id} : Supprimer une partie par ID.

Paramètres :
- id : Identifiant unique de la partie.


## Logique métier

La logique métier du Game Service est centrée sur la gestion des parties et des participations :

Gestion des parties :

Les utilisateurs peuvent créer une nouvelle partie via le contrôleur PartyController.

Chaque partie est associée à des propriétés telles que son nom, sa description (point à atteindre/type de partie) et sa date de création.

Gestion des participations :

Une participation représente l'association entre un joueur et une partie donnée.

Le contrôleur ParticipationController permet de créer, récupérer des participations.

Interactions avec Player Service :

Le Game Service utilise un client RestTemplate pour interagir avec le Player Service et valider les joueurs participant à une partie.

Les décisions prises pour ce service permettent de maintenir une séparation claire entre la gestion des parties et des joueurs, assurant ainsi une évolutivité et une modularité du système.

