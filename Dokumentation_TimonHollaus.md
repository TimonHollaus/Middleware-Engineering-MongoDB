# MidEng GK8.2 Document Oriented Middleware using MongoDB [GK/EK] - 4h



Zuerst das gihub gecloned.





befehle aus dem Kurs:

![](C:\Users\timon\AppData\Roaming\marktext\images\2025-04-29-13-59-43-image.png)

![](C:\Users\timon\AppData\Roaming\marktext\images\2025-04-29-14-00-18-image.png)

![](C:\Users\timon\AppData\Roaming\marktext\images\2025-04-29-14-01-30-image.png)

![](C:\Users\timon\AppData\Roaming\marktext\images\2025-04-29-14-01-59-image.png)







![](C:\Users\timon\AppData\Roaming\marktext\images\2025-04-29-14-17-51-image.png)

Um mit MongoDB zu arbeiten, habe ich die Konfiguration in der Datei `application.properties` ergänzt:

`spring.data.mongodb.uri=mongodb://localhost:27017/warehouse_db`

Damit lege ich fest, dass MongoDB lokal auf dem Standardport läuft. Ich verwende **MongoDB Compass**, wo ich eine neue Verbindung zur Datenbank erstelle. Wenn ich den Code mit `bootRun` starte (eine genauere Erklärung folgt noch), wird die Datenbank automatisch angelegt.

Sobald ich Daten über eine **POST-Anfrage** oder direkt über die **MongoDB Shell** einfüge, werden sie in der Datenbank sichtbar angezeigt.

<img src="file:///C:/Users/timon/AppData/Roaming/marktext/images/2025-04-29-15-12-38-image.png" title="" alt="" width="715">



**Änderungen an meinem Projekt für die Nutzung von MongoDB:**

Ich habe die Annotation `@Entity` durch `@Document` ersetzt, um meine Klassen mit MongoDB-Collections zu verknüpfen.

- Mit `@Document` mappe ich die Klasse auf eine MongoDB-Collection.

- Durch `@Getter` und `@Setter` lasse ich automatisch Getter- und Setter-Methoden für alle Felder generieren.

- Mit `@NoArgsConstructor` habe ich einen Konstruktor ohne Parameter erzeugt.

- Mit `@AllArgsConstructor` einen Konstruktor mit Parametern für alle Felder.

**Repository:**  
Ich bin vom `JpaRepository` auf `MongoRepository` umgestiegen, um die Unterstützung für MongoDB zu aktivieren:

`public interface WarehouseRepository extends MongoRepository<Warehouse, String>`

**Service-Methoden:**  
Meine Methoden arbeiten jetzt mit den CRUD-Operationen von MongoDB und deren dokumentenbasiertem Ansatz.

**Datenmodell:**  
Ich habe die Produkte direkt in die Lagerdokumente (Warehouse) eingebettet, anstatt sie getrennt zu speichern und über Fremdschlüssel zu verknüpfen.

**Für die REST-API** habe ich eine Controller-Klasse implementiert, die `POST`-, `GET`- und `DELETE`-Anfragen verarbeitet. 



### **CRUD Operations in MongoDB Shell**

**Produkt hinzufügen**

```shell
db.warehouses.update(    { _id: "warehouse1" },     
{ $push: { products: { productId: "1000", name: "Trinkflasche", 
category: "Drink", quantity: 1500, unit: "Trinken" } } })
```

**Produkt löschen**

```shell
db.warehouses.update(    { _id: "warehouse1" },    { $pull: { products: { productId: "00-123456" } } })
```

**Menge updaten**

```shell
db.warehouses.update(    { _id: "warehouse1", "products.productId": "00-123456" },    { $set: { "products.$.quantity": 2000 } })
```

**Alle produkte im warehouse getten**

```shell
db.warehouses.find({ _id: "warehouse1" }, { products: 1 })
```

**Produkte in Warehouses mit ID finden**

```shell
db.warehouses.find({  "products.productId": "4"
}, { "products.$": 1 }).pretty()
```



##### Postrequest mit postman:

Neues Warehouse hinzufügen
Body -> raw -> json

{
"id": "warehouse3",
  "products": [
    {
      "productId": "11",
      "name": "Stuhl",
      "category": "Möbel",
      "quantity": 50
    },
    {
      "productId": "12",
      "name": "Lampe",
      "category": "Beleuchtung",
      "quantity": 75
    }
  ]
}





**Vorteile von NoSQL gegenüber relationalen Datenbanksystemen (RDBMS):**

- **Skalierbarkeit**: Einfache horizontale Skalierung.

- **Flexibilität**: Kein festes Schema, unterstützt verschiedene Datenstrukturen.

- **Verfügbarkeit**: Hohe Verfügbarkeit, selbst bei Ausfällen einzelner Knoten.

- **Performance**: Schnelle Verarbeitung großer Datenmengen.

**Nachteile von NoSQL gegenüber relationalen Datenbanksystemen:**

- **Keine vollständigen ACID-Transaktionen**: Es gibt keine vollständige Transaktionssicherheit.

- **Komplexe Abfragen**: Weniger ausdrucksstark als SQL.

- **Mangel an Standardisierung**: Kein einheitliches Modell.

- **Dateninkonsistenz**: Potenzielle Probleme bei der Datenqualität.

**Herausforderungen beim Zusammenführen von Daten:**

- **Inkompatible Datenformate** und **Inkonsistenzen** zwischen verschiedenen Datenquellen.

**Arten von NoSQL-Datenbanken und Beispiele:**

- **Dokumentenorientiert**: MongoDB

- **Key-Value**: Redis

- **Spaltenorientiert**: Cassandra

- **Graphdatenbank**: Neo4j

**Abkürzungen im CAP-Theorem:**

- **C – Consistency (Konsistenz)**: Daten sind überall gleich.

- **A – Availability (Verfügbarkeit)**: Daten sind jederzeit verfügbar.

- **P – Partition Tolerance (Partitionstoleranz)**: Das System funktioniert auch bei Netzwerkausfällen.
