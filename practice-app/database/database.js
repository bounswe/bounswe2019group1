var MongoClient = require('mongodb').MongoClient;
var URL = "mongodb://localhost:27017/test";
var dbName = "test_db";

/*
create_database();
create_collection("test_1");
insertOneRecord("test_1", {name:"Viggo", nationality:"Spanish", age:23});
insertOneRecord("test_1", {name:"Viggo", nationality:"Spanish", age:23});
insertOneRecord("test_1", {name:"Scarlett", nationality:"American", age:34});
insertOneRecord("test_1", {name:"Bruno", nationality:"British", age:16});
insertOneRecord("test_1", {name:"Suleyman", nationality:"Turk", age:45});
findManyRecords("test_1");
findAllRecords("test_1", {name:"Viggo"});
deleteOneRecord("test_1", {name:"Viggo"});
sortRecords("test_1", {age:-1});
updateOneRecord("test_1", {name : "Viggo"}, { $set: {name: "Viggos"} });
*/

/*
    Creates database.
 */
function create_database() {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        console.log("Database created!");
        db.close();
    });
}

/*

 */
function create_collection(collection_type) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.createCollection(collection_type, function(err, res) {
            if (err) throw err;
            console.log("Collection created!");
            db.close();
        });
    });
}

/*
    record_JSON --> { name: "Company Inc", address: "Highway 37" }
 */
function insertOneRecord(collection_type, record_JSON) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).insertOne(record_JSON, function(err, res) {
            if (err) throw err;
            console.log("1 record inserted");
            db.close();
        });
    });
}

/*
    array_records_JSON --> [
            { name: 'John', address: 'Highway 71'},
            { name: 'Peter', address: 'Lowstreet 4'},
            { name: 'Amy', address: 'Apple st 652'} ]
 */
function insertMultipleRecords(collection_type, array_records_JSON) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).insertMany(array_records_JSON, function(err, res) {
            if (err) throw err;
            console.log("Number of records inserted: " + res.insertedCount);
            db.close();
        });
    });
}

/*
    Search without Query, return only 1 record.
 */
function findOneRecord(collection_type) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).findOne({}, function(err, result) {
            if (err) throw err;
            console.log(result.name);
            db.close();
        });
    });
}

/*
    Returns all records for this collection_type.
 */
function findManyRecords(collection_type) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).find({}).toArray(function(err, result) {
            if (err) throw err;
            console.log(result);
            db.close();
        });
    });
}

/*
    Find records that has address as below
    query_object --> { address: "Park Lane 38" }

    Find records that satisfies below regular expression
    query_object --> { address: /^S/ };
 */
function findAllRecords(collection_type, query_object) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).find(query_object).toArray(function(err, result) {
            if (err) throw err;
            console.log(result);
            db.close();
        });
    });
}

/*
    sort_field = { name: 1 };
    1 ascending, -1 descending
 */
function sortRecords(collection_type, sort_field) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);

        dbo.collection(collection_type).find().sort(sort_field).toArray(function(err, result) {
            if (err) throw err;
            console.log(result);
            db.close();
        });
    });
}

/*
    query_object = { address: 'Mountain 21' };
 */
function deleteOneRecord(collection_type, query_object) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);

        dbo.collection(collection_type).deleteOne(query_object, function(err, obj) {
            if (err) throw err;
            console.log("1 document deleted");
            db.close();
        });
    });
}

function deleteManyRecords(collection_type, query_object) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).deleteMany(query_object, function(err, obj) {
            if (err) throw err;
            console.log(obj.result.n + " document(s) deleted");
            db.close();
        });
    });
}

function deleteCollection(collection_type) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).drop(function(err, delOK) {
            if (err) throw err;
            if (delOK) console.log("Collection deleted");
            db.close();
        });
    });
}

/*
    query_object = { address: "Valley 345" };
    new_record_values = { $set: {name: "Mickey", address: "Canyon 123" } };
 */
function updateOneRecord(collection_type, query_object, new_record_values) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).updateOne(query_object, new_record_values, function(err, res) {
            if (err) throw err;
            console.log("1 document updated");
            db.close();
        });
    });
}

function updateManyRecords(collection_type, query_object, new_record_values) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(collection_type).updateMany(query_object, new_record_values, function(err, res) {
            if (err) throw err;
            console.log(res.result.nModified + " document(s) updated");
            db.close();
        });
    });
}

/*
    Joins two specified collection.

    Result:
    [
        { "_id": 1, "product_id": 154, "status": 1, "orderdetails": [
            { "_id": 154, "name": "Chocolate Heaven" } ]
        }
    ]

 */
function joinCollections(first_collection, second_collection, join_field_first, join_field_second, new_field) {
    MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
        if (err) throw err;
        var dbo = db.db(dbName);
        dbo.collection(first_collection).aggregate([
            { $lookup:
                    {
                        from: second_collection,
                        localField: join_field_first,
                        foreignField: join_field_second,
                        as: new_field
                    }
            }
        ]).toArray(function(err, res) {
            if (err) throw err;
            console.log(JSON.stringify(res));
            db.close();
        });
    });
}