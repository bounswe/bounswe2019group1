const MongoClient = require('mongodb').MongoClient;
const URL = "mongodb://localhost:27017/test";
const Promise = require('promise');
const dbName = "test_db";


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
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                //console.log("Database created!");
                resolve(db);
                db.close();
            });
        });
}

/*

 */
function create_collection(collection_type) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL, {useNewUrlParser: true}, function (err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.createCollection(collection_type, function (err, result) {
                    if (err) reject(err);
                    //console.log("Collection created!");
                    resolve(result);
                    db.close();
                });
            });
        });
}

/*
    record_JSON --> { name: "Company Inc", address: "Highway 37" }
 */
function insertOneRecord(collection_type, record_JSON) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL, {useNewUrlParser: true}, function (err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).insertOne(record_JSON, function (err, result) {
                    if (err) reject(err);
                    //console.log("1 record inserted");
                    resolve(result);
                    db.close();
                });
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
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL, {useNewUrlParser: true}, function (err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).insertMany(array_records_JSON, function (err, result) {
                    if (err) reject(err);
                    //console.log("Number of records inserted: " + res.insertedCount);
                    resolve(result);
                    db.close();
                });
            });
        });
}

/*
    Search without Query, return only 1 record.
 */
function findOneRecord(collection_type) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).findOne({}, function(err, result) {
                    if (err) reject(err);
                    //console.log(result.name);
                    resolve(result);
                    db.close();
                });
            });
        });
}

/*
    Returns all records for this collection_type.
 */
function findManyRecords(collection_type) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).find({}).toArray(function(err, result) {
                    if (err) reject(err);
                    //console.log(result);
                    resolve(result);
                    db.close();
                });
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
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).find(query_object).toArray(function(err, result) {
                    if (err) reject(err);
                    //console.log(result);
                    resolve(result);
                    db.close();
                });
            });
        });
}

/*
    sort_field = { name: 1 };
    1 ascending, -1 descending
 */
function sortRecords(collection_type, sort_field) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).find().sort(sort_field).toArray(function(err, result) {
                    if (err) reject(err);
                    //console.log(result);
                    resolve(result);
                    db.close();
                });
            });
        });
}

/*
    query_object = { address: 'Mountain 21' };
 */
function deleteOneRecord(collection_type, query_object) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).deleteOne(query_object, function(err, result) {
                    if (err) reject(err);
                    //console.log("1 document deleted");
                    resolve(result);
                    db.close();
                });
            });
        });
}

function deleteManyRecords(collection_type, query_object) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).deleteMany(query_object, function(err, result) {
                    if (err) reject(err);
                    //console.log(obj.result.n + " document(s) deleted");
                    resolve(result);
                    db.close();
                });
            });
        });
}

function deleteCollection(collection_type) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).drop(function(err, result) {
                    if (err) reject(err);
                    //if (delOK) console.log("Collection deleted");
                    resolve(result);
                    db.close();
                });
            });
        });
}

/*
    query_object = { address: "Valley 345" };
    new_record_values = { $set: {name: "Mickey", address: "Canyon 123" } };
 */
function updateOneRecord(collection_type, query_object, new_record_values) {
    return new Promise(
        function (resolve, reject ) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).updateOne(query_object, new_record_values, function(err, result) {
                    if (err) reject(err);
                    //console.log("1 document updated");
                    resolve(result);
                    db.close();
                });
            });
        });
}

function updateManyRecords(collection_type, query_object, new_record_values) {
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(collection_type).updateMany(query_object, new_record_values, function(err, result) {
                    if (err) reject(err);
                    //console.log(res.result.nModified + " document(s) updated");
                    resolve(result);
                    db.close();
                });
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
    return new Promise(
        function (resolve, reject) {
            MongoClient.connect(URL,{ useNewUrlParser: true },function(err, db) {
                if (err) reject(err);
                const dbo = db.db(dbName);
                dbo.collection(first_collection).aggregate([
                    { $lookup:
                            {
                                from: second_collection,
                                localField: join_field_first,
                                foreignField: join_field_second,
                                as: new_field
                            }
                    }
                ]).toArray(function(err, result) {
                    if (err) reject(err);
                    //console.log(JSON.stringify(res));
                    resolve(result);
                    db.close();
                });
            });
        });
}


module.exports = {
    create_database: create_database,
    create_collection: create_collection,
    insertOneRecord: insertOneRecord,
    insertMultipleRecords: insertMultipleRecords,
    findOneRecord: findOneRecord,
    findManyRecords: findManyRecords,
    findAllRecords: findAllRecords,
    sortRecords: sortRecords,
    deleteOneRecord: deleteOneRecord,
    deleteManyRecords: deleteManyRecords,
    deleteCollection: deleteCollection,
    updateOneRecord: updateOneRecord,
    updateManyRecords: updateManyRecords,
    joinCollections: joinCollections
};

